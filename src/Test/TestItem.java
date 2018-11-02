package Test;

import Exceptions.TooManyThingsToDoException;
import model.Item;
import model.ListOfItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import model.NormalItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItem {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String duedate = "02/10/2019";


    @Test
    public void testConstructor() throws ParseException {
        Item newNormalItem = new NormalItem("UBC", false, sdf.parse(duedate), false);
        assertEquals(newNormalItem.getItemName(), "UBC");
        assertEquals(newNormalItem.getItemStatusInString(), "NOT DONE");
    }

    @Test
    public void testAddToToDoList() throws ParseException, TooManyThingsToDoException {
        ListOfItem loi = new ListOfItem();
        Item i = new NormalItem("UBC", false, sdf.parse(duedate), false);
        i.addToToDoList(loi);
        assertEquals(loi.getSize(),1);
    }

    @Test
    public void testRemoveFromToDoList() throws ParseException, TooManyThingsToDoException{
        ListOfItem loi = new ListOfItem();
        Item i = new NormalItem("UBC", false, sdf.parse(duedate), false);
        i.addToToDoList(loi);
        i.removeFromToDoList(loi);
        assertEquals(loi.getSize(),0);
    }


}
