package ru.markina.hw2;

import java.util.Scanner;

public class Main {

    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';
    private static final char DOT_EMPTY = '*';
    private static final Scanner scanner = new Scanner(System.in);
    private static char[][] gameField;
    private static int gameFieldSizeX;
    private static int gameFieldSizeY;
    private static int winChipCount;

    public static void main(String[] args) {
        do {
            initialize();
            printGameField();
            while (true) {
                humanTurn();
                printGameField();

                if (isGameFinished(DOT_HUMAN, "Вы победили!")) {
                    break;
                }

                aiTurn();
                printGameField();

                if (isGameFinished(DOT_AI, "Компьютер победил!")) {
                    break;
                }
            }
            System.out.println("Желаете начать новую игру? (Y - да): ");
        } while (scanner.next().equalsIgnoreCase("y"));
    }

    /**
     * Инициализация игрового поля по выбранным пользователем размеру и количеству фишек для победы
     */
    private static void initialize() {
        System.out.println("Введите размер игрового поля - 'x' и 'y' через пробел: ");
        gameFieldSizeX = scanner.nextInt();
        gameFieldSizeY = scanner.nextInt();

        do {
            System.out.println("Введите количество фишек для победы " +
                    "(не должно быть больше меньшего из размеров игрового поля: ");
            winChipCount = scanner.nextInt();
        } while (!isWinCountCorrect(winChipCount));

        gameField = new char[gameFieldSizeY][gameFieldSizeX];
        for (int i = 0; i < gameFieldSizeY; i++) {
            for (int j = 0; j < gameFieldSizeX; j++) {
                gameField[i][j] = DOT_EMPTY;
            }
        }
    }

    /**
     * Метод для проверки корреткности количества фишек для победы (не должно быть больше меньшего из размеров поля)
     *
     * @param winChipCount количество фишек для победы
     * @return true - валидное каличество
     */
    private static boolean isWinCountCorrect(int winChipCount) {
        return gameFieldSizeX <= gameFieldSizeY ? winChipCount <= gameFieldSizeX : winChipCount <= gameFieldSizeY;
    }

