package colinajose.presentation;

public class MainMenu {
    private static MessagesHandler messagesHandler;
    private static ActionPicker actionPicker;
    private static InputHandler inputHandler;

    public static void init(){
        messagesHandler =  new MessagesHandler();
        actionPicker = new ActionPicker();
        inputHandler = new InputHandler();

        while (true){
            messagesHandler.showMainMenu();
            String action = inputHandler.getTextInput();
            actionPicker.runAction(action);
        }
    }
    public static void main(String[] args){
        init();
    }
}
