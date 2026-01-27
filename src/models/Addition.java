 package models;

public class Addition implements BinaryOperation{

    @Override
    public String calculate(String firstBinary, String secondBinary) throws Exception {
        int maximumLength = Math.max(firstBinary.length(), secondBinary.length());

        firstBinary = padFirstWithZeros(firstBinary, maximumLength);
        secondBinary = padSecondWithZeros(secondBinary, maximumLength);

        StringBuilder result = new StringBuilder();

        int remainder = 0;

        for (int i = maximumLength - 1; i >-1; 1--){
            int unoBit = firstBinary.charAt(i) - '0';
            int twoBit = secondBinary.charAt(i) - '0';

            
            int sumTotal = unoBit + twoBit + remainder;

            result.insert(0, sumTotal %2 );

            remainder = sumTotal /2;
        }
        
    }
}