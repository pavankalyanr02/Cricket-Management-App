# Cricket Management System 

A Core Java console-based application designed to manage cricketer profiles, stats, and records with text file persistence.

##  Features

- **Add New Cricketer**: Add a player with details such as ID, Name, Country, Age, Role, Runs, and Wickets. Includes duplicate ID prevention.
- **View All Cricketers**: Displays all registered players in a neatly formatted tabular format.
- **Search Cricketer by Name**: Search for players using case-insensitive name matching.
- **Update Cricketer Details**: Modify specific attributes of an existing player while preserving unchanged fields.
- **Delete Cricketer**: Remove player records by Player ID.
- **Data Persistence**: Automatically loads records from `cricketers.txt` on startup and saves changes on demand.

---

## Project Structure

```
Cricket Management App/
├── cricketer/
│   ├── main/
│   │   └── CricketManagementApp.java   # Main application class & CLI menu interface
│   └── model/
│       └── Cricketer.java              # Encapsulated player model class
├── cricketers.txt                      # Data storage file
└── README.md                           # Project documentation
```

---

##  Getting Started

### Prerequisites

- Java Development Kit (JDK 8 or higher installed)

### Compilation & Execution

1. **Compile the project**:
   ```bash
   javac -d bin cricketer/model/Cricketer.java cricketer/main/CricketManagementApp.java
   ```

2. **Run the application**:
   ```bash
   java -cp bin cricketer.main.CricketManagementApp
   ```

---

## Sample Data Format (`cricketers.txt`)

Data is stored as semicolon-delimited values (`ID;Name;Country;Age;Role;Runs;Wickets`):

```text
101;Virat Kohli;India;37;Batsman;13848;5
102;Rohit Sharma;India;39;Batsman;19400;47
103;Jasprit Bumrah;India;32;Bowler;350;380
```
