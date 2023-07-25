package DVLA;

import DVLA.utilities.ProjectMethods;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class FilteredVehicles extends ProjectMethods {

    public static void main(String[] args) throws CsvValidationException {

        // Initialize the Lists of vehicles to store the filtered records
        List<VehicleData> filteredVehicles = new ArrayList<>();
        List<VehicleData> invalidVehiclesData = new ArrayList<>();

        // Specify the CSV file path
        String csvFilePath = "src/test/resources/Vehicles.csv";

        try (CSVReader csvReader = new CSVReader(new FileReader(csvFilePath))) {
            String[] record;
            csvReader.readNext();
            while ((record = csvReader.readNext()) != null) {

                // Extract data from the record
                String vrn = record[0];
                String make = record[1];
                String colour = record[2];
                String dateOfManufacture = record[3];

                // Apply filtering conditions
                if (isValidVehicleNumber(vrn)
                        && isMakeValid(make)
                        && isColourValid(colour)
                        && isValidDate(dateOfManufacture)) {

                    // Create a new VehicleData object with the filtered data
                    VehicleData vehicle = new VehicleData(formatVehicleNumber(vrn),
                            formatVehicleMake(make),
                            formatColour(colour),
                            formatDate(dateOfManufacture));

                    // Add the filtered record to the list
                    filteredVehicles.add(vehicle);
                } else {
                    VehicleData invalidVehicleData = new VehicleData(vrn, make, colour, dateOfManufacture);
                    invalidVehiclesData.add(invalidVehicleData);

                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("Number of valid vehicles: " + filteredVehicles.size());
        System.out.println("Number of invalid vehicles: " + invalidVehiclesData.size());


        String filteredVehiclesPath = "src/test/resources/filteredVehicles.csv";
        String invalidVehiclesDataPath = "src/test/resources/invalidVehicleData.csv";


        writeDataToCSV(filteredVehicles, filteredVehiclesPath);
        writeDataToCSV(invalidVehiclesData, invalidVehiclesDataPath);

    }
}





























