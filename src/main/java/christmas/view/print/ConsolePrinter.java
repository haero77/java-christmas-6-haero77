package christmas.view.print;

public class ConsolePrinter implements Printer {

    @Override
    public void printLine(String message) {
        System.out.println(message);
    }

}
