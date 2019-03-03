import java.util.Stack;
import java.util.ArrayList;

public class Main
{
    public static void main(String[] args)
    {
        GUI app = new GUI();
        app.setVisible(true);
    }

    public static String micromain(String input)
    {
        ArrayList<String> buf = new ArrayList();
        buf = string_to_array(input);
        Stack<Double> stack_nubr = new Stack<>();
        Stack<String> stack_sign = new Stack<>();


        if (buf.get(0)=="Error input!") return "Error input!";

        for (int i = 0; i < buf.size(); i++) {
            if (!(buf.get(i).equals("+") | buf.get(i).equals("-") | buf.get(i).equals("/") | buf.get(i).equals("*") | buf.get(i).equals("(") | buf.get(i).equals(")"))) {
                stack_nubr.add(Double.parseDouble(buf.get(i)));
            }
            if (buf.get(i).equals("(")) {
                stack_sign.add("(");
            }

            if (buf.get(i).equals(")")) {
                while (!(stack_sign.peek().equals("("))) {
                    stack_nubr.add(oper(stack_nubr.pop(), stack_nubr.pop(), stack_sign.pop()));
                }
                stack_sign.pop();
            }


            if (buf.get(i).equals("+") | buf.get(i).equals("-"))
                if (stack_sign.size() == 0) stack_sign.add(buf.get(i));
                else {
                    if (stack_sign.peek().equals("(")) stack_sign.add(buf.get(i));
                    else
                        switch (priority(buf.get(i)) - priority(stack_sign.peek())) {
                            case 0:
                                stack_nubr.add(oper(stack_nubr.pop(), stack_nubr.pop(), stack_sign.pop()));
                                i--;
                                break;

                            case -1:
                                stack_nubr.add(oper(stack_nubr.pop(), stack_nubr.pop(), stack_sign.pop()));
                                i--;
                                break;
                        }
                }

            if (buf.get(i).equals("/") | buf.get(i).equals("*")) {

                if (stack_sign.size() == 0) stack_sign.add(buf.get(i));
                else {
                    switch (priority(buf.get(i)) - priority(stack_sign.peek())) {
                        case 0:
                            stack_nubr.add(oper(stack_nubr.pop(), stack_nubr.pop(), stack_sign.pop()));
                            i--;

                            break;
                        case 1:

                            stack_sign.add(buf.get(i));
                           
                            break;
                        case 2:

                            stack_sign.add(buf.get(i));

                            break;
                    }
                }
            }




        }

        while(stack_sign.size() != 0) {

            stack_nubr.add(oper(stack_nubr.pop(), stack_nubr.pop(), stack_sign.pop()));
        }
        if (stack_nubr.size()==1 & stack_sign.size()==0)
            return (Double.toString(stack_nubr.pop()));
        else return ("Error!");
    }

    public static int priority(String x) {
        switch(x) {
            case "(": return 0;
            case ")": return 0;
            case "+": return 1;
            case "-": return 1;
            case "*": return 2;
            case "/": return 2;
        }
        return 0;
    }

    public static Double oper(Double a, Double b,String sign) {
        switch (sign){
            case "+": return b + a;
            case "-": return b - a;
            case "*": return b * a;
            case "/": return b / a;
        }
        return 0.0;
    }

    public static ArrayList string_to_array(String x){
        ArrayList<String> buf = new ArrayList();
        String buf1 = "";
        Boolean flag_sign = false;
        for (int i =0; i< x.length(); i++)
        {
            if (!(x.charAt(i) == '.' | x.charAt(i) == ' ' |x.charAt(i) == '1' |x.charAt(i) == '0' |x.charAt(i) == '2' |x.charAt(i) == '3' | x.charAt(i) == '4' | x.charAt(i) == '5' | x.charAt(i) == '6' | x.charAt(i) == '7' | x.charAt(i) == '8' | x.charAt(i) == '9' | x.charAt(i) == '(' | x.charAt(i) == ')' | x.charAt(i) == '+' | x.charAt(i) == '-' | x.charAt(i) == '/' |x.charAt(i) == '*')) {
                if(buf.size()<1)
                    buf.add("Error input!");
                else
                    buf.set(0, "Error input!");


                return (buf);
            }
            if (x.charAt(i) == ' ')
                i++;

            if ((x.charAt(i) == '-' & i ==0)) {
                flag_sign = true;
                buf1 += x.charAt(i);
                i++;
            }
            else
            {
                if (i>0)
                    if (x.charAt(i) == '-' &( x.charAt(i-1) == '+' | x.charAt(i-1) == '*' | x.charAt(i-1) == '/' |x.charAt(i-1) == '(')) {
                        flag_sign = true;
                        buf1 += Character.toString(x.charAt(i));
                        i++;
                    }

            }

            if (x.charAt(i) == '+' | (x.charAt(i) == '-' & flag_sign == false) | x.charAt(i) == '*' | x.charAt(i) == '/' |x.charAt(i) == '(' |x.charAt(i) == ')')
            {
                if (buf1 != "")
                {
                    buf.add(buf1);
                    buf1 = "";
                }
                buf.add(Character.toString(x.charAt(i)));
            } else
                buf1 +=x.charAt(i);
            flag_sign=false;
        }
        if(buf1!="")
            buf.add(buf1);
        return buf;
    }
}
