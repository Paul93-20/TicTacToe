import javax.sound.midi.Soundbank;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
        /*
        Блок настроек игры
        */
    public static char[][] map; //матрица игры
    private static int SIZE = 3;// размерность поля

    private static final char DOT_EMPTY = '.';//пусток поле
    private static final char DOT_X = 'X';//крестик
    private static final char DOT_O = 'O';//нолик
    private static final boolean SILLY_MODE = true;

    private static Random random = new Random();

    private static Scanner scanner= new Scanner(System.in);

    public static void main(String[] args) {
        initMap();
        printMap();

        while (true){
            humanTurn();
            if(isEndGame(DOT_X)){
                break;
            }

            hardComputerTurn();
            if(isEndGame(DOT_O)){
                break;
            }
        }
        System.out.println("Игра закончена");

    }
    //метод реализации игрового поля
    private static void initMap(){
        map = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++){
            for (int j = 0; j < SIZE; j++){
                map[i][j] = DOT_EMPTY;
            }
        }
    }
    //метод вывода поля на экран
    private static void printMap() {
        for(int i = 0; i <= SIZE; i++){
            System.out.print(i + " ");
        }
        System.out.println();

        for(int i =0; i < SIZE; i++){
            System.out.print((i+1) + " ");
            for(int j = 0; j < SIZE; j++){
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println();

    }
    //ходит человек
    private static void humanTurn() {
        int x, y;
        do {
            System.out.println("Введите координаты ячейки (X Y)");
            y = scanner.nextInt() - 1; // Считывание номера строки
            x = scanner.nextInt() - 1; // Считывание номера столбца
        }
        while(!isCellValid(x, y));

        map[y][x] = DOT_X;
    }
    //проверка валидности ввода человека
    public static boolean isCellValid(int x, int y){
        boolean result = true;

        if(x < 0 || x >= SIZE || y < 0 || y >= SIZE) {
            result = false;
        }

        if(map[y][x] != DOT_EMPTY){
            result = false;
        }
        return result;
    }
    //ходит умный компьютер
    private static void hardComputerTurn(){
        int x = 0;
        int y = 0;

        int numCell = 0;

        for(int i = 0; i < SIZE;i++){
            for(int j = 0; j < SIZE;j++){
                if(map[i][j] == DOT_EMPTY){
                    int cell = checkCells(i,j);
                    if (cell > numCell){
                        numCell = cell;
                        x = i;
                        y = j;
                    }
                }
            }
        }
        if(numCell > 0){
            computerStep(x, y);
        }
         else {
            computerTurn();
        }
    }
    //проверяем рядомстоящие ячейки
    public static int checkCells(int x, int y){
        int quantity = 0;
        for(int i = x - 1; i < 2; i++){
            for (int j = y - 1; j < 2; j ++){
                if(isCellCorrect(i, j) && map[i][j] == DOT_O) {
                    quantity++;
                }
            }
        }
        return quantity;
    }

    private static void computerTurn() {
        int x = -1;
        int y = -1;
        if (SILLY_MODE) {
            do {
                x = random.nextInt(SIZE);
                y = random.nextInt(SIZE);
            } while (!isCellValid(x, y));
        }
        System.out.println("Компьютер выбрал ячейку " + (y + 1) + " " + (x + 1));
        map[y][x] = DOT_O;
    }
    private static void computerStep(int x, int y){
        map[x][y]=DOT_O;
        System.out.println("Компьютер выбрал ячейку " + (x + 1) + " " + (y + 1));
        System.out.println();
    }
    public static boolean isCellCorrect(int x, int y){

        boolean result = true;
        if(x < 0 || y < 0 || x >= SIZE || y >= SIZE){
            result = false;
        }
        return result;
    }
    private static boolean isEndGame(char playerSymbol) {
        boolean result = false;
        printMap();
        if (checkWin(playerSymbol)) {
            System.out.println("Победители " + playerSymbol);
            result = true;
        }
        if (isMapFull()) {
            System.out.println("Ничья");
            result = true;
        }
        return result;
    }
    private static boolean isMapFull() {
        boolean result = true;

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (map[i][j] == DOT_EMPTY)
                    result = false;
            }
        }
        return result;
    }
    private static boolean checkWin(char playerSymbol) {
        boolean result = false;
        if(
                        (map[0][0] == playerSymbol && map[0][1] == playerSymbol && map[0][2] == playerSymbol) ||
                        (map[1][0] == playerSymbol && map[1][1] == playerSymbol && map[1][2] == playerSymbol) ||
                        (map[2][0] == playerSymbol && map[2][1] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[0][0] == playerSymbol && map[1][0] == playerSymbol && map[2][0] == playerSymbol) ||
                        (map[0][1] == playerSymbol && map[1][1] == playerSymbol && map[2][1] == playerSymbol) ||
                        (map[0][2] == playerSymbol && map[1][2] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[0][0] == playerSymbol && map[1][1] == playerSymbol && map[2][2] == playerSymbol) ||
                        (map[2][0] == playerSymbol && map[1][1] == playerSymbol && map[0][2] == playerSymbol))
        {
            result = true;
        }
        return result;
    }
}



