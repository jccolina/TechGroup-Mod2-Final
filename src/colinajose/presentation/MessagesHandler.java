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

    public void showWrongValue() {
        System.out.println("Wrong value, please try again.");
    }

    public void showContinueRegister(String element) {
        System.out.println("Do you want to register another " + element + "? (Y/N)");
    }

    public void showEmptyList(String type) {
        System.out.println("Not possible to continue because of lack of " + type);
    }

    public <T> void showItems(MyHashMap<String, T> items, MyArrayList<String> keys, String type) {
        System.out.println("Select " + type + ":");
        for (int i = 0; i < keys.size(); i++) {
            T item = items.get(keys.get(i));
            System.out.println((i + 1) + ". " + item.toString());
        }
    }
}
