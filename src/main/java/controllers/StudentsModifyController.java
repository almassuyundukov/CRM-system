package controllers;

import database.DBManager;
import entity.Student;

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

@WebServlet(name = "StudentsModifyController", urlPatterns = "/student-modify")
public class StudentsModifyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("modifyHidden");
        DBManager dbManager = new DBManager();
        Student student = dbManager.getStudentById(id);
        req.setAttribute("student", student);
        req.getRequestDispatcher("JSP/student-modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String surname = req.getParameter("surname");
        String name = req.getParameter("name");
        String groupId = req.getParameter("group");
        String dateFromUser = req.getParameter("date");


        if(surname.isEmpty() || name.isEmpty() || groupId.isEmpty() || dateFromUser.isEmpty()){
            req.setAttribute("error", "1");
            req.getRequestDispatcher("JSP/student-create.jsp").forward(req, resp);
            return;
        }

        // String -> Date -> String
        DateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
        Date date = null;
        try {
            date = format.parse(dateFromUser);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Format formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateToDatabase = formatter.format(date);

        DBManager manager = new DBManager();
        manager.createStudent(surname, name, groupId, dateToDatabase);

        resp.sendRedirect("/students");
    }
}
