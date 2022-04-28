package controllers;

import database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

@WebServlet (name = "CreateCalendarController", urlPatterns = "/create-calendar")
public class CreateCalendarController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("JSP/create-calendar.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String startDate = req.getParameter("from");
        String endDate = req.getParameter("to");
        String firstWeek = req.getParameter("firstWeek");


        if(startDate.isEmpty() || endDate.isEmpty() || firstWeek.isEmpty()){
            req.setAttribute("error", "1");
            req.getRequestDispatcher("JSP/create-calendar.jsp").forward(req, resp);
            return;
        }


        // String -> Date -> String
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date start_date = null;
        Date end_date = null;
        Date first_week = null;
        try {
            start_date = format.parse(startDate);
            end_date = format.parse(endDate);
            first_week = format.parse(firstWeek);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if (!start_date.before(first_week) && !end_date.after(first_week)){
            req.setAttribute("errorWeek", "1");
            req.getRequestDispatcher("JSP/create-calendar.jsp").forward(req, resp);
            return;
        }
        SimpleDateFormat formatterWeek = new SimpleDateFormat("u");
        String numberOfWeekString = formatterWeek.format(first_week);
        if (Integer.parseInt(numberOfWeekString) != 1){
            req.setAttribute("errorWeek", "2");
            req.getRequestDispatcher("JSP/create-calendar.jsp").forward(req, resp);
            return;
        }


        Format formatter = new SimpleDateFormat("yyyy-MM-dd");
        String dateStartToDatabase = formatter.format(start_date);
        String dateEndToDatabase = formatter.format(end_date);
        String firstWeekToDatabase = formatter.format(first_week);

        Format formatForYears = new SimpleDateFormat("yyyy");
        String yearsForDatabase = formatForYears.format(start_date) + "-" + formatForYears.format(end_date);

        DBManager manager = new DBManager();
        manager.createCalendar(dateStartToDatabase, dateEndToDatabase, firstWeekToDatabase);
        manager.setAcademicYears(yearsForDatabase, dateStartToDatabase, dateEndToDatabase);

        resp.sendRedirect("/create-calendar");
    }
}
