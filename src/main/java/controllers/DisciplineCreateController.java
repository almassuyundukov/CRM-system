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

@WebServlet(name = "DisciplineCreateController", urlPatterns = "/discipline-create")
public class DisciplineCreateController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("JSP/discipline-creating.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String discipline = req.getParameter("discipline");

        if(discipline.isEmpty()){
            req.setAttribute("error", "1");
            req.getRequestDispatcher("JSP/discipline-creating.jsp").forward(req, resp);
            return;
        }

        DBManager manager = new DBManager();
        manager.createDiscipline(discipline);

        resp.sendRedirect("/disciplines");
    }
}
