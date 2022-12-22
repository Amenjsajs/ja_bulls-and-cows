package bullscows;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Please, enter the secret code's length:");

        String size = scanner.nextLine();

        if (!isValidNumber(size) || size.equals("0")) {
            System.out.printf(ErrorMsg.INVALID_NUMBER.getMsg(), size);
        } else if (Integer.parseInt(size) > 36) {
            System.out.printf(ErrorMsg.IMPOSSIBLE_TO_GENERATE_UNIQUE_DIGITS.getMsg(), size);
        } else {
            System.out.println("Input the number of possible symbols in the code:");
            String possibleSymbolSize = scanner.nextLine();

            if (!isValidNumber(possibleSymbolSize)) {
                System.out.printf(ErrorMsg.INVALID_NUMBER.getMsg(), possibleSymbolSize);
            } else {

                int nSize = Integer.parseInt(size);
                int nPossibleSymbolSize = Integer.parseInt(possibleSymbolSize);

                if (nSize > nPossibleSymbolSize) {
                    System.out.printf(ErrorMsg.SIZE_CODE_GREATER_THAN_SIZE_SYMBOLE.getMsg(), nSize, nPossibleSymbolSize);
                } else if (nPossibleSymbolSize > 36) {
                    System.out.printf(ErrorMsg.MAXIMUM_EXECEEDED.getMsg(), nPossibleSymbolSize);
                } else {
                    BullsAndCows bullsAndCows = new BullsAndCows(nSize, nPossibleSymbolSize);

                    System.out.printf("The secret is prepared: %s %s.\n", "*".repeat(nSize), bullsAndCows.getRangeInfo());
                    System.out.println("Okay, let's start a game!");

                    int turn = 0;
                    String grade;
                    String guess;
                    do {
                        System.out.printf("Turn %d:\n", ++turn);
                        guess = scanner.nextLine();

                        grade = bullsAndCows.grade(guess);
                        System.out.println(grade);
                    } while (!grade.contains(size + " bull"));
                }
            }
        }
    }

    private static boolean isValidNumber(String number) {
        char[] chars = number.toCharArray();
        int n;
        for (char aChar : chars) {
            n = aChar;
            if ((n < 48 || n > 57)) {
                return false;
            }
        }
        return true;
    }
}
