package model;

import java.text.SimpleDateFormat;
import java.util.Date;

public abstract class Item {
    protected String name;
    protected boolean status;
    protected Date dueDate;
    protected SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    protected boolean urgency;

    //MODIFIES: this
    //EFFECTS:  construct a new item
    public Item(String name,boolean status, Date dueDate, boolean urgency) {
        this.status = status;
        this.dueDate = dueDate;
        this.name = name;
        this.urgency = urgency;
    }
    public void setItemUrgency(boolean urgency){
        this.urgency = urgency;
    }

    public void setItemStatus(boolean status){
        this.status = status;
    }

    public abstract String getItemName();

    public Date getItemDueDate(){
        return this.dueDate;
    }

    public String getItemDueDateinString(){
        String s = sdf.format(this.dueDate);
        return s;
    }

    public String getItemStatusInString(){
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

    public boolean getItemUrgency(){
        return this.urgency;
    }
}
