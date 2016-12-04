package com.redgrem;

import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class ManagementSystem {

    private List<Group> groups;
    private Collection<Student> students;

    //instant for singlton
    private static ManagementSystem instance;

    //singlton constructor
    private ManagementSystem() {
        loadGroups();
        loadStudents();
    }

    private void loadGroups() {
        if (groups == null) {
            groups = new ArrayList<>();
        } else {
            groups.clear();
        }
        Group g = null;

        g = new Group();
        g.setGroupId(1);
        g.setNameGroup("Первая");
        g.setCurator("Доктор Борменталь");
        g.setSpeciality("Создание собачек из человеков");
        groups.add(g);

        g = new Group();
        g.setGroupId(2);
        g.setNameGroup("Вторая");
        g.setCurator("Профессор Преображенский");
        g.setSpeciality("Создание человеков из собачек");
        groups.add(g);

    }

    public void loadStudents() {
        if (students == null) {
            students = new TreeSet<>();
        } else {
            students.clear();
        }

        Student s = null;
        Calendar c = Calendar.getInstance();

        // Вторая группа
        s = new Student();
        s.setStudentId(1);
        s.setFirstName("Иван");
        s.setPatronymic("Сергеевич");
        s.setSurName("Степанов");
        s.setSex('М');
        c.set(1990, 3, 20);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2006);
        students.add(s);

        s = new Student();
        s.setStudentId(2);
        s.setFirstName("Наталья");
        s.setPatronymic("Андреевна");
        s.setSurName("Чичикова");
        s.setSex('Ж');
        c.set(1990, 6, 10);
        s.setDateOfBirth(c.getTime());
        s.setGroupId(2);
        s.setEducationYear(2006);
        students.add(s);

        // Первая группа
        s = new Student();
        s.setStudentId(3);
        s.setFirstName("Петр");
        s.setPatronymic("Викторович");
        s.setSurName("Сушкин");
        s.setSex('М');
        c.set(1991, 3, 12);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1);
        students.add(s);

        s = new Student();
        s.setStudentId(4);
        s.setFirstName("Вероника");
        s.setPatronymic("Сергеевна");
        s.setSurName("Ковалева");
        s.setSex('Ж');
        c.set(1991, 7, 19);
        s.setDateOfBirth(c.getTime());
        s.setEducationYear(2006);
        s.setGroupId(1);
        students.add(s);

    }

    public List<Group> getGroups() {
        return groups;
    }

    // Получить список всех студентов
    public Collection<Student> getAllStudents() {
        return students;
    }

    // Получить список студентов для определенной группы
    public Collection<Student> getStudentsFromGroup(Group group, int year) {
        Collection<Student> studentsTmp = new TreeSet<>();
        for (Student student : students) {
            if (student.getGroupId() == group.getGroupId() && student.getEducationYear() == year) {
                studentsTmp.add(student);
            }

        }
        return studentsTmp;
    }

    // Удалить студента
    public void deleteStudent(Student student) {
        // Надо найти нужного студента (по его ИД) и удалить
        Student delStudent = null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                // Вот этот студент - запоминаем его и прекращаем цикл
                delStudent = si;
                break;
            }
        }
        students.remove(delStudent);
    }

    // Перевести студентов из одной группы с одним годом обучения в другую
    public void moveStudentsToGroup(Group oldGroup, int oldYear, Group newGroup, int newYear) {
        for (Student s : students) {
            if (s.getGroupId() == oldGroup.getGroupId() && s.getEducationYear() == oldYear) {
                s.setGroupId(newGroup.getGroupId());
                s.setEducationYear(newYear);
            }
        }
    }

    public void removeStudentsFromGroup(Group group, int year) {
        Collection<Student> si = new TreeSet<>();
        for (Student student : students) {
            if (student.getGroupId() != group.getGroupId() || student.getEducationYear() != year) {
                si.add(student);
            }
        }
        students = si;
    }

    public static void printString() {
        System.out.println();
    }

    // метод getInstance - проверяет, инициализирована ли статическая
    // переменная (в случае надобности делает это) и возвращает ее
    public static synchronized ManagementSystem getInstance() {
        if (instance == null) {
            instance = new ManagementSystem();
        }
        return instance;
    }


    public static void printString(Object s) {
        //System.out.println(s.toString());
        try {
            System.out.println(new String(s.toString().getBytes("windows-1251"), "windows-1252"));
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
        }
    }

    // Добавить студента
    public void insertStudent(Student student) {
        // Просто добавляем объект в коллекцию
        students.add(student);
    }

    // Обновить данные о студенте
    public void updateStudent(Student student) {
        // Надо найти нужного студента (по его ИД) и заменить поля
        Student updStudent = null;
        for (Student si : students) {
            if (si.getStudentId() == student.getStudentId()) {
                // Вот этот студент - запоминаем его и прекращаем цикл
                updStudent = si;
                break;
            }
        }
        updStudent.setFirstName(student.getFirstName());
        updStudent.setPatronymic(student.getPatronymic());
        updStudent.setSurName(student.getSurName());
        updStudent.setSex(student.getSex());
        updStudent.setDateOfBirth(student.getDateOfBirth());
        updStudent.setGroupId(student.getGroupId());
        updStudent.setEducationYear(student.getEducationYear());
    }

    // Удалить студента




}
