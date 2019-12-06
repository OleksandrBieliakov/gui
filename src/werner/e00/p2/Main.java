package werner.e00.p2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Main {

    private static void printLine(Path file, int line, int position) {
        try (
                BufferedReader br = Files.newBufferedReader(file, UTF_8)
        ) {
            for(int i = 1; i < line; ++i) {
                br.readLine();
            }
            System.out.println(br.readLine());
            for(int i = 0; i < position - 1; ++i)
                System.out.print(" ");
            System.out.println('^');

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void checkError(Path file, ParenStack stack, int line, int pos, A_Type rightPar) {
        if(!stack.empty()) {
            A_Type leftPar = stack.pop();
            if(leftPar != rightPar) {
                printLine(file, line, pos);
                System.out.println("ERROR in line " + line + ". \'" + rightPar.getPar2() + "\' found, but \'" + leftPar.getPar1() + "\' expected.\n");
            }
        } else {
            printLine(file, line, pos);
            System.out.println("ERROR in line " + line + ". \'" + rightPar.getPar2() + "\' found when there is no ordered opening bracket for it.\n");
        }
    }


    public static void main (String[] args) {

        String fileName = "data/gui/werE01P2_in.txt";
        Path file = Paths.get(fileName);

        try (
                BufferedWriter bw = Files.newBufferedWriter(file, UTF_8)
        ) {
            bw.write("}Warsaw(){\n" +
                    "London[xxxx} (\n" +
                    "Madrid Paris)}\n" +
                    "Berlin[(");
        } catch (IOException e) {
            e.printStackTrace();
        }

        ParenStack stack = new ParenStack();
        A_Type t1 = new A_Type('(' , ')');
        A_Type t2 = new A_Type('[', ']');
        A_Type t3 = new A_Type('{' , '}');
        A_Type[] types = {t1, t2, t3};
         int line = 1;
        int position = 0;


        try (
                BufferedReader br = Files.newBufferedReader(file, UTF_8)
        ) {
            int ch;
            while((ch = br.read()) != -1) {
                ++position;
                for(A_Type t : types) {
                    if((char)ch == t.getPar1()){
                        stack.push(t);
                        break;
                    } else if((char)ch == t.getPar2()) {
                        checkError(file, stack, line, position, t);
                        break;
                    }
                }
                if((char)ch == '\n') {
                    ++line;
                    position = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        if(!stack.empty()) {
            System.out.print("Unclosed brackets: ");
            while(!stack.empty()) {
                System.out.print(stack.pop().getPar1() + " ");
            }
        }

    }

}
