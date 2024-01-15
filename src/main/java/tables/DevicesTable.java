package tables;

import db.MySQLConnector;
import objects.Device;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

public class DevicesTable extends AbsTable {
    private final static String TABLE_NAME = "devices";

    public DevicesTable() {
        super(TABLE_NAME);
        columns = new HashMap<>();
        columns.put("subscriber_id", "bigint");
        columns.put("device_name", "varchar(100)");
        columns.put("device_os", "varchar(100)");
        columns.put("active", "varchar(100)");
        create();
    }

    public ArrayList<Device> selectAll(){
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на выборку
        final String sqlRequest = String.format("SELECT * FROM %s", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        return resultSetToArray(rs);
    }

    public ArrayList<Device> selectAllActive(){
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на выборку
        final String sqlRequest = String.format("SELECT * FROM %s WHERE active = 'Y'", tableName);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        return resultSetToArray(rs);
    }

    public ArrayList<Device> selectAllByDeviceOS(String os){
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на выборку
        final String sqlRequest = String.format("SELECT * FROM %s WHERE device_os = '%s'", tableName, os);
        ResultSet rs = db.executeRequestWithAnswer(sqlRequest);
        return resultSetToArray(rs);
    }

    public void insert(Device device){
        //Подключиться к БД
        db = new MySQLConnector();
        //Сделать запрос на добавление
        final String sqlRequest = String.format("insert into %s (subscriber_id, device_name, device_os, active) VALUES (%d, '%s', '%s', '%s');",
                tableName, device.getSubscriber_id(), device.getDeviceName(), device.getDeviceOS(), device.isActive());
        db.executeRequest(sqlRequest);
        db.close();
    }

    private ArrayList<Device> resultSetToArray(ResultSet rs){
        ArrayList<Device> result = new ArrayList<>();
        //Обработать ответ по строчно
        try {
            // Перебор строк с данными
            while (rs.next()) {
                //Создать объект устройство и добавление его в результирующий массив
                result.add(
                        new Device(
                                rs.getLong("subscriber_id"),
                                rs.getString("device_name"),
                                rs.getString("device_os"),
                                rs.getString("active")));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            db.close();
        }
        return result;
    }
}
