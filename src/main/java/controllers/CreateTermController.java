package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Group;

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
import java.util.List;
import java.util.Locale;

@WebServlet (name = "CreateTermController", urlPatterns = "/create-term")
public class CreateTermController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = new DBManager();
        List<Group> groups = manager.getAllActiveGroups();
        req.setAttribute("groups", groups);
        List<Discipline> disciplines = manager.getAllActiveDisciplines();
        req.setAttribute("disciplines", disciplines);
        req.getRequestDispatcher("JSP/create-term.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String groupId = req.getParameter("group");
        String numTerm = req.getParameter("terms");
        String duration = req.getParameter("duration");

        String ids = req.getParameter("selectHidden");
        String[] idsToSelect = ids.split(" ");


        DBManager manager = new DBManager();
        manager.createTerm(numTerm, duration);
        manager.setTermInGroup(groupId);

        for (String id : idsToSelect) {
            manager.setDisciplinesInTerm(id);
        }

        resp.sendRedirect("/terms");
    }
}
