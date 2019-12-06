package additional;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class Main7 {

    public static void main(String [] args) {

        File fileIn = new File("data/gui/ad_0_t7");

        try (
                Scanner sc = new Scanner(fileIn);
                PrintWriter wr = new PrintWriter("data/gui/ad_0_t7_B.txt", "UTF-8"); )
        {
            String st;
            double d1, d2;
            while (sc.hasNextLine()) {
                d1 = Double.parseDouble(sc.next());
                d2 = Double.parseDouble(sc.next());
                System.out.println(d1 + " " + d2);
                wr.println((int)(d1+d2));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }
}
