package christmas.view.input;

import static christmas.view.constant.CharacterSymbol.COMMA;
import static christmas.view.constant.CharacterSymbol.HYPHEN;

import christmas.domain.order.OrderRequest;
import christmas.utils.convert.StringConverter;
import christmas.view.print.Printer;
import christmas.view.read.Reader;
import java.util.List;

public class InputViewImpl implements InputView {

    private final Reader reader;
    private final Printer printer;

    public InputViewImpl(Reader reader, Printer printer) {
        this.reader = reader;
        this.printer = printer;
    }

    @Override
    public int inputVisitDate() {
        printer.printLine(InputGuideMessage.VISIT_DATE_INPUT_GUIDE.getMessage());
        String rawInput = reader.readLine();

        InputFormatValidator.validateVisitDateFormat(rawInput);

        return Integer.parseInt(rawInput);
    }

    @Override
    public OrderRequest inputOrderRequest() {
        printer.printLine(InputGuideMessage.MENU_ORDER_INPUT_GUIDE.getMessage());
        String rawInput = reader.readLine();

        InputFormatValidator.validateOrderRequestInputFormat(rawInput);

        return toOrderRequest(rawInput);
    }

    private OrderRequest toOrderRequest(String rawInput) {
        List<String[]> menuAndCounts = StringConverter.spiltWith(rawInput, COMMA.getLiteral())
                .stream()
                .map(menuAndCount -> menuAndCount.split(HYPHEN.getLiteral()))
                .toList();

        List<OrderRequest.Form> forms = menuAndCounts.stream()
                .map(menuAndCount -> new OrderRequest.Form(menuAndCount[0], Integer.parseInt(menuAndCount[1])))
                .toList();

        return new OrderRequest(forms);
    }

}
