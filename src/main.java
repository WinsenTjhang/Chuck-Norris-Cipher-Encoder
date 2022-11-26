import java.util.Scanner;

class Main {
    static Scanner scanner = new Scanner(System.in);
    static String converted = "";
    static boolean exit = false;

    public static void encode() {
        System.out.println("Input string:");
        char[] str = scanner.nextLine().toCharArray();
        for (char i : str)
        {
            converted += String.format("%7s", Integer.toBinaryString(i)).replace(" ", "0");
        }

        int i = 0;
        char currentChar;
        System.out.println("Encoded string:");

        while (i < converted.length())
        {
            if (converted.charAt(i) == '0')
            {
                System.out.print("00 ");
                currentChar = '0';
            }
            else
            {
                System.out.print("0 ");
                currentChar = '1';
            }

            while (converted.charAt(i) == currentChar)
            {
                System.out.print("0");
                i++;
                if (i == converted.length()) break;
            }

            if (i < converted.length()) System.out.print(" ");
        }
        System.out.printf("%n%n");
    }

    public static void decode() {
        System.out.println("Input encoded string:");
        String input = scanner.nextLine();
        String[] array = input.split(" ");
        String binary = "";
        String str = "";
        boolean notValid = false;

        if(array.length % 2 == 0) {
            for (String i : input.split("")) {
                if(!i.equals("0") && !i.equals(" ")) {
                    notValid = true;
                    System.out.println("not valid");
                    break;
                }
            }
            if(!notValid) {
                // Array to binary conversion
                for (int i = 0; i < array.length; i += 2) {
                    if (array[i].equals("0")) {
                        for (int k = 0; k < array[i + 1].length(); k++) {
                            binary += "1";
                        }
                    } else if (array[i].equals("00")) {
                        for (int k = 0; k < array[i + 1].length(); k++) {
                            binary += "0";
                        }
                    } else {
                        notValid = true;
                        System.out.println("Encoded string is not valid.");
                        break;
                    }
                }

                if (!notValid) {
                    int charCounts = binary.length() / 7;
                    int[] binaryArray = new int[charCounts];
                    int k = 0;
                    for (int i = 0; i < binary.length(); i += 7) {
                        if (binary.length() % 7 == 0) {
                            binaryArray[k] = Integer.parseInt(binary.substring(i, i + 7), 2); //Binary String to Decimal Array conversion
                            str += (char) (binaryArray[k]); //Decimal to char conversion
                            k++;
                        } else {
                            System.out.println("Encoded string is not valid.");
                            break;
                        }

                    }
                    System.out.println("Decoded string:");
                    System.out.println(str);
                    System.out.println();
                }
            }


        } else {
            System.out.printf("Encoded string is not valid.%n%n");
        }
    }

    public static void main(String[] args) {
        while (!exit) {
            System.out.println("Please input operation (encode/decode/exit):");
            String operation = scanner.nextLine();
            switch (operation) {
                case "encode" -> encode();
                case "decode" -> decode();
                case "exit" -> {
                    System.out.println("Bye!");
                    exit = true;
                }
                default -> System.out.printf("There is no '%s' operation%n%n", operation);
            }

        }

    }
}
