
package model;
import java.util.Date;


public class NormalItem extends Item {

    //MODIFIES: this
    //EFFECTS:  construct an item
    public NormalItem(String name,boolean status, Date dueDate, boolean urgency){
        super(name, status, dueDate, urgency);
    }
    @Override
    public String getItemName(){
        return this.name;
    }


}
