package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Airplane {
    private String model;
    private String flightId;
    private String departure;
    private String destination;
    private LocalTime desiredTime;
    private LocalTime actualTime;
    private AirplaneStatus status;
    private int urgency;

    public Airplane(String[] params) {
        this.model = params[3];
        this.flightId = params[4];
        this.departure = params[5];
        this.destination = params[6];
        this.desiredTime = LocalTime.parse(params[7]);
        this.actualTime = null;
        this.status = (params[6].equals("Bucharest") ? AirplaneStatus.WAITING_FOR_LANDING : AirplaneStatus.WAITING_FOR_TAKEOFF);
        this.urgency = (params.length == 10 ? 0 : 1);
    }

    public String getFlightId() {
        return flightId;
    }

    public LocalTime getDesiredTime() {
        return desiredTime;
    }

    public void setActualTime(LocalTime actualTime) {
        this.actualTime = actualTime;
    }

    public void setStatus(AirplaneStatus airplaneStatus) {
        this.status = airplaneStatus;
    }

    public AirplaneStatus getStatus() {
        return status;
    }

    public int getUrgency() {
        return urgency;
    }

    @Override
    public String toString() {
        return model + " - " + flightId + " - " + departure + " - " + destination + " - " +
                status + " - " + desiredTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) +
                (actualTime != null ? " - " + actualTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) : "");
    }
}