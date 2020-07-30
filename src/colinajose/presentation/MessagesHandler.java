package colinajose.presentation;

import datastructures.arraylist.MyArrayList;
import datastructures.hashmap.MyHashMap;

public class MessagesHandler {
    public void showMainMenu(){
        System.out.println("=== School System Administrator === \n" +
                "Please select an option\n" +
                "1. Edit School Information\n" +
                "2. Create a Course\n" +
                "3. Create Teacher\n" +
                "4. Create Subject\n" +
                "5. Create Parent\n" +
                "6. Create Device\n" +
                "7. Register Student\n" +
                "8. Submit grades\n" +
                "9. Set grading scale\n" +
                "10. Notify parents\n" +
                "11. Save grades\n" +
                "0. Exit");
    }
    public void showInputRequest(String fieldName){
        System.out.println("Please enter " + fieldName + ":");
    }
    public void successMessage(){
        System.out.println("Action successfully completed.");
    }
    public void failureMessage(){
        System.out.println("Sorry the action could not be completed.");
    }
    public void showSubmenu(String submenu){
        switch (submenu){
            case "SchoolInfo":
                schoolInfo();
                break;
            case "SchoolAddress":
                break;
            case "StudentName":
                break;
            default:
                break;
        }

    }

    private void schoolInfo() {
    }

    public void showItems(MyArrayList<MyHashMap<String, String>> items, String itemType) {
        System.out.println("Select " + itemType + ":");
        for (int i = 0; i < items.size(); i++) {
            MyHashMap<String, String> item = items.get(i);
            System.out.println((i + 1) + ". " + item.get("item"));
        }
    }

    public void showWrongOption() {
        System.out.println("Wrong option, please try again.");
    }

    public void showContinueRegister(String element) {
        System.out.println("Do you want to register another " + element + "? (Y/N)");
    }

    public void showEmptyList(String type) {
        System.out.println("Not possible to continue because of lack of " + type);
    }
}
