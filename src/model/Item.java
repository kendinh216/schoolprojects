package model;

import Exceptions.TooManyThingsToDoException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public abstract class Item {
    protected String name;
    protected boolean status;
    protected Date dueDate;
    protected SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    protected boolean urgency;
    private ListOfItem toDoList;

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

    public void addToToDoList(ListOfItem loi) throws TooManyThingsToDoException {
        if (toDoList != null && !toDoList.equals(loi))
            loi.removeItem(this);
        if (loi == null)
            return;
        if (!loi.equals(toDoList)){
            toDoList = loi;
            if (urgency)
                loi.addUrgenItem(this);
            else
                loi.addNormalItem(this);
        }
    }

    public void removeFromToDoList(ListOfItem loi){
        if (loi == null)
            return;
        if (loi.equals(toDoList)){
            ListOfItem oldLoi = toDoList;
            toDoList = null;
            oldLoi.removeItem(this);
        }
    }

    public ListOfItem getToDoList(){
        return toDoList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return status == item.status &&
                urgency == item.urgency &&
                Objects.equals(name, item.name) &&
                Objects.equals(dueDate, item.dueDate) &&
                Objects.equals(sdf, item.sdf);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, status, dueDate, sdf, urgency);
    }
}
