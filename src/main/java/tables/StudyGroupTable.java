package tables;
import db.MySQLConnector;
import objects.StudyGroup;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
public class StudyGroupTable extends AbsTable{
    private final static String TABLE_NAME = "StudyGroup";

    public StudyGroupTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("group_id", "varchar(100)");
        columns.put("group_name", "varchar(100)");
        columns.put("curator_id", "varchar(100)");
        create();
    }

    public ArrayList<StudyGroup> selectAll(){
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на выборку
        final String sqlRequest = String.format("SELECT * FROM %s", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        return resultSetToArray(rs);
    }

    public void insert(StudyGroup group){
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на добавление
        final String sqlRequest = String.format("insert into %s (group_id, group_name, curator_id) VALUES (%s, '%s', '%s');",
                tableName, group.getGroup_id(), group.getGroup_name(), group.getCurator_id());
        db.executeRequest(sqlRequest);
        db.close();
    }

    public void updateCurator(){
        db = new MySQLConnector();
            final String sqlRequest = "update studygroup\n" +
                    "set curator_id = 4\n" +
                    "where  curator_id = 1; ";
        db.executeRequest(sqlRequest);
    }

    private ArrayList<StudyGroup> resultSetToArray(ResultSet rs){
        ArrayList<StudyGroup> result = new ArrayList<>();
        //Обработать ответ по строчно
        try {
            // Перебор строк с данными
            while (rs.next()) {
                //Создать объект устройство и добавление его в результирующий массив
                result.add(
                        new StudyGroup(
                                rs.getLong("group_id"),
                                rs.getString("group_name"),
                                rs.getLong("curator_id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return result;
    }
}
