package entity;

import java.util.ArrayList;

public class Timetable {
    private String id;
    private ArrayList<TimetableOneDate> timetableOneDates;
    private String idTerm;
    private String idGroup;

    public Timetable() {
    }

    public Timetable(String id, ArrayList<TimetableOneDate> timetableOneDates, String idTerm, String idGroup) {
        this.id = id;
        this.timetableOneDates = timetableOneDates;
        this.idTerm = idTerm;
        this.idGroup = idGroup;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<TimetableOneDate> getTimetableOneDates() {
        return timetableOneDates;
    }

    public void setTimetableOneDates(ArrayList<TimetableOneDate> timetableOneDates) {
        this.timetableOneDates = timetableOneDates;
    }

    public String getIdTerm() {
        return idTerm;
    }

    public void setIdTerm(String idTerm) {
        this.idTerm = idTerm;
    }

    public String getIdGroup() {
        return idGroup;
    }

    public void setIdGroup(String idGroup) {
        this.idGroup = idGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Timetable timetable = (Timetable) o;

        if (id != null ? !id.equals(timetable.id) : timetable.id != null) return false;
        if (timetableOneDates != null ? !timetableOneDates.equals(timetable.timetableOneDates) : timetable.timetableOneDates != null)
            return false;
        if (idTerm != null ? !idTerm.equals(timetable.idTerm) : timetable.idTerm != null) return false;
        return idGroup != null ? idGroup.equals(timetable.idGroup) : timetable.idGroup == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (timetableOneDates != null ? timetableOneDates.hashCode() : 0);
        result = 31 * result + (idTerm != null ? idTerm.hashCode() : 0);
        result = 31 * result + (idGroup != null ? idGroup.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "id='" + id + '\'' +
                ", timetableOneDates=" + timetableOneDates +
                ", idTerm='" + idTerm + '\'' +
                ", idGroup='" + idGroup + '\'' +
                '}';
    }
}
