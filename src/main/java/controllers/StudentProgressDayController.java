package controllers;

import database.DBManager;
import entity.Student;
import entity.Term;
import entity.Timetable;
import entity.TimetableWithDisc;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;

@WebServlet(name = "StudentProgressDayController", urlPatterns = "/student-progress-to-day")
public class StudentProgressDayController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("progressToDayHidden");
        DBManager manager = new DBManager();
        Student student = manager.getStudentById(id);
        List<Term> terms = manager.getTermsByStudentId(id);

        String idTerm = req.getParameter("termHidden");
        if ((idTerm == null) || idTerm.isEmpty()){
            if(terms.isEmpty()){
                idTerm = " ";
            } else {
                idTerm = terms.get(0).getId();
            }
        }

        String accessButton = req.getParameter("downloadTimetableForStudentHidden");
        if(accessButton == null){
            accessButton = "0";
        }
        if (accessButton.equals("1")){
            Map<String, Map<String, Map<String, TimetableWithDisc>>> timetableByTerm = manager.getTimetableByTerm(idTerm);

            Set<String> weeks = timetableByTerm.keySet();

            Map<String, String> allMarksByStudentAndTerm = manager.getAllMarksByStudentAndTerm(id, idTerm);
            req.setAttribute("weeks", weeks);
            req.setAttribute("timetable", timetableByTerm);
            req.setAttribute("marks", allMarksByStudentAndTerm);
        }

        req.setAttribute("student", student);
        req.setAttribute("terms", terms);
        req.setAttribute("idTerm", idTerm);

        req.getRequestDispatcher("JSP/student-progress-day.jsp").forward(req, resp);
    }
}
