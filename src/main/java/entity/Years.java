package entity;

import java.sql.Date;

public class Years {

    private String id;
    private String years;
    private Date startDate;
    private Date endDate;

    public Years() {
    }

    public Years(String id, String years, Date startDate, Date endDate) {
        this.id = id;
        this.years = years;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Years years1 = (Years) o;

        if (id != null ? !id.equals(years1.id) : years1.id != null) return false;
        if (years != null ? !years.equals(years1.years) : years1.years != null) return false;
        if (startDate != null ? !startDate.equals(years1.startDate) : years1.startDate != null) return false;
        return endDate != null ? endDate.equals(years1.endDate) : years1.endDate == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (years != null ? years.hashCode() : 0);
        result = 31 * result + (startDate != null ? startDate.hashCode() : 0);
        result = 31 * result + (endDate != null ? endDate.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Years{" +
                "id='" + id + '\'' +
                ", years='" + years + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
