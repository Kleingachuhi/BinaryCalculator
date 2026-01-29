package utilities;

public class Validator {
    
    public static void validateBinary( String binary) throws Exception {
        if (binary == null || binary.isEmpty()) {
            throw new Exception("PLease check, Input cannot be empty!");
        }

        for (int i = 0; i < binary.length(); i++){
            char currentCharacter = binary.charAt(i);

            if (currentCharacter != '0'  && currentCharacter != '1') {
                throw new Exception(" Invalid binary number. Either 1 or 0");
            }
        }
    }

    public static void validateInputs (String firstBinary, String secondBinary) throws Exception{
        validateBinary(firstBinary);
        validateBinary(secondBinary);
    }
}
