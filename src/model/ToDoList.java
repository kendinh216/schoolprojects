package model;

import Exceptions.TooManyThingsToDoException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;


public class ToDoList implements  Loadable, Saveable{

    private ListOfItem toDo = new ListOfItem();
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");


    //MODIFIES: this
    //EFFECTS:  prints out a list of options for the user to choose from
    //          execute either add, remove the last item, remove a specific item, show all items or quite based on user userData
    public ToDoList() throws ParseException, IOException, TooManyThingsToDoException {
        Scanner scanner = new Scanner(System.in);
        try {load();}
            catch(IndexOutOfBoundsException e){}

        while (true) {
            // Prints menu choices
            System.out.println("Please select one of the following options:");
            System.out.println("[1] Add a task to your ToDo list (replace a space with \"-\") ");
            System.out.println("[2] Cross-off the last task from your ToDo list");
            System.out.println("[3] Cross-off a specific task from your ToDo list");
            System.out.println("[4] Remove a specific task from your ToDo list");
            System.out.println("[5] Remove all tasks from ToDo list");
            System.out.println("[6] Show all tasks in Todo list");
            System.out.println("[7] Quit application");
            String userInput = scanner.next();
            scanner.nextLine();

            //Add a task
            if (userInput.equals("1")) {
                System.out.println("Enter your todo task: ");
                String task = scanner.nextLine();
                System.out.println("Enter the due date of your task: (in the format of MM/dd/yyyy)");
                String duedate  = scanner.nextLine();
                System.out.println("Is this task an urgency? (y/n)");
                String urgency = scanner.nextLine();
                if (urgency.contentEquals("y")){
                    try {
                        Item i = new UrgenItem(task,false, sdf.parse(duedate), true );
                        toDo.addUrgenItem(i);
                        i.addToToDoList(toDo);
                    } catch (TooManyThingsToDoException e) {
                        System.out.println("There are at least 5 undone tasks !");
                        System.out.println("Please cross off at least 1 task from list.");

                    }
                    finally{
                        System.out.println("");
                    }
                }
                else{
                    try {
                        Item i = new NormalItem(task,false, sdf.parse(duedate), false);
                        toDo.addNormalItem(i);
                        i.addToToDoList(toDo);
                    } catch (TooManyThingsToDoException e) {
                        System.out.println("There are more than 5 undone tasks!");
                        System.out.println("Please cross off at least 1 task from list.");
                    }
                    finally {
                        System.out.println("");
                    }
                }
            }
            //Cross-off the last item in to do list
            //If to-do list is empty then do nothing
            else if (userInput.equals("2")) {
                if (toDo.getSize() == 0) {
                    System.out.println("The ToDo list is empty");
                    System.out.println("");
                } else {
                    toDo.crossOffLastItem();
                    System.out.println("");
                }
            }
            //Cross off a specific item in the to do list
            else if (userInput.equals("3")) {
                System.out.println("The items to be done are: ");
                toDo.showAllTasksNameAndStatus();
                System.out.println("Enter the index of the item you want to cross off: ");
                int index;
                while (true){
                    index = scanner.nextInt();
                    try {toDo.crossOffIndexItem(index);
                        break;}
                    catch(IndexOutOfBoundsException e){
                        System.out.println("Invalid input. Please try again."); }
                }
                System.out.println("");
            }

            // Remove a task from the to do list at index
            else if (userInput.equals("4")){
                if (toDo.getSize() == 0) {
                    System.out.println("Sorry this action is not valid because the Todo list is empty.");
                    System.out.println("");
                    continue;
                }
                System.out.println("The items to be done are: ");
                toDo.showAllTasksNameAndStatus();
                System.out.println("Enter the index of the task you want to cross off: ");
                int index;
                while (true){
                    index = scanner.nextInt();
                try {Item i = toDo.getItem(index -1);
                    ListOfItem loi = i.getToDoList();
                    toDo.removeItem(i);
                    i.removeFromToDoList(toDo);
                    System.out.println("By removing this task, you have " + loi.getSize()+ " task left.");
                    break;}
                catch(IndexOutOfBoundsException e){
                    System.out.println("Invalid input. Please try again."); }
                }
                System.out.println("");
            }

            //Remove all tasks in to do list
            else if (userInput.equals("5")){
                toDo.removeAllItem();
                System.out.println("");
            }

            //Show all items in to do list with their status
            else if (userInput.equals("6")){
                System.out.println("The list of items you have entered are: ");
                toDo.showAllTasksNameAndStatus();
                System.out.println("");
            }
            //Ends application
            else if (userInput.equals("7")) {
                save();
                System.out.println("Thank you for using our application :)");
                break;
            }
            //User input wrong command so repeat the menu choices
            else {
                System.out.println("Sorry but you have chosen an invalid option. Please choose again." +"\n");
                System.out.println("");
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
                    (toDo.getItem(i).getItemDueDateinString()) + " " + (toDo.getItem(i).getItemUrgency()));
        }
        writer.close();
    }
    //MODIFIES: this
    //EFFECTS:  create new item for every line in userData file and add them to list of items
    public void load() throws IOException, ParseException, TooManyThingsToDoException {
        List<String> lines = Files.readAllLines(Paths.get("userData"));

        for (String line : lines){
            ArrayList<String> partsOfLine = splitOnSpace(line);
            if (partsOfLine.size() <= 1)
                throw new IndexOutOfBoundsException();
            Date duedate = sdf.parse(partsOfLine.get(2));
            Item i = new NormalItem(partsOfLine.get(0), Boolean.parseBoolean(partsOfLine.get(1)), duedate, Boolean.parseBoolean(partsOfLine.get(3)));
            toDo.addNormalItem(i);
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

    //MODIFIES: this
    //EFFECTS:  add new item to the to do list
    public void addNewItemToList(String name, boolean status, Date duedate ,boolean urgency) throws TooManyThingsToDoException {
        Item i = new NormalItem(name, status, duedate, urgency);
        toDo.addNormalItem(i);
        i.addToToDoList(toDo);
    }

}
