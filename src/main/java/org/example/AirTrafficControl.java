package org.example;

import java.io.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;

public class AirTrafficControl {
    private HashMap<String, Runway<?>> runways;
    private HashMap<String, String> aiplaneRunways;

    public AirTrafficControl(String[] args) throws IOException {
        runways = new HashMap<>();
        aiplaneRunways = new HashMap<>();
    }

    public void run(String[] args) throws IOException {
        Scanner in = new Scanner(new FileReader("src/main/resources/" + args[0] + "/input.in"));
        PrintWriter out = new PrintWriter(new FileWriter("src/main/resources/" + args[0] + "/flight_info.out"), true);
        PrintWriter err = new PrintWriter(new FileWriter("src/main/resources/" + args[0] + "/board_exceptions.out"), true);

        while (in.hasNextLine()) {
            String line = in.nextLine();
            String[] command = line.split(" - ");
            try {
                switch (command[1]) {
                    case "add_runway_in_use": runways.put(command[2], new Runway.RunwayBuilder(command[2])
                                                                                .setType(command[3])
                                                                                .build(command[4]));
                        break;
                    case "allocate_plane": allocate_plane_on_runway(command);
                        break;
                    case "permission_for_maneuver":
                        break;
                    case "runway_info": print_runway_info(args, command);
                        break;
                    case "flight_info": print_flight_info(out, command);
                        break;
                    case "exit" : break;
                }
            } catch (IncorrectRunwayException e) {
                err.println(e.getMessage());
            }
        }

        out.close();
    }

    public static void main(String[] args) throws IOException {
        AirTrafficControl atc = new AirTrafficControl(args);
        atc.run(args);
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

    public void print_flight_info(PrintWriter out, String[] command) throws IOException {
        String runwayId = aiplaneRunways.get(command[2]);
        Runway<? extends Airplane> runway = runways.get(runwayId);

        out.println(command[0] + " | " + runway.getAirplanes().get(command[2]).toString());
    }

    public void print_runway_info(String[] args, String[] command) throws IOException {
//        Runway<? extends Airplane> runway = runways.get(command[2]);
//
//        LocalTime timestamp = LocalTime.parse(command[0], DateTimeFormatter.ofPattern("HH-mm-ss"));
//        PrintWriter writer = new PrintWriter(new FileWriter("src/main/resources/" + args[0] +
//                "/runway_info_" + runway.getId() + "_" + timestamp + ".out", true));
//
//        writer.print(runway.toString());
//        writer.close();
    }
}