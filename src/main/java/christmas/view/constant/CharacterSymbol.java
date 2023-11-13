package christmas.view.constant;

public enum CharacterSymbol {

    HYPHEN("-"),
    BLANK(" "),
    COMMA(","),
    COLON(":"),
    EMPTY("");

    private final String literal;

    CharacterSymbol(String symbolLiteral) {
        this.literal = symbolLiteral;
    }

    public String getLiteral() {
        return literal;
    }

}
