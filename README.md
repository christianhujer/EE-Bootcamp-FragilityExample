# Equal Experts Pune Bootcamp 2015-07-26 Fragility Example

This code based on the Expense Report kata from Robert C. Martin as shown in [Clean Code Episode 10](http://cleancoders.com/episode/clean-code-episode-10/show).

The solution provided by Robert C. Martin is also available [here on Github](https://github.com/unclebob/Episode-10-ExpenseReport/tree/oo).

## Refactorings not yet done
The following refactorings haven't been done in the session due to lack of time and exhaustion - the discussions we had were much more important.

* Replace the polymorphism in the `enum Expense.Type` with pure data.
  The `isMeal()` methods do not differ in behavior, they only differ in data.
  This difference can be captured using different field values instead of different method implementations.
  During the session, we decided to use polymorphic behavior in order to demonstrate the capabilities of Java `enum`s.
* Replace `enum Expense.Type` with a data class which would be populated from a configuration.
* Split the source file into separate files.
* Split the ExpenseReportPrinter into two classes because it still violates the SRP.
  It currently assembles and prints the report, which is two different responsibilities.
  If these responsibilities are split, the unit test could be simplified:
  The unit test would no longer need to take care of redirecting `stdout`.

## Differences to the solution provided by Robert C. Martin

I frankly didn't like one aspect of the solution provided by Robert C. Martin, and that is that things which should be data that can be provided and modified by the user still were hard-coded in the source code.

Note that his different classes `BreakfastExpense`, `CarRentalExpense` and `DinnerExpense` truly follow the OCP.
There's a small difference between adding an enum constant and a new class.
A new class can be added without modifying an existing file.
This is not possible for an enum constant.
From the perspective of the OCP, the solution provided by Robert C. Martin is *more pure*.

However, I think that in this case the purity is overdone - and even wrong in design.
I do not agree with having different classes when they actually do not differ in behavior but only in data.
Classes which show the same behavior but with different data should actually instead be objects of the same class.
