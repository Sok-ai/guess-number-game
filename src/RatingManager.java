import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

/**
 * Управляет рейтингом игроков, сохраняя результаты в файл и предоставляя доступ к топу и списку.
 */
public class RatingManager {
    private static final String FILE_NAME = "results.txt";
    private static final Logger LOGGER = Logger.getLogger(RatingManager.class.getName());

    /**
     * Сохраняет игрока в файл
     *
     * @param player игрок для сохранения
     * @return true, если сохранение успешко, false в случае ошибки
     */
    public boolean saveResult(Player player) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME, true))) {
            writer.println(player);
            return true;
        } catch (IOException e) {
            LOGGER.severe("Ошибка записи в файл: " + e.getMessage());
            return false;
        }
    }

    /**
     * Получение всех игроков из файла
     *
     * @return список всех игроков из файла, пустой в случае ошибки или отсутствия данных
     */
    public List<Player> getAllPlayers() {
        List<Player> players = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                System.out.println(parts[0]);
                if (parts.length == 3) {
                    try {
                        String name = parts[0].trim();
                        int attempts = Integer.parseInt(parts[1].trim());
                        LocalDate date = LocalDate.parse(parts[2].trim());
                        players.add(new Player(name, attempts, date));
                    } catch (IllegalArgumentException e) {
                        LOGGER.warning("Неверный формат строки: " + line);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Ошибка чтения файла: " + e.getMessage());
        }

        return players;
    }

    /**
     * Загружает топ-5 игроков с наименьшим количеством попыток.
     *
     * @return список из максимум 5 лучших игроков
     */
    public List<Player> loadTopPlayers() {
        List<Player> players = getAllPlayers();

        Collections.sort(players);
        return players.subList(0, Math.min(5, players.size()));
    }

    /**
     * Очищает файл с результатами игроков.
     *
     * @return true, если очистка успешна, false в случае ошибки
     */
    public boolean clearResults() {
        try (PrintWriter writer = new PrintWriter(FILE_NAME)) {
            writer.print("");
            return true;
        } catch (IOException e) {
            LOGGER.severe("Ошибка очистки файла: " + e.getMessage());
            return false;
        }
    }
}
