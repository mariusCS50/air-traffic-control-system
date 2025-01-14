package org.example;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class AirTrafficControl {
    private HashMap<String, Runway<?>> runways;
    private HashMap<String, String> aiplaneRunways;

    public AirTrafficControl() {
        runways = new HashMap<>();
        aiplaneRunways = new HashMap<>();
    }

    public void run(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("src/main/resources/" + args[0] + "/input.in"));
        PrintWriter out = new PrintWriter(new FileWriter("src/main/resources/" + args[0] + "/flight_info.out"), true);
        PrintWriter err = new PrintWriter(new FileWriter("src/main/resources/" + args[0] + "/board_exceptions.out"), true);

        boolean running = true;
        while (running) {
            String line = in.nextLine();
            String[] command = line.split(" - ");

            updateRunwaysStatus(LocalTime.parse(command[0]));

            try {
                switch (command[1]) {
                    case "add_runway_in_use": add_runway(command);
                        break;
                    case "allocate_plane": allocate_plane_on_runway(command);
                        break;
                    case "permission_for_maneuver": check_permission_and_execute_maneuver(command);
                        break;
                    case "runway_info": print_runway_info(args, command);
                        break;
                    case "flight_info": print_flight_info(out, command);
                        break;
                    case "exit" : running = false;
                        break;
                }
            } catch (IncorrectRunwayException | UnavailableRunwayException e) {
                err.println(e.getMessage());
            }
        }

        out.close();
    }

    public static void main(String[] args) throws IOException {
        AirTrafficControl atc = new AirTrafficControl();
        atc.run(args);
    }

    public void add_runway(String[] command) {
        if (command[4].equals("wide body")) {
            Runway<WideBodyAirplane> runway = new Runway<>(command);
            runways.put(runway.getId(), runway);
        }
        if (command[4].equals("narrow body")) {
            Runway<NarrowBodyAirplane> runway = new Runway<>(command);
            runways.put(runway.getId(), runway);
        }
    }

    public void allocate_plane_on_runway(String[] command) throws IncorrectRunwayException {
        Runway<? extends Airplane> runway = runways.get(command[8]);
        RunwayType runwayType = runway.getRunwayType();

        if (command[5].equals("Bucharest") && runwayType.equals(RunwayType.LANDING)) {
            throw new IncorrectRunwayException(command[0]);
        }
        if (command[6].equals("Bucharest") && runwayType.equals(RunwayType.TAKEOFF)) {
            throw new IncorrectRunwayException(command[0]);
        }

        if (command[2].equals("wide body")) {
            WideBodyAirplane airplane = new WideBodyAirplane(command);
            ((Runway<WideBodyAirplane>) runway).addAirplane(airplane);
            aiplaneRunways.put(airplane.getFlightId(), runway.getId());
        } else {
            NarrowBodyAirplane airplane = new NarrowBodyAirplane(command);
            ((Runway<NarrowBodyAirplane>) runway).addAirplane(airplane);
            aiplaneRunways.put(airplane.getFlightId(), runway.getId());
        }
    }

    public void print_flight_info(PrintWriter out, String[] command) {
        String runwayId = aiplaneRunways.get(command[2]);
        Runway<? extends Airplane> runway = runways.get(runwayId);

        out.println(command[0] + " | " + runway.getAirplanesData().get(command[2]).toString());
    }

    public void print_runway_info(String[] args, String[] command) throws IOException {
        Runway<? extends Airplane> runway = runways.get(command[2]);

        LocalTime timestamp = LocalTime.parse(command[0]);
        PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/" + args[0] + "/runway_info_" +
                runway.getId() + "_" + timestamp.format(DateTimeFormatter.ofPattern("HH-mm-ss")) + ".out", true));

        writer.print(runway);
        writer.close();
    }

    public void check_permission_and_execute_maneuver(String[] command) throws UnavailableRunwayException {
        Runway<? extends Airplane> runway = runways.get(command[2]);
        LocalTime timestamp = LocalTime.parse(command[0]);

        if (runway.getStatus().equals(RunwayStatus.OCCUPIED)) {
            throw new UnavailableRunwayException(command[0]);
        }

        Airplane airplane = runway.retrieveFirstPriorityAirplane();

        if (airplane.getStatus().equals(AirplaneStatus.WAITING_FOR_TAKEOFF)) {
            airplane.setStatus(AirplaneStatus.DEPARTED);
            runway.setOccupiedUntil(timestamp.plusMinutes(5));
        }
        if (airplane.getStatus().equals(AirplaneStatus.WAITING_FOR_LANDING)) {
            airplane.setStatus(AirplaneStatus.LANDED);
            runway.setOccupiedUntil(timestamp.plusMinutes(10));
        }

        airplane.setActualTime(timestamp);
        runway.setStatus(RunwayStatus.OCCUPIED);
    }

    public void updateRunwaysStatus(LocalTime timestamp) {
        for (Runway<?> runway : runways.values()) {
            if (runway.getStatus().equals(RunwayStatus.OCCUPIED) && runway.getOccupiedUntil().isBefore(timestamp)) {
                runway.setStatus(RunwayStatus.FREE);
            }
        }
    }
}