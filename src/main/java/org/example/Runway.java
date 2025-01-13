package org.example;

import java.util.HashMap;

public class Runway<T extends Airplane> {
    private String id;
    private RunwayType type;
    private HashMap<String, T> airplanes;

    private Runway(RunwayBuilder builder) {
        this.id = builder.id;
        this.type = builder.type;
        this.airplanes = new HashMap<>();
    }

    public void addAirplane(T airplane) {
        airplanes.put(airplane.getFlightId(), airplane);
    }

    public HashMap<String,T> getAirplanes() {
        return airplanes;
    }

    public String getId() {
        return id;
    }

    public RunwayType getRunwayType() {
        return type;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(id).append("\n");
        for (T airplane : airplanes.values()) {
            sb.append(airplane.toString()).append("\n");
        }
        return sb.toString();
    }

    public static class RunwayBuilder {
        private String id;
        private RunwayType type;

        public RunwayBuilder(String id) {
            this.id = id;
        }

        public RunwayBuilder setType(String runwayType) {
            if (runwayType.equals("takeoff")) {
                this.type = RunwayType.TAKEOFF;
            } else {
                this.type = RunwayType.LANDING;
            }
            return this;
        }

        public Runway<? extends Airplane> build(String airplaneType) {
            if (airplaneType.equals("wide body")) {
                return new Runway<WideBodyAirplane>(this);
            } else {
                return new Runway<NarrowBodyAirplane>(this);
            }
        }
    }
}