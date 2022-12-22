package bullscows;

import java.util.Random;

public class BullsAndCows {
    private String code;
    private int codeSize;
    private final int possibleSymbolsSize;
    private static final String BULL = "bull";
    private static final String COW = "cow";

    private static final int NUMERIC_LOWER_BOUND = 48;
    private static final int NUMERIC_UPPER_BOUND = 57;
    private static final int ALPHA_LOWER_BOUND = 97;
    private static final int ALPHA_UPPER_BOUND = 122;
    private final Random random = new Random();


    public BullsAndCows(int codeSize, int possibleSymbolsSize) {
        this.codeSize = codeSize;
        this.possibleSymbolsSize = possibleSymbolsSize;
        this.code = getRandomCode(codeSize);
    }

    private int inclusiveRandom(int lower, int upper) {
        return random.nextInt(upper - lower + 1) + lower;
    }

    public String getCode() {
        return this.code;
    }

    public int getCodeSize() {
        return this.codeSize;
    }

    public int getPossibleSymbolsSize() {
        return this.possibleSymbolsSize;
    }

    public String getRangeInfo() {
        int nbPossibleAlpha = possibleSymbolsSize - 10;
        if (nbPossibleAlpha <= 0) {
            return "(0-9)";
        } else if (nbPossibleAlpha == 1) {
            return "(0-9, a)";
        }

        return String.format("(0-9, a-%c)", (char) (ALPHA_LOWER_BOUND + nbPossibleAlpha - 1));
    }

    public String getRandomCode(int size) {
        int nbPossibleAlpha = possibleSymbolsSize - 10;

        StringBuilder code = new StringBuilder();
        char c;
        int i;
        do {
            i = ((int) (Math.random() * 2)) + 1;
            if (i % 2 == 0 || nbPossibleAlpha <= 0) {
                if (code.toString().isEmpty()) {
                    c = (char) inclusiveRandom(NUMERIC_LOWER_BOUND + 1, NUMERIC_UPPER_BOUND);
                } else {
                    c = (char) inclusiveRandom(NUMERIC_LOWER_BOUND, NUMERIC_UPPER_BOUND);
                }
            } else {
                c = (char) (random.nextInt(nbPossibleAlpha) + ALPHA_LOWER_BOUND);
            }

            if (code.indexOf(String.valueOf(c)) == -1) {
                code.append(c);
            }
        } while (code.length() != size);

        return code.toString();
    }

    private boolean isBull(char digit, int pos) {
        return String.valueOf(this.code).charAt(pos) == digit;
    }

    private boolean isCow(char digit, int pos) {
        return !isBull(digit, pos) && String.valueOf(this.code).contains(digit + "");
    }

    public String grade(String guess) {
        int nbCow = 0;
        int nbBull = 0;
        char c;

        for (int i = 0, len = guess.length(); i < len; i++) {
            c = guess.charAt(i);
            if (isBull(c, i)) {
                nbBull++;
            } else if (isCow(c, i)) {
                nbCow++;
            }
        }

        StringBuilder grade = new StringBuilder("Grade: ");
        if (nbBull == 0 && nbCow == 0) {
            return grade.append("none").toString();
        }

        if (nbBull > 0) {
            grade.append(nbBull).append(" ").append(BULL);
            if (nbBull > 1) {
                grade.append("s");
            }

            if (nbBull == code.length()) {
                return String.format("%s\nCongratulations! You guessed the secret code.", grade);
            }
        }

        if (nbCow > 0) {
            if (grade.toString().contains("bull")) {
                grade.append(" ");
            }
            grade.append(nbCow).append(" ").append(COW);
            if (nbCow > 1) {
                grade.append("s");
            }
        }

        return grade.toString();
    }
}
