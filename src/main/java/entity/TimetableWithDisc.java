package entity;

public class TimetableWithDisc {
    private String id;
    private String discipline;

    public TimetableWithDisc() {
    }

    public TimetableWithDisc(String id, String discipline) {
        this.id = id;
        this.discipline = discipline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDiscipline() {
        return discipline;
    }

    public void setDiscipline(String discipline) {
        this.discipline = discipline;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TimetableWithDisc that = (TimetableWithDisc) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        return discipline != null ? discipline.equals(that.discipline) : that.discipline == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (discipline != null ? discipline.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "TimetableWithDisc{" +
                "id='" + id + '\'' +
                ", discipline='" + discipline + '\'' +
                '}';
    }
}
