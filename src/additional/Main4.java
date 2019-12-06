package additional;

public class Main4 {

    private static void print(int[][] arr) {
        for(int i = 0; i < arr.length; ++i) {
            for(int k = 0; k < arr[i].length; ++k) {
                System.out.printf("%2d ", arr[i][k]);
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void showDiagonals(int[][] a) {
        int len = a.length;
        for(int i = 0; i < len; ++i){
            for(int k = 0; k < len; ++k) {
                if(i == k) {
                    System.out.printf("%2d ", a[i][k]);
                } else if (len - 1 - k == i) {
                    System.out.printf("%2d ", a[i][k]);
                } else {
                    System.out.print("   ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void showEdges(int[][] a) {
        for(int i = 0; i < a[0].length; ++i) {
            System.out.printf("%2d ", a[0][i]);
        }
        System.out.println();
        for(int i = 1; i < a.length - 1; ++i) {
            System.out.printf("%2d ", a[i][0]);
            for(int k = 1; k < a[i].length - 1; ++k) {
                System.out.print("   ");
            }
            System.out.printf("%2d ", a[i][a[i].length - 1]);
            System.out.println();
        }
        for(int i = 0; i < a[a.length-1].length; ++i) {
            System.out.printf("%2d ", a[a.length-1][i]);
        }
        System.out.println("\n");
    }

    public static void main (String[] args) {
        int arr[][] = {{1,2,3},{4,5,6},{7,8,9}};
        int arr2[][] = {{1,2,3,4,5,6},{4,5,6,7,8,9},{7,8,9,10,11,12}};

        print(arr);
        showDiagonals(arr);
        showEdges(arr);
        print(arr2);
        showEdges(arr2);
    }

}
