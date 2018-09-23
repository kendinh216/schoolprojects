package model;

import java.util.ArrayList;

//Reference from B4LecLab-LittleCalculatorStarter - TransactionEntry.java


public class TransactionEntry {
    String selectedTransaction = "";
    ArrayList<Integer> transactions = new ArrayList<>();
    int budget = 0;

    public void setUserChosenOperation(String operation) { this.selectedTransaction = operation;}

    public void amountOfMoney(Integer amount) {
        transactions.add(amount);
    }

    public void setBudget (Integer budget) { this.budget = budget;}

    public String toString(){
        if (selectedTransaction.equals("1")) {
            return "Added $" +  transactions + "- budget is $" + budget;
        }
        else
            return "Spent $" + transactions + "- budget is $" + budget;

    }


}
