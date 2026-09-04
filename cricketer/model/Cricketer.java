package cricketer.model;

/**
 * Basic model class representing a Cricketer.
 * Demonstrates Core Java concepts: Private attributes, Encapsulation, Constructor, Getters & Setters.
 */
public class Cricketer {
    private int playerId;
    private String name;
    private String country;
    private int age;
    private String role;
    private int runs;
    private int wickets;

    // Default Constructor
    public Cricketer() {
    }

    // Parameterized Constructor
    public Cricketer(int playerId, String name, String country, int age, String role, int runs, int wickets) {
        this.playerId = playerId;
        this.name = name;
        this.country = country;
        this.age = age;
        this.role = role;
        this.runs = runs;
        this.wickets = wickets;
    }

    // Getters and Setters
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public int getWickets() {
        return wickets;
    }

    public void setWickets(int wickets) {
        this.wickets = wickets;
    }

    // Displays player info in a simple formatted row
    @Override
    public String toString() {
        return String.format("%-6d %-18s %-14s %-5d %-13s %-8d %-8d",
                playerId, name, country, age, role, runs, wickets);
    }
}
