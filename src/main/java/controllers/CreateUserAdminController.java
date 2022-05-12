package controllers;

import database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateUserAdminController", urlPatterns = "/create-user-admin")
public class CreateUserAdminController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("JSP/create-user-admin.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        DBManager manager = new DBManager();
        if (manager.canCreateUser(login)){
            manager.createUserAdmin(login, password);
            resp.sendRedirect("/");
        } else {
            req.setAttribute("error", "1");
            req.getRequestDispatcher("JSP/create-user-admin.jsp").forward(req, resp);
        }
    }
}
