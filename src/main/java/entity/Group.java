package entity;

public class Group {
    private int id;
    private String name_group;
    private int course;
    private int status;

    public Group() {
    }

    public Group(int id, String name_group, int course, int status) {
        this.id = id;
        this.name_group = name_group;
        this.course = course;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_group() {
        return name_group;
    }

    public void setName_group(String name_group) {
        this.name_group = name_group;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (id != group.id) return false;
        if (course != group.course) return false;
        if (status != group.status) return false;
        return name_group != null ? name_group.equals(group.name_group) : group.name_group == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name_group != null ? name_group.hashCode() : 0);
        result = 31 * result + course;
        result = 31 * result + status;
        return result;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name_group='" + name_group + '\'' +
                ", course=" + course +
                ", status=" + status +
                '}';
    }
}
