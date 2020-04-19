package colinajose;

import colinajose.model.schoolEntities.School;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        // Create School
        School school = new School("Dev School", "C. Lanza #666", "+59123335666","dev-school@mail.com");

        // Register teacher
        String teacherId = school.addTeacher("Jaime Escalante", "555555-CBA", 50, "Male", "Av. Reducto #666", "+5914445666","professor@mail.com");

        // Register Course
        String courseId = school.registerCourse("1 primaria", "2020", "A");

        // Register Subjects
        String subj1Id = school.registerSubject("Maths", courseId, teacherId);
        String subj2Id = school.registerSubject("Physics", courseId, teacherId);
        String subj3Id = school.registerSubject("Lit", courseId, teacherId);

        //Register parent
        String parentId = school.addParent("Juan Perez", "4578899-A", 45,"Male", "Av. Blanco Galindo #018", "+5914445555","myemail@mail.com",
                "Av. Melchor Edif Imperial Dep #26", "+5914445555","myemaul@mail.com");

        String parentId2 = school.addParent("Angelica Lopez", "56767777", 40,"Female", "Av. Blanco Galindo #018", "+5915666666","angie@mail.com",
                "Av. Melchor Edif Imperial Dep #26", "+5914466666","angie@mail.com");

        // Register Student
        String studentId1 = school.enrollStudent("Ana Perez", "3455555",15, "Female", "Av. Blanco Galindo #018", "+5914445555","myemail@mail.com", courseId);
        school.assignParent(studentId1, parentId);
        // Register Grade to Student
        //SCHOLARSHIP
        school.setGrade(90, subj1Id, studentId1, courseId);
        school.setGrade(80, subj2Id, studentId1, courseId);
        school.setGrade(85, subj3Id, studentId1, courseId);

        // Register Student
        String studentId2 = school.enrollStudent("Carmen Cespedes", "3455555",16, "Female", "Av. Blanco Galindo #018", "+5914445555","myemail@mail.com", courseId);
        school.assignParent(studentId2, parentId);
        school.assignParent(studentId2, parentId2);
        // Register Grade to Student
        // NOTIFY
        school.setGrade(20, subj1Id, studentId2, courseId);
        school.setGrade(69, subj2Id, studentId2, courseId);
        school.setGrade(35, subj3Id, studentId2, courseId);

        // Register Student
        String studentId3 = school.enrollStudent("Susana Almendras", "3455555",15, "Female", "Av. Blanco Galindo #018", "+5914445555","myemail@mail.com", courseId);
        school.assignParent(studentId3, parentId);
        // Register Grade to Student
        // EXPELLED
        school.setGrade(30, subj1Id, studentId3, courseId);
        school.setGrade(25, subj2Id, studentId3, courseId);
        school.setGrade(10, subj3Id, studentId3, courseId);

        // Register Student
        String studentId4 = school.enrollStudent("Luz Gonzales", "3455555",15, "Female", "Av. Blanco Galindo #018", "+5914445555","myemail@mail.com", courseId);
        school.assignParent(studentId4, parentId);
        // Register Grade to Student
        // AVERAGE - NO ACTION
        school.setGrade(50, subj1Id, studentId4, courseId);
        school.setGrade(50, subj2Id, studentId4, courseId);
        school.setGrade(50, subj3Id, studentId4, courseId);

        // Get average
        System.out.println("AVERAGES sorted higher to lower: \n"+ Arrays.toString(school.getStudentsAverage(courseId)));
        // Set scale for expelledGrade, notifyGrade, scholarshipGrade
        school.setGradingScale(30, 45, 80, courseId);
        // Update states
        school.updateStudentsState(courseId);
        // Get EXPELLED
        System.out.println("EXPELLED" + Arrays.toString(school.filterStudentsByState(courseId, "EXPELLED")));
        // Get NOTIFY
        System.out.println("NOTIFY" +Arrays.toString(school.filterStudentsByState(courseId, "NOTIFY")));
        // Get SCHOLARSHIP
        System.out.println("SCHOLARSHIP" +Arrays.toString(school.filterStudentsByState(courseId, "SCHOLARSHIP")));

        school.sendNotification();
    }
}
