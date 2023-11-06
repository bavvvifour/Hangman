import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    private static int LIVES = 5;

    public static void main(String[] args) throws IOException {
        initGame();
    }

    public static void initGame() throws IOException {
        String unknownWorld = randomWord();
        System.out.println(unknownWorld);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Нажмите [E]nter, чтобы начать игру.");
        System.out.println("Нажмите [C]lose, чтобы закончить игру.");
        char input = scanner.next().toLowerCase(Locale.ROOT).charAt(0);

        if (input == 'е') {
            guessWord(unknownWorld);

        } else if (input == 'с') {
            System.out.println("Приходите еще :-)");
        } else {
            System.out.println("Введите другую букву");
            initGame();
        }

    }

    public static String randomWord() throws IOException {
        ArrayList<String> arrayList = new ArrayList<>();
        Scanner scanner = new Scanner(new FileReader("src/words.txt"));
        while (scanner.hasNext()) {
            arrayList.add(scanner.nextLine());
        }
        Random random = new Random();
        return arrayList.get(random.nextInt(arrayList.size()));
    }

    public static void guessWord(String unknownWord) throws IOException {
        StringBuilder emptyWordBuilder = new StringBuilder();
        int sizeWord = unknownWord.length();

        emptyWordBuilder.append("*".repeat(sizeWord));

        String emptyWord = emptyWordBuilder.toString();
        System.out.println(emptyWord);

        while (LIVES != 0 && !emptyWord.equals(unknownWord)) {
            Scanner scanner = new Scanner(System.in);
            char input = scanner.next().toLowerCase(Locale.ROOT).charAt(0);

            char[] charsUnknownWord = unknownWord.toCharArray();
            char[] emptyCharsUnknownWord = emptyWord.toCharArray();

            if (unknownWord.contains(String.valueOf(input))) {
                for (int i = 0; i < sizeWord; i++) {
                    if (charsUnknownWord[i] == input) {
                        emptyCharsUnknownWord[i] = input;
                    }
                }
                emptyWord = new String(emptyCharsUnknownWord);
                System.out.println(emptyWord);
            } else {
                LIVES -= 1;
                System.out.println("Упс, ошибка. У вас осталось " + LIVES);
            }
        }
        if (emptyWord.equals(unknownWord)) {
            System.out.println("Поздравляю, вы победили!");
            System.out.println("Желаете начать новую игру?");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Если да, то нажмите 'Д'.");
            System.out.println("Если нет, то нажмите 'Н'.");
            char input = scanner.next().toLowerCase(Locale.ROOT).charAt(0);

            if (input == 'д') {
                initGame();
            } else if (input == 'н') {
                System.out.println("Приходите еще :-)");
            } else {
                System.out.println("Введите другую букву");
            }
        } else {
            System.out.println("Вы проиграли!");
            System.out.println("Желаете начать новую игру?");
            Scanner scanner = new Scanner(System.in);
            System.out.println("Если да, то нажмите 'Д'.");
            System.out.println("Если нет, то нажмите 'Н'.");
            char input = scanner.next().toLowerCase(Locale.ROOT).charAt(0);

            if (input == 'д') {
                initGame();
            } else if (input == 'н') {
                System.out.println("Приходите еще :-)");
            } else {
                System.out.println("Введите другую букву");
            }
        }
    }
}