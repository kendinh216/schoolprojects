package observers;

import model.Item;

public class MessagePrinter implements Observer {

    @Override
    public void update(Item i){
        System.out.println("You have successfully added task \"" + i.getItemName() + "\" to your ToDo list");
    }
}
