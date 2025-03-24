import java.time.LocalDate;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class GuessNumberGame {
    private static final RatingManager RATING_MANAGER = new RatingManager();
    private static final Random RANDOM = new Random();

    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        int rating;
        while (true) {
            System.out.println("\nВведите число\n" +
                    "1 - Начало игры\n" +
                    "2 - Вывести топ-5 игроков\n" +
                    "3 - Вывести всех игроков\n" +
                    "4 - Очистить полностью список игроков\n" +
                    "5 - Выход");

            System.out.print("\nКакую опцию Вы хотите применить? Ваше число: ");
            if (scanner.hasNextInt()) {
                rating = scanner.nextInt();
                if (rating == 5) {
                    scanner.close();
                    break;
                }
                if (rating >= 1 && rating <= 4) {
                    launchingTheSelectedOption(rating, scanner);
                } else {
                    System.out.println("\nНет такого варианта! Попробуйте ввести число от 1 до 5.");
                }
            } else {
                System.out.println("Нужно ввести целое число!");
                scanner.next();
            }
        }
    }

    /**
     * Запускает игру или выполняет выбранную пользователем опцию из меню.
     *
     * @param selectedOption номер опции(1-4), где 1 - игра, 2 - топ-5, 3 - все игроки, 4 - очистка
     * @param scanner        объект для чтения пользовательского ввода
     * @throws InterruptedException если выполнение потока прерывается во время ожидания
     */
    public static void launchingTheSelectedOption(int selectedOption, Scanner scanner) throws InterruptedException {
        switch (selectedOption) {
            case 1: {
                startGame(scanner);
                break;
            }
            case 2: {
                List<Player> topPlayers = RATING_MANAGER.loadTopPlayers();
                if (topPlayers.isEmpty()) {
                    System.out.println("Список пуст.");
                    break;
                }
                System.out.println("\nТоп-5 игроков:");
                for (int i = 0; i < topPlayers.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, topPlayers.get(i));
                }
                Thread.sleep(1500);
                break;
            }
            case 3: {
                List<Player> allPlayers = RATING_MANAGER.getAllPlayers();
                if (allPlayers.isEmpty()) {
                    System.out.println("\nСписок пуст.");
                    break;
                }
                for (int i = 0; i < allPlayers.size(); i++) {
                    System.out.printf("%d. %s%n", i + 1, allPlayers.get(i));
                }
                Thread.sleep(1500);
                break;
            }
            case 4: {
                if (RATING_MANAGER.clearResults()) {
                    System.out.println("Список игроков очищен.");
                } else {
                    System.out.println("Ошибка при очистке списка.");
                }
                break;
            }
        }
    }

    /**
     * Получает имя пользователя и его попытки
     *
     * @param scanner объект для чтения пользовательского ввода
     */
    public static void startGame(Scanner scanner) {
        System.out.println("\nНачало игры. Нужно угадать число в диапазоне от 1 до 100.");
        System.out.print("Введите ваше имя: ");
        String name = scanner.next();

        int countAttempt = numberSearch(scanner);

        System.out.printf("\nПоздравляем, %s! Вы угадали за %d попыток!", name, countAttempt);

        Player player = new Player(name, countAttempt, LocalDate.now());
        RATING_MANAGER.saveResult(player);
    }


    /**
     * Генерирует случайное число и подсчитывает попытки пользователя для его угадывания.
     *
     * @param scanner объект для чтения пользовательского ввода
     * @return количество попыток, затраченных на угадывание числа
     */
    public static int numberSearch(Scanner scanner) {
        int randomNumber = RANDOM.nextInt(100) + 1;
        int countAttempts = 0;
        int valuePlayer = 0;
        while (valuePlayer != randomNumber) {
            System.out.print("Угадайте число (1-100): ");

            if (scanner.hasNextInt()) {
                valuePlayer = scanner.nextInt();
                countAttempts++;

                if (valuePlayer < 1 || valuePlayer > 100) {
                    System.out.println("Число должно быть от 1 до 100!");
                    continue;
                }

                if (valuePlayer < randomNumber) {
                    System.out.println("Число больше!");
                } else if (valuePlayer == randomNumber) {
                    break;
                } else {
                    System.out.println("Число меньше!");
                }
            } else {
                System.out.println("Введите целое число!");
                scanner.next();
            }
        }
        return countAttempts;
    }
}
