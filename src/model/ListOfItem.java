package model;

import Exceptions.TooManyThingsToDoException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListOfItem {
    private List<Item> listOfItems = new ArrayList<>();

    //MODIFIES: this
    //EFFECTS:  creates new normal item and add it to the list of items
    public void addNormalItem(Item i) throws TooManyThingsToDoException {
        checkIfNumberOfUndoneTaskGreaterOrEqualToFive();
        addItemToListOfItemsAndUpdateRelationshipWithItem(i);

    }

    private void checkIfNumberOfUndoneTaskGreaterOrEqualToFive() throws TooManyThingsToDoException {
        int notDoneTask = 0;
        for (int a = 0; a < listOfItems.size(); a++) {
            if (listOfItems.get(a).getItemStatus() == false) {
                notDoneTask += 1;
            }
        }
        if (notDoneTask >= 5)
            throw new TooManyThingsToDoException();
    }


    private void addItemToListOfItemsAndUpdateRelationshipWithItem(Item i) throws TooManyThingsToDoException {
        if (i == null)
            return;
        if (!listOfItems.contains(i)) {
            listOfItems.add(i);
            i.addToToDoList(this);
        }
    }
    //MODIFIES: this
    //EFFECTS:  creates new urgent item and add it to the list of items
    public void addUrgenItem(Item i) throws TooManyThingsToDoException {
        checkIfNumberOfUndoneTaskGreaterOrEqualToFive();
        addItemToListOfItemsAndUpdateRelationshipWithItem(i);

    }


    //MODIFIES: this
    //EFFECTS:  remove the last item in list of to do
    public void crossOffLastItem(){
        Item removed = listOfItems.get(listOfItems.size() -1);
        removed.setItemStatus(true);
        printRemovedItemName(removed);
    }

    private void printRemovedItemName(Item removed) {
        System.out.println("The last item in the list: " + "(" + removed.getItemName() + ")" + " has been crossed off.");
    }

    //REQUIRES: index not out of bound of list
    //MODIFIES: this
    //EFFECTS:  remove an item at a specific index in list of to do
    public void crossOffIndexItem(int index){
        Item removed = listOfItems.get(index - 1);
        if (removed != null){
            removed.setItemStatus(true);
            printRemovedItemName(removed);
        }
        else {
            System.out.println("There is no item at the index [" + index + "] to be crossed off.");
        }
    }

    //EFFECTS: print out all tasks with their name, status, due date, and urgency
    public void showAllTasksNameAndStatus() {
        Date todayDate = new Date();
        for (int i = 0; i < listOfItems.size(); i++) {
                System.out.println((i + 1) + ". " + listOfItems.get(i).getItemName() + " - " + listOfItems.get(i).getItemStatusInString() + " - Due Date: "
                        + listOfItems.get(i).getItemDueDateinString() + printDueDate(todayDate, listOfItems.get(i).dueDate));
        }
    }

    //EFFECTS: print out "NOT DUE" if the task's due date is before today's date and vice versa
    private String printDueDate (Date todayDate, Date itemDate){
        if (todayDate.before(itemDate)){
            return " - NOT DUE";
        }
        else {
            return " - PASSED DUE-DATE";
        }
    }

    public int getSize(){
        return listOfItems.size();
    }

    public Item getItem(int index){
        return listOfItems.get(index);
    }

    //REQUIRES: index i is not out of bound
    //MODIFIES: this
    //EFFECTS:  remove item in list at index i
    public void removeItem(Item i){
        if (i == null)
            return;
        if (listOfItems.contains(i))
            listOfItems.remove(i);
            i.removeFromToDoList(this);
    }

    public void removeAllItem () {
        for (int i = 0; i < listOfItems.size();i=i ) {
            Item a = listOfItems.get(i);
            listOfItems.remove(a);
            a.removeFromToDoList(this);

        }
    }


}
