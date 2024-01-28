package tables;
import db.MySQLConnector;
import objects.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class StudentsTable extends AbsTable{
    private final static String TABLE_NAME = "students";

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




    public ArrayList<String> getFullInformation() {
        db = new MySQLConnector();
        final String sqlRequest =
                "SELECT s.student_id as 'номер', s.student_fio as 'фио', s.sex as 'пол', sg.group_name as 'группа', c.curator_fio as 'фио куратора'" +
                        " FROM students s" +
                        " INNER JOIN studygroup sg ON sg.group_id = s.group_id" +
                        " INNER JOIN curator c ON sg.curator_id = c.curator_id;";
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        ArrayList<String> resultArray = new ArrayList<>();
        try {
            while (rs.next()) {
                String id = rs.getString("номер");
                String fio = rs.getString("фио");
                String sex = rs.getString("пол");
                String group = rs.getString("группа");
                String curator = rs.getString("фио куратора");

                String result = "id = " + id + ", фио = " + fio + ", пол = " + sex + ", группа = " + group + ", фио куратора = " + curator + "\n" ;
                resultArray.add(result);
                System.out.println(result);  // Вывод элемента в консоль
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }
        return resultArray;
    }


    private int totalStudents;

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

    public ArrayList<String> countStd() {
        db = new MySQLConnector();
        final String sqlRequest = "SELECT COUNT(*) AS total_students FROM students";
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        ArrayList<String> resultArray = new ArrayList<>();

        try {
            if (rs.next()) {
                int totalStudents = rs.getInt("total_students");
                String resultString = "total_students = " + totalStudents;
                resultArray.add(resultString);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.close();
        }

        return resultArray;
    }

}

