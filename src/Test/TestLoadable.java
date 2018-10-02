package Test;

import model.Loadable;
import model.ToDoList;

import java.io.IOException;
import java.text.ParseException;

public class TestLoadable {
    public TestLoadable() throws IOException, ParseException {
        ToDoList newlist = new ToDoList();
        testLoad(newlist);
        newlist.getToDoList().showAllListItemsNameAndStatus();
    }
    public void testLoad (Loadable ld) throws IOException, ParseException {
        ld.load();
    }

    public static void main(String[] args) throws IOException, ParseException {
        TestLoadable newL = new TestLoadable();
    }

}
