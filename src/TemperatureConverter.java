import java.util.Scanner;

public class TemperatureConverter {

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

                // BASIC VALIDATION: Check if it's an empty line or just a single symbol
                if (input.isEmpty() || input.equals(".") || input.equals("+") || input.equals("-")) {
                    System.out.println("Error: Invalid temperature input. Please enter a valid number.");
                } else {

                    // Loop through the text to check if every character is a valid part of a number
                    boolean isValidNumber = true;
                    int decimalCount = 0;
                    int i = 0;

                    while (i < input.length()) {
                        char c = input.charAt(i);

                        if (c == '+' || c == '-') {
                            // Signs are only allowed at the very beginning (index 0)
                            if (i != 0) {
                                isValidNumber = false;
                            }
                        } else if (c == '.') {
                            decimalCount++;
                            // More than one decimal point makes it invalid
                            if (decimalCount > 1) {
                                isValidNumber = false;
                            }
                        } else if (c < '0' || c > '9') {
                            // If it's not a sign, a dot, or a digit, it's a letter/symbol
                            isValidNumber = false;
                        }

                        i++; // Move to the next character
                    }

                    // If it passed the basic character checks, proceed to convert
                    if (isValidNumber) {
                        double temperature = Double.parseDouble(input);
                        boolean validUnit = false;
                        String unit = "";

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

                        if (unit.equalsIgnoreCase("C")) {
                            System.out.printf("%.2f°C is equal to %.2f°F\n", temperature, convertedTemp);
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


