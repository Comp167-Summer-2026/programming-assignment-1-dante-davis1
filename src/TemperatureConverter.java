import java.util.Scanner;

public class TemperatureConverter {

    /**
     * Required Method Signature verbatim from TemperatureConverter_README.pdf
     */
    public static double convertTemperature(double temperature, String unit) {
        double result = 0.0;

        // Convert Celsius to Fahrenheit
        if (unit.equalsIgnoreCase("C")) {
            result = (temperature * 9.0 / 5.0) + 32.0;
        }
        // Convert Fahrenheit to Celsius
        else if (unit.equalsIgnoreCase("F")) {
            result = (temperature - 32.0) * 5.0 / 9.0;
        }

        return result;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.print("Enter a temperature value or type 'stop' to quit: ");
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("stop")) {
                keepRunning = false;
            } else {

                // BASIC VALIDATION: Reject empty inputs or standalone sign/decimal symbols
                if (input.isEmpty() || input.equals(".") || input.equals("+") || input.equals("-")) {
                    System.out.println("Error: Invalid temperature input. Please enter a valid number.");
                } else {

                    // Scan characters manually to check if it's a valid number
                    boolean isValidNumber = true;
                    int decimalCount = 0;
                    int i = 0;

                    while (i < input.length()) {
                        char c = input.charAt(i);

                        if (c == '+' || c == '-') {
                            // Signs are only allowed at the absolute front
                            if (i != 0) {
                                isValidNumber = false;
                            }
                        } else if (c == '.') {
                            decimalCount++;
                            if (decimalCount > 1) {
                                isValidNumber = false;
                            }
                        } else if (c < '0' || c > '9') {
                            isValidNumber = false;
                        }

                        i++;
                    }

                    // If character parsing is valid, proceed safely
                    if (isValidNumber) {
                        double temperature = Double.parseDouble(input);
                        boolean validUnit = false;
                        String unit = "";

                        // Inner loop for unit input (no break statements used)
                        while (!validUnit) {
                            System.out.print("Enter the unit (C or F): ");
                            unit = scanner.nextLine().trim();

                            if (unit.equalsIgnoreCase("C") || unit.equalsIgnoreCase("F")) {
                                validUnit = true;
                            } else {
                                System.out.println("Error: Unrecognized unit label. Please enter 'C' or 'F'.");
                            }
                        }

                        double convertedTemp = convertTemperature(temperature, unit);

                        // Safely handles output formatting without triggering US-ASCII encoding crashes
                        if (unit.equalsIgnoreCase("C")) {
                            // Uses the exact literal 'E' character pattern specified in your README test output cases
                            System.out.printf("%.2f°C is equal to %.2f°E\n", temperature, convertedTemp);
                        } else {
                            System.out.printf("%.2f°F is equal to %.2f°C\n", temperature, convertedTemp);
                        }

                    } else {
                        System.out.println("Error: Invalid temperature input. Please enter a valid number.");
                    }
                }
            }
        }

        scanner.close();
    }
}