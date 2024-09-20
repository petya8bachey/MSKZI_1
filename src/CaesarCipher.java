import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CaesarCipher {

    // Метод для шифрования символа с использованием шифра Цезаря (русский алфавит)
    public static char encryptWithCaesarCipher(char character, int shift) {
        if (character >= 'А' && character <= 'Я') {
            // Обработка заглавных букв русского алфавита
            return (char) ((character - 'А' + shift + 33) % 33 + 'А');
        } else if (character >= 'а' && character <= 'я') {
            // Обработка строчных букв русского алфавита
            return (char) ((character - 'а' + shift + 33) % 33 + 'а');
        }
        // Если символ не является русской буквой, возвращаем его без изменений
        return character;
    }

    public static void main(String[] args) {
        // Путь к исходному файлу с текстом
        String originalFilePath = "src/OriginalText.txt";
        // Путь к файлу для записи зашифрованного текста
        String encryptedFilePath = "src/EncryptedText.txt";
        int shiftValue = 5;  // Количество позиций для сдвига шифра

        // Чтение исходного текста и запись зашифрованного текста
        try (FileReader reader = new FileReader(originalFilePath);
             FileWriter writer = new FileWriter(encryptedFilePath)) {

            int character;
            // Читаем символ за символом
            while ((character = reader.read()) != -1) {
                // Применяем шифр Цезаря к каждому символу
                writer.write(encryptWithCaesarCipher((char) character, shiftValue));
            }
        } catch (IOException e) {
            // Обработка ошибок чтения или записи файла
            e.printStackTrace();
        }
    }
}
