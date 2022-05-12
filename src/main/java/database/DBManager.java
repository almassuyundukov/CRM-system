package database;

import constants.Constants;
import entity.*;
import entity.Calendar;

import java.sql.*;
import java.util.*;

public class DBManager implements IDBManager {

    @Override
    public List<Student> getAllActiveStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `student`.`id`, `student`.`surname`, `student`.`name`, `group`.`name_group`,`group`.`course`, `student`.`date` , `student`.`status`\n" +
                    "FROM `student` INNER JOIN `group` ON `student`.`group_id`=`group`.`id`" +
                    "where student.status = 1;");
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setSurname(rs.getString("surname"));
                student.setName(rs.getString("name"));
                student.setGroup(rs.getString("name_group") + "-" + rs.getString("course"));
                student.setDate(rs.getDate("date"));
                student.setStatus(1);
                students.add(student);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public void createStudent(String surname, String name, String groupId, String date) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `student` (`surname`, `name`, `group_id`, `date`) VALUES ('" + surname + "', '" + name + "', '" + groupId + "', '" + date + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteStudent(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `student` SET `status` = '0' WHERE (`id` = '" + id + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Student getStudentById(String id) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT s.id, s.surname, s.name, g.name_group, g.course, g.id as group_id, s.date FROM student as s\n" +
                    "left join `group` as g on s.group_id = g.id\n" +
                    "where s.status = 1 and s.id = " + id);
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setGroup(rs.getString("name_group") + "-" + rs.getString("course"));
                student.setGroupId(rs.getString("group_id"));
                student.setDate(rs.getDate("date"));
                student.setStatus(1);
                return student;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void updateStudent(String id, String surname, String name, String groupId, String date) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `student` SET `surname` = '" + surname + "', `name` = '" + name + "', " +
                    "`group_id` = '" + groupId + "', `date` = '" + date + "' WHERE (`id` = '" + id + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Discipline> getAllActiveDisciplines() {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from discipline where status = 1;");
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                discipline.setStatus(1);
                disciplines.add(discipline);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    @Override
    public void createDiscipline(String discipline) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `discipline` (`discipline`) VALUES ('" + discipline + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteDiscipline(String idDiscipline) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `discipline` SET `status` = '0' WHERE (`id` = '" + idDiscipline + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateDiscipline(String idDiscipline, String discipline) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `discipline` SET `discipline` = '" + discipline + "',/n" +
                    " `status` = '0' WHERE (`id` = '" + idDiscipline + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createCalendar(String startDate, String endDate, String firstWeek) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            Statement statement = conn.createStatement();
            stmt.execute("call create_year('" + startDate + "', '" + endDate + "');");
            ResultSet rs = stmt.executeQuery("SELECT * FROM calendar WHERE (date BETWEEN '" + firstWeek + "' AND '" + endDate + "')");
            int numberWeek = 0;
            Integer id = 0;
            String idStr = null;
            String numberWeekStr = null;
            do {
                numberWeek = numberWeek + 1;
                for (int i = 0; i < 7; i++) {
                    if (rs.next()) {
                        id = rs.getInt("id");
                        idStr = Integer.toString(id);
                        numberWeekStr = Integer.toString(numberWeek);
                        statement.execute("UPDATE `calendar` SET `number_week` = '" + numberWeekStr + "' WHERE (`id` = '" + idStr + "');");
                    }
                }
                rs.previous();
            } while (rs.next());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setAcademicYears(String years, String startDate, String endDate) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `years` (`years`, `year_start`, `year_end`) VALUES ('" + years + "', '" + startDate + "', '" + endDate + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createGroup(String groupName, String course) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `group` (`name_group`, `course`) VALUES ('" + groupName + "', '" + course + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Group> getAllActiveGroups() {
        ArrayList<Group> groups = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `group` where status = 1;");
            while (rs.next()) {
                Group group = new Group();
                group.setId(rs.getInt("id"));
                group.setName_group(rs.getString("name_group"));
                group.setCourse(rs.getInt("course"));
                group.setStatus(1);
                groups.add(group);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return groups;
    }

    @Override
    public void createTerm(String numTerm, String duration) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `term` (`num_term`, `duration`) VALUES ('" + numTerm + "', '" + duration + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setTermInGroup(String idGroup) {
        try {
            String idTerm = null;
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT max(id) FROM `term`;");
            while (rs.next()) {
                idTerm = rs.getString("max(id)");
            }
            stmt.execute("INSERT INTO `term_group` (`id_term`, `id_group`) VALUES ('" + idTerm + "', '" + idGroup + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setDisciplinesInTerm(String idDiscipline) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT max(id) FROM `term`;");
            rs.next();
            String idTerm = rs.getString("max(id)");
            stmt.execute("INSERT INTO `term_discipline` (`id_term`, `id_discipline`) VALUES ('" + idTerm + "', '" + idDiscipline + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Term> getAllActiveTerms() {
        ArrayList<Term> terms = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT *\n" +
                    "FROM `term` JOIN `term_group` ON `term`.`id`=`term_group`.`id_term`\n" +
                    "INNER JOIN `group` ON `term_group`.`id_group`=`group`.`id`\n" +
                    "where `term`.`status` = 1;");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getString("id"));
                term.setNumTerm(rs.getString("num_term"));
                term.setDuration(rs.getString("duration"));
                term.setGroup(rs.getString("name_group") + rs.getString("course"));
                term.setStatus("1");
                terms.add(term);
            }

            for (Term term : terms) {
                List<Discipline> disciplines = new ArrayList<>();
                Statement statement = conn.createStatement();
                ResultSet resultSet = statement.executeQuery("SELECT * FROM `term_discipline`\n" +
                        "inner join `discipline` ON `term_discipline`.`id_discipline`=`discipline`.`id`\n" +
                        "where id_term = " + term.getId() + ";");
                while (resultSet.next()) {
                    Discipline discipline = new Discipline();
                    discipline.setId(resultSet.getInt("id_discipline"));
                    discipline.setDiscipline(resultSet.getString("discipline"));
                    disciplines.add(discipline);
                }
                term.setDisciplines(disciplines);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terms;
    }

    @Override
    public List<Discipline> getDisciplinesWithTerm(String idTerm) {
        ArrayList<Discipline> disciplines = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select `d`.`id`, `d`.`discipline` from `term_discipline` as `td`\n" +
                    "left join `discipline` as `d` on `td`.`id_discipline` = `d`.`id`\n" +
                    "where `td`.`id_term` = " + idTerm + " and `d`.`status` = 1;");
            while (rs.next()) {
                Discipline discipline = new Discipline();
                discipline.setId(rs.getInt("id"));
                discipline.setDiscipline(rs.getString("discipline"));
                discipline.setStatus(1);
                disciplines.add(discipline);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return disciplines;
    }

    @Override
    public String getDurationTerm(String idTerm) {
        String duration = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `term`\n" +
                    "where id = " + idTerm + ";");
            while (rs.next()) {
                duration = rs.getString("duration");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return duration;
    }

    @Override
    public Term getTermById(String id) {
        Term term = new Term();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select `t`.`id`, `t`.`num_term`, `t`.`duration`, `g`.`name_group`, `g`.`course` from `term` as `t`\n" +
                    "left join `term_group` as `tg` on `t`.`id` = `tg`.`id_term`\n" +
                    "left join `group` as `g` on `tg`.`id_group` = `g`.`id`\n" +
                    "where `t`.`id` = " + id + " and `t`.`status` = 1;");
            while (rs.next()) {
                term.setId(rs.getString("id"));
                term.setNumTerm(rs.getString("num_term"));
                term.setDuration(rs.getString("duration"));
                term.setGroup(rs.getString("name_group") + "-" + rs.getString("course"));
                term.setStatus("1");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return term;
    }

    @Override
    public List<Years> getAllRangeYears() {
        ArrayList<Years> years = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `years`;");
            while (rs.next()) {
                Years year = new Years();
                year.setId(rs.getString("id"));
                year.setYears(rs.getString("years"));
                year.setStartDate(rs.getDate("year_start"));
                year.setEndDate(rs.getDate("year_end"));
                years.add(year);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return years;
    }

    @Override
    public List<String> getWeeksInYear(String idYear) {
        ArrayList<String> weeks = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `years`\n" +
                    "where id = " + idYear + ";");
            rs.next();
            String dateStart = rs.getString("year_start");
            String dateEnd = rs.getString("year_end");

            ResultSet resultSet = stmt.executeQuery("SELECT distinct `number_week` FROM `calendar`\n" +
                    "where `date` between '" + dateStart + "' and '" + dateEnd + "';");
            while (resultSet.next()) {
                if (resultSet.getInt("number_week") != 0) {
                    String week = resultSet.getString("number_week");
                    weeks.add(week);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weeks;
    }

    @Override
    public List<String> getWeeksInTerm(String firstWeek, String duration, String idYear) {
        ArrayList<String> weeksInTerm = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `years`\n" +
                    "where id = " + idYear + ";");
            rs.next();
            String dateStart = rs.getString("year_start");
            String dateEnd = rs.getString("year_end");

            ResultSet resultSet = stmt.executeQuery("SELECT distinct `number_week` FROM `calendar` \n" +
                    "where (`date` between '" + dateStart + "' and '" + dateEnd + "') \n" +
                    "and (`number_week` between '" + firstWeek + "' and '80') limit " + duration + ";");
            while (resultSet.next()) {
                if (resultSet.getInt("number_week") != 0) {
                    String week = resultSet.getString("number_week");
                    weeksInTerm.add(week);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weeksInTerm;
    }

    @Override
    public List<Week> getAllDayInTerm(List<String> weekList, String idYear) {
        List<Week> weeks = new ArrayList<>();
        String weekStart = weekList.get(0);
        String weekEnd = weekList.get(weekList.size() - 1);
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
            ResultSet rs = stmt.executeQuery("SELECT * FROM `years`\n" +
                    "where id = " + idYear + ";");
            rs.next();
            String dateStart = rs.getString("year_start");
            String dateEnd = rs.getString("year_end");
            ResultSet resultSet = stmt.executeQuery("SELECT *, dayofweek(`date`)-1 as n FROM `calendar`\n" +
                    "where (`date` between '" + dateStart + "' and '" + dateEnd + "')\n" +
                    "and (`number_week` between '" + weekStart + "' and '" + weekEnd + "');");
            while (resultSet.next()) {
                Week week = new Week();
                List<Calendar> daysInOneWeek = new ArrayList<>();
                resultSet.previous();
                for (int i = 0; i < 7; i++) {
                    if (resultSet.next()) {
                        Calendar calendar = new Calendar();
                        calendar.setId(resultSet.getString("id"));
                        calendar.setDate(resultSet.getDate("date"));
                        calendar.setStatus(resultSet.getString("status"));
//                        daysInOneWeek.set(resultSet.getInt("n"), calendar);

                        switch (resultSet.getInt("n")) {
                            case 1:
                                daysInOneWeek.add(calendar);
                                break;
                            case 2:
                                daysInOneWeek.add(calendar);
                                break;
                            case 3:
                                daysInOneWeek.add(calendar);
                                break;
                            case 4:
                                daysInOneWeek.add(calendar);
                                break;
                            case 5:
                                daysInOneWeek.add(calendar);
                                break;
                            case 6:
                                daysInOneWeek.add(calendar);
                                break;
                            case 0:
                                daysInOneWeek.add(calendar);
                                break;
                            default:
                                break;
                        }
//                        daysInOneWeek.add(resultSet.getInt("n"), calendar);
                    }
                }
                week.setNumberWeek(resultSet.getString("number_week"));
                week.setDaysOfWeek(daysInOneWeek);
                weeks.add(week);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weeks;
    }

    @Override
    public String getIdGroupWithIdTerm(String idTerm) {
        String idGroup = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `term_group`\n" +
                    "where `id_term` = " + idTerm + ";");
            while (rs.next()) {
                idGroup = rs.getString("id_group");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idGroup;
    }

    @Override
    public void setTimetableInTerm(String idDate, String idDiscipline, String idTerm, Integer posDisc) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `timetable` (`id_date`, `id_discipline`, `id_term`, `position_disc`) VALUES ('" + idDate + "', '" + idDiscipline + "', '" + idTerm + "', '" + posDisc + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, Map<String, Map<String, Timetable>>> getTimetableByTerm(String idTerm) {
        Map<String, Map<String, Map<String, Timetable>>> weekWithDays = new LinkedHashMap<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT timetable.id, number_week,\n" +
                    "dayname(`date`) as dn, discipline.id as disc_id, discipline, position_disc FROM timetable\n" +
                    "left join calendar on timetable.id_date = calendar.id\n" +
                    "left join discipline on timetable.id_discipline = discipline.id\n" +
                    "where id_term = " + idTerm + " and timetable.status = 1;");
            while (rs.next()) {
                if (!weekWithDays.containsKey(rs.getString("number_week"))) {
                    Map<String, Timetable> discInDay = new LinkedHashMap<>();
                    Map<String, Map<String, Timetable>> daysInWeek = new LinkedHashMap<>();
                    Timetable timetable = new Timetable();
                    timetable.setDiscipline(rs.getString("discipline"));
                    timetable.setId(rs.getString("id"));
                    timetable.setIdDiscipline(rs.getString("disc_id"));
                    discInDay.put(rs.getString("position_disc"), timetable);
                    daysInWeek.put(rs.getString("dn"), discInDay);
                    weekWithDays.put(rs.getString("number_week"), daysInWeek);
                } else if (!weekWithDays.get(rs.getString("number_week"))
                        .containsKey(rs.getString("dn"))) {
                    Map<String, Timetable> discInDay = new LinkedHashMap<>();
                    Timetable timetable = new Timetable();
                    timetable.setDiscipline(rs.getString("discipline"));
                    timetable.setId(rs.getString("id"));
                    timetable.setIdDiscipline(rs.getString("disc_id"));
                    discInDay.put(rs.getString("position_disc"), timetable);
                    weekWithDays.get(rs.getString("number_week"))
                            .put(rs.getString("dn"), discInDay);
                } else if (!weekWithDays.get(rs.getString("number_week"))
                        .get(rs.getString("dn")).containsKey(rs.getString("position_disc"))) {
                    Timetable timetable = new Timetable();
                    timetable.setDiscipline(rs.getString("discipline"));
                    timetable.setId(rs.getString("id"));
                    timetable.setIdDiscipline(rs.getString("disc_id"));
                    weekWithDays.get(rs.getString("number_week"))
                            .get(rs.getString("dn"))
                            .put(rs.getString("position_disc"), timetable);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weekWithDays;
    }

    @Override
    public void updateTimetableInTerm(String idTimetable, String idDiscipline) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("UPDATE `timetable` SET `id_discipline` = '" + idDiscipline + "' WHERE (`id` = '" + idTimetable + "');");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTimetableByTerm(String idTerm) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            Statement statement = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM timetable\n" +
                    "where id_term = " + idTerm + " and status = 1;");
            while (rs.next()) {
                String idTimetable = rs.getString("id");
                statement.execute("UPDATE `timetable` SET `status` = '0' WHERE (`id` = '" + idTimetable + "');");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Map<String, String> getTimetableWithDiscByTerm(String idTerm) {
        Map<String, String> timetableWithInTerm = new LinkedHashMap<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM timetable\n" +
                    "where id_term = " + idTerm + " and status = 1;");
            while (rs.next()) {
                timetableWithInTerm.put(rs.getString("id"), rs.getString("id_discipline"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return timetableWithInTerm;
    }

    @Override
    public List<String> getTermsByDiscipline(String idDisc) {
        List<String> idTerms = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT distinct id_term FROM timetable\n" +
                    "where id_discipline = " + idDisc + " and status = 1;");
            while (rs.next()) {
                idTerms.add(rs.getString("id_term"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idTerms;
    }

    @Override
    public List<Student> getAllStudentsByIdTerm(String idTerm) {
        List<Student> students = new ArrayList<>();
        String idGroup = "";
        if (idTerm == " ") {
            return students;
        }
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT distinct g.id FROM `timetable` as t\n" +
                    "left join `term_group` as tg on tg.id_term = t.id_term\n" +
                    "left join `group` as g on tg.id_group = g.id\n" +
                    "where t.id_term = " + idTerm + " and t.status = 1;");
            while (rs.next()) {
                idGroup = rs.getString("id");
            }
            if (idGroup == "") {
                return students;
            }
            ResultSet resultSet = stmt.executeQuery("SELECT * FROM student\n" +
                    "where group_id = " + idGroup + " and status = 1;");
            while (resultSet.next()) {
                Student student = new Student();
                student.setId(resultSet.getInt("id"));
                student.setSurname(resultSet.getString("surname"));
                student.setName(resultSet.getString("name"));
                student.setDate(resultSet.getDate("date"));
                student.setGroup(resultSet.getString("group_id"));
                student.setStatus(1);
                students.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public Map<String, List<Timetable>> getTimetableByTermAndDisc(String idTerm, String idDisc) {
        Map<String, List<Timetable>> weekWithDays = new LinkedHashMap<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT timetable.id, number_week,\n" +
                    "dayname(`date`) as dn, `date`, discipline, position_disc FROM timetable\n" +
                    "left join calendar on timetable.id_date = calendar.id\n" +
                    "left join discipline on timetable.id_discipline = discipline.id\n" +
                    "where id_term = " + idTerm + " and timetable.status = 1 and timetable.id_discipline = " + idDisc + ";");
            while (rs.next()) {
                if (!weekWithDays.containsKey(rs.getString("number_week"))) {
                    List<Timetable> timetables = new ArrayList<>();
                    Timetable timetable = new Timetable();
                    timetable.setId(rs.getString("id"));
                    timetable.setDate(rs.getDate("date"));
                    timetable.setPosDisc(rs.getString("position_disc"));
                    timetables.add(timetable);
                    weekWithDays.put(rs.getString("number_week"), timetables);
                } else {
                    Timetable timetable = new Timetable();
                    timetable.setId(rs.getString("id"));
                    timetable.setDate(rs.getDate("date"));
                    timetable.setPosDisc(rs.getString("position_disc"));
                    weekWithDays.get(rs.getString("number_week"))
                            .add(timetable);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return weekWithDays;
    }

    @Override
    public Map<String, String> getAllMarksInTerm(String idTerm) {
        Map<String, String> marksInTerm = new LinkedHashMap<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT student_id, id_timetable, id_term, mark FROM student_performance\n" +
                    "left join timetable on student_performance.id_timetable = timetable.id\n" +
                    "where id_term = " + idTerm + ";");
            while (rs.next()) {
                marksInTerm.put(rs.getString("student_id") + rs.getString("id_timetable"), rs.getString("mark"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marksInTerm;
    }

    @Override
    public void setMarkInDB(String idStudent, String idTimetable, String mark) {
        String idPerformance = " ";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM student_performance\n" +
                    "where student_id = " + idStudent + " and id_timetable = " + idTimetable + ";");
            while (rs.next()) {
                idPerformance = rs.getString("id");
            }
            if (idPerformance.equals(" ")) {
                stmt.execute("INSERT INTO `student_performance` (`student_id`, `id_timetable`, `mark`)\n" +
                        " VALUES ('" + idStudent + "', '" + idTimetable + "', '" + mark + "');");
            } else {
                stmt.execute("UPDATE `student_performance` SET `mark` = '" + mark + "' WHERE (`id` = '" + idPerformance + "');");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Term> getTermsByStudentId(String idStudent) {
        List<Term> terms = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT t.id, t.num_term, t.duration FROM student as s\n" +
                    "left join term_group as tg on s.group_id = tg.id_group \n" +
                    "left join term as t on tg.id_term = t.id\n" +
                    "where s.id = " + idStudent + " and s.status = 1 and t.status = 1;");
            while (rs.next()) {
                Term term = new Term();
                term.setId(rs.getString("id"));
                term.setNumTerm(rs.getString("num_term"));
                term.setDuration(rs.getString("duration"));
                term.setStatus("1");
                terms.add(term);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return terms;
    }

    @Override
    public Map<String, String> getAllMarksByStudentAndTerm(String idStudent, String idTerm) {
        Map<String, String> marksByStudentAndTerm = new LinkedHashMap<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT student_id, id_timetable, id_term, mark FROM student_performance\n" +
                    "left join timetable on student_performance.id_timetable = timetable.id\n" +
                    "where id_term = " + idTerm + " and student_id = " + idStudent + ";");
            while (rs.next()) {
                marksByStudentAndTerm.put(rs.getString("id_timetable"), rs.getString("mark"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return marksByStudentAndTerm;
    }

    @Override
    public Discipline getDisciplineById(String discId) {
        Discipline discipline = new Discipline();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM discipline\n" +
                    "where id = " + discId + ";");
            while (rs.next()) {
                discipline.setDiscipline(rs.getString("discipline"));
                discipline.setId(rs.getInt("id"));
                discipline.setStatus(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return discipline;
    }

    @Override
    public boolean canLogin(String login, String password, String role) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM user_role as ur\n" +
                    "left join user as u on ur.id_user = u.id\n" +
                    "where u.login = '" + login + "' and u.password = '" + password + "' and ur.id_role = " + role + ";");
            while (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean canCreateUser(String login) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `user`\n" +
                    "where login = '"+login+"'");
            while (rs.next()) {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }

    @Override
    public void createUserAdmin(String login, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `user` (`login`, `password`) VALUES ('" + login + "', '" + password + "');");
            ResultSet rs = stmt.executeQuery("SELECT * FROM `user`\n" +
                    "order by id desc\n" +
                    "limit 1;");
            while (rs.next()) {
                String idUser = rs.getString("id");
                Statement statement = conn.createStatement();
                statement.execute("INSERT INTO `user_role` (`id_user`, `id_role`) VALUES ('" + idUser + "', '1');");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUserTeacher(String login, String password, String idDiscipline) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `user` (`login`, `password`) VALUES ('" + login + "', '" + password + "');");
            ResultSet rs = stmt.executeQuery("SELECT * FROM `user`\n" +
                    "order by id desc\n" +
                    "limit 1;");
            while (rs.next()) {
                String idUser = rs.getString("id");
                Statement statement = conn.createStatement();
                statement.execute("INSERT INTO `user_role` (`id_user`, `id_role`) VALUES ('" + idUser + "', '2');");
                statement.execute("INSERT INTO `teacher_discipline` (`user_id`, `disc_id`) VALUES ('"+idUser+"', '"+idDiscipline+"');");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void createUserStudent(String login, String password, String idStudent) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            stmt.execute("INSERT INTO `user` (`login`, `password`) VALUES ('" + login + "', '" + password + "');");
            ResultSet rs = stmt.executeQuery("SELECT * FROM `user`\n" +
                    "order by id desc\n" +
                    "limit 1;");
            while (rs.next()) {
                String idUser = rs.getString("id");
                Statement statement = conn.createStatement();
                statement.execute("INSERT INTO `user_role` (`id_user`, `id_role`) VALUES ('" + idUser + "', '3');");
                statement.execute("INSERT INTO `user_students` (`id_user`, `id_student`) VALUES ('"+idUser+"', '"+idStudent+"');");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getIdStudentByLogin(String login, String password) {
        String idStudent = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `user` as u\n" +
                    "left join user_students as us on u.id = us.id_user\n" +
                    "where login = '"+login+"' and password = '"+password+"';");
            while (rs.next()) {
                idStudent = rs.getString("id_student");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idStudent;
    }

    @Override
    public String getIdDiscByLogin(String login, String password) {
        String idDisc = "";
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM `user` as u\n" +
                    "left join teacher_discipline as td on u.id = td.user_id\n" +
                    "where login = '"+login+"' and password = '"+password+"';");
            while (rs.next()) {
                idDisc = rs.getString("disc_id");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return idDisc;
    }


}
