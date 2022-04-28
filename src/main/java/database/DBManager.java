package database;

import constants.Constants;
import entity.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DBManager implements IDBManager {

    @Override
    public List<Student> getAllActiveStudents() {
        ArrayList<Student> students = new ArrayList<>();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection(Constants.DB_URL_CONNECTION);
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT `student`.`id`, `student`.`surname`, `student`.`name`, `group`.`name_group`,`group`.`course`, `student`.`date` , `student`.`status`\n" +
                    "FROM `student` INNER JOIN `group` ON `student`.`group_id`=`group`.`id`;");
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
            stmt.execute("INSERT INTO `students_27`.`student` (`surname`, `name`, `group_id`, `date`) VALUES ('" + surname + "', '" + name + "', '" + groupId + "', '" + date + "');");
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
            ResultSet rs = stmt.executeQuery("select * from student where status = 1 and id = " + id);
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getInt("id"));
                student.setName(rs.getString("name"));
                student.setSurname(rs.getString("surname"));
                student.setGroup(rs.getString("group"));
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


}
