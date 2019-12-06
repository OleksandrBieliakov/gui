package calculator2;


import java.awt.*;
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.DataFormatException;

class Polynomial {

    boolean isDisplayed;
    Color color;
    String text;
    LinkedList<PolyElement> elements = new LinkedList<>();

    Polynomial(String s) throws DataFormatException{
            extractElements(s);
            color = Color.BLACK;
            isDisplayed = true;
    }

    int getY(int x) {
        int y = 0;
        for (PolyElement e : elements) {
            if(e.hasX) y += (Math.pow(x, e.power))*e.ind;
            else y+= e.ind;
        }
        return y;
    }

    void extractElements(String s) throws DataFormatException {
        s = s.trim();
        LinkedList<PolyElement> newPoly = new LinkedList<>();
        Pattern p = Pattern.compile("([-+]?\\d*\\.?\\d*)([xX]?\\^?)(\\d*)");
        Matcher m = p.matcher(s);
        String comp = "";
        String indexS = "";
        String xS = "";
        String powerS = "";
        double indexN = 1;
        boolean hasX = false;
        int powerN = 1;
        while (m.find()) {
            indexS = m.group(1);
            xS = m.group(2);
            powerS = m.group(3);
            if(!indexS.equals("")) {
                if(indexS.equals("-")) indexN = -1;
                else indexN = Double.parseDouble(indexS);
            }
            if(!xS.equals("")) hasX = true;
            if(!powerS.equals("")) powerN = Integer.parseInt(powerS);

            if((indexS + xS + powerS).equals("")) break;

           // System.out.println(indexN + " " + hasX + " " + powerN);

            newPoly.add(new PolyElement(indexN, hasX, powerN));
            comp = comp + indexS + xS + powerS;
            indexN = 1;
            hasX = false;
            powerN = 1;
        }
        if(!s.equals(comp)) throw new DataFormatException();
        text = s;
        elements = newPoly;
    }

}
