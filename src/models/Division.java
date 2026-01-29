package models;

public class Division implements BinaryOperation{

    @Override
    public String calculate(String firstBinary,String secondBinary) throws Exception{

        if (isZero(secondBinary)) {
            throw new Exception("Sorry, but can not divide by zero");
        }

        String toBeDivided = removeLeadingZeros(firstBinary);
        String Divisor = removeLeadingZeros(secondBinary);

        String quotient = "0";


        while (isGreaterOrEqual(toBeDivided, Divisor)) {
            toBeDivided = subtractBinary(toBeDivided, Divisor);
            quotient = addOne(quotient);
        }
        return quotient;
    }

    @Override
    public String getSymbol () {
        return"/";
    }

    private boolean isZero(String binary) {
        for(char n: binary.toCharArray()){
            if (n == '1') {
                return false;
            }
        }
        return true;
    }

    private String subtractBinary ( String firstBinary, String secondBinary) {
        int maximumLength = Math.max(firstBinary.length(), secondBinary.length());

        firstBinary = paddingWithZeros(firstBinary, maximumLength);
        secondBinary = paddingWithZeros(secondBinary, maximumLength);


        StringBuilder result = new StringBuilder();
        int borrow = 0;


        for(int i = maximumLength-1; i>=0; i--){

            int unoBit = firstBinary.charAt(i) - '0' - borrow;
            int twoBit = secondBinary.charAt(i) - '0';

            if (unoBit < twoBit) {
                unoBit+=2;
                borrow = 1;
            } else {
                borrow = 0;
            }

            result.insert(0, (unoBit - twoBit));
        }

        return removeLeadingZeros(result.toString());
    }

    private String addOne(String binary) {
        return addBinary(binary, "1");
    }

    private String addBinary(String firstBinary, String secondBinary){
        int maximumLength = Math.max(firstBinary.length(), secondBinary.length());

         firstBinary = paddingWithZeros(firstBinary, maximumLength);
         secondBinary = paddingWithZeros(secondBinary, maximumLength);


        StringBuilder result = new StringBuilder();

        int carry =0;


        for (int  i = maximumLength -1 ; i>-1; i--){
            int unoBit = firstBinary.charAt(i) - '0';
            int twoBit = secondBinary.charAt(i) - '0';

            int sumTotal = unoBit + twoBit + carry;

            result.insert(0, sumTotal %2);
            carry = sumTotal / 2;
        }

        if (carry == 1) {
            result.insert(0, "1");
        }

        return result.toString();
    }

    private boolean isGreaterOrEqual(String firstBinary, String secondBinary) {
        firstBinary = removeLeadingZeros(firstBinary);
        secondBinary  = removeLeadingZeros(secondBinary);

        if (firstBinary.length() != secondBinary.length()){
            return firstBinary.length() > secondBinary.length();
        }
        return firstBinary.compareTo(secondBinary) >=0;
    }

    private String removeLeadingZeros(String value) {
        while (value.length() > 1 && value.charAt(0) == '0') {
            value = value.substring(1);
        }
        return value;
    }
    private String paddingWithZeros (String value, int length) {
        StringBuilder paddedBinary = new StringBuilder(value);

        while (paddedBinary.length() < length) {
            paddedBinary.insert(0, "0");
        }
        return paddedBinary.toString();
    }
}
