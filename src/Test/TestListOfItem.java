package Test;
import model.Item;
import model.ListOfItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class TestListOfItem {
    private ListOfItem loi;

    @BeforeEach
    void runBefore() {
        loi = new ListOfItem();
    }

    @Test
    void testAddOneItem() {
        loi.addItem("eat food", false);
        assertEquals(loi.getSize(), 1);
    }

    @Test
    void testAddManyItem() {
        loi.addItem("play games", false);
        loi.addItem("do homework", false);
        loi.addItem("workout", false);
        assertEquals(loi.getSize(), 3);
    }

    @Test
    void testRemoveLastItemIfThereIsOneItem() {
        loi.addItem("go outdoor", false);
        loi.removeLastItem();
        assertEquals(loi.getSize(), 1);
    }

    @Test
    void testRemoveLastItemIfThereIsMoreThanOneItem() {
        loi.addItem("go sunbathing", false);
        loi.addItem("play games", false);
        loi.addItem("do homework", false);
        loi.addItem("workout", false);
        loi.removeLastItem();
        assertEquals(loi.getSize(), 4);
        assertTrue(loi.getItem(3).getItemStatus());
    }

    @Test
    void testRemoveIndexItem() {
        loi.addItem("go sunbathing", true);
        loi.addItem("play games", false);
        loi.addItem("do homework", true);
        loi.addItem("workout", true);
        loi.removeIndexItem(1);
        assertEquals(loi.getSize(),4);
        Item i = loi.getItem(0);
        boolean B = i.getItemStatus();
        assertTrue(B);
    }
}
