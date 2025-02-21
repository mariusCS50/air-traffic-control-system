# Air Traffic Control System

This repository implements a flight operations simulation system as part of a university assignment. The application reads commands from input files, processes runway and flight operations, and writes output files. It also handles exceptional scenarios, such as allocation failures and permission issues during maneuvers.

## Project Structure

- **Gradle Build**
  The project is built using Gradle. The wrapper scripts ([gradlew](gradlew) and [gradlew.bat](gradlew.bat)) are provided to ensure a consistent build environment.

- **Main Application**
  The main logic is implemented in [`org.example.Main`](src/main/java/org/example/Main.java). It reads the input file from `src/main/resources/<test-case>/input.in` and writes output files such as `flight_info.out` and `board_exceptions.out`.

- **Resource Files**
  Each test case (e.g., `01-basic-flight-search`, `02-exceptions-flight-search`, etc.) has a dedicated folder under `src/main/resources/`. These folders contain the input files as well as reference output files (with `.ref` extensions) used for testing.

- **JUnit Tests**
  The test suite in [`TestMain`](src/test/java/TestMain.java) invokes the main application with various test-case names. It compares the generated output files with the reference files, ensuring that operations like runway allocation, flight search, and maneuver permission are handled correctly.

- **Autograding Configuration**
  The workflow defined in [`.github/workflows/classroom.yml`](.github/workflows/classroom.yml) automates testing using Gradle tasks. Specific tests (e.g., `basicFlightSearch01`, `exceptionsFlightSearch02`, etc.) are invoked as part of the continuous integration process.

## How It Works

1. **Input Processing**
   The application expects a single command-line argument corresponding to the test-case folder name in `src/main/resources/`. For example, running

```sh
./gradlew run --args="01-basic-flight-search"
```

   will execute the commands in the input file `src/main/resources/01-basic-flight-search/input.in`.

2. **Command Handling**
   In [`Main.main`](src/main/java/org/example/Main.java), input is processed line by line. Each line of input is split using `" - "` into a timestamp and a command. The supported commands include:
   - `"add_runway_in_use"` – Adds a runway for operations.
   - `"allocate_plane"` – Allocates a plane to a runway.
   - `"permission_for_maneuver"` – Checks permissions and executes complex maneuvers.

   The appropriate method is called for each command, such as [`Main.add_runway`](src/main/java/org/example/Main.java) or [`Main.allocate_plane_on_runway`](src/main/java/org/example/Main.java).

3. **Output Generation**
   Depending on the commands processed, the application writes output files (e.g., `flight_info.out` and `board_exceptions.out`) in the corresponding resource folder. These outputs are later validated against reference files in the automated tests.

4. **Testing and Verification**
   The tests in [`TestMain`](src/test/java/TestMain.java) run the main application for each scenario. They use helper functions like `areFilesEqual` to compare generated output files with expected reference files, ensuring that the program behaves correctly under both normal and exceptional conditions.

## Running the Project

- **Build the Project**
  Execute the following command in a terminal:

```sh
./gradlew build
```

- **Run a Specific Test Case**
  For example, to run the test case for basic flight search:

```sh
./gradlew run --args="01-basic-flight-search"
```

- **Run Automated Tests**
  To execute all the tests:

```sh
./gradlew test
```
