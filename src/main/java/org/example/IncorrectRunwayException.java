package org.example;

public class IncorrectRunwayException extends RuntimeException {
    public IncorrectRunwayException(String timestamp) {
        super(timestamp + " | The chosen runway for allocating the plane is incorrect");
    }
}
