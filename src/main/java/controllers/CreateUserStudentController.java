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

@WebServlet(name = "CreateUserStudentController", urlPatterns = "/create-user-student")
public class CreateUserStudentController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = new DBManager();
        List<Student> students = manager.getAllActiveStudents();
        req.setAttribute("students", students);
        req.getRequestDispatcher("JSP/create-user-student.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String idStudent = req.getParameter("studentIdHidden");
        DBManager manager = new DBManager();
        if (manager.canCreateUser(login)){
            manager.createUserStudent(login, password, idStudent);
            resp.sendRedirect("/");
        } else {
            List<Student> students = manager.getAllActiveStudents();
            req.setAttribute("students", students);
            req.setAttribute("error", "1");
            req.getRequestDispatcher("JSP/create-user-student.jsp").forward(req, resp);
        }
    }
}
