package org.example;

import java.time.LocalTime;
import java.util.*;

public class Runway<T extends Airplane> {
    private String id;
    private RunwayType type;
    private RunwayStatus status;
    private Comparator<T> comparator;
    private LocalTime occupiedUntil;
    private HashMap<String, T> airplanesData;
    private ArrayList<T> airplanesPriority;

    public Runway(String[] params) {
        this.id = params[2];
        this.type = (params[3].equals("takeoff") ? RunwayType.TAKEOFF : RunwayType.LANDING);
        this.comparator = params[3].equals("takeoff")
                ? Comparator.comparing(T::getDesiredTime)
                : Comparator.comparing(T::getUrgency).thenComparing(T::getDesiredTime);
        this.status = RunwayStatus.FREE;
        this.airplanesData = new HashMap<>();
        this.airplanesPriority = new ArrayList<>();
    }

    public void addAirplane(T airplane) {
        airplanesData.put(airplane.getFlightId(), airplane);
        airplanesPriority.add(airplane);
        airplanesPriority.sort(comparator);
    }

    public String getId() {
        return id;
    }

    public RunwayType getRunwayType() {
        return type;
    }

    public RunwayStatus getStatus() {
        return status;
    }

    public void setStatus(RunwayStatus status) {
        this.status = status;
    }

    public LocalTime getOccupiedUntil() {
        return occupiedUntil;
    }

    public void setOccupiedUntil(LocalTime occupiedUntil) {
        this.occupiedUntil = occupiedUntil;
    }

    public HashMap<String,T> getAirplanesData() {
        return airplanesData;
    }

    public T retrieveFirstPriorityAirplane() {
        T airplane = airplanesPriority.get(0);
        airplanesPriority.remove(airplane);
        return airplane;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append(" - ").append(status).append("\n");

        for (T airplane : airplanesPriority) {
            sb.append(airplane.toString()).append("\n");
        }
        return sb.toString();
    }
}