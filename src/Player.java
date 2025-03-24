import java.time.LocalDate;

/**
 * Представляет игрока в игре "Угадай число" с именем, количеством попыток и датой игры.
 */
public class Player implements Comparable<Player> {
    private final String name;
    private final int attempts;
    private final LocalDate time;

    /**
     * Создаёт нового игрока.
     *
     * @param name     имя игрока
     * @param attempts количество попыток, затраченных на угадывание
     * @param time     дата игры
     * @throws IllegalArgumentException если имя null или attempts меньше 0
     */
    public Player(String name, int attempts, LocalDate time) {
        if (name == null || time == null) throw new IllegalArgumentException("Имя и дата не могут быть null");
        if (attempts < 0) throw new IllegalArgumentException("Количество попыток не может быть отрицательным");
        if (name.length() > 20) throw new IllegalArgumentException("Имя не может быть больше 20 символов");
        this.name = name;
        this.attempts = attempts;
        this.time = time;
    }

    /**
     * Возвращает имя игрока.
     *
     * @return имя игрока
     */
    public String getName() {
        return name;
    }

    /**
     * Возвращает количество попыток.
     *
     * @return количество попыток
     */
    public int getAttempts() {
        return attempts;
    }

    /**
     * Возвращает дату игры.
     *
     * @return дата игры
     */
    public LocalDate getTime() {
        return time;
    }

    /**
     * Сравнивает игроков по количеству попыток, а при равенстве — по дате.
     *
     * @param other другой игрок для сравнения
     * @return отрицательное число, ноль или положительное число в зависимости от порядка
     */
    @Override
    public int compareTo(Player other) {
        int resRat = Integer.compare(this.attempts, other.attempts);
        return resRat == 0 ? time.compareTo(other.getTime()) : resRat;
    }

    /**
     * Возвращает строковое представление игрока для сохранения или вывода.
     *
     * @return строка в формате "имя, попытки, дата"
     */
    @Override
    public String toString() {
        return String.format("%s, %d, %s", name, attempts, time);
    }
}
