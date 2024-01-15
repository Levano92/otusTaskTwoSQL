import objects.Curator;
import objects.Student;
import objects.StudyGroup;
import settings.RandomNumberGenerator;
import tables.StudentsTable;
import tables.CuratorTable;
import tables.StudyGroupTable;
import tables.DevicesTable;
import objects.Device;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        DevicesTable table = new DevicesTable();
        //table.writeAll();


        StudentsTable table_1 = new StudentsTable();
        table_1.writeAll();
        System.out.println("----------------------------------------------------------");
        CuratorTable table_2 = new CuratorTable();
        table_2.writeAll();
        System.out.println("----------------------------------------------------------");
        StudyGroupTable table_3 = new StudyGroupTable();
        table_3.writeAll();

        String[] namesMale = {
                "Иванов Алексей Сергеевич",
                "Петров Владимир Александрович",
                "Сидоров Николай Иванович",
                "Михайлов Дмитрий Валерьевич",
                "Федоров Игорь Анатольевич",
                "Егоров Андрей Викторович",
                "Павлов Константин Геннадьевич",
                "Семенов Вячеслав Михайлович",
                "Кузнецов Юрий Борисович",
                "Александров Павел Николаевич",

        };
        String[] namesFemale = {
                "Васильева Екатерина Александровна",
                "Захарова Анна Максимовна",
                "Николаева Ольга Дмитриевна",
                "Соколова Юлия Романовна",
                "Тихонова Мария Владимировна"
        };


        int idM = 1;
        for (String name : namesMale)
        {
            Student student = new Student(idM, name, "М", RandomNumberGenerator.generateRandomNumber(3) );
            table_1.insert(student);
            idM += 1;
        }
        int idF = 11;
        for (String name : namesFemale)
        {
            Student student = new Student(idF, name, "Ж",RandomNumberGenerator.generateRandomNumber(3));
            table_1.insert(student);
            idF += 1;
        }

        //table_1.writeAll();

        String[] curatorName = {
                "Иванов Иван Иванович",
                "Петров Петр Васильевич",
                "Сидорова Ольга Александровна",
                "Козлов Андрей Константинович",
        };
        int idC = 1;
        for (String name : curatorName)
        {
            Curator curator = new Curator(idC , name);
            table_2.insert(curator);
            idC += 1;
        }
        //table_2.writeAll();

        String[] groupName = {
                "JAVA",
                "JavaScript",
                "Python"
        };
        int idG = 1;
        idC = 1;
        for (String name : groupName)
        {
            StudyGroup group = new StudyGroup(idG, name , idC);
            table_3.insert(group);
            idG += 1;
            idC += 1;
        }
        //table_3.writeAll();


        System.out.println("----------------------------------------------------------");

        ArrayList<Device> devices = table.selectAll();
        for (Device tmp: devices) {
            System.out.println(tmp.toString());
        }
        System.out.println("----------------------------------------------------------");
        devices = table.selectAllActive();
        for (Device tmp: devices) {
            System.out.println(tmp.toString());
        }
        System.out.println("----------------------------------------------------------");
        devices = table.selectAllByDeviceOS("iOS");
        for (Device tmp: devices) {
            System.out.println(tmp.toString());
        }
        System.out.println("----------------------------------------------------------");
        Device device = new Device(3, "IPhone 17", "iOS", "Y");
        table.insert(device);
        table.writeAll();

        System.out.println("----------------------------------------------------------");
        ArrayList<Student> students = table_1.selectBySex("Ж");
        for (Student tmp: students) {
            System.out.println(tmp.toString());
        }

        System.out.println("Смотри тут");
        StudentsTable studentsTable = new StudentsTable();
        studentsTable.countStd();

    }
}
