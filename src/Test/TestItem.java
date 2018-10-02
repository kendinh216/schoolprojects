package Test;

import org.junit.jupiter.api.Test;
import model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItem {
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
    String duedate = "2017-05-06";
    @Test
    public void TestConstructItem() throws ParseException {
        Item newItem = new Item("UBC", false, sdf.parse(duedate));
        assertEquals(newItem.getItemName(), "UBC");
        assertEquals(newItem.getItemStatusInString(), "NOT DONE");
    }
}
