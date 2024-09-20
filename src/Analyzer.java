import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Analyzer {
    private static final String RUSSIAN_LETTERS = "абвгдеёжзийклмнопрстуфхцчшщъыьэюя";

    public static void main(String[] args) {
        // Хранение частоты встречаемости символов
        HashMap<Character, Integer> charFrequencyMap = new HashMap<>();

        // Путь к файлу с анализируемым текстом
        String analyzedFilePath = "src/OriginalText.txt";

        // Чтение зашифрованного текста и подсчет частоты символов
        try (FileReader reader = new FileReader(analyzedFilePath)) {
            int character;
            // Читаем каждый символ из файла
            while ((character = reader.read()) != -1) {
                char currentChar = Character.toLowerCase((char) character); // Преобразуем к нижнему регистру
                // Увеличиваем счетчик для символа
                charFrequencyMap.put(currentChar, charFrequencyMap.getOrDefault(currentChar, 0) + 1);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Сортировка символов по убыванию частоты встречаемости
        List<Map.Entry<Character, Integer>> sortedCharList = new ArrayList<>(charFrequencyMap.entrySet());
        sortedCharList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));


        for (Map.Entry<Character, Integer> entry : sortedCharList) {
            char currentChar = entry.getKey();
            // Если символ является русской буквой
            if (RUSSIAN_LETTERS.contains(Character.toString(currentChar))) {
                System.out.print(Character.toString(currentChar));
             }
        }
    }
}
