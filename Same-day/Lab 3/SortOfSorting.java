import java.util.*;

public class SortOfSorting{
    public static void main(String[] args) {
        Kattio k = new Kattio(System.in, System.out);
        int n;
        boolean first = true;
        
        while ((n = k.getInt()) > 0) {
            if (!first){
                k.println();
            }else{
                first = false;
            }

            String[] names = new String[n];
            for (int i = 0; i < n; i++){
                names[i] = k.getWord();
            }

            Arrays.sort(names, Comparator.comparing(s -> s.substring(0, 2)));
            for (String s : names){
                k.println(s);
            }
        }

        k.close();
    }
}
