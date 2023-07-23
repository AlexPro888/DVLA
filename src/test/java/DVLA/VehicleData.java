package DVLA;


//define the VehicleData class representing the data of each vehicle:

    public class VehicleData {
        private final String vrn;
        private final String make;
        private final String colour;
        private final String dateOfManufacture;


        // Constructor
        public VehicleData(String vrn, String make, String colour, String dateOfManufacture) {
            this.vrn = vrn;
            this.make = make;
            this.colour = colour;
            this.dateOfManufacture = dateOfManufacture;
        }



        @Override
        public String toString() {
            return "VehicleData{" +
                    "vrn='" + vrn + '\'' +
                    ", make='" + make + '\'' +
                    ", colour='" + colour + '\'' +
                    ", dateOfManufacture='" + dateOfManufacture + '\'' +
                    '}';
        }

        //Adding getters
        public String getVrn() {
            return vrn;
        }

        public String getMake() {
            return make;
        }

        public String getColour() {
            return colour;
        }

        public String getDateOfManufacture() {
            return dateOfManufacture;
        }



    }


