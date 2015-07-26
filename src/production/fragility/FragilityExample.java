// Problem: Following is the current implementation of the accounting system.
// The new requirement is to add a new meal type called 'Lunch'
// Your job is to apply OCP to the printReport functional, so that in future
// if you add new expense types, we should be able to do it by adding new code rather than 
// modifing existing one.  
// Also discover whether it is violating any other principle. 

package fragility;

import java.util.Date;
import java.util.List;

import static fragility.Expense.Type.BREAKFAST;
import static fragility.Expense.Type.DINNER;

class Expense {
    public enum Type {DINNER, BREAKFAST, CAR_RENTAL}

    ;
    public Type type;
    public int amount;

    public Expense(Type type, int amount) {
        this.type = type;
        this.amount = amount;
    }
}

public class FragilityExample {

    public void printReport(List<Expense> expenses) {
        int total = 0;
        int mealExpenses = 0;

        System.out.println("Expenses " + new Date() + "\n");

        for (Expense expense : expenses) {
            if (expense.type == DINNER || expense.type == BREAKFAST) {
                mealExpenses += expense.amount;
            }

            String expenseName = "";
            switch (expense.type) {
                case DINNER:
                    expenseName = "Dinner";
                    break;
                case BREAKFAST:
                    expenseName = "Breakfast";
                    break;
                case CAR_RENTAL:
                    expenseName = "Car Rental";
                    break;
            }

            String mealOverexpensesMarker =
                    ((expense.type == DINNER && expense.amount > 5000) ||
                            (expense.type == BREAKFAST && expense.amount > 1000)) ? "X" : " ";

            System.out.println(expenseName + "\t" + expense + "\t" + "mealOverExpensesMarker");
            total += expense.amount;
        }

        System.out.println("Meal expenses : " + mealExpenses);
        System.out.println("Total expenses : " + total);
    }
}