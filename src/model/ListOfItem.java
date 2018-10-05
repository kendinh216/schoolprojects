package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ListOfItem {
    private List<Item> listOfItems = new ArrayList<>();

    //MODIFIES: this
    //EFFECTS:  creates new item and add it to the list of items
    public void addItem(String name, boolean status, Date duedate){
        Item newItem = new Item(name, status, duedate);
        this.listOfItems.add(newItem);
    }

    public void removeLastItem (){
        Item removed = listOfItems.get(listOfItems.size() -1);
        removed.setItemStatus(true);
        System.out.println("The last item in the list: " + "(" + removed.getItemName() + ")" + " is removed");
    }

    public void removeIndexItem(int index){
        Item removed = listOfItems.get(index - 1);
        if (removed != null){
            removed.setItemStatus(true);
            System.out.println("The item " + "(" + removed.getItemName() + ")" + " has been crossed off.");
        }
        else {
            System.out.println("There is no item at the index [" + index + "] to be crossed off.");
        }
    }

    public void showAllListItemsNameAndStatus() {
        Date todayDate = new Date();
        for (int i = 0; i < listOfItems.size(); i++) {
            if (todayDate.before(listOfItems.get(i).getItemDueDate())){
            System.out.println((i + 1) + ". " + listOfItems.get(i).getItemName() + " - " + listOfItems.get(i).getItemStatusInString() + " - Due Date: "
                    + listOfItems.get(i).getItemDueDateinString() + " - Not yet due" + "\n"); }
                    else{
                System.out.println((i + 1) + ". " + listOfItems.get(i).getItemName() + " - " + listOfItems.get(i).getItemStatusInString() + " - Due Date: "
                        + listOfItems.get(i).getItemDueDateinString() + " - Item has passed due date" + "\n"); }
            }
    }

    public int getSize(){
        return listOfItems.size();
    }

    public Item getItem(int index){
        return listOfItems.get(index);
    }

}
