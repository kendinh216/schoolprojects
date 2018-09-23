package model;

import java.util.ArrayList;
import java.util.Scanner;

//Reference from B4LecLab-LittleCalculatorStarter - LoggingCalculator.java



public class BudgetingApp {
    ArrayList<TransactionEntry> transactionLog = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);
    int budget = 0;
    public BudgetingApp(){
        String transaction = "";
        System.out.println("Please enter your budget($): ");
        budget = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Your current budget is: $" + budget);
        System.out.println("");

        while(true){
            TransactionEntry opEntry = new TransactionEntry();

            System.out.println("Please select an options:");
            System.out.println("[1] Add an amount of money");
            System.out.println("[2] Spend an amount of money");
            System.out.println("[3] Show all transactions");
            transaction = scanner.nextLine();
            System.out.println("");
            if (transaction.equals("1")){
                System.out.println("You have selected: " + "Add an amount of money of money");}
            else if (transaction.equals("2")){
                System.out.println("You have selected: " + "Spend an amount of money");}
            else{
                System.out.println("You have selected: " + "Show all transactions");}


            if (transaction.equals("1")){
                opEntry.setUserChosenOperation(transaction);
                budget = add(opEntry);
                opEntry.setBudget(budget);
            }
            else if (transaction.equals("2")){
                opEntry.setUserChosenOperation(transaction);
                budget = spend(opEntry);
                opEntry.setBudget(budget);

            }
            else if (transaction.equals("3")){
                break;
            }
            System.out.println("The current budget is $" + budget);
            System.out.println("");
            transactionLog.add(opEntry);


        }
        System.out.println("All transactions are: " + transactionLog);


    }
    private int add(TransactionEntry transactionEntry) {
        System.out.println("Please enter the amount of money you want to add in: ");
        int addAmount = scanner.nextInt();
        scanner.nextLine();
        transactionEntry.amountOfMoney(addAmount);
        return budget + addAmount;
    }

    private int spend(TransactionEntry transactionEntry){
        System.out.println("Please enter the amount of money you are spending: ");
        int minusAmount = scanner.nextInt();
        scanner.nextLine();
        transactionEntry.amountOfMoney(minusAmount);
        return budget - minusAmount;
    }

}
