package starter;

import java.util.ArrayList;

public class LogEntry {
    String transaction = "";
    ArrayList<Integer> transactions = new ArrayList<>();
    int budget = 0;

    public void setOperation(String operation) { this.transaction = operation;}

    public void addOperand(Integer operand) {
        transactions.add(operand);
    }

    public void setBudget (Integer budget) { this.budget = budget;}

    public String toString() {
        return transaction + " " + transactions + " budget is " + budget;
    }


}
