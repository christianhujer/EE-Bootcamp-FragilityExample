package fragility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static fragility.Expense.Type.BREAKFAST;
import static fragility.Expense.Type.CAR_RENTAL;
import static fragility.Expense.Type.DINNER;
import static org.junit.Assert.assertEquals;

public class FragilityExampleTest {

    private ByteArrayOutputStream out;
    private PrintStream oldOut;
    private static final String EXPECTED = "\n" +
            "Dinner\t5001\tmealOverExpensesMarker\n" +
            "Dinner\t5000\tmealOverExpensesMarker\n" +
            "Breakfast\t1000\tmealOverExpensesMarker\n" +
            "Breakfast\t1001\tmealOverExpensesMarker\n" +
            "Car Rental\t4000\tmealOverExpensesMarker\n" +
            "Meal expenses : 12002\n" +
            "Total expenses : 16002\n";

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
        final List<Expense> expenses = Arrays.asList(
            new Expense(DINNER, 5001),
            new Expense(DINNER, 5000),
            new Expense(BREAKFAST, 1000),
            new Expense(BREAKFAST, 1001),
            new Expense(CAR_RENTAL, 4000));

        new FragilityExample().printReport(expenses);

        String actual = getStdoutWithoutFirstLine();
        assertEquals(EXPECTED, actual);
    }

    private String getStdoutWithoutFirstLine() {
        final String actual = out.toString();
        return actual.substring(actual.indexOf('\n') + 1);
    }
}
