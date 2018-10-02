
package model;
import java.util.Date;

public class Item {
    private final String name;
    private boolean status;
    private Date duedate;



    //MODIFIES: this
    //EFFECTS:  construct an item
    public Item(String name, boolean status, Date duedate){
        this.name = name;
        this.status = status;
        this.duedate = duedate;
    }
    //true means Done
    //false means Not Done
    public void setItemStatus (boolean status){
        this.status = status;
    }

    public String getItemName (){
        return this.name;
    }

    public Date getItemDueDate(){
        return this.duedate;
    }

    public String getItemStatusInString() {
        if (this.status){
            return "DONE";
        }
        else{
            return "NOT DONE";
        }
    }

    public boolean getItemStatus(){
        return this.status;
    }
}
