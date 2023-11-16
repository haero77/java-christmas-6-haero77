package christmas.view.input;

public enum InputGuideMessage {

    VISIT_DATE_INPUT_GUIDE("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)"),
    MENU_ORDER_INPUT_GUIDE("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

    private final String message;

    InputGuideMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

}

