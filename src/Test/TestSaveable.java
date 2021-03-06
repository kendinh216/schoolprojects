package Test;

import Exceptions.TooManyThingsToDoException;
import model.ToDoList;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TestSaveable {
    public static void main (String[] args) throws IOException, ParseException, TooManyThingsToDoException {
        SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
        String duedate = "01/09/2019";

        ToDoList newlist = new ToDoList();
        newlist.addNewItemToList("eat-food", false, sdf.parse(duedate),true);
        newlist.addNewItemToList("play-outside", true, sdf.parse(duedate),true);
        newlist.save();
        newlist.getToDoList().showAllTasksNameAndStatus();
    }
}

