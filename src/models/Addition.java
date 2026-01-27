 package models;

public class Addition implements BinaryOperation{

    @Override
    public String calculate(String firstBinary, String secondBinary) throws Exception {
        int maximumLength = Math.max(firstBinary.length(), secondBinary.length());

        firstBinary = paddingWithZeros(firstBinary, maximumLength);
        secondBinary = paddingWithZeros(secondBinary, maximumLength);

        StringBuilder result = new StringBuilder();

        int remainder = 0;

        for (int i = maximumLength - 1; i >-1; i--){
            int unoBit = firstBinary.charAt(i) - '0';
            int twoBit = secondBinary.charAt(i) - '0';

            
            int sumTotal = unoBit + twoBit + remainder;

            result.insert(0, sumTotal %2 );

            remainder = sumTotal /2;
        }
        
        if (remainder ==1){
            result.insert(0, "1");
        }
        return result.toString();
    }

    @Override

    public String getSymbol() {
        return "+";
    }

    private String paddingWithZeros( String value, int length) {
        StringBuilder paddedBinary = new StringBuilder(value);
        while (paddedBinary.length() < length) {
            paddedBinary.insert(0, "0");
            
        }
        return paddedBinary.toString();
    }
}