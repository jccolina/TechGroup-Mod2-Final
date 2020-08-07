package colinajose.presentation;

import colinajose.service.SchoolService;
import datastructures.arraylist.MyArrayList;
import datastructures.hashmap.MyHashMap;

public class ActionPicker {
    private SchoolService schoolService;
    private MessagesHandler messagesHandler;
    private InputHandler inputHandler;

    private static final String NAME = "name";
    private static final String CI = "CI";
    private static final String GENDER = "gender";
    private static final String ADDRESS = "address";
    private static final String PHONE = "phone";
    private static final String EMAIL = "email";
    private static final String AGE = "age";
    private static final String COURSE = "Course";
    private static final String SUBJECT = "Subject";
    private static final String TOPIC = "topic";
    private static final String TEACHER = "Teacher";
    private static final String FILE_PATH = "file path";
    private static final String FULL_NAME = "Full Name";
    private static final String GRADE = "Grade";
    private static final String EXPELLED_GRADE = "expelled grade";
    private static final String NOTIFY_GRADE = "notify grade";
    private static final String SCHOLARSHIP_GRADE = "scholarship grade";
    private static final String PARENT = "Parent";
    private static final String DEVICE = "Device";
    private static final String Y_N_PATTERN = "(?i)[y|n]";
    private static final String NO_VALUE = "N";
    private static final String GRADE_LOWER_CASE = "grade";
    private static final String YEAR = "year";
    private static final String GROUP = "group";
    private static final String EMPTY_STRING = "";

    public ActionPicker(){
        this.messagesHandler =  new MessagesHandler();
        this.inputHandler = new InputHandler();
        this.schoolService = SchoolService.getSchoolService();
    }

    public void runAction(String action){
        switch (action){
            case "1":
                editSchool();
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
                this.messagesHandler.showWrongValue();
        }

    }

    private void editSchool(){
        String[] fields = {NAME, ADDRESS, PHONE, EMAIL};
        MyHashMap<String, String> inputs = this.inputHandler.getTextInputs(fields, this.messagesHandler);
        this.schoolService.editSchool(inputs.get(NAME), inputs.get(ADDRESS), inputs.get(PHONE), inputs.get(EMAIL));
        this.messagesHandler.successMessage();
    }

    private void createCourse() {
        String[] fields = {GRADE_LOWER_CASE, YEAR, GROUP};
        MyHashMap<String, String> inputs = this.inputHandler.getTextInputs(fields, this.messagesHandler);
        String courseId = this.schoolService.registerCourse(inputs.get(GRADE_LOWER_CASE), inputs.get(YEAR), inputs.get(GROUP));
        this.schoolService.registerKardex(courseId);
        this.messagesHandler.successMessage();
    }

    private void createTeacher() {
        String[] fields = {NAME, CI, GENDER, ADDRESS, PHONE, EMAIL};
        MyHashMap<String, String> textInputs = this.inputHandler.getTextInputs(fields, this.messagesHandler);
        MyHashMap<String, Integer> integerInputs = this.inputHandler.getIntegerInputs(new String[]{AGE}, this.messagesHandler, 0);
        this.schoolService.registerTeacher(textInputs.get(NAME), textInputs.get(CI), integerInputs.get(AGE),
                textInputs.get(GENDER), textInputs.get(ADDRESS), textInputs.get(PHONE), textInputs.get(EMAIL));
        this.messagesHandler.successMessage();
    }

    private void createSubject() {
        String courseId = selectItem(this.schoolService.getCourses(), COURSE);
        if(courseId.equals(EMPTY_STRING)) return;
        String teacherId = selectItem(this.schoolService.getTeachers(), TEACHER);
        if(teacherId.equals(EMPTY_STRING)) return;
        MyHashMap<String, String> inputs = this.inputHandler.getTextInputs(new String[]{TOPIC}, this.messagesHandler);
        schoolService.registerSubject(inputs.get(TOPIC), courseId, teacherId);
        this.messagesHandler.successMessage();
    }

    private void createParent() {
        String[] fields = {NAME, CI, GENDER, ADDRESS, PHONE, EMAIL};
        MyHashMap<String, String> textInputs = this.inputHandler.getTextInputs(fields, this.messagesHandler);
        MyHashMap<String, Integer> integerInputs = this.inputHandler.getIntegerInputs(new String[]{AGE}, this.messagesHandler, 0);
        this.schoolService.registerParent(textInputs.get(NAME), textInputs.get(CI), integerInputs.get(AGE),
                textInputs.get(GENDER), textInputs.get(ADDRESS), textInputs.get(PHONE), textInputs.get(EMAIL));
        this.messagesHandler.successMessage();
    }

