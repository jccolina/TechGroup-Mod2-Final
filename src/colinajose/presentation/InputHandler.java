package colinajose.presentation;

import datastructures.hashmap.MyHashMap;

import java.util.Scanner;

public class InputHandler {
    private Scanner scanner;

    public InputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String getTextInput() {
        String text = this.scanner.nextLine();
        return text;
    }

    public String getTextInput(String pattern, MessagesHandler messagesHandler) {
        while (true) {
            String text = this.scanner.nextLine();
            if(text.matches(pattern)){
                return text;
            } else {
                messagesHandler.showWrongValue();
            }
        }
    }

    public int getIntegerInput(int minValue, MessagesHandler messagesHandler) {
        while (true) {
            try {
                String input = this.scanner.nextLine();
                int value = Integer.parseInt(input);
                if(value > minValue){
                    return value;
                } else {
                    messagesHandler.showWrongValue();
                }
            } catch (Exception exception) {
                messagesHandler.showWrongValue();
            }
        }
    }

    public int getIntegerBetween(int minValue, int maxValue, MessagesHandler messagesHandler) {
        while (true) {
            try {
                String input = this.scanner.nextLine();
                int value = Integer.parseInt(input);
                if(value < maxValue && value > minValue){
                    return value;
                } else {
                    messagesHandler.showWrongValue();
                }
            } catch (Exception exception) {
                messagesHandler.showWrongValue();
            }
        }
    }

    public double getDoubleInput(double minValue, MessagesHandler messagesHandler) {
        while (true) {
            try {
                String input = this.scanner.nextLine();
                double value = Double.parseDouble(input);
                if(value > minValue){
                    return value;
                } else {
                    messagesHandler.showWrongValue();
                }
            } catch (Exception exception) {
                messagesHandler.showWrongValue();
            }
        }
    }

    public MyHashMap<String, String> getTextInputs(String[] fields, MessagesHandler messagesHandler){
        MyHashMap<String, String> inputs = new MyHashMap<>();
        for (int i = 0; i < fields.length; i++) {
            messagesHandler.showInputRequest(fields[i]);
            inputs.put(fields[i], this.getTextInput());
        }
        return inputs;
    }

    public MyHashMap<String, Integer> getIntegerInputs(String[] fields, MessagesHandler messagesHandler, int minValue){
        MyHashMap<String, Integer> inputs = new MyHashMap<>();
        for (int i = 0; i < fields.length; i++) {
            messagesHandler.showInputRequest(fields[i]);
            inputs.put(fields[i], this.getIntegerInput(minValue, messagesHandler));
        }
        return inputs;
    }

    public MyHashMap<String, Double> getDoubleInputs(String[] fields, MessagesHandler messagesHandler, double minValue){
        MyHashMap<String, Double> inputs = new MyHashMap<>();
        for (int i = 0; i < fields.length; i++) {
            messagesHandler.showInputRequest(fields[i]);
            inputs.put(fields[i], this.getDoubleInput(minValue, messagesHandler));
        }
        return inputs;
    }
}
