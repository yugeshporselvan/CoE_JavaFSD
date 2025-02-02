public class T5 {


    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }


    public static int countOccurrences(String text, String sub) {
        int count = 0;
        int index = 0;
        while ((index = text.indexOf(sub, index)) != -1) {
            count++;
            index += sub.length();
        }
        return count;
    }


    public static String splitAndCapitalize(String str) {
        String[] words = str.split(" ");
        StringBuilder capitalizedString = new StringBuilder();
        for (String word : words) {
            if (!word.isEmpty()) {
                capitalizedString.append(Character.toUpperCase(word.charAt(0)))
                        .append(word.substring(1)).append(" ");
            }
        }
        return capitalizedString.toString().trim();
    }


    public static void main(String[] args) {
        String testString = "hello world";
        System.out.println("Reversed: " + reverseString(testString));
        System.out.println("Occurrences of 'l': " + countOccurrences(testString, "l"));
        System.out.println("Capitalized: " + splitAndCapitalize(testString));
    }
}