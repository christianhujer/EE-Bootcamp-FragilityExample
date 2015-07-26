// Problem: Following is the current implementation of the accounting system.
// The new requirement is to add a new meal type called 'Lunch'
// Your job is to apply OCP to the printReport functional, so that in future
// if you add new expense types, we should be able to do it by adding new code rather than 
// modifing existing one.  
// Also discover whether it is violating any other principle. 

package fragility;

import java.util.Date;
import java.util.List;

class Expense {
    public enum Type {
        DINNER("Dinner", 5000) {
            public boolean isMeal() {
                return true;
            }
        },
        BREAKFAST("Breakfast", 1000) {
            public boolean isMeal() {
                return true;
            }
        },
        CAR_RENTAL("Car Rental", Integer.MAX_VALUE) {
            public boolean isMeal() {
                return false;
            }
        };

        private final String expenseName;
        private final int expenseLimit;

        Type(String expenseName, int expenseLimit) {
            this.expenseName = expenseName;
            this.expenseLimit = expenseLimit;
        }
        public abstract boolean isMeal();
    }
    public Type type;

    public int amount;
    public Expense(Type type, int amount) {
        this.type = type;
        this.amount = amount;
    }

    public String getName() {
        return type.expenseName;
    }
    public boolean isMeal() {
        return type.isMeal();
    }
    public boolean isMealOverExpense() {
        return amount > type.expenseLimit;
    }

}

public class FragilityExample {

    public void printReport(List<Expense> expenses) {
        printHeader();
        printExpenses(expenses);
        printFooter(expenses);
    }

    private static void printFooter(List<Expense> expenses) {
        System.out.println("Meal expenses : " + getMealExpenses(expenses));
        System.out.println("Total expenses : " + getTotal(expenses));
    }

    private static void printExpenses(List<Expense> expenses) {
        for (Expense expense : expenses)
            System.out.println(formatExpense(expense));
    }

    private static String formatExpense(Expense expense) {
        String mealOverExpensesMarker = expense.isMealOverExpense() ? "X" : " ";
        return String.format("%s\t%s\t%s", expense.getName(), expense.amount, mealOverExpensesMarker);
    }

    private static void printHeader() {
        System.out.println("Expenses " + new Date() + "\n");
    }

    private static int getTotal(List<Expense> expenses) {
        int total = 0;
        for (Expense expense : expenses)
            total += expense.amount;
        return total;
    }

    private static int getMealExpenses(List<Expense> expenses) {
        int mealExpenses = 0;
        for (Expense expense : expenses)
            if (expense.isMeal())
                mealExpenses += expense.amount;
        return mealExpenses;
    }

}