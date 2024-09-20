import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Main {
    // Часто встречающиеся русские буквы по убыванию частоты
    private static final String FREQUENT_RUSSIAN_LETTERS = "оиатенсрлвпдмьзябушыфкчющгйцжхэ";

    public static void main(String[] args) {
        // Хранение частоты встречаемости символов
        HashMap<Character, Integer> charFrequencyMap = new HashMap<>();

        // Путь к файлу с зашифрованным текстом
        String encryptedFilePath = "src/EncryptedText.txt";
        // Путь к файлу с расшифрованным текстом
        String decryptedFilePath = "src/DecryptedText.txt";

        // Чтение зашифрованного текста и подсчет частоты символов
        try (FileReader reader = new FileReader(encryptedFilePath)) {
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

        // Сопоставление зашифрованных символов с русскими буквами на основе частоты
        HashMap<Character, Character> decryptionMap = new HashMap<>();

        int russianLetterIndex = 0;
        for (Map.Entry<Character, Integer> entry : sortedCharList) {
            char currentChar = entry.getKey();
            // Если символ является русской буквой
            if (FREQUENT_RUSSIAN_LETTERS.contains(Character.toString(currentChar))) {
                // Сопоставляем текущий символ с соответствующей русской буквой
                decryptionMap.put(currentChar, FREQUENT_RUSSIAN_LETTERS.charAt(russianLetterIndex));
                russianLetterIndex++;
            } else {
                // Если символ не является русской буквой (например, цифры или пробелы), оставляем его как есть
                decryptionMap.put(currentChar, currentChar);
            }
        }

        // Чтение зашифрованного файла и запись расшифрованного текста в новый файл
        try (FileReader reader = new FileReader(encryptedFilePath); FileWriter writer = new FileWriter(decryptedFilePath)) {
            int character;
            // Читаем каждый символ из зашифрованного файла
            while ((character = reader.read()) != -1) {
                char originalChar = (char) character; // Сохраняем исходный символ
                char lowerChar = Character.toLowerCase(originalChar); // Преобразуем символ к нижнему регистру

                // Если символ присутствует в словаре расшифровки, восстанавливаем регистр
                if (decryptionMap.containsKey(lowerChar)) {
                    char decryptedChar = decryptionMap.get(lowerChar);

                    // Восстанавливаем исходный регистр
                    if (Character.isUpperCase(originalChar)) {
                        decryptedChar = Character.toUpperCase(decryptedChar);
                    }

                    // Записываем расшифрованный символ с учётом регистра
                    writer.write(decryptedChar);
                } else {
                    // Если символ не подлежит расшифровке, записываем его как есть
                    writer.write(originalChar);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
