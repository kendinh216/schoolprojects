package Test;

import model.Item;
import org.junit.jupiter.api.Test;
import model.ToDoList;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class TestToDoList {
    ToDoList t = new ToDoList();
    ArrayList<Item> toDoItems = new ArrayList<>();

    @Test
    public void testAddOneItemToList (){
        t.AddNewItemToList("do homework", toDoItems);
        assertEquals(toDoItems.size(), 1);
        assertEquals(toDoItems.get(0).getItemName(), "do homework");
    }
    
    @Test
    public void testAddManyItemToList(){
        t.AddNewItemToList("eat", toDoItems);
        t.AddNewItemToList("play", toDoItems);
        t.AddNewItemToList("workout",toDoItems);
        assertEquals(toDoItems.size(), 3);
    }

}
