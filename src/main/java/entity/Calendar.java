package entity;

import java.sql.Date;

public class Calendar {
    private String id;
    private Date date;
    private String status;

    public Calendar() {
    }

    public Calendar(String id, Date date, String status) {
        this.id = id;
        this.date = date;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Calendar calendar = (Calendar) o;

        if (id != null ? !id.equals(calendar.id) : calendar.id != null) return false;
        if (date != null ? !date.equals(calendar.date) : calendar.date != null) return false;
        return status != null ? status.equals(calendar.status) : calendar.status == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (date != null ? date.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Calendar{" +
                "id='" + id + '\'' +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
