public class AddDigits {
    public static void main(String[] args) {

        System.out.println(addDigits(98));
        System.out.println(addDigitsDigitalRoot(98)); // 8

    }

    public static int addDigits(int num) {

        int dup = num;
        while(true) {

            int sum = 0;
            while(dup > 0) {
                int digit = dup % 10;
                sum += digit;

                dup = dup / 10;
            }

            if(sum / 10 == 0) return sum;

            dup = sum;
        }
    }

    // Digital root solution (for num >= 0)
    public static int addDigitsDigitalRoot(int num) {
        if (num == 0) return 0;
        return 1 + (num - 1) % 9;
    }
}
