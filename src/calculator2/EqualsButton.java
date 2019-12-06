package calculator2;

import java.awt.*;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class EqualsButton extends CalButton {

    private static NumSyst numSyst;

    EqualsButton(String title, Calculator cal) {
        super(title, cal);
        setBackground(Color.ORANGE);
        setForeground(Color.BLACK);
    }

    private static Matcher getMatcher(String str) {
        Pattern p = Pattern.compile("-?\\d+\\.*\\d*");
        return p.matcher(str);
    }

    private static String parseFirstNumber(String str) {
        String res = "";
        Matcher matcher = getMatcher(str);
        if (matcher.find()) {
            res = matcher.group();
        }
        return res;
    }

    private static String parseLastNumber(String str) {
        String res = "";
        Matcher matcher = getMatcher(str);
        while (matcher.find()) {
            res = matcher.group();
        }
        return res;
    }

    private static int appearsFirs(String str, char a, char b) {
        int aInd = str.indexOf(a);
        int bInd = str.indexOf(b);
        if (aInd == -1) return bInd;
        else if (bInd != -1) return aInd < bInd ? aInd : bInd;
        return aInd;
    }

    private static int appearsFirstMinus(String str) {
        int minInd = str.indexOf("-");
        int plusInd = str.indexOf("+");
        if (minInd == -1) return plusInd;
        if (minInd == 0) {
            int secondRound = appearsFirstMinus(str.substring(1));
            if (secondRound == -1) return secondRound;
            return secondRound + 1;
        }
        if (plusInd != -1) return minInd < plusInd ? minInd : plusInd;
        return minInd;
    }

    private static int getFrom(String str, String symb) {
        int i = -1;
        switch (symb) {
            case "^":
                i = str.indexOf('^');
                break;
            case "*/":
                i = appearsFirs(str, '*', '/');
                break;
            case "+-":
                i = appearsFirstMinus(str);
        }
        return i;
    }

    private static String twoValOper(String str, String symb) {
        String val1, val2, res = "";
        int val1Len, val2Len, from;
        char op;

        from = getFrom(str, symb);

        while (from != -1) {
            try {
                op = str.charAt(from);
                val1 = parseLastNumber(str.substring(0, from));
                val1Len = val1.length();
                val2 = parseFirstNumber(str.substring(from + 1));
                val2Len = val2.length();
                switch (op) {
                    case '^':
                        res = "" + Math.pow(Double.parseDouble(val1), Double.parseDouble(val2));
                        break;
                    case '*':
                        res = "" + Double.parseDouble(val1) * Double.parseDouble(val2);
                        break;
                    case '/':
                        res = "" + Double.parseDouble(val1) / Double.parseDouble(val2);
                        break;
                    case '+':
                        res = "" + (Double.parseDouble(val1) + Double.parseDouble(val2));
                        break;
                    case '-':
                        res = "" + (Double.parseDouble(val1) - Double.parseDouble(val2));
                }
            } catch (Exception e) {
                return "ERROR";
            }
            if (from + val2Len + 1 != str.length()) {
                str = str.substring(0, from - val1Len) + res + str.substring(from + val2Len + 1);
            } else {
                str = str.substring(0, from - val1Len) + res;
            }
            from = getFrom(str, symb);
        }
        return str;
    }

    private static String calculate(String str) {
        int from = str.lastIndexOf("(");
        int to;
        while (from != -1) {
            to = str.indexOf(")", from + 1);
            if (to != str.length() - 1) {
                str = str.substring(0, from) + calculate(str.substring(from + 1, to)) + str.substring(to + 1);
            } else {
                str = str.substring(0, from) + calculate(str.substring(from + 1, to));
            }
            from = str.lastIndexOf("(");
        }

        String val1;
        int val1Len;
        from = str.lastIndexOf("\u221A");
        while (from != -1) {
            try {
                val1 = parseFirstNumber(str.substring(from + 1));
                val1Len = val1.length();
                val1 = "" + Math.sqrt(Double.parseDouble(val1));
            } catch (Exception e) {
                return "ERROR IN SQRT";
            }
            str = str.substring(0, from) + val1 + str.substring(from + val1Len + 1);
            from = str.lastIndexOf("\u221A");
        }

        from = str.indexOf("%");
        while (from != -1) {
            try {
                val1 = parseLastNumber(str.substring(0, from));
                val1Len = val1.length();
                val1 = "" + Double.parseDouble(val1) / 100;
            } catch (Exception e) {
                return "ERROR";
            }
            if (from + 1 != str.length()) {
                str = str.substring(0, from - val1Len) + val1 + str.substring(from + 1);
            } else {
                str = str.substring(0, from - val1Len) + val1;
            }
            from = str.indexOf("%");
        }

        str = twoValOper(str, "^");
        str = twoValOper(str, "*/");
        str = twoValOper(str, "+-");

        return str;
    }

    private static String completeInput(String str) {
        TreeSet<Character> symbols = new TreeSet<>();
        symbols.add('(');
        symbols.add('\u221A');
        symbols.add('*');
        symbols.add('/');
        symbols.add('+');
        symbols.add('-');
        symbols.add('^');
        symbols.add('.');
        if (symbols.contains(str.charAt(str.length() - 1))) {
            str = str.substring(0, str.length() - 1);
        }
        int openBr = 0, closeBr = 0;
        Pattern p = Pattern.compile("[(]");
        Matcher matcher = p.matcher(str);
        while (matcher.find()) {
            openBr++;
        }
        p = Pattern.compile("[)]");
        matcher = p.matcher(str);
        while (matcher.find()) {
            closeBr++;
        }
        for (int i = openBr - closeBr; i > 0; i--) {
            str = str + ")";
        }
        return str;
    }

    private static String hexToDec(String str) {
        Pattern p = Pattern.compile("-?[0-9A-F]+");
        Matcher m = p.matcher(str);
        String s;
        String result = str;
        double num;
        int ind, len;
        while (m.find()) {
            s = m.group();
            ind = result.indexOf(s);
            len = s.length();
            num = Long.parseLong(s, 16);
            if (ind + len < result.length())
                result = result.substring(0, ind) + num + result.substring(ind + len);
            else result = result.substring(0, ind) + num;
        }
        return result;
    }

    private static String octToDec(String str) {
        Pattern p = Pattern.compile("-?[0-7]+");
        Matcher m = p.matcher(str);
        String s;
        String result = str;
        double num;
        int ind, len;
        while (m.find()) {
            s = m.group();
            ind = result.indexOf(s);
            len = s.length();
            num = Long.parseLong(s, 8);
            if (ind + len < result.length())
                result = result.substring(0, ind) + num + result.substring(ind + len);
            else result = result.substring(0, ind) + num;
        }
        return result;
    }

    private static String binToDec(String str) {
        Pattern p = Pattern.compile("-?[0-1]+");
        Matcher m = p.matcher(str);
        String s;
        String result = str;
        double num;
        int ind, len;
        while (m.find()) {
            s = m.group();
            ind = result.indexOf(s);
            len = s.length();
            num = Long.parseLong(s, 2);
            if (ind + len < result.length())
                result = result.substring(0, ind) + num + result.substring(ind + len);
            else result = result.substring(0, ind) + num;
        }
        return result;
    }

    @Override
    void addListener() {
        addActionListener(e -> {
            String strOriginal = cal.getOutput();
            numSyst = cal.getSystem();
            strOriginal = completeInput(strOriginal);
            String operated = strOriginal;
            String result = "ERROR", decRes = "ERROR";
            switch (numSyst) {
                case HEXADECIMAL:
                    operated = hexToDec(operated);
                    decRes = calculate(operated);
                    result = Integer.toHexString((int) Double.parseDouble(decRes));
                    result = result.toUpperCase();
                    break;
                case OCTAL:
                    operated = octToDec(operated);
                    decRes = calculate(operated);
                    result = Integer.toOctalString((int) Double.parseDouble(decRes));
                    break;
                case BINARY:
                    operated = binToDec(operated);
                    decRes = calculate(operated);
                    result = Integer.toBinaryString((int) Double.parseDouble(decRes));
                    break;
            }
            if (!numSyst.equals(NumSyst.DECIMAL)) {
                cal.updateHistory(strOriginal + "=" + result);
                cal.updateHistory("(" + operated + "=" + decRes + ")");
                cal.setOutput(result);
            } else {
                decRes = calculate(operated);
                cal.updateHistory(operated + "=" + decRes);
                cal.setOutput(decRes);
            }

        });
    }
}
