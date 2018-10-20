package Test;

import Exceptions.TooManyThingsToDoException;
import model.Item;
import model.ListOfItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TestListOfItem {
    private ListOfItem loi;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private String duedate = "02/02/2019";

    @BeforeEach
    void runBefore() {
        loi = new ListOfItem();
    }

    @Test
    void testAddOneItem() throws ParseException, TooManyThingsToDoException {
        loi.addNormalItem("eat food", false,sdf.parse(duedate), true);
        assertEquals(loi.getSize(), 1);
    }

    @Test
    void testAddManyItem() throws ParseException, TooManyThingsToDoException {
        loi.addNormalItem("play games", false,sdf.parse(duedate),true);
        loi.addNormalItem("do homework", false,sdf.parse(duedate), true);
        loi.addNormalItem("workout", false,sdf.parse(duedate), true);
        assertEquals(loi.getSize(), 3);
    }

    @Test
    void testCrossOffLastItemIfThereIsOneItem() throws ParseException, TooManyThingsToDoException {
        loi.addNormalItem("go outdoor", false,sdf.parse(duedate),true);
        loi.crossOffLastItem();
        assertEquals(loi.getSize(), 1);
    }

    @Test
    void testCrossOffLastItemIfThereIsMoreThanOneItem() throws ParseException, TooManyThingsToDoException {
        loi.addNormalItem("go sunbathing", false,sdf.parse(duedate),true);
        loi.addNormalItem("play games", false,sdf.parse(duedate),true);
        loi.addNormalItem("do homework", false,sdf.parse(duedate),true);
        loi.addNormalItem("workout", false,sdf.parse(duedate),true);
        loi.crossOffLastItem();
        assertEquals(loi.getSize(), 4);
        assertTrue(loi.getItem(3).getItemStatus());
    }

    @Test
    void testCrossOffIndexItem() throws ParseException, TooManyThingsToDoException {
        loi.addNormalItem("go sunbathing", true,sdf.parse(duedate),true);
        loi.addNormalItem("play games", false,sdf.parse(duedate),true);
        loi.addNormalItem("do homework", true,sdf.parse(duedate),true);
        loi.addNormalItem("workout", true,sdf.parse(duedate),true);
        loi.crossOffIndexItem(1);
        assertEquals(loi.getSize(),4);
        Item i = loi.getItem(0);
        boolean B = i.getItemStatus();
        assertTrue(B);
    }

    @Test
    void testRemoveIndexItem()throws ParseException, TooManyThingsToDoException{
        loi.addNormalItem("go sunbathing", true,sdf.parse(duedate),true);
        loi.addNormalItem("play games", false,sdf.parse(duedate),true);
        loi.addNormalItem("do homework", true,sdf.parse(duedate),true);
        loi.addNormalItem("workout", true,sdf.parse(duedate),true);
        loi.removeItem(1);
        assertEquals(3, loi.getSize());
        assertEquals(loi.getItem(0).getItemName(), "go sunbathing");
    }

    @Test
    void testRemoveAllItem() throws ParseException, TooManyThingsToDoException{
        loi.addNormalItem("go sunbathing", true,sdf.parse(duedate),true);
        loi.addNormalItem("play games", false,sdf.parse(duedate),true);
        loi.addNormalItem("do homework", true,sdf.parse(duedate),true);
        loi.addNormalItem("workout", true,sdf.parse(duedate),true);
        loi.removeAllItem();
        assertEquals(0,loi.getSize());
    }

    @Test
    void testAddNormalItemNotThrowException()throws ParseException, TooManyThingsToDoException{
        loi.addNormalItem("go sunbathing", true,sdf.parse(duedate),true);
        loi.addNormalItem("play games", false,sdf.parse(duedate),true);
        try { loi.addNormalItem("play games", false,sdf.parse(duedate),true);}
        catch (TooManyThingsToDoException e){
            fail("Not suppose to throw exception");
        }
    }

    @Test
    void testAddNormalItemThrowException() throws ParseException, TooManyThingsToDoException{
        loi.addNormalItem("go sunbathing", true,sdf.parse(duedate),true);
        loi.addNormalItem("play games", true,sdf.parse(duedate),true);
        loi.addNormalItem("do homework", true,sdf.parse(duedate),true);
        loi.addNormalItem("workout", true,sdf.parse(duedate),true);
        loi.addNormalItem("workout", true,sdf.parse(duedate),true);
        try {loi.addNormalItem("workout", true,sdf.parse(duedate),true);
            fail("Not suppose to reach this stage");}
            catch (TooManyThingsToDoException e){
                System.out.println("Good");
            }
    }
}
