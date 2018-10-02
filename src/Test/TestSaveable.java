package Test;

import model.ToDoList;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestSaveable {
    public static void main (String[] args) throws IOException, ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        String duedate = "2017-05-06";

        ToDoList newlist = new ToDoList();
        newlist.addNewItemToList("eat-food", false, sdf.parse(duedate));
        newlist.addNewItemToList("play-outside", true, sdf.parse(duedate));
        newlist.save();
        newlist.getToDoList().showAllListItemsNameAndStatus();
    }
}

