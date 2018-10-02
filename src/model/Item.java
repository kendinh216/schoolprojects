
package model;

public class Item {
    private final String name;
    private boolean status;


    //MODIFIES: this
    //EFFECTS:  construct an item
    public Item(String name, boolean status){
        this.name = name;
        this.status = status;
    }
    //true means Done
    //false means Not Done
    public void setItemStatus (boolean status){
        this.status = status;
    }

    public String getItemName (){
        return this.name;
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
