import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class T7 {
    public static List<Integer> findFirstIndices(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s == null || p == null || p.length() == 0) {
            return result;
        }

        Set<Character> visited = new HashSet<>();
        Set<Integer> addedIndices = new HashSet<>();

        for (char c : p.toCharArray()) {
            if (!visited.contains(c)) {
                visited.add(c);

                for (int i = 0; i < s.length(); i++) {
                    if (s.charAt(i) == c && !addedIndices.contains(i)) {
                        result.add(i);
                        addedIndices.add(i);
                        break;
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        String s1 = "abwctrbca";
        String p1 = "abc";
        List<Integer> result1 = findFirstIndices(s1, p1);
        System.out.println(result1);

        String s2 = "abscd";
        String p2 = "bca";
        List<Integer> result2 = findFirstIndices(s2, p2);
        System.out.println(result2);
    }
}
