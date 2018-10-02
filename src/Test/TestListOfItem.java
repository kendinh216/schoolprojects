package Test;
import model.ListOfItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TestListOfItem {
    private ListOfItem loi;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
    String duedate = "2017-05-06";

    @BeforeEach
    void runBefore() {
        loi = new ListOfItem();
    }

    @Test
    void testAddOneItem() throws ParseException {
        loi.addItem("eat food", false,sdf.parse(duedate));
        assertEquals(loi.getSize(), 1);
    }

    @Test
    void testAddManyItem() throws ParseException {
        loi.addItem("play games", false,sdf.parse(duedate));
        loi.addItem("do homework", false,sdf.parse(duedate));
        loi.addItem("workout", false,sdf.parse(duedate));
        assertEquals(loi.getSize(), 3);
    }

    @Test
    void testRemoveLastItemIfThereIsOneItem() throws ParseException {
        loi.addItem("go outdoor", false,sdf.parse(duedate));
        loi.removeLastItem();
        assertEquals(loi.getSize(), 1);
    }

    @Test
    void testRemoveLastItemIfThereIsMoreThanOneItem() throws ParseException {
        loi.addItem("go sunbathing", false,sdf.parse(duedate));
        loi.addItem("play games", false,sdf.parse(duedate));
        loi.addItem("do homework", false,sdf.parse(duedate));
        loi.addItem("workout", false,sdf.parse(duedate));
        loi.removeLastItem();
        assertEquals(loi.getSize(), 4);
        assertTrue(loi.getItem(3).getItemStatus());
    }

    @Test
    void testRemoveIndexItem() throws ParseException {
        loi.addItem("go sunbathing", true,sdf.parse(duedate));
        loi.addItem("play games", false,sdf.parse(duedate));
        loi.addItem("do homework", true,sdf.parse(duedate));
        loi.addItem("workout", true,sdf.parse(duedate));
        loi.removeIndexItem(1);
        assertEquals(loi.getSize(),4);
        Item i = loi.getItem(0);
        boolean B = i.getItemStatus();
        assertTrue(B);
    }
}
