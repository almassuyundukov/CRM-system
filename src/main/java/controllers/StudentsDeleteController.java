package controllers;

import database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentsDeleteController", urlPatterns = "/student-delete")
public class StudentsDeleteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String ids = req.getParameter("deleteHidden");
        String[] idsToDelete = ids.split(" ");

        DBManager manager = new DBManager();
        for (String id : idsToDelete) {
            manager.deleteStudent(id);
        }

        resp.sendRedirect("/students");
    }
}
