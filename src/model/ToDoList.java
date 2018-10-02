package model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ToDoList implements  Loadable, Saveable{

    public static ListOfItem toDo = new ListOfItem();
    public SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");


    //MODIFIES: this
    //EFFECTS:  prints out a list of options for the user to choose from
    //          execute either add, remove the last item, remove a specific item, show all items or quite based on user userData
    public ToDoList() throws ParseException, IOException {
        Scanner scanner = new Scanner(System.in);
        load();
        while (true) {
            // Prints menu choices
            System.out.println("Please select one of the following options:");
            System.out.println("[1] Add an item to your ToDo list (replace a space with \"-\") ");
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
                System.out.println("Enter the due date of your task: (in the format of yyyy-mm-dd)");
                String duedate  = scanner.nextLine();
                toDo.addItem(task,false, sdf.parse(duedate));
                System.out.println("");
            }
            //Delete the last item in to do list
            //If to do list is empty then do nothing
            else if (userInput.equals("2")) {
                if (toDo.getSize() == 0) {
                    System.out.println("The ToDo list is empty");
                    System.out.println("");
                } else {
                    toDo.removeLastItem();
                    System.out.println("");
                }
            }
            //Cross off a specific item in the to do list
            else if (userInput.equals("3")) {
                System.out.println("The items to be done are: ");
                toDo.showAllListItemsNameAndStatus();
                System.out.println("Enter the index of the item you want to cross off: ");
                int index = scanner.nextInt();
                toDo.removeIndexItem(index);
            }
            //Show all items in to do list with their status
            else if (userInput.equals("4")){
                System.out.println("The list of items you have entered are: ");
                toDo.showAllListItemsNameAndStatus();
            }
            //Ends application
            else if (userInput.equals("5")) {
                save();
                System.out.println("Thank you for using our application :)");
                break;
            }
            //User input wrong command so repeat the menu choices
            else {
                System.out.println("Sorry but you have chosen an invalid option. Please choose again." +"\n");

            }
        }
    }
    //MODIFIES: this
    //EFFECTS:  save all items with their name, status, and due date in list of item into userData file
    public void save () throws IOException {
        PrintWriter writer = new PrintWriter("userData","UTF-8");
        for (int i = 0; i < toDo.getSize(); i++){
            writer.println((toDo.getItem(i).getItemName()) + " " +
                    (String.valueOf(toDo.getItem(i).getItemStatus())) + " " +
                    (toDo.getItem(i).getItemDueDateinString()));
        }
        writer.close();
    }
    //MODIFIES: this
    //EFFECTS:  create new item for every line in userData file and add them to list of items
    public void load() throws IOException, ParseException {
        List<String> lines = Files.readAllLines(Paths.get("userData"));
        for (String line : lines){
            ArrayList<String> partsOfLine = splitOnSpace(line);
            Date duedate = sdf.parse(partsOfLine.get(2));
            toDo.addItem(partsOfLine.get(0), Boolean.parseBoolean(partsOfLine.get(1)), duedate );
        }
    }

    //EFFECTS: split each line in userData file into a list of string
    public static ArrayList<String> splitOnSpace(String line){
        String[] splits = line.split(" ");
        return new ArrayList<>(Arrays.asList(splits));
    }

    public ListOfItem getToDoList(){
        return toDo;
    }
    public void addNewItemToList(String name, boolean status, Date duedate){
        toDo.addItem(name, status,duedate);
    }

}
