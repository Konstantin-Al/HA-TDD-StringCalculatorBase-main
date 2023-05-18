
import java.util.Scanner;
public class MainApp {
    public static void main(String... args) {
        Logger logger = new ConsoleLogger();
        StringCalculator calculator = new StringCalculatorImpl(logger);

        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Welcome to Calculator!");
        System.out.println("Usage: ...");


        do {
            System.out.println("Enter input (or leave empty to exit): ");
            input = scanner.nextLine();

            if (!input.isEmpty()) {
                try {
                    int result = calculator.add(input);
                    System.out.println("Result: " + result);
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input: " + e.getMessage());
                }
            }
        } while (!input.isEmpty());
    }
}
