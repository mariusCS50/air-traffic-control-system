package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import static java.lang.System.in;

public class AirTrafficControl {
    private boolean running;
    private String path;
    private Scanner scanner;
    private HashMap<String, Runway<?>> runways;

    public AirTrafficControl(String[] args) throws FileNotFoundException {
        running = true;
        runways = new HashMap<>();

        path = "src/main/resources" + args[0];
        scanner = new Scanner(new FileReader(path + "/input.txt"));
    }

    public void run(String[] args) throws IOException {
        while (running) {
            String line = scanner.nextLine();
            String[] command = line.split(" - ");

            try {
                switch (command[1]) {
                    case "add_runway_in_use": runways.put(command[2], new Runway.RunwayBuilder(command[2])
                                                                                .setType(command[3])
                                                                                .build(command[4]));
                        break;
                    case "allocate_plane": runways.get(command[8]).addAirplane();
                        break;
                    case "permission_for_maneuver":
                        break;
                    case "runway_info":
                        break;
                    case "flight_info":
                        break;
                    case "exit" : running = false;
                        break;
                }
            } catch (Exception e) {
                System.out.println("Invalid command");
            }
        }

        scanner.close();
    }

    public static void main(String[] args) throws IOException {
        AirTrafficControl atc = new AirTrafficControl(args);
        atc.run(args);
    }
}