package model;

import java.util.ArrayList;
import java.util.Scanner;

public class ToDoList {

    private static ArrayList<Item> toDo = new ArrayList<>();
    //private static ArrayList<Item> crossedOff = new ArrayList<>();

    //MODIFIES: this
    //EFFECTS:  prints out a list of options for the user to choose from
    //          execute either add, remove the last item, remove a specific item, show all items or quite based on user input
    public ToDoList() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            // Prints menu choices
            System.out.println("Please select one of the following options:");
            System.out.println("[1] Add an item to your ToDo list.");
            System.out.println("[2] Remove the last item from your ToDo list.");
            System.out.println("[3] Remove a specific item from your ToDo list");
            System.out.println("[4] Show all items in Todo and Crossed Off lists.");
            System.out.println("[5] Quit application.");
            String userInput = scanner.next();
            scanner.nextLine();

            //Add a task
            if (userInput.equals("1")) {
                System.out.println("Enter your todo task: ");
                String task = scanner.nextLine();
                AddNewItemToList(task, toDo);
                System.out.println("");
            }
            //Delete the last item in to do list
            //If to do list is empty then do nothing
            else if (userInput.equals("2")) {
                if (toDo.size() == 0) {
                    System.out.println("The ToDo list is empty");
                    System.out.println("");
                } else {
                    Item removed = toDo.get(toDo.size() - 1);
                    removed.setItemStatus(true);
                    System.out.println("The last item in the list: " + "(" + removed.getItemName() + ")" + " is removed");
                    System.out.println("");
                }
            }
            //Cross off a specific item in the to do list
            else if (userInput.equals("3")) {
                System.out.println("The items to be done are: ");
                ShowAllListItemsNameAndStatus(toDo);
                System.out.println("Enter the index of the item you want to cross off: ");
                int index = scanner.nextInt();
                Item removed = toDo.get(index - 1);
                if (removed != null) {
                    removed.setItemStatus(true);
                    System.out.println("The item " + "(" + removed.getItemName() + ")" + " has been crossed off.");
                } else {
                    System.out.println("There is no item at the index [" + index + "] to be crossed off.");
                }
            }
            //Show all items in to do list with their status
            else if (userInput.equals("4")){
                System.out.println("The list of items you have entered are: ");
                ShowAllListItemsNameAndStatus(toDo);
            }
            //Ends application
            else if (userInput.equals("5")) {
                System.out.println("Thank you for using our application.");
                break;
            }
            //User input wrong command so repeat the menu choices
            else {
                System.out.println("Sorry but you have chosen an invalid option. Please choose again." +"\n");

            }
        }
    }
    //EFFECTS: prints out all items in a list with their name and status
    public void ShowAllListItemsNameAndStatus(ArrayList<Item> array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.println((i + 1) + ". " + array.get(i).getItemName() + " - " + array.get(i).getItemStatus() +"\n"); }
    }
    //EFFECTS: prints out all items in a list with their name only
    public void PrintAllListItemsNames(ArrayList<Item> array) {
        for (int i = 0; i < array.size(); i++) {
            System.out.println((i + 1) + ". " + array.get(i).getItemName() + "\n");
        }
    }

    //MODIFIES: this
    //EFFECTS:  add new item to a list of items
    public void AddNewItemToList(String task, ArrayList<Item> array) {
        Item newItem = new Item(task,false);
        array.add(newItem);
    }

}
