package tables;
import db.MySQLConnector;
import objects.Curator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
public class CuratorTable extends AbsTable {
    private final static String TABLE_NAME = "Curator";

    public CuratorTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("curator_id", "bigint");
        columns.put("curator_fio", "varchar(100)");
        create();
    }

    public ArrayList<Curator> selectAll(){
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на выборку
        final String sqlRequest = String.format("SELECT * FROM %s", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        return resultSetToArray(rs);
    }

    public void insert(Curator curator){
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на добавление
        final String sqlRequest = String.format("insert into %s (curator_id, curator_fio) VALUES (%d, '%s');",
                tableName, curator.getCurator_id(), curator.getCurator_fio());
        db.executeRequest(sqlRequest);
        db.close();
    }


    private ArrayList<Curator> resultSetToArray(ResultSet rs){
        ArrayList<Curator> result = new ArrayList<>();
        //Обработать ответ по строчно
        try {
            // Перебор строк с данными
            while (rs.next()) {
                //Создать объект устройство и добавление его в результирующий массив
                result.add(
                        new Curator(
                                rs.getLong("group_id"),
                                rs.getString("curator_id")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return result;
    }
}
