package cricketer.main;

import cricketer.model.Cricketer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Core Java Console Application: Cricketer Management System.
 * 
 * Demonstrates Core Java Fundamentals:
 * 1. Variables & Data Types
 * 2. Classes & Objects (OOP Encapsulation)
 * 3. Collections (ArrayList)
 * 4. Control Flow (while loop, switch-case, for loops, if-else)
 * 5. Console Input using Scanner
 * 6. File Handling (BufferedReader, BufferedWriter)
 * 7. Exception Handling (try-catch blocks)
 */
public class CricketManagementApp {

    private static List<Cricketer> playerList = new ArrayList<>();
    private static Scanner scanner = new Scanner(System.in);
    private static final String FILE_NAME = "cricketers.txt";

    public static void main(String[] args) {
        // Load existing player data from file on program start
        loadDataFromFile();

        boolean running = true;

        while (running) {
            displayMenu();
            System.out.print("Enter your choice (1-7): ");
            String input = scanner.nextLine().trim();

            int choice;
            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a valid number.\n");
                continue;
            }

            switch (choice) {
                case 1:
                    addPlayer();
                    break;
                case 2:
                    viewAllPlayers();
                    break;
                case 3:
                    searchPlayerByName();
                    break;
                case 4:
                    updatePlayer();
                    break;
                case 5:
                    deletePlayer();
                    break;
                case 6:
                    saveDataToFile();
                    break;
                case 7:
                    running = false;
                    System.out.println("Thank you for using Cricketer Management System. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid option! Please select between 1 and 7.\n");
                    break;
            }
        }
    }

    // Displays the main menu
    private static void displayMenu() {
        System.out.println("=========================================");
        System.out.println("      CRICKETER MANAGEMENT SYSTEM        ");
        System.out.println("=========================================");
        System.out.println("1. Add New Cricketer");
        System.out.println("2. View All Cricketers");
        System.out.println("3. Search Cricketer by Name");
        System.out.println("4. Update Cricketer Details");
        System.out.println("5. Delete Cricketer");
        System.out.println("6. Save Data to File");
        System.out.println("7. Exit");
        System.out.println("=========================================");
    }

    // 1. Add a new player to the ArrayList
    private static void addPlayer() {
        System.out.println("\n--- ADD NEW CRICKETER ---");

        int id = readIntInput("Enter Player ID: ", false);

        // Check if ID already exists
        for (Cricketer p : playerList) {
            if (p.getPlayerId() == id) {
                System.out.println("Error: Player with ID " + id + " already exists!\n");
                return;
            }
        }

        System.out.print("Enter Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Country: ");
        String country = scanner.nextLine().trim();

        int age = readIntInput("Enter Age: ", false);

        System.out.print("Enter Role (Batsman / Bowler / All Rounder): ");
        String role = scanner.nextLine().trim();

        int runs = readIntInput("Enter Total Runs: ", false);
        int wickets = readIntInput("Enter Total Wickets: ", false);

        // Create new Cricketer object
        Cricketer newPlayer = new Cricketer(id, name, country, age, role, runs, wickets);
        playerList.add(newPlayer);

        System.out.println("Player added successfully!\n");
    }

    // 2. View all players in a formatted list
    private static void viewAllPlayers() {
        System.out.println("\n--- ALL CRICKETERS ---");
        if (playerList.isEmpty()) {
            System.out.println("No player records available.\n");
            return;
        }

        printTableHeader();
        for (Cricketer p : playerList) {
            System.out.println(p.toString());
        }
        System.out.println();
    }

    // 3. Search player by name (case-insensitive)
    private static void searchPlayerByName() {
        System.out.println("\n--- SEARCH PLAYER BY NAME ---");
        System.out.print("Enter player name to search: ");
        String searchName = scanner.nextLine().trim().toLowerCase();

        boolean found = false;
        for (Cricketer p : playerList) {
            if (p.getName() != null && p.getName().toLowerCase().contains(searchName)) {
                if (!found) {
                    printTableHeader();
                    found = true;
                }
                System.out.println(p.toString());
            }
        }

        if (!found) {
            System.out.println("No player found with name matching '" + searchName + "'.");
        }
        System.out.println();
    }

    // 4. Update an existing player's details
    private static void updatePlayer() {
        System.out.println("\n--- UPDATE CRICKETER ---");
        int id = readIntInput("Enter Player ID to update: ", false);

        Cricketer targetPlayer = null;
        for (Cricketer p : playerList) {
            if (p.getPlayerId() == id) {
                targetPlayer = p;
                break;
            }
        }

        if (targetPlayer == null) {
            System.out.println("Player with ID " + id + " not found!\n");
            return;
        }

        System.out.println("Current Name: " + targetPlayer.getName());
        System.out.print("Enter New Name (press Enter to keep current): ");
        String newName = scanner.nextLine().trim();
        if (!newName.isEmpty()) {
            targetPlayer.setName(newName);
        }

        System.out.println("Current Country: " + targetPlayer.getCountry());
        System.out.print("Enter New Country (press Enter to keep current): ");
        String newCountry = scanner.nextLine().trim();
        if (!newCountry.isEmpty()) {
            targetPlayer.setCountry(newCountry);
        }

        System.out.println("Current Age: " + targetPlayer.getAge());
        int newAge = readIntInput("Enter New Age (press Enter or -1 to keep current): ", true);
        if (newAge != -1) {
            targetPlayer.setAge(newAge);
        }

        System.out.println("Current Role: " + targetPlayer.getRole());
        System.out.print("Enter New Role (press Enter to keep current): ");
        String newRole = scanner.nextLine().trim();
        if (!newRole.isEmpty()) {
            targetPlayer.setRole(newRole);
        }

        System.out.println("Current Runs: " + targetPlayer.getRuns());
        int newRuns = readIntInput("Enter New Runs (press Enter or -1 to keep current): ", true);
        if (newRuns != -1) {
            targetPlayer.setRuns(newRuns);
        }

        System.out.println("Current Wickets: " + targetPlayer.getWickets());
        int newWickets = readIntInput("Enter New Wickets (press Enter or -1 to keep current): ", true);
        if (newWickets != -1) {
            targetPlayer.setWickets(newWickets);
        }

        System.out.println("Player updated successfully!\n");
    }

    // 5. Delete a player by ID
    private static void deletePlayer() {
        System.out.println("\n--- DELETE CRICKETER ---");
        int id = readIntInput("Enter Player ID to delete: ", false);

        boolean removed = false;
        for (int i = 0; i < playerList.size(); i++) {
            if (playerList.get(i).getPlayerId() == id) {
                playerList.remove(i);
                removed = true;
                break;
            }
        }

        if (removed) {
            System.out.println("Player deleted successfully!\n");
        } else {
            System.out.println("Player with ID " + id + " not found!\n");
        }
    }

    // 6. Save player records to text file
    private static void saveDataToFile() {
        System.out.println("\n--- SAVE DATA TO FILE ---");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Cricketer p : playerList) {
                String line = p.getPlayerId() + ";" + p.getName() + ";" + p.getCountry() + ";"
                        + p.getAge() + ";" + p.getRole() + ";" + p.getRuns() + ";" + p.getWickets();
                writer.write(line);
                writer.newLine();
            }
            System.out.println("Data saved successfully to " + FILE_NAME + "!\n");
        } catch (IOException e) {
            System.out.println("Error saving data to file: " + e.getMessage() + "\n");
        }
    }

    // Load player records from text file on app startup
    private static void loadDataFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split(";");
                if (parts.length == 7) {
                    try {
                        int id = Integer.parseInt(parts[0].trim());
                        String name = parts[1].trim();
                        String country = parts[2].trim();
                        int age = Integer.parseInt(parts[3].trim());
                        String role = parts[4].trim();
                        int runs = Integer.parseInt(parts[5].trim());
                        int wickets = Integer.parseInt(parts[6].trim());

                        Cricketer player = new Cricketer(id, name, country, age, role, runs, wickets);
                        playerList.add(player);
                    } catch (NumberFormatException e) {
                        // Skip corrupted line
                    }
                }
            }
            System.out.println("Loaded " + playerList.size() + " player records from " + FILE_NAME + ".\n");
        } catch (IOException e) {
            System.out.println("Error loading data from file: " + e.getMessage());
        }
    }

    // Helper method to safely read an integer input
    private static int readIntInput(String prompt, boolean allowEmpty) {
        while (true) {
            if (!prompt.isEmpty()) {
                System.out.print(prompt);
            }
            String input = scanner.nextLine().trim();
            if (allowEmpty && input.isEmpty()) {
                return -1; // Default for optional fields
            }
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number! Please re-enter: ");
            }
        }
    }

    // Helper method to print table headers
    private static void printTableHeader() {
        System.out.println(String.format("%-6s %-18s %-14s %-5s %-13s %-8s %-8s",
                "ID", "Name", "Country", "Age", "Role", "Runs", "Wickets"));
        System.out.println("-------------------------------------------------------------------------");
    }
}
