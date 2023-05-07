package cpsc2150.banking.views;

import java.util.*;
import cpsc2150.banking.controllers.IMortgageController;
import cpsc2150.banking.controllers.MortgageController;
import cpsc2150.banking.models.Mortgage;

public class MortgageView implements IMortgageView{

    Scanner scnr;
    IMortgageController controller;

    public MortgageView() {
        scnr = new Scanner(System.in);
    }

    public void setController(IMortgageController c) {
        controller = c;
    }

    public double getHouseCost() {
        double cost;
        System.out.println("How much does the house cost?");
        cost = scnr.nextDouble();
        scnr.nextLine();
        return cost;
    }

    public double getDownPayment() {
        double payment;
        System.out.println("How much is the down payment?");
        payment = scnr.nextDouble();
        scnr.nextLine();
        return payment;
    }

    public int getYears() {
        int years;
        System.out.println("How many years?");
        years = scnr.nextInt();
        scnr.nextLine();
        return years;
    }

    public double getMonthlyDebt() {
        double debt;
        System.out.println("How much are your monthly debt payments?");
        debt = scnr.nextDouble();
        scnr.nextLine();
        return debt;
    }

    public double getYearlyIncome() {
        double income;
        System.out.println("How much is your yearly income?");
        income = scnr.nextDouble();
        scnr.nextLine();
        return income;
    }

    public int getCreditScore() {
        int score;
        System.out.println("What is your credit score?");
        score = scnr.nextInt();
        scnr.nextLine();
        return score;
    }

    public String getName() {
        String name;
        System.out.println("What's your name?");
        name = scnr.nextLine();
        return name;
    }

    public void printToUser(String s) {
        System.out.println(s);
    }

    public void displayPayment(double p) {
        System.out.println(p);
    }

    public void displayRate(double r) {
        System.out.println(r);
    }

    public void displayApproved(boolean a) {
        System.out.println(a);
    }

    public boolean getAnotherMortgage() {
        char answer = 'x';
        String input;
        while (answer != 'Y' && answer != 'y' && answer != 'N' && answer != 'n') {
            System.out.println("Would you like to apply for another mortgage? Y/N");
            input = scnr.nextLine();
            answer = input.charAt(0);
            if (answer == 'Y' || answer == 'y') {
                return true;
            }
        }
        return false;
    }

    public boolean getAnotherCustomer() {
        char answer = 'x';
        String input;
        while (answer != 'Y' && answer != 'y' && answer != 'N' && answer != 'n') {
            System.out.println("Would you like to consider another customer? Y/N");
            input = scnr.nextLine();
            answer = input.charAt(0);
            if (answer == 'Y' || answer == 'y') {
                return true;
            }
        }
        return false;
    }
}
