package objects;

public class Student {

    private long student_id;
    private String student_fio;
    private String sex;
    private long group_id;

    @Override
    public String toString() {
        return "Student{" +
                "student_id=" + student_id +
                ", student_fio='" + student_fio + '\'' +
                ", sex='" + sex + '\'' +
                ", group_id=" + group_id +
                '}';
    }


    public Student(long student_id, String student_fio, String sex, long group_id) {
        this.student_id = student_id;
        this.student_fio = student_fio;
        this.sex = sex;
        this.group_id = group_id;
    }

    public long getStudent_id() {
        return student_id;
    }

    public String getStudent_fio() {
        return student_fio;
    }

    public String getSex() {
        return sex;
    }

    public long getGroup_id() {
        return group_id;
    }

    public void setStudent_id(long student_id) {
        this.student_id = student_id;
    }

    public void setStudent_fio(String student_fio) {
        this.student_fio = student_fio;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }
}
