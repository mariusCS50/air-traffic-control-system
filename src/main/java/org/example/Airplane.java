package org.example;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Airplane implements Comparable<Airplane> {
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
        this.urgency = (params[8] == null ? 0 : 1);
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
        return model + " - " + flightId + " - " + departure + " - " + destination + " - " +
                status + " - " + desiredTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + (actualTime != null ? " - " + actualTime : "");
    }

    @Override
    public int compareTo(Airplane o) {
        return 0;
    }
}