package Test;

import Exceptions.TooManyThingsToDoException;
import model.Item;
import model.ListOfItem;
import model.NormalItem;
import model.UrgenItem;
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
        Item i = new NormalItem("eat food", false,sdf.parse(duedate), true);
        loi.addNormalItem(i);
        assertEquals(loi.getSize(), 1);
    }

    @Test
    void testAddManyItem() throws ParseException, TooManyThingsToDoException {
        Item i = new NormalItem("eat food", false,sdf.parse(duedate), true);
        Item x = new NormalItem("eat ice cream", false,sdf.parse(duedate), true);
        Item k = new NormalItem("eat jello", false,sdf.parse(duedate), true);
        loi.addNormalItem(i);
        loi.addNormalItem(x);
        loi.addNormalItem(k);
        assertEquals(loi.getSize(), 3);
    }

    @Test
    void testCrossOffLastItemIfThereIsOneItem() throws ParseException, TooManyThingsToDoException {
        Item i = new NormalItem("eat food", false,sdf.parse(duedate), true);
        loi.addNormalItem(i);
        loi.crossOffLastItem();
        assertEquals(loi.getSize(), 1);
    }

    @Test
    void testCrossOffLastItemIfThereIsMoreThanOneItem() throws ParseException, TooManyThingsToDoException {
        Item i = new NormalItem("go sunbathing", false,sdf.parse(duedate),true);
        Item x = new NormalItem("play games", false,sdf.parse(duedate),true);
        Item k = new NormalItem("do homework", false,sdf.parse(duedate),true);
        Item j = new NormalItem("workout", false,sdf.parse(duedate),true);
        loi.addNormalItem(k);
        loi.addNormalItem(i);
        loi.addNormalItem(x);
        loi.addNormalItem(j);
        loi.crossOffLastItem();
        assertEquals(loi.getSize(), 4);
        assertTrue(loi.getItem(3).getItemStatus());
    }

    @Test
    void testCrossOffIndexItem() throws ParseException, TooManyThingsToDoException {
        Item i = new NormalItem("go sunbathing", false,sdf.parse(duedate),true);
        Item x = new NormalItem("play games", false,sdf.parse(duedate),true);
        Item k = new NormalItem("do homework", false,sdf.parse(duedate),true);
        Item j = new NormalItem("workout", false,sdf.parse(duedate),true);
        loi.addNormalItem(k);
        loi.addNormalItem(i);
        loi.addNormalItem(x);
        loi.addNormalItem(j);
        loi.crossOffIndexItem(1);
        assertEquals(loi.getSize(),4);
        Item a = loi.getItem(0);
        boolean B = a.getItemStatus();
        assertTrue(B);
    }

    @Test
    void testRemoveIndexItem()throws ParseException, TooManyThingsToDoException{
        Item i = new NormalItem("go sunbathing", false,sdf.parse(duedate),true);
        Item x = new NormalItem("play games", false,sdf.parse(duedate),true);
        Item k = new NormalItem("do homework", false,sdf.parse(duedate),true);
        Item j = new NormalItem("workout", false,sdf.parse(duedate),true);
        loi.addNormalItem(k);
        loi.addNormalItem(i);
        loi.addNormalItem(x);
        loi.addNormalItem(j);
        Item item = loi.getItem(1);
        loi.removeItem(item);
        assertEquals(3, loi.getSize());
        assertEquals(loi.getItem(0).getItemName(), "do homework");
    }

    @Test
    void testRemoveAllItem() throws ParseException, TooManyThingsToDoException{
        Item i = new NormalItem("go sunbathing", false,sdf.parse(duedate),true);
        Item x = new NormalItem("play games", false,sdf.parse(duedate),true);
        Item k = new NormalItem("do homework", false,sdf.parse(duedate),true);
        Item j = new NormalItem("workout", false,sdf.parse(duedate),true);
        loi.addNormalItem(k);
        loi.addNormalItem(i);
        loi.addNormalItem(x);
        loi.addNormalItem(j);
        loi.removeAllItem();
        assertEquals(0,loi.getSize());
    }

    @Test
    void testAddNormalItemNotThrowException()throws ParseException, TooManyThingsToDoException{
        Item i = new NormalItem("go sunbathing", false,sdf.parse(duedate),false);
        Item x = new NormalItem("play games", false,sdf.parse(duedate),false);
        Item k = new NormalItem("do homework", false,sdf.parse(duedate),false);
        Item j = new NormalItem("workout", false,sdf.parse(duedate),false);
        loi.addNormalItem(k);
        loi.addNormalItem(i);
        loi.addNormalItem(x);
        try { loi.addNormalItem(j);}
        catch (TooManyThingsToDoException e){
            fail("Not suppose to throw exception");
        }
    }

    @Test
    void testAddNormalItemThrowException() throws ParseException, TooManyThingsToDoException{
        Item i = new NormalItem("go sunbathing", false,sdf.parse(duedate),false);
        Item x = new NormalItem("play games", false,sdf.parse(duedate),false);
        Item k = new NormalItem("do homework", false,sdf.parse(duedate),false);
        Item j = new NormalItem("workout", false,sdf.parse(duedate),false);
        Item b = new NormalItem("workout baby yeah", false,sdf.parse(duedate),false);
        loi.addNormalItem(k);
        loi.addNormalItem(i);
        loi.addNormalItem(x);
        loi.addNormalItem(j);
        try {loi.addNormalItem(b);
            fail("Not suppose to reach this stage");}
            catch (TooManyThingsToDoException e){
                System.out.println("Good");
            }
    }

    @Test
    void testAddUrgenItemNotThrowException()throws ParseException, TooManyThingsToDoException{
        Item i = new UrgenItem("go sunbathing", false,sdf.parse(duedate),true);
        Item x = new UrgenItem("play games", false,sdf.parse(duedate),true);
        Item k = new UrgenItem("do homework", false,sdf.parse(duedate),true);
        Item j = new UrgenItem("workout", false,sdf.parse(duedate),true);
        loi.addUrgenItem(k);
        loi.addUrgenItem(i);
        loi.addUrgenItem(x);
        try { loi.addUrgenItem(j);}
        catch (TooManyThingsToDoException e){
            fail("Not suppose to throw exception");
        }
    }

    @Test
    void testAddUrgenItemThrowException() throws ParseException, TooManyThingsToDoException{
        Item i = new UrgenItem("go sunbathing", false,sdf.parse(duedate),false);
        Item x = new UrgenItem("play games", false,sdf.parse(duedate),false);
        Item k = new UrgenItem("do homework", false,sdf.parse(duedate),false);
        Item j = new UrgenItem("workout", false,sdf.parse(duedate),false);
        Item b = new UrgenItem("workout baby yeah", false,sdf.parse(duedate),false);
        loi.addUrgenItem(k);
        loi.addUrgenItem(i);
        loi.addUrgenItem(x);
        loi.addUrgenItem(j);
        try {loi.addUrgenItem(b);
            fail("Not suppose to reach this stage");}
        catch (TooManyThingsToDoException e){
            System.out.println("Good");
        }
    }

    @Test
    void testAddNormalItemRelationshipWithItem() throws ParseException, TooManyThingsToDoException {
        Item i = new NormalItem("go sunbathing", false,sdf.parse(duedate),false);
        loi.addNormalItem(i);
        assertEquals(i.getToDoList().getSize(),1);
    }

    @Test
    void testAddUrgenItemRelationshipWithItem() throws ParseException, TooManyThingsToDoException{
        Item i = new NormalItem("go sunbathing", false,sdf.parse(duedate),true);
        loi.addNormalItem(i);
        assertEquals(i.getToDoList().getSize(),1);

    }
}
