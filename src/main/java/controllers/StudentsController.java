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

@WebServlet(name = "StudentsController", urlPatterns = "/students")
public class StudentsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = (String) req.getSession().getAttribute("role");
        if(role.equals("3")){
            resp.sendRedirect("/student-progress-to-day");
        } else {
            DBManager manager = new DBManager();
            List<Student> students = manager.getAllActiveStudents();
            req.setAttribute("students", students);
            req.getRequestDispatcher("JSP/students.jsp").forward(req, resp);
        }
    }
}
