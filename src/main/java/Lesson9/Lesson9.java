package Lesson9;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Lesson9 {

    interface Student {
        String getName();
        List<Course> getAllCourses();
    }
    interface Course {
        String getTitle();
    }

    static class MyStudent implements Student{
        private String name;
        private List<Course> courses;

        public MyStudent(String name, List<Course> courses){
            this.name = name;
            this.courses = courses;
        }

        @Override
        public String getName() {
            return name;
        }

        @Override
        public List<Course> getAllCourses() {
            return courses;
        }
    }

    static class MyCourse implements Course{
        private String title;

        public MyCourse(String title){
            this.title = title;
        }

        @Override
        public String getTitle() {
            return title;
        }
    }

    public static void main (String[] args){
        System.out.println("Hello");
        Course[] courses = {
                new MyCourse("Programming"),
                new MyCourse("Algorithmic"),
                new MyCourse("Functional"),
                new MyCourse("Processing"),
                new MyCourse("Management"),
                new MyCourse("Marketing"),
                new MyCourse("Salary"),
                new MyCourse("Public relation"),
                new MyCourse("Content"),
                new MyCourse("Development")};

        List<Student> students = Arrays.asList(
                new MyStudent("Kate", Arrays.asList(courses[0], courses[1], courses[8])),
                new MyStudent("Peter", Arrays.asList(courses[2], courses[3])),
                new MyStudent("Anny", Arrays.asList(courses[4], courses[5], courses[6], courses[7])),
                new MyStudent("Mike", Arrays.asList(courses[6], courses[7], courses[6], courses[8])),
                new MyStudent("Sonya", Arrays.asList(courses[8], courses[9])),
                new MyStudent("Bob", Arrays.asList(courses[1])),
                new MyStudent("Rocky", Arrays.asList(courses[2]))
        );


        // 1.
        System.out.println(uniqueCourses(students));
        // Выведено на консоль:
        // [Programming, Algorithmic, Content, Functional, Processing, Management, Marketing, Salary, Public relation, Development]

        // 2.
        System.out.println(wiseStudents(students));
        // Выведено на консоль:
        // [Kate, Anny, Mike]

        // 3.
        System.out.println(findStudentsByCourse(students, courses[6]));
        // Выведено на консоль:
        // [Anny, Mike]
    }

    // 1. Написать функцию, принимающую список Student
    // и возвращающую список уникальных курсов, на которые подписаны студенты
    public static List<String> uniqueCourses(List<Student> students){
        return students.stream()
                .map(Student::getAllCourses)
                .flatMap(List::stream)
                .map(Course::getTitle)
                .distinct()
                .collect(Collectors.toList());
    }

    // 2. Написать функцию, принимающую на вход список Student
    // и возвращающую список из трех самых любознательных (любознательность определяется количеством курсов).
    public static List<String> wiseStudents(List<Student> students){
        return students.stream()
                .filter(student -> student.getAllCourses().size() >= 3)
                .map(Student::getName)
                .collect(Collectors.toList());
    }

    // 3. Написать функцию, принимающую на вход список Student и экземпляр Course,
    // возвращающую список студентов, которые посещают этот курс.
    static List<String> findStudentsByCourse(List<Student> students, Course course) {
        return students.stream()
                .filter(student -> student.getAllCourses().contains(course))
                .map(Student::getName)
                .collect(Collectors.toList());
    }

}


