package bullscows;

enum ErrorMsg {
    INVALID_NUMBER("Error: \"%s\" isn't a valid number."),
    IMPOSSIBLE_TO_GENERATE_UNIQUE_DIGITS("Error: can't generate a secret number with a length of %s because there aren't enough unique digits."),
    MAXIMUM_EXECEEDED("Error: maximum number of possible symbols in the code is 36 (0-9, a-z)."),
    SIZE_CODE_GREATER_THAN_SIZE_SYMBOLE("Error: it's not possible to generate a code with a length of %d with %d unique symbols.");

    ErrorMsg(String msg) {
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }

    private String msg;
}