    private static void printGameField() {
        System.out.print("+");
        for (int i = 0; i < gameFieldSizeX * 2 + 1; i++) {
            System.out.print(i % 2 == 0 ? "-" : i / 2 + 1);
        }
        System.out.println();

        for (int i = 0; i < gameFieldSizeY; i++) {
            System.out.print(i + 1 + "|");
            for (int j = 0; j < gameFieldSizeX; j++) {
                System.out.print(gameField[i][j] + "|");
            }
            System.out.println();
        }

        for (int i = 0; i < gameFieldSizeX * 2 + 2; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private static void humanTurn() {
        int x, y;
        do {
            System.out.print("Введите координаты хода - 'x' и 'y' через пробел: ");
            x = scanner.nextInt() - 1;
            y = scanner.nextInt() - 1;
        } while (!isCellValid(x, y) || !isCellEmpty(x, y));
        gameField[x][y] = DOT_HUMAN;
    }

    /**
     * Ход бота с реализацией интеллекта
     * <p>
     * Реализовала таким образом, чтобы проверял не только тогда, когда осталась одна фишка до победы, но и каждый раз
     * перекрывал игрока, ставя фишку рядом, для чего сделала внешний цикл с убывающим счетчиком от 'количества
     * победных фишек - 1' до единицы. Таким образом, уже при первом ходе игрока компьютер перекрывает один из вариантов
     * постановки следующей фишки.
     * <p>
     * Вроде работает, очень долго делала и перепроверяла. Спасибо за такое интересное задание))
     */
    private static void aiTurn() {
        for (int k = winChipCount - 1; k >= 1; k--) {
            for (int i = 0; i < gameFieldSizeX; i++) {
                for (int j = 0; j < gameFieldSizeY; j++) {
                    if (aiAttackHorizontally(i, j, k)) return;

                    if (aiAttackVertically(i, j, k)) return;

                    if (aiAttackDownDiagonally(i, j, k)) return;

                    if (aiAttackUpDiagonally(i, j, k)) return;
                }
            }
        }
    }

    /**
     * Метод для проверки и в удачном случае совершения хода компьютером, используя проверку количества
     * фишек по горизонтали
     *
     * @param i координаты x поля
     * @param j координаты y поля
     * @param k количество фишек подряд
     * @return true - если пустому полю присвоилось значение хода компьютера - 0
     */
    private static boolean aiAttackHorizontally(final int i, final int j, final int k) {
        if (checkHorizontal(i, j, DOT_HUMAN, k)) {
            if (j + k >= gameFieldSizeY) {
                if (gameField[i][j - 1] == DOT_EMPTY) {
                    gameField[i][j - 1] = DOT_AI;
                    return true;
                }
            } else if (gameField[i][j + k] == DOT_EMPTY) {
                gameField[i][j + k] = DOT_AI;
                return true;
            }
        }
        return false;
    }


    /**
     * Метод для проверки и в удачном случае совершения хода компьютером, используя проверку количества
     * фишек по вертикали
     *
     * @param i координаты x поля
     * @param j координаты y поля
     * @param k количество фишек подряд
     * @return true - если пустому полю присвоилось значение хода компьютера - 0
     */
    private static boolean aiAttackVertically(final int i, final int j, final int k) {
        if (checkVertical(i, j, DOT_HUMAN, k)) {
            if (i + k >= gameFieldSizeX) {
                if (gameField[i - 1][j] == DOT_EMPTY) {
                    gameField[i - 1][j] = DOT_AI;
                    return true;
                }
            } else if (gameField[i + k][j] == DOT_EMPTY) {
                gameField[i + k][j] = DOT_AI;
                return true;
            }
        }
        return false;
    }


    /**
     * Метод для проверки и в удачном случае совершения хода компьютером, используя проверку количества
     * фишек по диагонали вниз и вправо
     *
     * @param i координаты x поля
     * @param j координаты y поля
     * @param k количество фишек подряд
     * @return true - если пустому полю присвоилось значение хода компьютера - 0
     */
    private static boolean aiAttackDownDiagonally(final int i, final int j, final int k) {
        if (checkDownDiagonal(i, j, DOT_HUMAN, k)) {
            if (i + k < gameFieldSizeX && j + k < gameFieldSizeY && gameField[i + k][j + k] == DOT_EMPTY) {
                gameField[i + k][j + k] = DOT_AI;
                return true;
            } else if (i != 0 && j != 0 && gameField[i - 1][j - 1] == DOT_EMPTY) {
                gameField[i - 1][j - 1] = DOT_AI;
                return true;
            }

        }
        return false;
    }


    /**
     * Метод для проверки и в удачном случае совершения хода компьютером, используя проверку количества
     * фишек по диагонали вверх и вправо
     *
     * @param i координаты x поля
     * @param j координаты y поля
     * @param k количество фишек подряд
     * @return true - если пустому полю присвоилось значение хода компьютера - 0
     */
    private static boolean aiAttackUpDiagonally(final int i, final int j, final int k) {
        if (checkUpDiagonal(i, j, DOT_HUMAN, k)) {
            if (i - k >= 0 && j + k < gameFieldSizeY && gameField[i - k][j + k] == DOT_EMPTY) {
                gameField[i - k][j + k] = DOT_AI;
                return true;
            } else if (i < gameFieldSizeX - 1 && j != 0 && gameField[i + 1][j - 1] == DOT_EMPTY) {
                gameField[i + 1][j - 1] = DOT_AI;
                return true;
            }
        }
        return false;
    }

    private static boolean isCellEmpty(int x, int y) {
        return gameField[x][y] == DOT_EMPTY;
    }

    private static boolean isCellValid(int x, int y) {
        return x >= 0 && x < gameFieldSizeX && y >= 0 && y < gameFieldSizeY;
    }

    private static boolean isGameFinished(char dot, String winStr) {
        if (isVictory(dot)) {
            System.out.println(winStr);
            return true;
        }
        if (isDraw()) {
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    /**
     * Функция определения победы
     *
     * @param c фишка (крестик или нолик)
     * @return true - победа
     */
    private static boolean isVictory(char c) {
        for (int i = 0; i < gameFieldSizeY; i++) {
            for (int j = 0; j < gameFieldSizeX; j++) {
                if (checkHorizontal(i, j, c, winChipCount) || checkVertical(i, j, c, winChipCount)
                        || checkDownDiagonal(i, j, c, winChipCount) || checkUpDiagonal(i, j, c, winChipCount)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Проверка победы по горизонтали
     *
     * @param x координата x
     * @param y координата y
     * @param c крестик или нолик
     * @return true - победа
     */
    private static boolean checkHorizontal(int x, int y, char c, int winChipCount) {
        for (int i = 0; i < winChipCount; i++) {
            if (y == gameFieldSizeY) {
                return false;
            }
            if (gameField[x][y++] != c) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверка победы по вертикали
     *
     * @param x координата x
     * @param y координата y
     * @param c крестик или нолик
     * @return true - победа
     */
    private static boolean checkVertical(int x, int y, char c, int winChipCount) {
        for (int i = 0; i < winChipCount; i++) {
            if (x == gameFieldSizeX) {
                return false;
            }
            if (gameField[x++][y] != c) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверка победы по диагонали вниз и вправо
     *
     * @param x координата x
     * @param y координата y
     * @param c крестик или нолик
     * @return true - победа
     */
    private static boolean checkDownDiagonal(int x, int y, char c, int winChipCount) {
        for (int i = 0; i < winChipCount; i++) {
            if (y == gameFieldSizeY || x == gameFieldSizeX) {
                return false;
            }
            if (gameField[x++][y++] != c) {
                return false;
            }
        }
        return true;
    }

    /**
     * Проверка победы по диагонали вверх и вправо
     *
     * @param x координата x
     * @param y координата y
     * @param c крестик или нолик
     * @return true - победа
     */
    private static boolean checkUpDiagonal(int x, int y, char c, int winChipCount) {
        for (int i = 0; i < winChipCount; i++) {
            if (y == gameFieldSizeY || x < 0) {
                return false;
            }
            if (gameField[x--][y++] != c) {
                return false;
            }
        }
        return true;
    }

    private static boolean isDraw() {
        for (int i = 0; i < gameFieldSizeY; i++) {
            for (int j = 0; j < gameFieldSizeX; j++) {
                if (isCellEmpty(i, j)) {
                    return false;
                }
            }
        }
        return true;
    }

}