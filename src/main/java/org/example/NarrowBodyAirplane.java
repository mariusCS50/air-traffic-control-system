package org.example;

import java.time.LocalTime;

public class NarrowBodyAirplane extends Airplane {
    public NarrowBodyAirplane(String[] params) {
        super(params);
    }

    @Override
    public String toString() {
        return "Narrow Body - " + super.toString();
    }

    @Override
    public int compareTo(Airplane o) {
        return 0;
    }
}