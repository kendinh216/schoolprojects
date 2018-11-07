package model;

import java.util.Date;

public class UrgenItem extends Item{

    //MODIFIES: this
    //EFFECTS:  construct an item
    public UrgenItem(String name,boolean status, Date dueDate, boolean urgency){
        super(name, status, dueDate, urgency);
    }

    @Override
    public String getItemName(){
        return this.name + "<URGENT>";
    }

}
