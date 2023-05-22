import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringCalculatorImpl implements StringCalculator {

    private Logger logger;

    public StringCalculatorImpl(Logger logger) {
        this.logger = logger;
    }


    private int get_int_from_str(String num_str){

        try{
            int number = Integer.parseInt(num_str.trim());
            if (number < 0) {
                throw new NumberFormatException("Negatives not allowed");
            }
            else if (number > 1000 && logger != null){
                logger.log(number);
            }
            return number;
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
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
            if (input.startsWith("//")) {
                int endIndex = input.indexOf('\n');
                String delimiterSequence = input.substring(2, endIndex);
                input = input.substring(endIndex + 1);

                // Hitta alla delimiters som är avgränsade med [ ] i kontrollsekvensen
                List<String> delimiters = new ArrayList<>();
                Matcher matcher = Pattern.compile("\\[(.*?)]").matcher(delimiterSequence);
                while (matcher.find()) {
                    delimiters.add(matcher.group(1));
                }

                // Byt ut alla delimiters med standardseparatorn ","
                for (String delimiter : delimiters) {
                    input = input.replace(delimiter, ",");
                }
            }

            input = input.replace('\n', sep);

            String[] splitStr = input.split(String.valueOf(sep));
            int sum_result = 0;
            for (String num_str : splitStr) {
                sum_result += get_int_from_str(num_str);
            }
            return sum_result;
        }
    }

}
