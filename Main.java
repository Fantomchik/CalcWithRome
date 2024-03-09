import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        System.out.print("Input: ");
        String expression = in.nextLine();

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





        try{
            Integer.parseInt(symbols[0]);
            Integer.parseInt(symbols[2]);


                Integer.parseInt(symbols[2]);

                if (symbols[1].equals("+"))
                    result = Integer.toString(Integer.parseInt(symbols[0]) + Integer.parseInt(symbols[symbols.length - 1]));

                if (symbols[1].equals("-"))
                    result = Integer.toString(Integer.parseInt(symbols[0]) - Integer.parseInt(symbols[symbols.length - 1]));

                if (symbols[1].equals("*"))
                    result = Integer.toString(Integer.parseInt(symbols[0]) * Integer.parseInt(symbols[symbols.length - 1]));

                if (symbols[1].equals("/"))
                    result = Integer.toString(Integer.parseInt(symbols[0]) / Integer.parseInt(symbols[symbols.length - 1]));


        }catch (Exception NumberFormatException) {

                if (symbols[1].equals("+"))
                    result = Integer.toString(numberConversionToArab(symbols[0]) + numberConversionToArab(symbols[2]));

                if (symbols[1].equals("-"))
                    result = Integer.toString(numberConversionToArab(symbols[0]) - numberConversionToArab(symbols[2]));

                if (symbols[1].equals("*"))
                    result = Integer.toString(numberConversionToArab(symbols[0]) * numberConversionToArab(symbols[2]));

                if (symbols[1].equals("/"))
                    result = Integer.toString(numberConversionToArab(symbols[0]) / numberConversionToArab(symbols[2]));

                if (Integer.parseInt(result) < 0)
                    throw new ArithmeticException("в римской системе нет отрицательных чисел");
                result = resultConversion(Integer.parseInt(result));



        }

        return result;
    }

    private static int numberConversionToArab(String num){
        switch (num) {
            case "I":
                return 1;

            case "II":
                return 2;

            case "III":
                return 3;

            case "IV":
                return 4;

            case "V":
                return 5;

            case "VI":
                return 6;

            case "VII":
                return 7;

            case "VIII":
                return 8;

            case "IX":
                return 9;

            case "X":
                return 10;

            default:
                return 0;

        }
    }

    private static String numberConversionToRim(int num){
        switch (num) {
            case 1:
                return "I";

            case 2:
                return "II";

            case 3:
                return "III";

            case 4:
                return "IV";

            case 5:
                return "V";

            case 6:
                return "VI";

            case 7:
                return "VII";

            case 8:
                return "VIII";

            case 9:
                return "IX";

            case 10:
                return "X";

            default:
                return "";

        }
    }

    private static String resultConversion(int num){
        String result = "";

        int c = num / 100;
        int remainderС = num % 100;

        if (c == 1){
            return result + "C";
        }else {
            int l = remainderС / 50;
            int remainderL = remainderС % 50;

            if (l == 1){
                if (remainderL / 10 == 4){
                    result += "XC";
                    result += numberConversionToRim(remainderL % 10);
                }else{
                    result += "L";
                    int x = remainderL / 10;
                    for (int i = 0; i < x; i++){
                        result += "X";
                    }
                    result += numberConversionToRim(remainderL % 10);
                }
            }else {
                if (remainderL / 10 == 4){
                    result += "XL";
                    result += numberConversionToRim(remainderL % 10);
                }else{
                    int x = remainderL / 10;
                    for (int i = 0; i < x; i++){
                        result += "X";
                    }
                    result += numberConversionToRim(remainderL % 10);
                }
            }

            return result;
        }


    }
}