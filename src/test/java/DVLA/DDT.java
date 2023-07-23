package DVLA;

import DVLA.utilities.ProjectMethods;
import org.junit.jupiter.api.Assertions;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;



public class DDT extends ProjectMethods {


    @ParameterizedTest
    @CsvFileSource(resources = "/Vehicles.csv", numLinesToSkip = 1)
    public void vehicleDataTesting(String vrn, String make, String colour, String dateOfManufacture) {

        Map<String, Object> vehicles = new LinkedHashMap<>();

        vehicles.put("vrn", vrn);
        vehicles.put("make", make);
        vehicles.put("colour", colour);
        vehicles.put("dateOfManufacture", dateOfManufacture);



        // Validate VRN
        boolean isVRNvalid = isValidVehicleNumber(vrn);
        Assertions.assertTrue(isVRNvalid, "Invalid VRN: " + vrn);

        // Validate make
        boolean isMakeValid = isMakeValid(make);
        Assertions.assertTrue(isMakeValid, "Invalid make: " + make);

        // Validate colour
        boolean isColourValid = isColourValid(colour);
        Assertions.assertTrue(isColourValid, "Invalid colour: " + colour);

        // Validate dateOfManufacture
        boolean isDateValid = isValidDate(dateOfManufacture);
        Assertions.assertTrue(isDateValid, "Invalid date: " + dateOfManufacture);


        }

    }





