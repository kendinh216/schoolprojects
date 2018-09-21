package starter;

import java.util.ArrayList;

//Reference from B4LecLab-LittleCalculatorStarter - LogEntry.java

public class LogEntry {
    String transaction = "";
    ArrayList<Integer> transactions = new ArrayList<>();
    int budget = 0;

    public void setOperation(String operation) { this.transaction = operation;}

    public void addOperand(Integer operand) {
        transactions.add(operand);
    }

    public void setBudget (Integer budget) { this.budget = budget;}

    public String toString(){
        if (transaction.equals("1")) {
            return "Added $" +  transactions + " budget is $" + budget;
        }
        else
            return "Spent $" + transactions + " budget is $" + budget;

    }

//
//    public String toString() {
//        return transaction + " " + transactions + " budget is $" + budget;
//    }


}
