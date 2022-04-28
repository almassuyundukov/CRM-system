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

@WebServlet (name = "CreateGroupController", urlPatterns = "/create-group")
public class CreateGroupController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("JSP/create-group.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nameGroup = req.getParameter("groupName");
        String course = req.getParameter("course");


        if(nameGroup.isEmpty() || course.isEmpty()){
            req.setAttribute("error", "1");
            req.getRequestDispatcher("JSP/create-group.jsp").forward(req, resp);
            return;
        }

        DBManager manager = new DBManager();
        manager.createGroup(nameGroup, course);

        resp.sendRedirect("/students");
    }
}
