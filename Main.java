import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.println("Согласно правилу Шварцмана результат не должен привышать 3999!");
        System.out.print("Input: ");
        String expression = in.nextLine();
        //System.out.println(intToStrRome(strToIntRome(expression)));

        //System.out.println(strToIntRome(expression));
        System.out.println("Output: " + calc(expression));

    }

    public static String calc(String input){

        String result = "";
        String [] symbols = input.split(" ");

        boolean first = true;
        boolean second = true;

        if (symbols.length > 3)
            throw new IndexOutOfBoundsException("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        if (symbols.length < 3)
            throw new IndexOutOfBoundsException("строка не является математической операцией");


        try{
            Integer.parseInt(symbols[0]);
        }catch(Exception NumberFormatException){
            first = false;
        }

        try{
            Integer.parseInt(symbols[2]);
        }catch(Exception NumberFormatException){
            second = false;
        }

        if (first != second){
            throw new NumberFormatException("используются одновременно разные системы счисления");
        }




        boolean max = false;

        try{
            Integer.parseInt(symbols[0]);
            Integer.parseInt(symbols[2]);

            if ((Integer.parseInt(symbols[0]) > 10) || (Integer.parseInt(symbols[2]) > 10))
                max = true;



            if (symbols[1].equals("+"))
                result = Integer.toString(Integer.parseInt(symbols[0]) + Integer.parseInt(symbols[symbols.length - 1]));

            if (symbols[1].equals("-"))
                result = Integer.toString(Integer.parseInt(symbols[0]) - Integer.parseInt(symbols[symbols.length - 1]));

            if (symbols[1].equals("*"))
                result = Integer.toString(Integer.parseInt(symbols[0]) * Integer.parseInt(symbols[symbols.length - 1]));

            if (symbols[1].equals("/"))
                result = Integer.toString(Integer.parseInt(symbols[0]) / Integer.parseInt(symbols[symbols.length - 1]));


        }catch (Exception NumberFormatException) {


            if ((strToIntRome(symbols[0]) > 10))
                throw new ArithmeticException("Введено число больше X");

            if ((strToIntRome(symbols[2]) > 10))
                throw new ArithmeticException("Введено число больше X");

            if (symbols[1].equals("+"))
                result = Integer.toString(strToIntRome(symbols[0]) + strToIntRome(symbols[2]));

            if (symbols[1].equals("-"))
                result = Integer.toString(strToIntRome(symbols[0]) - strToIntRome(symbols[2]));

            if (symbols[1].equals("*"))
                result = Integer.toString(strToIntRome(symbols[0]) * strToIntRome(symbols[2]));

            if (symbols[1].equals("/"))
                result = Integer.toString(strToIntRome(symbols[0]) / strToIntRome(symbols[2]));

            if (Integer.parseInt(result) < 0)
                throw new ArithmeticException("в римской системе нет отрицательных чисел");
            result = intToStrRome(Integer.parseInt(result));



        }

        if (max)
            throw new ArithmeticException("введено число больше 10");

        return result;
    }

    private static int strToIntRome (String num){
        num = " " + num + " ";
        String[] words = num.split("");
        int kol = 0;

        for (int i = 1; i < words.length; i++){

            if (words[i].equals("M") && !(words[i - 1].equals("C"))){
                kol += 1000;
            }

            if (words[i].equals("C") && words[i + 1].equals("M")){
                kol += 900;
            }

            if (words[i].equals("D") && !(words[i - 1].equals("C")))
                kol += 500;

            if (words[i].equals("C") && words[i + 1].equals("D"))
                kol += 400;

            if ((words[i].equals("C") && !(words[i + 1].equals("M")) && !(words[i - 1].equals("X")) && !(words[i + 1].equals("D"))))
                kol += 100;

            if (words[i].equals("X") && words[i + 1].equals("C"))
                kol += 90;

            if (words[i].equals("L") && !(words[i - 1].equals("X")))
                kol += 50;

            if (words[i].equals("X") && words[i + 1].equals("L"))
                kol += 40;

            if (words[i].equals("X") && !(words[i - 1].equals("I")) && !(words[i + 1].equals("C")) && !(words[i + 1].equals("L")))
                kol += 10;

            if (words[i].equals("I") && words[i + 1].equals("X"))
                kol += 9;

            if (words[i].equals("V") && !(words[i - 1].equals("I")))
                kol += 5;

            if (words[i].equals("I") && words[i + 1].equals("V"))
                kol += 4;

            if (words[i].equals("I") && !(words[i + 1].equals("X")) && !(words[i + 1].equals("V")))
                kol += 1;

        }

        return kol;
    }

    private static String intToStrRome (int num){
        String result = "";

        if (num % 1000 > 0){
            int i;
            for (i =0; i < (num / 1000); i++ )
                result += "M";

            num -= 1000 * i;
        }

        if (num / 900 > 0){
            result += "CM";
            num -= 900;
        }

        if (num / 500 > 0){
            result += "D";
            num -= 500;
        }

        if (num / 400 > 0){
            result += "CD";
            num -= 400;
        }

        if (num / 100 > 0){
            int i;
            for (i = 0; i < (num / 100); i++)
                result += "C";

            num -= 100 * i;
        }

        if (num / 90 > 0){
            result += "XC";
            num -= 90;
        }

        if (num / 50 > 0){
            result += "L";
            num -= 50;
        }

        if (num / 40 > 0){
            result += "XL";
            num -= 40;
        }

        if (num / 10 > 0){
            int i;
            for (i = 0; i < (num / 10); i++)
                result += "X";
            num -= 10 * i;
        }

        if (num / 9 > 0){
            result += "IX";
            num -= 9;
        }

        if (num / 5 > 0){
            result += "V";
            num -= 5;
        }

        if (num / 4 > 0){
            result += "IV";
            num -= 4;
        }

        if (num > 0){
            int i;
            for (i = 0; i < num; i++)
                result += "I";
        }

        return result;
    }
}