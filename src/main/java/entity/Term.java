package entity;

import java.util.ArrayList;
import java.util.List;

public class Term {
    private String  id;
    private String numTerm;
    private String duration;
    private String status;
    private String group;
    private List<Discipline> disciplines = new ArrayList<>();

    public Term() {
    }

    public Term(String id, String numTerm, String duration, String status, String group, List<Discipline> disciplines) {
        this.id = id;
        this.numTerm = numTerm;
        this.duration = duration;
        this.status = status;
        this.group = group;
        this.disciplines = disciplines;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNumTerm() {
        return numTerm;
    }

    public void setNumTerm(String numTerm) {
        this.numTerm = numTerm;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public List<Discipline> getDisciplines() {
        return disciplines;
    }

    public void setDisciplines(List<Discipline> disciplines) {
        this.disciplines = disciplines;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Term term = (Term) o;

        if (id != null ? !id.equals(term.id) : term.id != null) return false;
        if (numTerm != null ? !numTerm.equals(term.numTerm) : term.numTerm != null) return false;
        if (duration != null ? !duration.equals(term.duration) : term.duration != null) return false;
        if (status != null ? !status.equals(term.status) : term.status != null) return false;
        if (group != null ? !group.equals(term.group) : term.group != null) return false;
        return disciplines != null ? disciplines.equals(term.disciplines) : term.disciplines == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numTerm != null ? numTerm.hashCode() : 0);
        result = 31 * result + (duration != null ? duration.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (group != null ? group.hashCode() : 0);
        result = 31 * result + (disciplines != null ? disciplines.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Terms{" +
                "id='" + id + '\'' +
                ", numTerm='" + numTerm + '\'' +
                ", duration='" + duration + '\'' +
                ", status='" + status + '\'' +
                ", group='" + group + '\'' +
                ", disciplines=" + disciplines +
                '}';
    }
}
