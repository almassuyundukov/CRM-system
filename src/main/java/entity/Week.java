package entity;

import java.util.List;

public class Week {
    private String numberWeek;
    private List<Calendar> daysOfWeek;

    public Week() {
    }

    public Week(String numberWeek, List<Calendar> daysOfWeek) {
        this.numberWeek = numberWeek;
        this.daysOfWeek = daysOfWeek;
    }

    public String getNumberWeek() {
        return numberWeek;
    }

    public void setNumberWeek(String numberWeek) {
        this.numberWeek = numberWeek;
    }

    public List<Calendar> getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(List<Calendar> daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Week week = (Week) o;

        if (numberWeek != null ? !numberWeek.equals(week.numberWeek) : week.numberWeek != null) return false;
        return daysOfWeek != null ? daysOfWeek.equals(week.daysOfWeek) : week.daysOfWeek == null;
    }

    @Override
    public int hashCode() {
        int result = numberWeek != null ? numberWeek.hashCode() : 0;
        result = 31 * result + (daysOfWeek != null ? daysOfWeek.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Week{" +
                "numberWeek='" + numberWeek + '\'' +
                ", daysOfWeek=" + daysOfWeek +
                '}';
    }
}
