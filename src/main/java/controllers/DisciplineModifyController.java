package controllers;

import database.DBManager;
import entity.Discipline;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "DisciplineModifyController", urlPatterns = "/discipline-modify")
public class DisciplineModifyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idDiscipline = req.getParameter("discModifyHidden");
        DBManager manager = new DBManager();
        Discipline discipline = manager.getDisciplineById(idDiscipline);
        req.setAttribute("discipline", discipline);
        req.getRequestDispatcher("JSP/discipline-modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idDisc = req.getParameter("idDisc");
        String disciplineName = req.getParameter("discipline");
        DBManager manager = new DBManager();
        manager.updateDiscipline(idDisc, disciplineName);
        resp.sendRedirect("/disciplines");
    }
}
