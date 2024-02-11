import objects.Curator;
import objects.Student;
import objects.StudyGroup;
import settings.RandomNumberGenerator;
import tables.StudentsTable;
import tables.CuratorTable;
import tables.StudyGroupTable;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
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
        table_3.writeAll();

        System.out.println("----------------------------------------------------------");
        ArrayList<Student> students = table_1.selectBySex("Ж");
        for (Student tmp: students) {
            System.out.println(tmp.toString());
        }


        StudentsTable studentsTable = new StudentsTable();
        ArrayList<String> studentscount = studentsTable.countStd();

       System.out.println("Количество студентов");
       System.out.println("----------------------------------------------------------");
       System.out.println(studentscount);

       ArrayList<String> studentsinfo = studentsTable.getFullInformation();
       System.out.println("----------------------------------------------------------");
       System.out.println("Полная инфа!!!");
       System.out.println(studentsinfo);

       System.out.println("----------------------------------------------------------");
       System.out.println("Смена куратора у группы");
       StudyGroupTable group = new StudyGroupTable();
       group.update();

       System.out.println("----------------------------------------------------------");
        System.out.println("вывод инфы по группам");
       StudyGroupTable studyGroupTable = new StudyGroupTable();
       ArrayList<String> curAndGR = studyGroupTable.getCuratorAndGroup();
       System.out.println(curAndGR);


    }


}
