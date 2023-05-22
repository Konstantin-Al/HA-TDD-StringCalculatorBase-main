
import java.util.Scanner;
public class MainApp {
    public static void main(String... args) {

        System.out.println("Welcome to Calculator!");
        System.out.println("Usage: ...");

        Scanner scanner = new Scanner(System.in);
        String input;
        StringBuilder inputBuilder = new StringBuilder();


        do {
            System.out.println("Enter input (or leave empty to exit): ");
            input = scanner.nextLine();
            if (!input.isEmpty()) {
                if(input.endsWith("]")){
                    input = input + "\n" + scanner.nextLine();
                }
                //System.out.println(input);
                calc(input);
            }
            else {
                break;
            }
        } while (true);
    }

    private static void calc(String input){
        Logger logger = new ConsoleLogger();
        StringCalculator calculator = new StringCalculatorImpl(logger);

        try {
            int result = calculator.add(input);
            System.out.println("Result: " + result);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input: " + e.getMessage());
        }

    }


}
