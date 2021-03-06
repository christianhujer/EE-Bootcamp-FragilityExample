package fragility;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static fragility.Expense.Type.BREAKFAST;
import static fragility.Expense.Type.CAR_RENTAL;
import static fragility.Expense.Type.DINNER;
import static org.junit.Assert.assertEquals;

public class ExpenseReportPrinterTest {

    private ByteArrayOutputStream out;
    private PrintStream oldOut;
    private static final String EXPECTED = "\n" +
            "Dinner\t5001\tX\n" +
            "Dinner\t5000\t \n" +
            "Breakfast\t1000\t \n" +
            "Breakfast\t1001\tX\n" +
            "Car Rental\t4000\t \n" +
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

        ExpenseReportPrinter.printReport(new Expenses(expenses));

        String actual = getStdoutWithoutFirstLine();
        assertEquals(EXPECTED, actual);
    }

    private String getStdoutWithoutFirstLine() {
        final String actual = out.toString();
        return actual.substring(actual.indexOf('\n') + 1);
    }
}
