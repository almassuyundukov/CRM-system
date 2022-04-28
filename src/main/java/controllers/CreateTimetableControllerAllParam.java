package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Term;
import entity.Week;
import entity.Years;

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

@WebServlet (name = "CreateTimetableControllerAllParam", urlPatterns = "/create-time-all")
public class CreateTimetableControllerAllParam extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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

        Set<String> keySet = groupsWithTerms.keySet();

        String idTerm = req.getParameter("disciplinesHidden");
        if ((idTerm == null) || idTerm.isEmpty()){
            idTerm = terms.get(0).getId();
        }
        List<Discipline> disciplines = manager.getDisciplinesWithTerm(idTerm);

        String duration = manager.getDurationTerm(idTerm);

        String idYears = req.getParameter("yearsHidden");
        if (idYears.isEmpty() || idYears == null) {
            idYears = "1";
        }

        List<String> weeks = manager.getWeeksInYear(idYears);

        String firstWeek = req.getParameter("firstWeekHidden");
        String durationInTerm = req.getParameter("durationHidden");
        List<String> weeksInTerm = manager.getWeeksInTerm(firstWeek,durationInTerm, idYears);

        // Получение диапозона дат для расписания
        List<Week> weeksInTermWithDays = manager.getAllDayInTerm(weeksInTerm, idYears);

        req.setAttribute("years", years);
        req.setAttribute("weeks", weeks);
        req.setAttribute("idYears", idYears);
        req.setAttribute("terms", terms);
        req.setAttribute("idTerm", idTerm);
        req.setAttribute("nameGroup", nameGroup);
        req.setAttribute("termsKeySet", keySet);
        req.setAttribute("disciplines", disciplines);
        req.setAttribute("duration", duration);

        req.setAttribute("firstWeek", firstWeek);
        req.setAttribute("weeksInTerm", weeksInTerm);
        req.setAttribute("weeksInTermWithDays", weeksInTermWithDays);
        req.getRequestDispatcher("JSP/create-timetable.jsp").forward(req, resp);
    }
}
