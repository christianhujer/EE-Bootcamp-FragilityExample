package fragility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class FragilityExampleTest {

    private ByteArrayOutputStream out;
    private PrintStream oldOut;

    @Before
    public void redirectStdout() throws Exception {
        out = new ByteArrayOutputStream();
        oldOut = System.out;
        System.setOut(new PrintStream(out));
    }

    @After
    public void restoreStdout() {
        System.setOut(oldOut);
    }

    @Test
    public void testPrintReport() {
        final String expected = //"Expenses Sun Jul 26 11:57:22 IST 2015\n" +
                "\n" +
                "Dinner\t5001\tmealOverExpensesMarker\n" +
                "Dinner\t5000\tmealOverExpensesMarker\n" +
                "Breakfast\t1000\tmealOverExpensesMarker\n" +
                "Breakfast\t1001\tmealOverExpensesMarker\n" +
                "Car Rental\t4000\tmealOverExpensesMarker\n" +
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
    }
}
