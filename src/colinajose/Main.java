package colinajose;

import colinajose.model.schoolEntities.School;

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

        // Register Student
        String studentId = school.enrollStudent("Ana Perez", "3455555",15, "Female", "Av. Blanco Galindo #018", "+5914445555","myemail@mail.com", courseId);
        school.assignParent(studentId, parentId);
        // Register Grade to Student
        school.setGrade(90, subj1Id, studentId, courseId);
        school.setGrade(45, subj2Id, studentId, courseId);
        school.setGrade(68, subj3Id, studentId, courseId);
    }
}
