package com.pluralsight;

import java.io.*;
import java.util.ArrayList;

public class DealershipFileManager {

    public Dealership getDealership() {
        try (BufferedReader reader = new BufferedReader(new FileReader("inventory.csv"))) {
            String headerLine = reader.readLine();
            if (headerLine == null) {
                throw new IOException("File is empty or missing header line.");
            }

            String[] header = headerLine.split("\\|");
            Dealership dealership = new Dealership(header[0], header[1], header[2]);

            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length < 8) continue; // skip invalid lines

                Vehicle v = new Vehicle(
                        Integer.parseInt(parts[0]),
                        Integer.parseInt(parts[1]),
                        parts[2],
                        parts[3],
                        parts[4],
                        parts[5],
                        Integer.parseInt(parts[6]),
                        Double.parseDouble(parts[7])
                );
                dealership.addVehicle(v);
            }

            return dealership;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveDealership(Dealership dealership) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("inventory.csv"))) {
            writer.write(dealership.getName() + "|" + dealership.getAddress() + "|" + dealership.getPhone());
            writer.newLine();

            for (Vehicle v : dealership.getAllVehicles()) {
                writer.write(v.getVin() + "|" + v.getYear() + "|" + v.getMake() + "|" + v.getModel() + "|" +
                        v.getVehicleType() + "|" + v.getColor() + "|" + v.getOdometer() + "|" + v.getPrice());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}