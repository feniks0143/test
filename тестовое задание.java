import java.io.IOException;
import java.util.Scanner;

public class Main {
     static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);//создаю обьект класса сканнер

        String input = scanner.nextLine(); // ввожу с консоли стринг

        System.out.println(parse(input));
    }

     static String parse(String input) throws IOException {
        boolean isRoman;
        int num1;
        int num2;
        String result;
        String oper;
        String[] operands = input.split(" "); //ввожу разделитель с добавлением в массив
        oper = detectOperation(input);
        if (oper == null) throw new IOException();
        if (operands.length > 3)// длина вводимого массива не больше 3
            throw new IOException();// ошибка
        isRoman = true;

        if (Roman.isRoman(operands[0]) && Roman.isRoman(operands[2])) { //проверяет введенные значения на римскость или арабскость
            num1 = Roman.convertToArabian(operands[0]);// 1 число
            num2 = Roman.convertToArabian(operands[2]);// 2 число
            isRoman = true;

        } else if (!Roman.isRoman(operands[0]) && !Roman.isRoman(operands[2])) { // проверяю число на римскость
            num1 = Integer.parseInt(operands[0]);// перевожу 1 число стр в инт
            num2 = Integer.parseInt(operands[2]);// перевожу 2 число стр в инт
            isRoman = false;
        } else throw new IOException();

        if (num1 > 10 || num1 < 1 || num2 > 10 || num2 < 1) // значения, с которыми не работает программа
            throw new IOException();

        int arabian = calc(num1, num2, oper); // ввожу операцию калькулятора для введенных чисел
        if (isRoman){ // если числа римские, то должны быть больше 0
            if (arabian <= 0) {
                throw new IOException();} //иначе ошибка
            result = Roman.convertToRoman(arabian); //результат должен быть римским, если вводили римские
        } else result = String.valueOf(arabian); // если не римскими, то вернуть строку арабскими
        return result; // вывести результат
    }

    static String detectOperation(String input) { // ввожу функции для опера
        if (input.contains("+")) return "+";
        if (input.contains("-")) return "-";
        if (input.contains("*")) return "*";
        if (input.contains("/")) return "/";
        else return null;

    }
    static int calc(int a, int b, String oper) { // калькулятор с опер( +-*/)
        if (oper.equals("+")) return a + b;
        else if (oper.equals("-")) return a - b;
        else if (oper.equals("*")) return a * b;
//        else if (oper.equals("/")) return a / b;
        else return a / b;
    }
    class Roman {
        static String[] romanArray = new String[]{"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        static boolean isRoman(String val) {//сравнение с римскими цифрами
            for (int i = 0; i < romanArray.length; i++) { // присвоение инцдексам
                if (val.equals(romanArray[i]))
                    return true;
            }
            return false;
        }
        static int convertToArabian(String roman) { // конвертация в арабские
            for (int i = 0; i < romanArray.length; i++) {
                if (roman.equals(romanArray[i]))
                    return i;
            }
            return -1;
        }

        static String convertToRoman(int Arabian) {
            return romanArray[Arabian];
        } //конвертация в римские

    }
}



