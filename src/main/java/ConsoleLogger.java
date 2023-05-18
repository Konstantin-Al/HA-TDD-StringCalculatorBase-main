public class ConsoleLogger implements Logger{

    @Override
    public void log(Integer number){
        System.out.println("Logged: " + number);
    }
}
