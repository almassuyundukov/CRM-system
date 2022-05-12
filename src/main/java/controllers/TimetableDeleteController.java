package controllers;

import database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "TimetableDeleteController", urlPatterns = "/timetable-delete")
public class TimetableDeleteController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTerm = req.getParameter("termForDeleteHidden");
        DBManager manager = new DBManager();
        manager.deleteTimetableByTerm(idTerm);
        resp.sendRedirect("/timetable");
    }
}
