package models;

public interface BinaryOperation {
    String calculate(String binary1, String binary2) throws Exception;
    String getSymbol();
}