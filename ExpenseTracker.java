import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Expense {
    String description;
    double amount;
    String date;

    Expense(String description, double amount, String date) {
        this.description = description;
        this.amount = amount;
        this.date = date;
    }

    @Override
    public String toString() {
        return date + ": " + description + " - $" + amount;
    }
}

public class ExpenseTracker {
    private static final String FILE_NAME = "expenses.txt";

    public static void main(String[] args) {
        List<Expense> expenses = loadExpenses();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a command (add/view/total/save/exit): ");
            String command = scanner.nextLine();

            switch (command.toLowerCase()) {
                case "add":
                    addExpense(scanner, expenses);
                    break;
                case "view":
                    viewExpenses(expenses);
                    break;
                case "total":
                    calculateTotal(expenses);
                    break;
                case "save":
                    saveExpenses(expenses);
                    break;
                case "exit":
                    saveExpenses(expenses); // Save before exiting
                    scanner.close();
                    return;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }

    private static void addExpense(Scanner scanner, List<Expense> expenses) {
        System.out.print("Enter expense description: ");
        String description = scanner.nextLine();
        System.out.print("Enter expense amount: ");
        double amount = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter date (YYYY-MM-DD): ");
        String date = scanner.nextLine();
        expenses.add(new Expense(description, amount, date));
        System.out.println("Expense added.");
    }

    private static void viewExpenses(List<Expense> expenses) {
        if (expenses.isEmpty()) {
            System.out.println("No expenses recorded.");
        } else {
            System.out.println("Recorded expenses:");
            for (int i = 0; i < expenses.size(); i++) {
                System.out.println((i + 1) + ". " + expenses.get(i));
            }
        }
    }

    private static void calculateTotal(List<Expense> expenses) {
        double total = 0;
        for (Expense expense : expenses) {
            total += expense.amount;
        }
        System.out.println("Total expenses: $" + total);
    }

    private static void saveExpenses(List<Expense> expenses) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Expense expense : expenses) {
                writer.write(expense.description + "," + expense.amount + "," + expense.date);
                writer.newLine();
            }
            System.out.println("Expenses saved to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving expenses: " + e.getMessage());
        }
    }

    private static List<Expense> loadExpenses() {
        List<Expense> expenses = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    expenses.add(new Expense(parts[0], Double.parseDouble(parts[1]), parts[2]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading expenses: " + e.getMessage());
        }
        return expenses;
    }
}
