
import java.util.Scanner;
public class MainApp {
    public static void main(String... args) {
        Logger logger = new ConsoleLogger();
        StringCalculator calculator = new StringCalculatorImpl(logger);

        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter input: ");
        String input = scanner.nextLine();

        try {
            int result = calculator.add(input);
            System.out.println("Result: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }
}
