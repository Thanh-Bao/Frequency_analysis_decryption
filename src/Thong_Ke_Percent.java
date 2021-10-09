import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Thong_Ke_Percent {

    public static final int TOTAL_LETTERS_ALPHABET = 26;
    public static final int ASCII_CODE_OF_A = 65;

    public static String[] initAlphabet() {
        String[] alphabet = new String[TOTAL_LETTERS_ALPHABET];
        int index = 0;
        for (int i = ASCII_CODE_OF_A; i < ASCII_CODE_OF_A + TOTAL_LETTERS_ALPHABET; i++) {
            alphabet[index] = (char) i + "";
            index++;
        }
        return alphabet;
    }

    public static String[] initBo2KyTu() {
        String[] alphabet = new String[TOTAL_LETTERS_ALPHABET * TOTAL_LETTERS_ALPHABET];
        int index = 0;
        for (int i = ASCII_CODE_OF_A; i < ASCII_CODE_OF_A + TOTAL_LETTERS_ALPHABET; i++) {
            for (int j = ASCII_CODE_OF_A; j < ASCII_CODE_OF_A + TOTAL_LETTERS_ALPHABET; j++) {
                alphabet[index] = (char) i + "" + (char) j;
                index++;
            }
        }
        return alphabet;
    }

    public static String[] initDsVan3KyTu() {
        String[] alphabet = new String[TOTAL_LETTERS_ALPHABET * TOTAL_LETTERS_ALPHABET * TOTAL_LETTERS_ALPHABET];
        int index = 0;
        for (int i = ASCII_CODE_OF_A; i < ASCII_CODE_OF_A + TOTAL_LETTERS_ALPHABET; i++) {
            for (int j = ASCII_CODE_OF_A; j < ASCII_CODE_OF_A + TOTAL_LETTERS_ALPHABET; j++) {
                for (int x = ASCII_CODE_OF_A; x < ASCII_CODE_OF_A + TOTAL_LETTERS_ALPHABET; x++) {
                    alphabet[index] = (char) i + "" + (char) j + (char) x;
                    index++;
                }
            }
        }
        return alphabet;
    }

    public static LinkedHashMap<String, Double> thongKeTanSoTheoKyTu(String input, String[] elements) {
        long size = 0;
        LinkedHashMap<String, Double> table = new LinkedHashMap<String, Double>();
        // count
        for (String e : elements) {
            Pattern pattern = Pattern.compile(e + "");
            Matcher matcher = pattern.matcher(input);
            long matches = matcher.results().count();
            size += matches;
            table.put(e, (double) matches);
        }
        // tính %
        for (String e : elements) {
            table.put(e, (double) Math.round((table.get(e) * 1.0) * 100 / size * 10) / 10);
        }
        //sort
        LinkedHashMap<String, Double> reverseSortedMap = new LinkedHashMap<>();
        table.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .forEachOrdered(x -> reverseSortedMap.put(x.getKey(), x.getValue()));

        return reverseSortedMap;
    }

    public static String printMap(Map map, int numberLine) {
        StringBuilder str = new StringBuilder();
        int currentLine = 1;
        for (Object ch : map.keySet()) {
            str.append(ch + " ==> " + map.get(ch) + " % \n");
            currentLine++;
            if (currentLine > numberLine) {
                break;
            }
        }
        return str.toString();
    }



    public static void main(String[] args) {
        String input = "đây_là_email_nông_lâm@gmail.com";

        System.out.println(printMap(thongKeTanSoTheoKyTu(input, initAlphabet()), 30));
        System.out.println(printMap(thongKeTanSoTheoKyTu(input, initBo2KyTu()), 10));
        System.out.println(printMap(thongKeTanSoTheoKyTu(input, initDsVan3KyTu()),10));


    }
}





