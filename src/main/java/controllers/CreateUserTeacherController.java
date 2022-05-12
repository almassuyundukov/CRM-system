package controllers;

import database.DBManager;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "CreateUserTeacherController", urlPatterns = "/create-user-teacher")
public class CreateUserTeacherController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = new DBManager();
        List<Discipline> disciplines = manager.getAllActiveDisciplines();
        req.setAttribute("disciplines", disciplines);
        req.getRequestDispatcher("JSP/create-user-teacher.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String idDisc = req.getParameter("idDiscipline");
        DBManager manager = new DBManager();
        if (manager.canCreateUser(login)){
            manager.createUserTeacher(login, password, idDisc);
            resp.sendRedirect("/");
        } else {
            List<Discipline> disciplines = manager.getAllActiveDisciplines();
            req.setAttribute("disciplines", disciplines);
            req.setAttribute("error", "1");
            req.getRequestDispatcher("JSP/create-user-teacher.jsp").forward(req, resp);
        }

    }
}
