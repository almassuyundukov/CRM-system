package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Group;
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

@WebServlet(name = "TermsController", urlPatterns = "/terms")
public class TermsController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = new DBManager();
        List<Term> termsForDatabase = manager.getAllActiveTerms();
        HashMap<String, List<Term>> groupsWithTerms = new HashMap<>();
        for (Term term : termsForDatabase) {
            if(groupsWithTerms.containsKey(term.getGroup())){
                groupsWithTerms.get(term.getGroup()).add(term);
            } else {
                List<Term> terms = new ArrayList<>();
                terms.add(term);
                groupsWithTerms.put(term.getGroup(), terms);
            }
        }
        for (String s : groupsWithTerms.keySet()) {
            groupsWithTerms.get(s);
        }
        Set<String> keySet = groupsWithTerms.keySet();


        req.setAttribute("termsKeySet", keySet);
        req.getRequestDispatcher("JSP/terms.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = new DBManager();
        List<Term> termsForDatabase = manager.getAllActiveTerms();
        HashMap<String, List<Term>> groupsWithTerms = new HashMap<>();
        for (Term term : termsForDatabase) {
            if(groupsWithTerms.containsKey(term.getGroup())){
                groupsWithTerms.get(term.getGroup()).add(term);
            } else {
                List<Term> terms = new ArrayList<>();
                terms.add(term);
                groupsWithTerms.put(term.getGroup(), terms);
            }
        }

        String nameGroup = req.getParameter("termHidden");

        List<Term> terms = groupsWithTerms.get(nameGroup);

        Set<String> keySet = groupsWithTerms.keySet();

        String id = req.getParameter("disciplinesHidden");
        if (id.isEmpty()){
            id = terms.get(0).getId();
        }
        List<Discipline> disciplines = manager.getDisciplinesWithTerm(id);

        String duration = manager.getDurationTerm(id);

        req.setAttribute("terms", terms);
        req.setAttribute("idTerm", id);
        req.setAttribute("nameGroup", nameGroup);
        req.setAttribute("termsKeySet", keySet);
        req.setAttribute("disciplines", disciplines);
        req.setAttribute("duration", duration);
        req.getRequestDispatcher("JSP/terms.jsp").forward(req, resp);
    }
}
