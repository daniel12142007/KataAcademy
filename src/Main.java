public class Main {
    public static void main(String[] args) throws Exception {
        calc("2+2");
    }

    public static String calc(String input) throws Exception {
        if (input.length() < 2)
            throw new Exception("строка не является математической операцией");
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(input);
        int index = 0;
        int checkSign = 0;
        StringBuilder sign = new StringBuilder();
        for (int i = 0; i < stringBuilder.toString().toCharArray().length; i++) {
            if (stringBuilder.charAt(i) == '+'
                    || stringBuilder.charAt(i) == '-'
                    || stringBuilder.charAt(i) == '/'
                    || stringBuilder.charAt(i) == '*') {
                sign.append(stringBuilder.charAt(i));
                stringBuilder.replace(i, i + 1, " ");
                index = i;
                checkSign++;
            }
        }
        StringBuilder start = new StringBuilder();
        StringBuilder end = new StringBuilder();
        start.append(stringBuilder);
        start.replace(index, stringBuilder.length(), "");
        end.append(stringBuilder);
        end.replace(0, index, "");
        String st = start.toString().replaceAll("\\s+", "");
        String en = end.toString().replaceAll("\\s+", "");
        if ((romanNumberInt(st) == 0
                && romanNumberInt(en) != 0)
                || ((romanNumberInt(st)) != 0
                && romanNumberInt(en) == 0)) {
            throw new Exception("используются одновременно разные системы счисления");
        }
        if (romanNumberInt(st) != 0
                && romanNumberInt(en) != 0) {
            int result = calcSystem(sign.toString(), romanNumberInt(st), romanNumberInt(en));
            if (result < 1)
                throw new Exception("в римской системе нет отрицательных чисел");
            return romanNumberString(result);
        }
        if (checkSign == 2)
            throw new Exception("формат математической операции не удовлетворяет заданию - два операнда и один оператор (+, -, /, *)");
        return String.valueOf(calcSystem(sign.toString(), Integer.parseInt(st), Integer.parseInt(en)));
    }

    private static int romanNumberInt(String str) {
        return switch (str) {
            case "I" -> 1;
            case "II" -> 2;
            case "III" -> 3;
            case "IV" -> 4;
            case "V" -> 5;
            case "VI" -> 6;
            case "VII" -> 7;
            case "VIII" -> 8;
            case "IX" -> 9;
            default -> 0;
        };
    }

    private static String romanNumberString(int number) {
        if (number < 1 || number > 100)
            throw new IllegalArgumentException("Число должно быть от 1 до 100");
        String[] units = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX"};
        String[] tens = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC"};
        int ten = number / 10;
        int unit = number % 10;
        return tens[ten] + units[unit];
    }

    private static int calcSystem(String sign,
                                  int a,
                                  int b) throws Exception {
        return switch (sign) {
            case "+" -> a + b;
            case "-" -> a - b;
            case "*" -> a * b;
            case "/" -> a / b;
            default -> throw new Exception("такого знака не существует");
        };
    }
}