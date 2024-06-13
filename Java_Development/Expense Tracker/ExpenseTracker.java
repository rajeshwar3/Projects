import java.util.ArrayList;
import java.util.Scanner;

class Expense {
    String description;
    double amount;
    String category;

    public Expense(String description, double amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }
}

public class ExpenseTracker {
    private ArrayList<Expense> expenses = new ArrayList<>();

    public void addExpense(String description, double amount, String category) {
        expenses.add(new Expense(description, amount, category));
    }

    public void viewExpense() {
        for (Expense expense : expenses) {
            System.out.println("Description:" + expense.description);
            System.out.println("Amount: ₹" + expense.amount);
            System.out.println("Category:" + expense.category);
            System.out.println();
        }
    }

    public double calculateTotalExpenses(String category) {
        double total = 0;
        for (Expense expense : expenses) {
            if (expense.category.equalsIgnoreCase(category)) {
                total += expense.amount;
            }
        }
        return total;
    }

    public static void main(String[] args) {
        ExpenseTracker tracker = new ExpenseTracker();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Expense Tracker Menu:");
            System.out.println("1. Add Expense");
            System.out.println("2. View Expenses");
            System.out.println("3. View Expense Summaries");
            System.out.println("4. Exit");
            System.out.println("Enter your choice:");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("Enter description:");
                    String description = scanner.next();
                    System.out.print("Enter amount: ₹");
                    double amount = scanner.nextDouble();
                    System.out.print("Enter category: ");
                    String category = scanner.next();
                    tracker.addExpense(description, amount, category);
                    break;
                case 2:
                    System.out.println("Expenses:");
                    tracker.viewExpense();
                    break;
                case 3:
                    System.out.print("Enter category to calculate total expenses: ");
                    String categoryToCalculate = scanner.next();
                    double total = tracker.calculateTotalExpenses(categoryToCalculate);
                    System.out.println("Total expenses for category '" + categoryToCalculate + "': ₹" + total);
                    break;
                case 4:
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid choiced. Please try again.");
            }
        }
    }
}