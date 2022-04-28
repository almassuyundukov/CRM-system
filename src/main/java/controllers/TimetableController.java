package controllers;

import database.DBManager;
import entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet (name = "TimetableController", urlPatterns = "/timetable")
public class TimetableController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        DBManager manager = new DBManager();
//        List<Student> students = manager.getAllActiveStudents();
//        req.setAttribute("timetable", timetables);
        req.getRequestDispatcher("JSP/timetable.jsp").forward(req, resp);
    }
}
