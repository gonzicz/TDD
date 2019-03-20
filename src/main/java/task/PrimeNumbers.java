package task;

/**
 * Napisz program, który liczy liczby pierwsze, a następnie przetestuj go pisząc
 * testy jednostkowe z wykorzystaniem testów parametryzowanych
 */
public class PrimeNumbers {

    public static boolean IsPrimeNumber(int number) {
        if (number == 1) {
            return false;
        }
        if (number == 2) {
            return true;
        }
        for (int i = 2; i < number - 1; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
