package fragility;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FragilityExampleTest {

    @Test
    public void testPrintReport() {
        final ByteArrayOutputStream out = new ByteArrayOutputStream();
        final PrintStream newOut = new PrintStream(out);
        final PrintStream oldOut = System.out;
        try {
            System.setOut(newOut);

            final String expected = //"Expenses Sun Jul 26 11:57:22 IST 2015\n" +
                    "\n" +
                    "Dinner\tfragility.Expense@77556fd\tmealOverExpensesMarker\n" +
                    "Dinner\tfragility.Expense@368239c8\tmealOverExpensesMarker\n" +
                    "Breakfast\tfragility.Expense@9e89d68\tmealOverExpensesMarker\n" +
                    "Breakfast\tfragility.Expense@3b192d32\tmealOverExpensesMarker\n" +
                    "Car Rental\tfragility.Expense@16f65612\tmealOverExpensesMarker\n" +
                    "Meal expenses : 12002\n" +
                    "Total expenses : 16002\n";

            final List<Expense> expenses = new ArrayList<>();
            expenses.add(new Expense(Expense.Type.DINNER, 5001));
            expenses.add(new Expense(Expense.Type.DINNER, 5000));
            expenses.add(new Expense(Expense.Type.BREAKFAST, 1000));
            expenses.add(new Expense(Expense.Type.BREAKFAST, 1001));
            expenses.add(new Expense(Expense.Type.CAR_RENTAL, 4000));
            new FragilityExample().printReport(expenses);

            String actual = out.toString();
            actual = actual.substring(actual.indexOf('\n') + 1);
            assertEquals(expected, actual);
        } finally {
            System.setOut(oldOut);
        }
    }
}
