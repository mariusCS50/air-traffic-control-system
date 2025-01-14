package org.example;

import java.time.LocalTime;

public class WideBodyAirplane extends Airplane {
    public WideBodyAirplane(String[] params) {
        super(params);
    }

    @Override
    public String toString() {
        return "Wide Body - " + super.toString();
    }
}