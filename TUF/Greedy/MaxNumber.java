public class MaxNumber {    // Max 69 Number
    public static void main(String[] args) {

        int n = 9669;
        int ans = max69NumberOptimized(n);
        System.out.println(ans);

    }

    static int max69Number(int num) {

        StringBuilder sb = new StringBuilder(Integer.toString(num));

        for (int i = 0; i < sb.length(); i++) {
            
            if (sb.charAt(i) == '6') {
                sb.setCharAt(i, '9');
                break;
            }
        }

        return Integer.parseInt(sb.toString());
    }

    static int max69NumberOptimized(int num) {
        int temp = num;
        int placeValue = 1;
        int add = 0;

        while (temp > 0) {
            if (temp % 10 == 6) {
                add = 3 * placeValue;
            }
            temp /= 10;
            placeValue *= 10;
        }

        return num + add;
    }

}
