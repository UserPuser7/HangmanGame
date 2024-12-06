import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;

public class HangmanGame {
    private static final HashMap<String, String> CATEGORIES = new HashMap<>();

    static {
        CATEGORIES.put("Животные", "src/animals.txt");
        CATEGORIES.put("Еда", "src/food.txt");
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        //вывод категорий
        System.out.println("Выбирай любого животного, которого захочешь(ящерица):");
        for (String category : CATEGORIES.keySet()) {
            System.out.println("- " + category);
        }

        //ввод категории
        String selectedCategory = scanner.nextLine();
        if (!CATEGORIES.containsKey(selectedCategory)) {
            System.out.println("Вань, ты дурак совсем? Вот: мышь иг-рис-та-я");
            return;
        }

        //проверка на ашипку
        ArrayList<String> words = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(new File(CATEGORIES.get(selectedCategory)));
            while (fileScanner.hasNextLine()) {
                words.add(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Файл со словами не найден.");
            return;
        }

        // Выбор случайного слова для игры
        String randomWord = words.get(random.nextInt(words.size()));
        Word word = new Word(randomWord);
        Player player = new Player();

        // Вывод звездочек перед началом игры
        System.out.println("Загаданное слово: " + word.getMaskedWord());

        while (true) {
            System.out.println(word);
            HangmanDrawer.draw(player.getMistakes());

            if (player.getMistakes() >= 5) {
                System.out.println("Ты проиграл, дружище, но не печалься, вот то слово: " + word.getWord());
                break;
            }
            if (word.isGuessed()) {
                System.out.println("Ну ничего себе ты провидец, дружище! Ты угадал слово: " + word.getWord());
                break;
            }

            //ввод букв
            System.out.print("Если хочешь выйти отседова, жмай 'Ctrl + D'\nЧтобы продолжить пытки жмай букву: ");
            String input = scanner.nextLine();
            if (input.length() != 1) {
                System.out.println("Дружище, куда ты жмал, вводи по одной букве");
                continue;
            }
            char letter = input.charAt(0);

            //проверка буквы
            if (!word.guessLetter(letter)) {
                player.makeMistake();
            }
        }
    }
}