    private void createDevice() {
        boolean continueRegister = true;
        while(continueRegister){
            String parentId = selectItem(this.schoolService.getParents(), PARENT);
            if(parentId.equals(EMPTY_STRING)) return;
            String[] fields = {PHONE};
            MyHashMap<String, String> textInputs = this.inputHandler.getTextInputs(fields, this.messagesHandler);
            this.schoolService.registerDevice(parentId, textInputs.get(PHONE));
            this.messagesHandler.showContinueRegister(DEVICE);
            String register = this.inputHandler.getTextInput(Y_N_PATTERN, this.messagesHandler);
            if(register.toUpperCase().equals(NO_VALUE)){
                continueRegister = false;
            }
        }
        this.messagesHandler.successMessage();
    }

    private void createStudent() {
        String[] fields = {NAME, CI, GENDER, ADDRESS, PHONE, EMAIL};
        MyHashMap<String, String> textInputs = this.inputHandler.getTextInputs(fields, this.messagesHandler);
        MyHashMap<String, Integer> integerInputs = this.inputHandler.getIntegerInputs(new String[]{AGE}, this.messagesHandler, 0);
        String kardexId = selectItem(this.schoolService.getKardexes(), COURSE);
        if(kardexId.equals(EMPTY_STRING)) return;
        String studentId = this.schoolService.enrollStudent(textInputs.get(NAME), textInputs.get(CI), integerInputs.get(AGE),
                textInputs.get(GENDER), kardexId, textInputs.get(ADDRESS), textInputs.get(PHONE), textInputs.get(EMAIL));
        boolean registerParent = true;
        while(registerParent){
            String parentId = selectItem(this.schoolService.getParents(), PARENT);
            if(parentId.equals(EMPTY_STRING)) return;
            this.schoolService.assignParent(parentId, studentId);
            this.messagesHandler.showContinueRegister(PARENT);
            String register = this.inputHandler.getTextInput(Y_N_PATTERN, this.messagesHandler);
            if(register.toUpperCase().equals(NO_VALUE)){
                registerParent = false;
            }
        }
        this.messagesHandler.successMessage();

    }

    private void submitGrades() {
        String kardexId = selectItem(this.schoolService.getKardexes(), COURSE);
        if(kardexId.equals(EMPTY_STRING)) return;
        String subjectId = selectItem(this.schoolService.getSubjects(kardexId), SUBJECT);
        if(subjectId.equals(EMPTY_STRING)) return;
        this.messagesHandler.showInputRequest(FILE_PATH);
        String path = this.inputHandler.getTextInput();
        boolean isImported = this.schoolService.importGrades(kardexId, subjectId, path, FULL_NAME, GRADE);
        if(isImported){
            this.schoolService.computeGrades(kardexId);
            this.schoolService.updateNotifyList(kardexId);
            this.messagesHandler.successMessage();
        } else {
            this.messagesHandler.failureMessage();
        }
    }

    private void setGradingScale() {
        String kardexId = selectItem(this.schoolService.getKardexes(), COURSE);
        if(kardexId.equals(EMPTY_STRING)) return;
        MyHashMap<String, Double> expelled = this.inputHandler.getDoubleInputs(new String[]{EXPELLED_GRADE}, this.messagesHandler, 0);
        MyHashMap<String, Double> notify = this.inputHandler.getDoubleInputs(new String[]{NOTIFY_GRADE}, this.messagesHandler, expelled.get(EXPELLED_GRADE));
        MyHashMap<String, Double> scholarship = this.inputHandler.getDoubleInputs(new String[]{SCHOLARSHIP_GRADE}, this.messagesHandler, notify.get(NOTIFY_GRADE));
        this.schoolService.setGradingScale(kardexId, scholarship.get(SCHOLARSHIP_GRADE), expelled.get(EXPELLED_GRADE), notify.get(NOTIFY_GRADE));
        this.schoolService.computeGrades(kardexId);
        this.schoolService.updateNotifyList(kardexId);
        this.messagesHandler.successMessage();
    }

    private void notifyParents() {
        this.schoolService.sendNotifications();
    }

    private void saveGrades() {
        String kardexId = selectItem(this.schoolService.getKardexes(), COURSE);
        if(kardexId.equals(EMPTY_STRING)) return;
        this.messagesHandler.showInputRequest(FILE_PATH);
        String path = this.inputHandler.getTextInput();
        boolean result = this.schoolService.exportGrades(kardexId, path, FULL_NAME, GRADE);
        if(result){
            this.messagesHandler.successMessage();
        } else {
            this.messagesHandler.failureMessage();
        }
    }

    private <T> String selectItem(MyHashMap<String, T> items, String type){
        int item;
        if(items.size() > 0) {
            MyArrayList<String> keys = items.getKeys();
            this.messagesHandler.showItems(items, keys, type);
            item = this.inputHandler.getIntegerBetween(0, items.size() + 1, this.messagesHandler) - 1;
            return keys.get(item);
        } else {
            this.messagesHandler.showEmptyList(type);
            return EMPTY_STRING;
        }
    }
}
