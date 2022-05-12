package controllers;

import database.DBManager;
import entity.Discipline;
import entity.Timetable;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(name = "TimetableModifyController", urlPatterns = "/timetable-modify")
public class TimetableModifyController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idTerm = req.getParameter("selectTermByModifyHidden");
        String numTerm = req.getParameter("selectNumTermByModifyHidden");
        String nameGroup = req.getParameter("selectGroupByModifyHidden");
        DBManager manager = new DBManager();
        List<Discipline> disciplines = manager.getDisciplinesWithTerm(idTerm);
        Map<String, Map<String, Map<String, Timetable>>> timetableByTerm = manager.getTimetableByTerm(idTerm);
        Set<String> weeksInTerm = timetableByTerm.keySet();
        String monday = timetableByTerm.get("1").get("Monday").get("1").getDiscipline();

        req.setAttribute("timetable", timetableByTerm);
        req.setAttribute("weeks", weeksInTerm);
        req.setAttribute("nameGroup", nameGroup);
        req.setAttribute("numTerm", numTerm);
        req.setAttribute("idTerm", idTerm);
        req.setAttribute("disciplines", disciplines);
        req.getRequestDispatcher("JSP/timetable-modify.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = new DBManager();
        String timetable = req.getParameter("timetableHidden");
        Pattern pattern = Pattern.compile("id='\\d+'");
        Matcher matcher = pattern.matcher(timetable);
        ArrayList<String> ids = new ArrayList<>();
        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            String substring = timetable.substring(start+4, end-1);
            ids.add(substring);
        }
        for (String idTimetable : ids) {
            String discId = req.getParameter(idTimetable);
            if (discId != null){
                manager.updateTimetableInTerm(idTimetable, discId);
            }
        }
        resp.sendRedirect("/timetable");
    }
}
