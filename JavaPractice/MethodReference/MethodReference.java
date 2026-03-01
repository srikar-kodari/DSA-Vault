interface Parser {
    String parse(String str);
}

class StringParser {
    public static String convert(String s) {

        if(s.length() >= 3) s = s.toUpperCase();
        else s = s.toLowerCase();

        return s;
    }
}

class MyPrinter {
    public void print(String str, Parser p) {

        str = p.parse(str);
        System.out.println(str);
    }
}

public class MethodReference {
    public static void main(String[] args) {
        
        String s = "Hello Srikar..";
        MyPrinter mp = new MyPrinter();

        // 1
        mp.print(s, new Parser() {

            public String parse(String str) {
                return StringParser.convert(str);
            }
            
        });

        // 2
        mp.print(s, str -> StringParser.convert(str));  // LAMBDA EXPRESSION FOR ABOVE CODE

        // 3
        mp.print(s, StringParser::convert);     // METHOD REFERENCE USING ::



    }
    
}
