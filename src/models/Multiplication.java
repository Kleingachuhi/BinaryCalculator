package models;

public class Multiplication implements BinaryOperation {
    @Override

    public String calculate (String firstBinary, String secondBinary) throws Exception{

        String result = "0";

        for  (int i = secondBinary.length() - 1; i>-1; i--) {
            char currentBit = SecondBinary = secondBinary.charAt(i);

            if (currentBit == '1') { 
                String shiftedValue = firstBinary;

                for (int j  =0; j < secondBinary.length() - 1-i; j++) {
                    shiftedValue = shiftedValue + "0";
                }
                result = addbinary(result, shiftedValue);
            }
        }
return result;
    }

    @Override
    public String getSymbol() {
        return "*";
    }

    private string addBinary(String firstBinary, string secondBinary){

    }
}