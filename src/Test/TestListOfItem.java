package Test;

import model.Item;
import model.NormalItem;
import model.ListOfItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class TestListOfItem {
    private ListOfItem loi;
    private SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    private String duedate = "2017-05-06";

    @BeforeEach
    void runBefore() {
        loi = new ListOfItem();
    }

    @Test
    void testAddOneItem() throws ParseException {
        loi.addNormalItem("eat food", false,sdf.parse(duedate), true);
        assertEquals(loi.getSize(), 1);
    }

    @Test
    void testAddManyItem() throws ParseException {
        loi.addNormalItem("play games", false,sdf.parse(duedate),true);
        loi.addNormalItem("do homework", false,sdf.parse(duedate), true);
        loi.addNormalItem("workout", false,sdf.parse(duedate), true);
        assertEquals(loi.getSize(), 3);
    }

    @Test
    void testRemoveLastItemIfThereIsOneItem() throws ParseException {
        loi.addNormalItem("go outdoor", false,sdf.parse(duedate),true);
        loi.removeLastItem();
        assertEquals(loi.getSize(), 1);
    }

    @Test
    void testRemoveLastItemIfThereIsMoreThanOneItem() throws ParseException {
        loi.addNormalItem("go sunbathing", false,sdf.parse(duedate),true);
        loi.addNormalItem("play games", false,sdf.parse(duedate),true);
        loi.addNormalItem("do homework", false,sdf.parse(duedate),true);
        loi.addNormalItem("workout", false,sdf.parse(duedate),true);
        loi.removeLastItem();
        assertEquals(loi.getSize(), 4);
        assertTrue(loi.getItem(3).getItemStatus());
    }

    @Test
    void testRemoveIndexItem() throws ParseException {
        loi.addNormalItem("go sunbathing", true,sdf.parse(duedate),true);
        loi.addNormalItem("play games", false,sdf.parse(duedate),true);
        loi.addNormalItem("do homework", true,sdf.parse(duedate),true);
        loi.addNormalItem("workout", true,sdf.parse(duedate),true);
        loi.removeIndexItem(1);
        assertEquals(loi.getSize(),4);
        Item i = loi.getItem(0);
        boolean B = i.getItemStatus();
        assertTrue(B);
    }
}
