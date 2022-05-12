package entity;

import java.sql.Date;

public class Timetable {
    private String id;
    private Date date;
    private String idDiscipline;
    private String discipline;
    private String idTerm;
    private String posDisc;

    public Timetable() {
    }

    public Timetable(String id, Date date, String idDiscipline, String discipline, String idTerm, String posDisc) {
        this.id = id;
        this.date = date;
        this.idDiscipline = idDiscipline;
        this.discipline = discipline;
        this.idTerm = idTerm;
        this.posDisc = posDisc;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getIdDiscipline() {
        return idDiscipline;
    }

    public void setIdDiscipline(String idDiscipline) {
        this.idDiscipline = idDiscipline;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    public String getIdTerm() {
        return idTerm;
    }

    public void setIdTerm(String idTerm) {
        this.idTerm = idTerm;
    }

    public String getPosDisc() {
        return posDisc;
    }

    public void setPosDisc(String posDisc) {
        this.posDisc = posDisc;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Timetable timetable = (Timetable) o;

        if (id != null ? !id.equals(timetable.id) : timetable.id != null) return false;
        if (date != null ? !date.equals(timetable.date) : timetable.date != null) return false;
        if (idDiscipline != null ? !idDiscipline.equals(timetable.idDiscipline) : timetable.idDiscipline != null)
            return false;
        if (discipline != null ? !discipline.equals(timetable.discipline) : timetable.discipline != null) return false;
        if (idTerm != null ? !idTerm.equals(timetable.idTerm) : timetable.idTerm != null) return false;
        return posDisc != null ? posDisc.equals(timetable.posDisc) : timetable.posDisc == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (idDiscipline != null ? idDiscipline.hashCode() : 0);
        result = 31 * result + (discipline != null ? discipline.hashCode() : 0);
        result = 31 * result + (idTerm != null ? idTerm.hashCode() : 0);
        result = 31 * result + (posDisc != null ? posDisc.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", idDiscipline='" + idDiscipline + '\'' +
                ", discipline='" + discipline + '\'' +
                ", idTerm='" + idTerm + '\'' +
                ", posDisc='" + posDisc + '\'' +
                '}';
    }
}
