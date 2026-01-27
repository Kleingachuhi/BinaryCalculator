package models;

public class Multiplication implements BinaryOperation {
    @Override

    public String calculate (String firstBinary, String secondBinary) throws Exception{

        String result = "0";

        for  (int i = secondBinary.length() - 1; i>-1; i--) {
            char currentBit  = secondBinary.charAt(i);

            if (currentBit == '1') { 
                String shiftedValue = firstBinary;

                for (int j  =0; j < secondBinary.length() - 1-i; j++) {
                    shiftedValue = shiftedValue + "0";
                }
                result = addBinary(result, shiftedValue);
            }
        }
return result;
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    private String addBinary(String firstBinary, String secondBinary){


        int maximumLenghth = Math.max(firstBinary.length(), secondBinary.length());
        firstBinary = paddingWithZeros(firstBinary, maximumLenghth);
        secondBinary = paddingWithZeros(secondBinary, maximumLenghth);

        StringBuilder result = new StringBuilder();
        int remainder = 0;

        for (int i = maximumLenghth - 1; i > -1 ; i--) {
            int unoBit = firstBinary.charAt(i) - '0';
            int twoBit = secondBinary.charAt(i) - '0';

            int sumTotal = unoBit + twoBit + remainder;

            result.insert(0, sumTotal %2);
            remainder  = sumTotal / 2;
        }

        if (remainder == 1) {
            result.insert(0, "1");
        }

        return result.toString();
    }
    private String paddingWithZeros(String value, int lenght) {
        StringBuilder paddedBinary = new StringBuilder(value);

        while (paddedBinary.length() < lenght) {
            paddedBinary.insert(0, "0");
        }
        return paddedBinary.toString();
    }
}