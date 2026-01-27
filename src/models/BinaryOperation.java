package models;

public interface BinaryOperation {
    String calculate(String firstBinary, String secondBinary) throws Exception;
    String getSymbol();
}