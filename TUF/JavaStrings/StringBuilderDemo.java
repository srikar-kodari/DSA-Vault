public class StringBuilderDemo {    //Comparing time for String & StringBuilder
    public static void main(String[] args) {
        int n = 1000000;
        
        // SLOW: O(n²) - creates new string each iteration
        long start = System.currentTimeMillis();

        @SuppressWarnings("unused")
        String s = "";  //Getting warning for 's' declaration because each iteration creates a new String object

        for (int i = 0; i < n; i++) {
            s += "a"; // Copies entire string each time!
        }
        long time1 = System.currentTimeMillis() - start;
        
        // FAST: O(n) - appends to internal buffer
        start = System.currentTimeMillis();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append("a");
        }
        //String result = sb.toString();

        long time2 = System.currentTimeMillis() - start;
        
        System.out.println("String concatenation: " + time1 + "ms");
        System.out.println("StringBuilder: " + time2 + "ms");
        // StringBuilder is typically 100-1000x faster for large n
    }
}