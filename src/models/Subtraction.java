package models;

public class Subtraction implements BinaryOperation {

@Override
public String calculate(String firstBinary, String secondBinary) throws Exception {
  int maximumLength = Math.max(firstBinary.length(), secondBinary.length());

    firstBinary = paddingWithZeros(firstBinary, maximumLength);
    secondBinary = paddingWithZeros(secondBinary, maximumLength);

    StringBuilder result = new StringBuilder();
    int borrow = 0;

    for(int  i = maximumLength - 1; i >-1 ;i--)  {
        int unoBit = firstBinary.charAt(i) - '0';
        int twoBit = secondBinary.charAt(i) - '0';



        unoBit = unoBit - borrow;

        if (unoBit < twoBit) {
            unoBit = unoBit + 2;
            borrow = 1;
        } else if (twoBit >  unoBit) {
            borrow = 0;
        }
        
    int difference = unoBit - twoBit;
    result.insert(0, difference);
    }
while (result.length()> 1 && result.charAt(0) == '0' ){
    result.deleteCharAt(0);
}
return result.toString();
}

@Override

public String getSymbol(){
    return "-";
}

private String paddingWithZeros(String value, int length){ 
    StringBuilder paddedBinary = new StringBuilder(value);
    while (paddedBinary.length() < length) {
        paddedBinary.insert(0, "0");
    }
    return paddedBinary.toString();
}

}