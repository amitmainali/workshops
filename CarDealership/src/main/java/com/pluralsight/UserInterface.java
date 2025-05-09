package com.pluralsight;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInterface {
    private Dealership dealership;

    public UserInterface() {
        DealershipFileManager dfm = new DealershipFileManager();
        dealership = dfm.getDealership();
    }

    public void display() {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            try {
                System.out.println("1 - Find vehicles within a price range");
                System.out.println("2 - Find vehicles by make/model");
                System.out.println("3 - Find vehicles by year range");
                System.out.println("4 - Find vehicles by color");
                System.out.println("5 - Find vehicles by mileage range");
                System.out.println("6 - Find vehicles by type");
                System.out.println("7 - List all vehicles");
                System.out.println("8 - Add a vehicle");
                System.out.println("9 - Remove a vehicle");
                System.out.println("99 - Quit");
                System.out.print("Enter option: ");

                int option = Integer.parseInt(scanner.nextLine());

                switch (option) {
                    case 1:
                        processGetByPriceRequest(scanner);
                        break;
                    case 2:
                        processGetByMakeModelRequest(scanner);
                        break;
                    case 3:
                        processGetByYearRequest(scanner);
                        break;
                    case 4:
                        processGetByColorRequest(scanner);
                        break;
                    case 5:
                        processGetByMileageRequest(scanner);
                        break;
                    case 6:
                        processGetByVehicleTypeRequest(scanner);
                        break;
                    case 7:
                        displayVehicles(dealership.getAllVehicles());
                        break;
                    case 8:
                        processAddVehicleRequest(scanner);
                        break;
                    case 9:
                        processRemoveVehicleRequest(scanner);
                        break;
                    case 99:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (Exception e) {
                System.out.println("Something went wrong. Try again.");
            }
        }
    }

    private void displayVehicles(ArrayList<Vehicle> vehicles) {
        for (Vehicle v : vehicles) {
            System.out.println(v);
        }
    }

    private void processGetByPriceRequest(Scanner scanner) {
        System.out.print("Min price: ");
        double min = Double.parseDouble(scanner.nextLine());
        System.out.print("Max price: ");
        double max = Double.parseDouble(scanner.nextLine());

        displayVehicles(dealership.getVehiclesByPrice(min, max));
    }

    private void processGetByMakeModelRequest(Scanner scanner) {
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
    }

    private void processGetByYearRequest(Scanner scanner) {
        System.out.print("Min year: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Max year: ");
        int max = Integer.parseInt(scanner.nextLine());

        displayVehicles(dealership.getVehiclesByYear(min, max));
    }

    private void processGetByColorRequest(Scanner scanner) {
        System.out.print("Color: ");
        String color = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByColor(color));
    }

    private void processGetByMileageRequest(Scanner scanner) {
        System.out.print("Min mileage: ");
        int min = Integer.parseInt(scanner.nextLine());
        System.out.print("Max mileage: ");
        int max = Integer.parseInt(scanner.nextLine());

        displayVehicles(dealership.getVehiclesByMileage(min, max));
    }

    private void processGetByVehicleTypeRequest(Scanner scanner) {
        System.out.print("Vehicle type: ");
        String type = scanner.nextLine();

        displayVehicles(dealership.getVehiclesByType(type));
    }

    private void processAddVehicleRequest(Scanner scanner) {
        System.out.print("VIN: ");
        int vin = Integer.parseInt(scanner.nextLine());
        System.out.print("Year: ");
        int year = Integer.parseInt(scanner.nextLine());
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Type: ");
        String type = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Odometer: ");
        int odometer = Integer.parseInt(scanner.nextLine());
        System.out.print("Price: ");
        double price = Double.parseDouble(scanner.nextLine());

        Vehicle v = new Vehicle(vin, year, make, model, type, color, odometer, price);
        dealership.addVehicle(v);
        new DealershipFileManager().saveDealership(dealership);
    }

    private void processRemoveVehicleRequest(Scanner scanner) {
        System.out.print("Enter VIN to remove: ");
        int vin = Integer.parseInt(scanner.nextLine());

        Vehicle toRemove = null;
        for (Vehicle v : dealership.getAllVehicles()) {
            if (v.getVin() == vin) {
                toRemove = v;
                break;
            }
        }

        if (toRemove != null) {
            dealership.removeVehicle(toRemove);
            new DealershipFileManager().saveDealership(dealership);
        } else {
            System.out.println("Vehicle not found.");
        }
    }
}