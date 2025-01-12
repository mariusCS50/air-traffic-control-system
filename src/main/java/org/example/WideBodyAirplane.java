package org.example;

import java.time.LocalTime;

public class WideBodyAirplane extends Airplane {
    public WideBodyAirplane(String model, String flightId, String departureLocation, String destination, LocalTime desiredTime, AirplaneStatus airplaneStatus, int urgency) {
        super(model, flightId, departureLocation, destination, desiredTime, airplaneStatus, urgency);
    }

    @Override
    public String toString() {
        return "Wide Body - " + super.toString();
    }

    @Override
    public int compareTo(Airplane o) {
        return 0;
    }
}