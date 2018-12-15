package Test;

import model.Item;
import org.junit.jupiter.api.Test;
import model.NormalItem;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItem {
    SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
    String duedate = "02/10/2019";
    @Test
    public void TestConstructItem() throws ParseException {
        Item newNormalItem = new NormalItem("UBC", false, sdf.parse(duedate), false);
        assertEquals(newNormalItem.getItemName(), "UBC");
        assertEquals(newNormalItem.getItemStatusInString(), "NOT YET DONE");
    }

    
}
