package controllers;

import database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LoginController", urlPatterns = "/login")
public class LoginController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("JSP/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String role = req.getParameter("role");

        DBManager manager = new DBManager();

        manager.getIdStudentByLogin(login,password);
        if (role.equals("1")){
            if(manager.canLogin(login, password, role)){
                req.getSession().setAttribute("role", role);
                req.getSession().setAttribute("login", login);
                req.getSession().setAttribute("isLogin", 1);
                resp.sendRedirect("/");
            } else {
                req.setAttribute("error", "1");
                req.getRequestDispatcher("JSP/login.jsp").forward(req, resp);
            }
        }
        if (role.equals("2")){
            if(manager.canLogin(login, password, role)){
                String idDisc = manager.getIdDiscByLogin(login, password);
                req.getSession().setAttribute("role", role);
                req.getSession().setAttribute("login", login);
                req.getSession().setAttribute("idDisc", idDisc);
                req.getSession().setAttribute("isLogin", 1);
                resp.sendRedirect("/");
            } else {
                req.setAttribute("error", "1");
                req.getRequestDispatcher("JSP/login.jsp").forward(req, resp);
            }
        }
        if (role.equals("3")){
            if(manager.canLogin(login, password, role)){
                String student = manager.getIdStudentByLogin(login, password);
                req.getSession().setAttribute("role", role);
                req.getSession().setAttribute("login", login);
                req.getSession().setAttribute("student", student);
                req.getSession().setAttribute("isLogin", 1);
                resp.sendRedirect("/");
            } else {
                req.setAttribute("error", "1");
                req.getRequestDispatcher("JSP/login.jsp").forward(req, resp);
            }
        }
    }
}
