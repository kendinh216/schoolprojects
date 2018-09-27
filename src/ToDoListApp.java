package ui;
import java.util.ArrayList;
import java.util.Scanner;

public class ToDoListApp {

    private static ArrayList<String> toDo = new ArrayList<>();
    private static ArrayList<String> crossedOff = new ArrayList<>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to ToDoListApp");

        while (true) {
            // Prints menu choices
            System.out.println("Please select one of the following options:");
            System.out.println("[1] Add an item to your ToDo list.");
            System.out.println("[2] Remove the last item from your ToDo list.");
            System.out.println("[3] Remove a specific item from your ToDo list");
            System.out.println("[4] Show all items in Todo and Crossed Off lists.");
            System.out.println("[5] Quit application.");
            int userInput = scanner.nextInt();

            if (userInput == 1) {
                System.out.println("Enter your todo task: ");
                String task = scanner.nextLine();
                scanner.nextLine();
                toDo.add(task);

            } else if (userInput == 2) {
                String removed = toDo.get(toDo.size() -1);
                if (removed != null) {
                    toDo.remove(toDo.size() -1);
                    crossedOff.add(toDo.get(toDo.size() -1 ));
                    System.out.println("The last item in the list: " + "(" + toDo.get(toDo.size() -1) + ")" + " is removed");
                } else {
                    System.out.println("The ToDo list is empty.");
                }

            } else if (userInput == 3) {
                System.out.println("The items to be done are: ");
                for (int i = 0; i < toDo.size(); i ++){
                    System.out.println((i+1) + ". " + toDo.get(i) + "\n" );
                }
                System.out.println("Enter the index of the item you want to cross off: ");
                int index = scanner.nextInt();
                String removed = toDo.remove(index - 1);
                if (removed != null) {
                    System.out.println("The item " + "(" + removed + ")" + " has been crossed off.");
                } else {
                    System.out.println("There is no item at the index " + index + " to be crossed off.");
                }
            }

            else if (userInput == 4) {
                System.out.println("The items that are still need to do are: ");
                for (int i = 0; i < toDo.size(); i ++){
                    System.out.println((i+1) + ". " + toDo.get(i) + "\n");
                }
                System.out.println("");
                System.out.println("The items that have been crossed off are: ");
                for (int i = 0; i < crossedOff.size(); i ++){
                    System.out.println((i+1) + ". " + crossedOff.get(i) + "\n" );
                }


            } else if (userInput == 5) {
                System.out.println("Thank you for using our application.");
                break;
            } else {
                System.out.println("Sorry but you have chosen an invalid option. Please choose again.");


            }
        }
    }
}