import java.util.Arrays;

public class StringCalculatorImpl implements StringCalculator {


    private int get_int_from_str(String num_str) throws NumberFormatException{
        try{
            int number = Integer.parseInt(num_str.trim());
            if (number < 0) {

                throw new NumberFormatException("Negatives not allowed");
            }

            return number;
        }
        catch (NumberFormatException ex){
            //ex.printStackTrace();
            return 0;
        }
    }


    @Override
    public int add(String input) throws NumberFormatException {

        if (input.length() == 0) {
            return 0;
        }
        else {
            char sep = ',';
            if (input.startsWith("//")){
                sep = input.charAt(2);
                input = input.substring(3);
            }

            input = input.replace('\n', sep);

            String[] splitStr = input.split(String.valueOf(sep));
            int sum_result = 0;
            for (String num_str: splitStr) {

                try{
                sum_result+=get_int_from_str(num_str);
                }
                catch (NumberFormatException ex){
                ex.printStackTrace();
                    return 0;
                }
            }
            return sum_result;
        }
    }

}
