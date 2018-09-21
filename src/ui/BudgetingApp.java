package ui;

import starter.LogEntry;

import java.util.ArrayList;

import java.util.Scanner;

public class BudgetingApp {
    ArrayList<LogEntry> transactionLog = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    int budget = 0;
    public BudgetingApp(){
        String transaction = "";
        System.out.println("Please enter your budget($): ");
        budget = scanner.nextInt();
        scanner.nextLine();
        while(true){
            LogEntry opEntry = new LogEntry();
            System.out.println("Please select an options:");
            System.out.println("[1] Add an amount of money");
            System.out.println("[2] Spend an amount of money");
            System.out.println("[3] Show all transactions");
            transaction = scanner.nextLine();
            System.out.println("You have selected " + "["+  transaction + "]");

            if (transaction.equals("1")){
                opEntry.setOperation(transaction);
                budget = plus(opEntry);
                opEntry.setBudget(budget);
            }
            else if (transaction.equals("2")){
                opEntry.setOperation(transaction);
                budget = minus(opEntry);
                opEntry.setBudget(budget);

            }
            else if (transaction.equals("3")){
                break;
            }
            System.out.println("the current budget is $" + budget);
            transactionLog.add(opEntry);


        }
        System.out.println("All transactions are: " + transactionLog);


    }
    private int plus (LogEntry logEntry) {
        System.out.println("Please enter the amount of money you want to add in: ");
        int addAmount = scanner.nextInt();
        scanner.nextLine();
        logEntry.addOperand(addAmount);
        return budget + addAmount;
    }

    private int minus (LogEntry logEntry){
        System.out.println("Please enter the amount of money you are spending ");
        int minusAmount = scanner.nextInt();
        scanner.nextLine();
        logEntry.addOperand(minusAmount);
        return budget - minusAmount;
    }

    public static void main(String[] args) {
        new BudgetingApp();
    }




}
