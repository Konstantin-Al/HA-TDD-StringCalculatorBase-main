import java.util.Arrays;

public class StringCalculatorImpl implements StringCalculator {


    private int get_int_from_str(String num_str) {
        try{
            int number = Integer.parseInt(num_str.trim());
            return number;
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
            return 0;
        }
    }

    @Override
    public int add(String input) {

        if (input.length() == 0) {
            return 0;
        }
        else {
            input = input.replace("\n", ",");
            String[] splitStr = input.split(",");
            System.out.println(Arrays.toString(splitStr));

            int sum_result = 0;
            for (String num_str: splitStr) {
                sum_result+=get_int_from_str(num_str);
            }
            return sum_result;
        }
    }
}
