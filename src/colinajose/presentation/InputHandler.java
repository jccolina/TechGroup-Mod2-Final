package colinajose.presentation;

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

    public int getIntegerInput() {
        try{
            String input = this.scanner.nextLine();
            int number = Integer.parseInt(input);
            return number;
        }
        catch (Exception exception){
            return -1;
        }
    }

    public double getDoubleInput() {
        double number = this.scanner.nextDouble();
        this.scanner.nextLine();
        return number;
    }
}
