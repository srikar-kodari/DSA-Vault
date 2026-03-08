public class LemonadeChange {
    public static void main(String[] args) {
        
        int[] arr = {5,5,5,10,20};
        boolean ans = lemonadeChange1(arr);
        System.out.println(ans);

    }

    static boolean lemonadeChange(int[] bills) {

        if(bills[0] == 10 || bills[0] == 20) return false;

        int n = bills.length;
        int[] currency = new int[3];    // {5,10,20}

        for(int i=0; i<n; i++) {

            if(bills[i] == 5) currency[0]++;

            else if(bills[i] == 10) {

                if(currency[0] > 0) {
                    currency[0]--;
                    currency[1]++;
                }
                else return false;
            }

            else {

                if(currency[1] > 0 && currency[0]>0) {
                    currency[0]--;
                    currency[1]--;
                    currency[2]++;
                }
                else if(currency[0] >= 3) {
                    currency[0] = currency[0] - 3;
                    currency[2]++;
                }
                else return false;
            }
        }

        return true;
    }

    // TC: O(N), SC(1)
    static boolean lemonadeChange1(int[] bills) {

        if(bills[0] == 10 || bills[0] == 20) return false;

        int n = bills.length;
        int five = 0, ten = 0;

        for(int i=0; i<n; i++) {

            if(bills[i] == 5) five++;

            else if(bills[i] == 10) {

                if(five > 0) {
                    five--;
                    ten++;
                }
                else return false;
            }

            else {

                if(ten > 0 && five>0) {
                    five--;
                    ten--;
                }
                else if(five >= 3) five -= 3;
                else return false;
            }
        }

        return true;
    }

}
