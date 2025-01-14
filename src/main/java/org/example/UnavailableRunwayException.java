package org.example;

public class UnavailableRunwayException extends Exception {
    public UnavailableRunwayException(String timestamp) {
        super(timestamp + " | The chosen runway for maneuver is currently occupied");
    }
}