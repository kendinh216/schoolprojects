package Test;

import Exceptions.TooManyThingsToDoException;
import model.Loadable;
import model.ToDoList;

import java.io.IOException;
import java.text.ParseException;

public class TestLoadable {
    public TestLoadable() throws IOException, ParseException, TooManyThingsToDoException {
        ToDoList newlist = new ToDoList();
        testLoad(newlist);
        newlist.getToDoList().showAllTasksNameAndStatus();
    }
    public void testLoad (Loadable ld) throws IOException, ParseException, TooManyThingsToDoException {
        ld.load();
    }


    public static void main(String[] args) throws IOException, ParseException, TooManyThingsToDoException {
        TestLoadable newL = new TestLoadable();
    }

}
