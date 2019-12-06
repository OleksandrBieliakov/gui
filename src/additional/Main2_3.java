package additional;

public class Main2_3 {

    private static void evenNumbers(int[] arr) {
        for(int n : arr) {
            if(n%2 == 0) {
                System.out.print(n + " ");
            }
        }
    }

    private static void oddIndexes(int[] arr) {
        for(int i = 1; i < arr.length; i += 2) {
            System.out.print(arr[i] + " ");
        }
    }

    private static void print(int[][] arr) {
        for(int i = 0; i < arr.length; ++i) {
            for(int k = 0; k < arr[i].length; ++k) {
                System.out.print(arr[i][k] + " ");
            }
            System.out.println();
        }
    }

    public static void main (String[] args) {
        int[] arr = new int[10];
        for(int i = 0; i < arr.length; ++i) {
            arr[i] = (int)((11-2)*Math.random()) + 3;
            System.out.print(arr[i] + " ");
        }
        System.out.println();
        evenNumbers(arr);
        System.out.println();
        oddIndexes(arr);
        System.out.println("\n");

        int[][] brr = new int[5][];
        for(int i = 0; i < brr.length; ++i) {
            brr[i] = new int[(int)((5-2)*Math.random())+3];
            for(int k = 0; k < brr[i].length; ++k) {
                brr[i][k] = (int)(21*Math.random());
                System.out.print(brr[i][k] + " ");
            }
            System.out.println();
        }
        System.out.println();
        print(brr);

    }
}
