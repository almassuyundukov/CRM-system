package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Student;
import entity.Term;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

@WebServlet (name = "TermModifyController", urlPatterns = "/term-modify")
public class TermModifyController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTerm = req.getParameter("termModifyHidden");
        DBManager manager = new DBManager();
        Term term = manager.getTermById(idTerm);
        term.setDisciplines(manager.getDisciplinesWithTerm(idTerm));
        List<Discipline> allActiveDisciplines = manager.getAllActiveDisciplines();

        req.setAttribute("term", term);
        req.setAttribute("allActiveDisciplines", allActiveDisciplines);
        req.getRequestDispatcher("JSP/term-modify.jsp").forward(req, resp);
    }
}
