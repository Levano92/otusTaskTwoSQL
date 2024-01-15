package tables;
import db.MySQLConnector;
import objects.Device;
import objects.Student;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
public class StudentsTable extends AbsTable{
    private final static String TABLE_NAME = "Students";

    public StudentsTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("student_id", "bigint");
        columns.put("student_fio", "varchar(100)");
        columns.put("sex", "varchar(100)");
        columns.put("group_id",  "bigint");
        create();
    }

    public ArrayList<Student> selectAll(){
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на выборку
        final String sqlRequest = String.format("SELECT * FROM %s", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        return resultSetToArray(rs);
    }


    public ArrayList<Student> selectBySex(String Sex){
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на выборку
        final String sqlRequest = String.format("SELECT * FROM %s WHERE sex = '%s'", tableName , Sex);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        return resultSetToArray(rs);
    }

    public void  insert(Student student){
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на добавление
        final String sqlRequest = String.format("insert into %s (student_id, student_fio, sex, group_id) VALUES (%d, '%s', '%s', '%d');",
                tableName, student.getStudent_id(), student.getStudent_fio(), student.getSex(), student.getGroup_id());
        db.executeRequest(sqlRequest);
        db.close();
    }




    public  void  getFullInformation(){
        db = new MySQLConnector();
        final String sqlRequest = "select students.student_id  , students.student_fio , students.sex , studygroup.group_name , curator.curator_fio\n" +
                "from students\n" +
                "inner join studygroup on students.group_id = studygroup.group_id \n" +
                "inner join curator on studygroup.curator_id  = curator.curator_id  ;";
        db.executeRequest(sqlRequest);
        db.close();
    }

    private int totalStudents;
    public ArrayList<Student> countStd(){
        db = new MySQLConnector();
        final String sqlRequest = "SELECT COUNT(*) AS total_students FROM students";
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        db.close();
        return resultSetToArray(rs);
    }

    public ArrayList<Student> studentList = countStd();

    public int getTotalStudents() {
        return totalStudents;
    }


    private ArrayList<Student> resultSetToArray(ResultSet rs){
        ArrayList<Student> result = new ArrayList<>();
        //Обработать ответ по строчно
        try {
            // Перебор строк с данными
            while (rs.next()) {
                //Создать объект устройство и добавление его в результирующий массив
                result.add(
                        new Student(
                                rs.getLong("student_id"),
                                rs.getString("student_fio"),
                                rs.getString("sex"),
                                rs.getLong("group_id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return result;
    }
}

