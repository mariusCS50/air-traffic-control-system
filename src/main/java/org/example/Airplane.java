package org.example;

import java.time.LocalTime;

public class Airplane implements Comparable<Airplane> {
    private String model;
    private String flightId;
    private String departure;
    private String destination;
    private LocalTime desiredTime;
    private LocalTime actualTime;
    private AirplaneStatus status;
    private int urgency;

    public Airplane(String model, String flightId, String departureLocation, String destination, LocalTime desiredTime, AirplaneStatus status, int urgency) {
        this.model = model;
        this.flightId = flightId;
        this.departure = departureLocation;
        this.destination = destination;
        this.desiredTime = desiredTime;
        this.status = status;
        this.urgency = urgency;
    }

    public String getModel() {
        return model;
    }

    public String getFlightId() {
        return flightId;
    }

    public String getDeparture() {
        return departure;
    }

    public String getDestination() {
        return destination;
    }

    public LocalTime getDesiredTime() {
        return desiredTime;
    }

    public LocalTime getActualTime() {
        return actualTime;
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
        return model + " - " + flightId + " - " + departure + " - " + destination + " - " + status + " - " + desiredTime + (actualTime != null ? " - " + actualTime : "") + " - " + urgency;
    }

    @Override
    public int compareTo(Airplane o) {
        return 0;
    }
}