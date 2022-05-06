package controllers;

import database.DBManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet (name = "CreateTimetableControllerAllParam", urlPatterns = "/create-time-all")
public class CreateTimetableControllerAllParam extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = new DBManager();
        String idTerm = req.getParameter("idTermHidden");
        String idGroup = manager.getIdGroupWithIdTerm(idTerm);
        String[] daysHidden = req.getParameterValues("daysHidden");
        Pattern pattern = Pattern.compile("id='\\d+'");
        Matcher matcher = pattern.matcher(daysHidden[0]);
        ArrayList<String> ids = new ArrayList<>();
        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            String substring = daysHidden[0].substring(start+4, end-1);
            ids.add(substring);
        }
        for (String idDays : ids) {
            for (Integer i = 1; i <= 8; i++){
                String idDiscipline = req.getParameter(idDays+i);
                manager.setTimetableInTerm(idDays, idDiscipline, idTerm, i);
            }
        }
        resp.sendRedirect("/timetable");
    }
}
