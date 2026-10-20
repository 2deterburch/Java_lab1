
import java.util.Locale;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner =
            new Scanner(System.in).useLocale(Locale.US);

    private static final String[] UNITS = {
            "мл", "л", "м³", "гал (США)"
    };

    // Коефіцієнти переведення в літри
    private static final double[] TO_LITERS = {
            0.001, 1.0, 1000.0, 3.785411784
    };

    public static void main(String[] args) {
        System.out.println("=== Конвертер одиниць об'єму ===");

        while (true) {
            System.out.println("\n1) Конвертувати об'єм");
            System.out.println("0) Вихід");

            int choice = readInt("Ваш вибір: ", 0, 1);

            if (choice == 0) {
                System.out.println("Дякуємо за використання!");
                break;
            }

            convertVolume();
        }

        scanner.close();
    }

    private static void convertVolume() {
        System.out.println("\nОберіть початкову одиницю:");

        printUnits();
        int from = readInt("Звідки: ", 1, UNITS.length) - 1;

        System.out.println("\nОберіть кінцеву одиницю:");
        printUnits();
        int to = readInt("Куди: ", 1, UNITS.length) - 1;

        double value = readDouble("Введіть об'єм: ", 0);

        double liters = value * TO_LITERS[from];
        double result = liters / TO_LITERS[to];

        System.out.printf(
                Locale.US,
                "\nРезультат: %.6f %s = %.6f %s%n",
                value, UNITS[from], result, UNITS[to]
        );
    }

    private static void printUnits() {
        for (int i = 0; i < UNITS.length; i++) {
            System.out.println((i + 1) + ") " + UNITS[i]);
        }
    }

    private static int readInt(String message, int min, int max) {
        while (true) {
            System.out.print(message);

            if (scanner.hasNextInt()) {
                int value = scanner.nextInt();

                if (value >= min && value <= max) {
                    return value;
                }

                System.out.println(
                        "Помилка: введіть число від "
                                + min + " до " + max + "."
                );
            } else {
                System.out.println(
                        "Помилка: потрібно ввести ціле число."
                );
                scanner.next();
            }
        }
    }

    private static double readDouble(String message, double min) {
        while (true) {
            System.out.print(message);

            if (scanner.hasNextDouble()) {
                double value = scanner.nextDouble();

                if (Double.isFinite(value) && value >= min) {
                    return value;
                }

                System.out.println(
                        "Помилка: введіть невід'ємне скінченне число."
                );
            } else {
                System.out.println(
                        "Помилка: введіть число, наприклад 12.5."
                );
                scanner.next();
            }
        }
    }
}
