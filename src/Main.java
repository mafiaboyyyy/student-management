import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class StudentManagement {
    public static void main(String[] args) {
        ArrayList<Student> students = new ArrayList<>();
        Student Dung = new Student("Dung", "105190001");
        Student Nam = new Student("Nam", "105190002");
        Student Hoa = new Student("Hoa", "105190003");
        students.add(Dung);
        students.add(Nam);
        students.add(Hoa);

        LinkedList<Course> courses = new LinkedList<>();
        Course Math = new Course("Math");
        Course Biology = new Course("Biology");
        courses.add(Math);
        courses.add(Biology);

        ArrayList<Score> scores = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        boolean quit = false;

        while(!quit) {
            printMenu();
            int option = Integer.parseInt(sc.nextLine().trim());
            switch(option) {
                case 0 -> quit = true;
                case 1 -> addStudent(students);
                case 2 -> showStudent(students);
                case 3 -> addCourse(courses);
                case 4 -> showCourse(courses);
                case 5 -> addScore(students, courses, scores);
                case 6 -> showFull(scores, students);
                case 7 -> saveToFiles(scores, students);
                default -> System.out.println("Option invalid!!");
            }
        }

    }
    public static String capitalizeLetter(String letter) {
        if (letter == null || letter.isEmpty()) {
            return "Letter is empty";
        } else {
            String[] arrLetter = letter.split(" ");
            String output = "";
            for (String el : arrLetter) {
                char firstLetter = Character.toUpperCase(el.charAt(0));
                String fullLetter = firstLetter + el.substring(1);
                output += fullLetter + " ";
            }
            return output;
        }
    }

    // STUDENTS MANAGEMENT
    public static void addStudent(ArrayList<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter student name: ");
        String name = sc.nextLine().trim();
        System.out.println("Enter student code: ");
        String code = sc.nextLine().trim();
        for (Student student : students) {
            if (Integer.parseInt(student.code) == Integer.parseInt(code)) {
                System.out.println("Student is already exist\nPlease re-enter");
                addStudent(students);
                return;
            }
        }
        students.add(new Student(name, code));
        System.out.println("Student has been successfully added");
    }
    //------------------------------------------------------------------------------------

    // SHOW LIST STUDENT (NAME, STUDENT CODE)
    public static void showStudent(ArrayList<Student> students) {
        for (Student student : students) {
            System.out.println("Name: " + capitalizeLetter(student.name) + " | " + "Student Code: " + student.code);
        }
    }
    //------------------------------------------------------------------------------------

    // COURSES MANAGEMENT
    public static void addCourse(LinkedList<Course> courses) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter course: ");
        String course = sc.nextLine().trim();
        courses.add(new Course(course));
    }
    //------------------------------------------------------------------------------------

    // SHOW COURSE
    public static void showCourse(LinkedList<Course> courses) {
        System.out.println("List course");
        for (Course course : courses) {
            System.out.println(capitalizeLetter(course.name));
        }
    }
    //------------------------------------------------------------------------------------

    // SCORES MANAGEMENT
    public static void addScore(ArrayList<Student> students, LinkedList<Course> courses, ArrayList<Score> scores) {
        Scanner sc = new Scanner(System.in);
        System.out.println("List student:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println(i +  ". " + students.get(i).name);
        }
        System.out.println("Pick student (0 - " + (students.size() - 1) + " )");
        int studentIndex = sc.nextInt();
        sc.nextLine();
        System.out.println("List courses:");
        for (int i = 0; i < courses.size(); i++) {
            System.out.println(i + ". " + courses.get(i).name);
        }
        System.out.println("Pick course (0 - " + (courses.size() - 1) + " )");
        int courseIndex = sc.nextInt();
        sc.nextLine();

        System.out.println("Score: ");
        double scoreValue = sc.nextDouble();
        sc.nextLine();

        String selectedStudentCode = students.get(studentIndex).code;
        String selectedCourseName = courses.get(courseIndex).name;
        scores.add(new Score(selectedStudentCode, selectedCourseName, scoreValue));
    }
    public static String getStudentNameByCode(ArrayList<Student> students, String code) {
        for (Student student : students) {
            if (student.code == code) {
                return capitalizeLetter(student.name);
            }
        }
        return "";
    }
    //------------------------------------------------------------------------------------

    // SHOW FULL LIST (NAME, CODE STUDENT, COURSE, SCORE)
    public static void showFull(ArrayList<Score> scores, ArrayList<Student> students) {
        for (Score score : scores) {
            System.out.println("Name: " + getStudentNameByCode(students, score.studentCode) + ", Student Code: " + score.studentCode
                    + ", Course: " + capitalizeLetter(score.courseName) + ", Score: " + score.value);
        }
    }
    //------------------------------------------------------------------------------------

    // SAVE LIST TO FILE
    public static void saveToFiles(ArrayList<Score> scores, ArrayList<Student> students) {
        FileWriter fileWriter = null;
        try {
            fileWriter = new FileWriter("diem.txt");
            for (Score score : scores) {
                String name = getStudentNameByCode(students, score.studentCode);
                fileWriter.write("Name: " + name + ", Student Code: " + score.studentCode
                        + ", Subject: " + score.courseName + ", Score: " + score.value + "\n");
            }
            System.out.println("Score has been successfully saved in 'diem.txt'.");
        } catch (IOException e) {
            System.out.println("Error!");
            e.printStackTrace();
        } finally {
            if (fileWriter != null) {
                try {
                    fileWriter.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //------------------------------------------------------------------------------------

    // PRINT MENU
    public static void printMenu() {
        System.out.println("""
                Enter your option:
                0. Quit
                1. Student Management
                2. List Student
                3. Course Management
                4. List Course
                5. Score Management
                6. List (Student name, course, score)
                7. Save
                """);
    }

}







