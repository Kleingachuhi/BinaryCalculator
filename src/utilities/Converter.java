package utilities;

public class Converter{
    public static int binaryToDecimal(String binary) {

        int decimalvalue = 0;
        int power = 0;

        for (int i  =  binary.length()-1; i>-1; i--) {

            char currentBit = binary.charAt(i);
            if (currentBit == '1') {
                decimalvalue+=Math.pow(2,power);
            }
            power++;
        }
        return decimalvalue;
    }
}