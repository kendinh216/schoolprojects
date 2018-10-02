package Test;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestItem {

    @Test
    public void TestConstructItem(){
        Item newItem = new Item("UBC", false);
        assertEquals(newItem.getItemName(), "UBC");
        assertEquals(newItem.getItemStatusInString(), "NOT DONE");
    }
}
