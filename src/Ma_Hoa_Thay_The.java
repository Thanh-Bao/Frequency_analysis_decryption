import java.sql.Array;
import java.util.*;

public class Ma_Hoa_Thay_The {

    public static final int TOTAL_LETTERS_ALPHABET = 26;
    public static final int ASCII_CODE_OF_A = 65;
    public static final int ASCII_CODE_OF_Z = 90;

    private static char randomLetter() {
        Random random = new Random();
        return (char) (random.nextInt(ASCII_CODE_OF_Z + 1 - ASCII_CODE_OF_A) + ASCII_CODE_OF_A);
    }

    public static String printKey(Map<Character, Character> keyPair) {
        StringBuilder key = new StringBuilder();
        for (char i : keyPair.values()) {
            key.append(i);
        }
        return key.toString();
    }

    public static Map<Character, Character> keyPairRandomGenerator() {
        Map<Character, Character> table = new HashMap<Character, Character>();
        ArrayList<Character> tempList = new ArrayList<Character>(); //chứa những số đã random ra rồi
        for (int i = ASCII_CODE_OF_A; i < ASCII_CODE_OF_A + TOTAL_LETTERS_ALPHABET; i++) {
            char value = randomLetter();
            while (tempList.contains(value)) { // random cho đến khi có giá trị không trùng
                value = randomLetter();
            }
            table.put((char) i, value);
            tempList.add(value);
        }
        return table;
    }

    public static String replaceEncrypt(String input, Map<Character, Character> keyPair) {
        StringBuilder result = new StringBuilder();
        for (char ch : input.toCharArray()) {
            if (keyPair.containsKey(ch)) { // chỉ mã hóa ký tự in hoa A->Z
                result.append(keyPair.get(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static String decrypt(String input, Map<Character, Character> keyPair) {
        StringBuilder result = new StringBuilder();
        // đảo ngược keyPair
        Map<Character, Character> tempMap = new HashMap<Character, Character>();
        for (char ch : keyPair.keySet()) {
            tempMap.put(keyPair.get(ch), ch);
        }
        // chỉ giải mã ký tự in hoa A->Z
        for (char ch : input.toCharArray()) {
            if (tempMap.containsKey(ch)) {
                result.append(tempMap.get(ch));
            } else {
                result.append(ch);
            }
        }
        return result.toString();
    }

    public static void main(String args[]) {
        Map<Character, Character> keyPair = keyPairRandomGenerator();
        String input = "đây_là_email_nông_lâm@gmail.com";
        input = Thong_Ke_Percent.xoaDauAndUppercase(input);
        System.out.println("Random key: " + printKey(keyPair));
        System.out.println("*******************************************************************************************************");
        String output = replaceEncrypt(input, keyPair);
        System.out.println(output);

    }
}
