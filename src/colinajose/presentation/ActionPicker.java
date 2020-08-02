package colinajose.presentation;

import colinajose.service.SchoolService;
import datastructures.arraylist.MyArrayList;
import datastructures.hashmap.MyHashMap;

public class ActionPicker {
    private SchoolService schoolService;
    private MessagesHandler messagesHandler;
    private InputHandler inputHandler;

    public ActionPicker(){
        this.messagesHandler =  new MessagesHandler();
        this.inputHandler = new InputHandler();
        this.schoolService = new SchoolService();
    }

    public void runAction(String action){
        switch (action){
            case "1":
                registerSchool();
                break;
            case "2":
                createCourse();
                break;
            case "3":
                createTeacher();
                break;
            case "4":
                createSubject();
                break;
            case "5":
                createParent();
                break;
            case "6":
                createDevice();
                break;
            case "7":
                createStudent();
                break;
            case "8":
                submitGrades();
                break;
            case "9":
                setGradingScale();
                break;
            case "10":
                notifyParents();
                break;
            case "11":
                saveGrades();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.println("Option not found, try again");
                break;
        }

    }

    private void saveGrades() {
        String kardexId = selectItem(this.schoolService.getKardexes(), "Course");
        this.messagesHandler.showInputRequest("file path");
        String path = this.inputHandler.getTextInput();
        boolean result = this.schoolService.exportGrades(kardexId, path, "Full Name", "Grade");
        if(result){
            this.messagesHandler.successMessage();
        } else {
            this.messagesHandler.failureMessage();
        }
    }

    private void notifyParents() {
        this.schoolService.sendNotifications();
    }

    private void submitGrades() {
        String kardexId = selectItem(this.schoolService.getKardexes(), "Course");
        String subjectId = selectItem(this.schoolService.getSubjects(kardexId), "Subject");
        this.messagesHandler.showInputRequest("file path");
        String path = this.inputHandler.getTextInput();
        boolean isImported = this.schoolService.importGrades(kardexId, subjectId, path, "Full Name", "Grade");
        if(isImported){
            this.schoolService.computeGrades(kardexId);
            this.schoolService.updateNotifyList(kardexId);
            this.messagesHandler.successMessage();
        } else {
            this.messagesHandler.failureMessage();
        }
    }

    private void setGradingScale() {
        String kardexId = selectItem(this.schoolService.getKardexes(), "Course");
        this.messagesHandler.showInputRequest("scholarship grade");
        double scholarship = this.inputHandler.getDoubleInput();
        this.messagesHandler.showInputRequest("notify grade");
        double notify = this.inputHandler.getDoubleInput();
        this.messagesHandler.showInputRequest("expelled grade");
        double expelled = this.inputHandler.getDoubleInput();
        this.schoolService.setGradingScale(kardexId, scholarship, expelled, notify);
        this.schoolService.computeGrades(kardexId);
        this.schoolService.updateNotifyList(kardexId);
        this.messagesHandler.successMessage();
    }

    private void createStudent() {
        this.messagesHandler.showInputRequest("name");
        String name = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("CI");
        String ci = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("age");
        int age = this.inputHandler.getIntegerInput();
        this.messagesHandler.showInputRequest("gender");
        String gender = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("address");
        String address = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("phone");
        String phone = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("email");
        String email = this.inputHandler.getTextInput();
        String kardexId = selectItem(this.schoolService.getKardexes(), "Course");
        String studentId = this.schoolService.enrollStudent(name, ci, age, gender, kardexId, address, phone, email);
        boolean registerParent = true;
        while(registerParent){
            String parentId = selectItem(this.schoolService.getParents(), "Parent");
            this.schoolService.assignParent(parentId, studentId);
            this.messagesHandler.showContinueRegister("Parent");
            String register = this.inputHandler.getTextInput();
            while(!register.toUpperCase().equals("Y") && !register.toUpperCase().equals("N") ){
                register = this.inputHandler.getTextInput();
                this.messagesHandler.showWrongOption();
            }
            if(register.toUpperCase().equals("N")){
                registerParent = false;
            }
        }
        this.messagesHandler.successMessage();

    }

    private void createDevice() {
        boolean continueRegister = true;
        while(continueRegister){
            String parentId = selectItem(this.schoolService.getParents(), "Parent");
            this.messagesHandler.showInputRequest("phone");
            String phone = this.inputHandler.getTextInput();
            this.schoolService.registerDevice(parentId, phone);
            this.messagesHandler.showContinueRegister("Device");
            String register = this.inputHandler.getTextInput();
            while(!register.toUpperCase().equals("Y") && !register.toUpperCase().equals("N") ){
                this.messagesHandler.showWrongOption();
                register = this.inputHandler.getTextInput();
            }
            if(register.toUpperCase().equals("N")){
                continueRegister = false;
            }
        }
        this.messagesHandler.successMessage();
    }

    private void createParent() {
        this.messagesHandler.showInputRequest("name");
        String name = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("CI");
        String ci = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("age");
        int age = this.inputHandler.getIntegerInput();
        this.messagesHandler.showInputRequest("gender");
        String gender = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("address");
        String address = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("phone");
        String phone = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("email");
        String email = this.inputHandler.getTextInput();
        this.schoolService.registerParent(name, ci, age, gender, address, phone, email);
        this.messagesHandler.successMessage();
    }

    private void createTeacher() {
        this.messagesHandler.showInputRequest("name");
        String name = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("CI");
        String ci = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("age");
        int age = this.inputHandler.getIntegerInput();
        this.messagesHandler.showInputRequest("gender");
        String gender = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("address");
        String address = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("phone");
        String phone = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("email");
        String email = this.inputHandler.getTextInput();
        this.schoolService.registerTeacher(name, ci, age, gender, address, phone, email);
        this.messagesHandler.successMessage();
    }

    private void createSubject() {
        String courseId = selectItem(this.schoolService.getCourses(), "Course");
        String teacherId = selectItem(this.schoolService.getTeachers(), "Teacher");
        this.messagesHandler.showInputRequest("topic");
        String topic = this.inputHandler.getTextInput();
        schoolService.registerSubject(topic, courseId, teacherId);
        this.messagesHandler.successMessage();
    }

    private void createCourse() {
        this.messagesHandler.showInputRequest("grade");
        String grade = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("year");
        String year = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("group");
        String group = this.inputHandler.getTextInput();
        String courseId = this.schoolService.registerCourse(grade, year, group);
        this.schoolService.registerKardex(courseId);
        this.messagesHandler.successMessage();
    }

    private void registerSchool(){
        this.messagesHandler.showInputRequest("name");
        String name = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("address");
        String address = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("phone");
        String phone = this.inputHandler.getTextInput();
        this.messagesHandler.showInputRequest("email");
        String email = this.inputHandler.getTextInput();
        this.schoolService.createSchool(name, address, phone, email);
        this.messagesHandler.successMessage();
    }

    private <T> String selectItem(MyHashMap<String, T> items, String type){
        boolean isWrongInput = true;
        int item = 0;
        if(items.size() > 0) {
            MyArrayList<String> keys = items.getKeys();
            while (isWrongInput) {
                this.messagesHandler.showItems(items, keys, type);
                item = this.inputHandler.getIntegerInput() - 1;
                if (item >= 0 && item < items.size()) {
                    isWrongInput = false;
                    continue;
                }
                this.messagesHandler.showWrongOption();
            }
            return keys.get(item);
        } else {
            this.messagesHandler.showEmptyList(type);
            return "";
        }
    }
}
