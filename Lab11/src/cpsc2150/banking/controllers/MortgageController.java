package cpsc2150.banking.controllers;

import cpsc2150.banking.views.IMortgageView;
import cpsc2150.banking.models.*;

public class MortgageController implements IMortgageController{
    private IMortgageView view;

    public MortgageController(IMortgageView v) {
        view = v;
    }

    public void submitApplication() {
        boolean newCustomer = true;
        boolean newMortgage = true;

        //Getting customer info
        while(newCustomer) {
            String name = view.getName();

            double income = view.getYearlyIncome();
            while (income <= 0) {
                view.printToUser("Income must be greater than 0.");
                income = view.getYearlyIncome();
            }

            double debt = view.getMonthlyDebt();
            while (debt < 0) {
                view.printToUser("Debt must be greater than or equal to 0.");
                debt = view.getMonthlyDebt();
            }

            int score = view.getCreditScore();
            while (score <= 0 || score >= 850) {
                view.printToUser("Credit score must be greater than 0 and less than 850");
                score = view.getCreditScore();
            }

            Customer person = new Customer(debt, income, score, name);

            //Getting mortgage info
            while(newMortgage) {

                double cost = view.getHouseCost();
                while (cost <= 0) {
                    view.printToUser("Cost must be greater than 0.");
                    cost = view.getHouseCost();
                }

                double payment = view.getDownPayment();
                while (payment < 0 || payment > cost) {
                    view.printToUser("Down payment must be greater than 0 and less than the cost of the house.");
                    payment = view.getDownPayment();
                }

                int years = view.getYears();
                while (years < 0) {
                    view.printToUser("Years must be greater than 0.");
                    years = view.getYears();
                }

                Mortgage mort = new Mortgage(cost, payment, years, person);

                //Displaying info
                view.printToUser(person.toString());
                if(mort.loanApproved()){
                    view.printToUser(mort.toString());
                }

                newMortgage = view.getAnotherMortgage();
                if(!newMortgage){
                    newCustomer = view.getAnotherCustomer();
                }
            }
        }
    }
}
