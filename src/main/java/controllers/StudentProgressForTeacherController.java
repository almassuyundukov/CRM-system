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
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet (name = "StudentProgressForTeacherController", urlPatterns = "/progress-for-teacher")
public class StudentProgressForTeacherController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = new DBManager();
        List<Discipline> disciplines = manager.getAllActiveDisciplines();
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

        String idDiscipline = req.getParameter("disciplineHidden");

        if ((idDiscipline == null) || idDiscipline.isEmpty()){
            idDiscipline = String.valueOf(disciplines.get(0).getId());
        }

        List<String> idTerms = manager.getTermsByDiscipline(idDiscipline);
        List<String> nameGroups = new ArrayList<>();
        for (Term term : termsForDatabase) {
            if (idTerms.contains(term.getId())){
                String group = term.getGroup();
                nameGroups.add(group);
            }
        }


        String nameGroup = req.getParameter("groupHidden");
        if (nameGroup == null || nameGroup.isEmpty()){
            if (nameGroups.isEmpty()){
                nameGroup = " ";
            } else {
                nameGroup = nameGroups.get(0);
            }
        }


        List<Term> terms = new ArrayList<>();

        if (nameGroups.isEmpty()){
            terms.clear();
        } else {
            terms = groupsWithTerms.get(nameGroup);
        }



        String idTerm = req.getParameter("termHidden");
        if ((idTerm == null) || idTerm.isEmpty()){
            if (terms.isEmpty()){
                idTerm = " ";
            } else {
                idTerm = terms.get(0).getId();
            }
        }

        String accessButton = req.getParameter("downloadTimetableHidden");
        if(accessButton == null){
            accessButton = "0";
        }
        if (accessButton.equals("1")){
            List<Student> students = manager.getAllStudentsByIdTerm(idTerm);
            Map<String, List<Timetable>> timetableByTermAndDisc = manager.getTimetableByTermAndDisc(idTerm, idDiscipline);
            Set<String> weeks = timetableByTermAndDisc.keySet();
            Map<String, String> marksInTerm = manager.getAllMarksInTerm(idTerm);
            Set<String> idStWithIdTimetable = marksInTerm.keySet();
            req.setAttribute("students", students);
            req.setAttribute("timetable", timetableByTermAndDisc);
            req.setAttribute("idStWithIdTimetable", idStWithIdTimetable);
            req.setAttribute("marksInTerm", marksInTerm);
            req.setAttribute("weeks", weeks);
        }

        req.setAttribute("disciplines", disciplines);
        req.setAttribute("idDiscipline", idDiscipline);
        req.setAttribute("terms", terms);
        req.setAttribute("idTerm", idTerm);
        req.setAttribute("nameGroup", nameGroup);
        req.setAttribute("nameGroups", nameGroups);

        req.getRequestDispatcher("JSP/student-progress-for-teacher.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DBManager manager = new DBManager();
        String students = req.getParameter("studentsHidden");
        String timetable = req.getParameter("timetable");
        Pattern pattern = Pattern.compile("id=\\d+");
        Pattern patternForTimetable = Pattern.compile("id='\\d+'");
        Matcher matcher = pattern.matcher(students);
        ArrayList<String> idStudents = new ArrayList<>();
        ArrayList<String> idTimetables = new ArrayList<>();
        while (matcher.find()){
            int start = matcher.start();
            int end = matcher.end();
            String substring = students.substring(start+3, end);
            idStudents.add(substring);
        }
        Matcher matcherForTimetable = patternForTimetable.matcher(timetable);
        while (matcherForTimetable.find()){
            int start = matcherForTimetable.start();
            int end = matcherForTimetable.end();
            String substring = timetable.substring(start+4, end-1);
            idTimetables.add(substring);
        }

        for (String idStudent : idStudents) {
            for (String idTimetable : idTimetables) {
                String mark = req.getParameter(idStudent+idTimetable);
                manager.setMarkInDB(idStudent, idTimetable, mark);
            }
        }
        resp.sendRedirect("/");
    }
}
