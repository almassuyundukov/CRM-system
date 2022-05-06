package controllers;

import database.DBManager;
import entity.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@WebServlet (name = "TimetableController", urlPatterns = "/timetable")
public class TimetableController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = new DBManager();
        List<Years> years = manager.getAllRangeYears();
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
        if (nameGroup == null || nameGroup.isEmpty()){
            nameGroup = manager.getAllActiveGroups().get(0).getName_group()+
                    manager.getAllActiveGroups().get(0).getCourse();
        }

        List<Term> terms = groupsWithTerms.get(nameGroup);

        Set<String> groupNameList = groupsWithTerms.keySet();

        String idTerm = req.getParameter("selectTermHidden");
        if ((idTerm == null) || idTerm.isEmpty()){
            idTerm = terms.get(0).getId();
        }

        req.setAttribute("years", years);
        req.setAttribute("terms", terms);
        req.setAttribute("idTerm", idTerm);
        req.setAttribute("nameGroup", nameGroup);
        req.setAttribute("termsKeySet", groupNameList);

        req.getRequestDispatcher("JSP/timetable.jsp").forward(req, resp);
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
        if (nameGroup == null || nameGroup.isEmpty()){
            nameGroup = manager.getAllActiveGroups().get(0).getName_group()+
                    manager.getAllActiveGroups().get(0).getCourse();
        }

        List<Term> terms = groupsWithTerms.get(nameGroup);

        Set<String> keySet = groupsWithTerms.keySet();

        String idTerm = req.getParameter("selectTermHidden");
        if ((idTerm == null) || idTerm.isEmpty()){
            idTerm = terms.get(0).getId();
        }

        Map<String, Map<String, Map<String, TimetableWithDisc>>> timetableByTerm = manager.getTimetableByTerm(idTerm);
        Set<String> weeksInTerm = timetableByTerm.keySet();

        req.setAttribute("weeks", weeksInTerm);
        req.setAttribute("timetable", timetableByTerm);
        req.setAttribute("terms", terms);
        req.setAttribute("idTerm", idTerm);
        req.setAttribute("nameGroup", nameGroup);
        req.setAttribute("termsKeySet", keySet);
        req.getRequestDispatcher("JSP/timetable.jsp").forward(req, resp);
    }
}
