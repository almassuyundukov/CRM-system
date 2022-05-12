package controllers;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SelectRoleForCreateUserController", urlPatterns = "/select-role")
public class SelectRoleForCreateUserController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("JSP/select-role-create-user.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String role = req.getParameter("role");
        if(role.equals("1")){
            resp.sendRedirect("/create-user-admin");
        }
        if(role.equals("2")){
            resp.sendRedirect("/create-user-teacher");
        }
        if(role.equals("3")){
            resp.sendRedirect("/create-user-student");
        }
    }
}
