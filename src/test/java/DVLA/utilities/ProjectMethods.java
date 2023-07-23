package DVLA.utilities;
import DVLA.VehicleData;
import com.opencsv.CSVWriter;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

public class ProjectMethods {

    public static boolean isValidVehicleNumber(String vrn) {
        // Remove spaces from the vehicleNumberPlate to match the pattern
        vrn = vrn.replaceAll("\\s", "");
        // The pattern checks for 2 uppercase/lowercase letters, followed by 2 digits, and then 3 uppercase/lowercase letters.
        Pattern pattern = Pattern.compile("[A-Za-z]{2}\\d{2}[A-Za-z]{3}");
        return pattern.matcher(vrn).matches();
    }


    // Format the vehicle number to the correct format LLDD LLL
    public static String formatVehicleNumber(String vrn) {
        vrn = vrn.replaceAll("\\s", "");
        String firstPart = vrn.substring(0, 4);
        String secondPart = vrn.substring(4, 7);
        return (firstPart + " " + secondPart).toUpperCase();
    }

    public static boolean isMakeValid(String make) {
        String[] validMakes = {"Audi", "BMW", "VW", "Mercedes"};
        for (String validMake : validMakes) {
            if (make.equalsIgnoreCase(validMake)) {
                return true;
            }
        }
        return false;
    }



    public static String formatVehicleMake(String make) {
        String[] validMakes = {"Audi", "BMW", "VW", "Mercedes"};

        for (int i = 0; i < validMakes.length; i++) {
            if (make.equalsIgnoreCase(validMakes[i])) {
                switch (validMakes[i]) {
                    case "BMW":
                    case "VW":
                        make = validMakes[i].toUpperCase();
                        break;
                    case "Audi":
                    case "Mercedes":
                        make = validMakes[i].substring(0, 1).toUpperCase() + validMakes[i].substring(1).toLowerCase();
                        break;
                }
                break; // Exit the loop once a valid make is found and formatted
            }
        }

        return make;
    }


    public static boolean isColourValid(String colour) {
        String[] validColours = {"White", "Black", "Red", "Blue"};
        for (String validColour : validColours) {
            if (colour.equalsIgnoreCase(validColour)) {
                return true;
            }
        }
        return false;
    }

    public static String formatColour(String colour) {
        String[] validColours = {"White", "Black", "Red", "Blue"};
        for (int i = 0; i < validColours.length; i++) {
            if (colour.equalsIgnoreCase(validColours[i])) {
                switch (validColours[i]) {
                    case "White":
                    case "Black":
                    case "Red":
                    case "Blue":
                        colour = validColours[i].substring(0, 1).toUpperCase() + validColours[i].substring(1).toLowerCase();
                        break;
                }
                break; // Exit the loop once a valid make is found and formatted
            }
        }
        return colour;
    }


    //Check if dates are valid
    public static boolean isValidDate(String inputDate) {
        // Define the valid date formats
        String[] validDateFormats = {"dd-MM-yyyy", "d-MM-yyyy", "dd/MM/yyyy", "d/MM/yyyy"};

        // Remove any whitespace from the date string
        String trimmedDate = inputDate.trim();

        // Check if the date string matches any of the valid formats
        Date date = null;
        for (String validFormat : validDateFormats) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(validFormat);
                sdf.setLenient(false); // Set lenient to false to enforce strict parsing
                date = sdf.parse(trimmedDate);

                // Check if the parsed date is not in the future
                if (date.after(new Date())) {
                    date = null;
                } else {
                    break; // Break the loop if parsing succeeds with any valid format
                }
            } catch (ParseException ignored) {
                // Parsing failed with this format, try the next one
            }
        }

        // Return true if date is not null, which means the inputDate was successfully parsed
        return date != null;
    }


    //Format the dates as "E, dd MMMM yyyy"
    private static final String[] validDateFormats = {"dd-MM-yyyy", "d-MM-yyyy", "dd/MM/yyyy", "d/MM/yyyy"};

    public static String formatDate(String inputDate) {
        Date date = null;
        for (String format : validDateFormats) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.ENGLISH);
                date = sdf.parse(inputDate);
                break; // If parsing succeeds, break the loop
            } catch (ParseException ignored) {
            }
        }

        if (date == null) {
            throw new IllegalArgumentException("Invalid date format");
        }

        SimpleDateFormat outputSdf = new SimpleDateFormat("E, dd MMMM yyyy", Locale.ENGLISH);
        return outputSdf.format(date);
    }


//Write data to CSV file with path
    public static void writeDataToCSV(List<VehicleData> vehicleDataList, String filePath) {
        Path path = Paths.get(filePath);
        try (CSVWriter writer = new CSVWriter(new FileWriter(path.toFile()))) {
            // Create a header for the CSV file
            String[] header = {"VRN", "Make", "Colour", "Date Of Manufacture"};
            writer.writeNext(header);

            // Iterate through the list and write each vehicle data to the CSV file
            for (VehicleData vehicleData : vehicleDataList) {
                String[] record = {
                        vehicleData.getVrn(),
                        vehicleData.getMake(),
                        vehicleData.getColour(),
                        vehicleData.getDateOfManufacture(),
                };
                writer.writeNext(record);
            }
            System.out.println("Data has been written to CSV successfully!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //Generate table
    public static void generateTable(List<VehicleData> filteredVehicles) {

        // Table header
        System.out.println("----------------------------------------------------------");
        System.out.printf("| %-10s | %-15s | %-10s | %-22s |\n", "VRN", "Make", "Colour", "Date Of Manufacture");
        System.out.println("----------------------------------------------------------");

        for (VehicleData vehicle : filteredVehicles) {
            String vrn = vehicle.getVrn();
            String make = vehicle.getMake();
            String colour = vehicle.getColour();
            String dateOfManufacture = vehicle.getDateOfManufacture();

            // Table row
            System.out.printf("| %-10s | %-15s | %-10s | %-22s |\n", vrn, make, colour, dateOfManufacture);
        }

        // Table footer
        System.out.println("----------------------------------------------------------");
    }



}
