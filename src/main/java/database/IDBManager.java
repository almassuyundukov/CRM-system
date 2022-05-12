package database;

import entity.*;

import java.util.List;
import java.util.Map;

public interface IDBManager {

    List<Student> getAllActiveStudents();

    void createStudent(String surname, String name, String groupId, String date);

    void deleteStudent(String id);

    Student getStudentById(String id);

    void updateStudent(String id,String surname, String name, String groupId, String date);

    List<Discipline> getAllActiveDisciplines();

    void createDiscipline(String discipline);

    void deleteDiscipline(String idDiscipline);

    void updateDiscipline(String idDiscipline, String discipline);

    void createCalendar(String startDate, String endDate, String firstWeek);

    void setAcademicYears(String years, String startDate, String endDate);

    void createGroup(String groupName, String course);

    List<Group> getAllActiveGroups();

    void createTerm(String numTerm, String duration);

    void setTermInGroup (String idGroup);

    void setDisciplinesInTerm (String idDiscipline);

    List<Term> getAllActiveTerms();

    List<Discipline> getDisciplinesWithTerm(String idTerm);

    String getDurationTerm(String idTerm);

    Term getTermById(String id);

    List<Years> getAllRangeYears();

    List<String> getWeeksInYear(String idYear);

    List<String> getWeeksInTerm(String firstWeek, String duration, String idYear);

    List<Week> getAllDayInTerm(List<String> weekList, String idYear);

    String getIdGroupWithIdTerm(String idTerm);

    void setTimetableInTerm(String idDate, String idDiscipline, String idTerm, Integer posDisc);

    Map<String, Map<String, Map<String, Timetable>>> getTimetableByTerm(String idTerm);

    void updateTimetableInTerm(String idTimetable, String idDiscipline);

    void deleteTimetableByTerm(String idTerm);

    Map<String, String> getTimetableWithDiscByTerm(String idTerm);

    List<String> getTermsByDiscipline(String idDisc);

    List<Student> getAllStudentsByIdTerm(String idTerm);

    Map<String, List<Timetable>>  getTimetableByTermAndDisc(String idTerm, String idDisc);

    Map<String, String> getAllMarksInTerm(String idTerm);

    void setMarkInDB (String idStudent, String idTimetable, String mark);

    List<Term> getTermsByStudentId (String idStudent);

    Map<String, String> getAllMarksByStudentAndTerm(String idStudent, String idTerm);

    Discipline getDisciplineById(String discId);

    boolean canLogin(String login, String password, String role);

    boolean canCreateUser(String login);

    void createUserAdmin(String login, String password);

    void createUserTeacher(String login, String password, String idDiscipline);

    void createUserStudent(String login, String password, String idStudent);

    String getIdStudentByLogin(String login, String password);

    String getIdDiscByLogin(String login, String password);
}
