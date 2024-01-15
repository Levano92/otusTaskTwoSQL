package objects;

public class TotalSum {

    private long total_count_students;

    public String toString() {
        return "Student{" +
                "total_count_students=" + total_count_students;
    }


    public TotalSum(long total_count_students) {
        this.total_count_students = total_count_students;

    }

    public long getTotal_count_students() {
        return total_count_students;
    }

    public void setTotal_count_students(long total_count_students) {
        this.total_count_students = total_count_students;
    }
}

