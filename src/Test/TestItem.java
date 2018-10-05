package Test;

import org.junit.jupiter.api.Test;
import model.Item;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItem {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String duedate = "02-10-2019";
    @Test
    public void TestConstructItem() throws ParseException {
        Item newItem = new Item("UBC", false, sdf.parse(duedate));
        assertEquals(newItem.getItemName(), "UBC");
        assertEquals(newItem.getItemStatusInString(), "NOT DONE");
    }
}
