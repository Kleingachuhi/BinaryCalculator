package models;

public class Division implements BinaryOperation{

    @Override
    public String calculalte(String firstBinary, secondBinary) throws Exception{

        if (isZero(secondBinary)) {
            throw new Exception("Sorry, but can not divide by zero");
        }

        String toBeDivided = removeLeadingZeros(firstBinary);
        String Divisor = removeLeadingZeros(secondBinary);

        String quotient = "0";


        while (isGreaterOrEqual(toBeDivided, Divisor)) {
            toBeDivided = subtractBinary(toBeDivided, Divisor);
            quotient = addOne(quotiend);
        }
    }
}