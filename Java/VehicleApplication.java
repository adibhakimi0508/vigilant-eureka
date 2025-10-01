import java.util.*;
import java.io.*;

public class VehicleApplication {

   public static void main(String[] args) {
      Scanner scan = new Scanner(System.in);

      while (true) {
         System.out.println("========== MAIN MENU ==========");
         System.out.println("1. Applicant");
         System.out.println("2. Admin");
         System.out.println("0. Exit");
         System.out.print("Enter your role: ");
         int choice = Integer.parseInt(scan.nextLine());

         if (choice == 1) {
            applicantMenu(scan);
         } else if (choice == 2) {
            adminMenu(scan);
         } else if (choice == 0) {
            System.out.println("Exiting system. Goodbye!");
            break;
         } else {
            System.out.println("Invalid choice. Try again.");
         }
      }
   }

   // ------------------ Applicant Menu ------------------
   public static void applicantMenu(Scanner scan) {
      while (true) {
         System.out.println("\n==== APPLICANT MENU ====");
         System.out.println("1. Submit New Application");
         System.out.println("2. View Application Status");
         System.out.println("0. Return to Main Menu");
         System.out.print("Your choice: ");
         int choice = Integer.parseInt(scan.nextLine());

         if (choice == 1) {
            submitApplication(scan);
         } else if (choice == 2) {
            checkApplicationStatus(scan);
         } else if (choice == 0) {
            break;
         } else {
            System.out.println("Invalid choice.");
         }
      }
   }

   public static void submitApplication(Scanner scan) {
      try {
         int lastId = 20000;
         BufferedReader reader = new BufferedReader(new FileReader("Application List.txt"));
         String line, lastLine = null;
         while ((line = reader.readLine()) != null) {
            if (!line.trim().isEmpty()) lastLine = line;
         }
         if (lastLine != null) {
            StringTokenizer st = new StringTokenizer(lastLine, ",");
            lastId = Integer.parseInt(st.nextToken());
         }
         reader.close();
         int newId = lastId + 1;

         System.out.print("Full Name: ");
         String name = scan.nextLine();
         System.out.print("Contact Number: ");
         String phone = scan.nextLine();
         System.out.print("Application Date (e.g., 2025-07-06): ");
         String date = scan.nextLine();
         System.out.print("Vehicle Type (car/van/bus): ");
         String type = scan.nextLine();
         System.out.print("Reason of Usage: ");
         String reason = scan.nextLine();
         System.out.print("Estimated Distance (km): ");
         double distance = Double.parseDouble(scan.nextLine());

         PrintWriter out = new PrintWriter(new FileWriter("Application List.txt", true));
         out.println("\n" + newId + "," + name + "," + phone + "," + date + "," + type + "," + reason + "," + distance);
         out.close();

         System.out.println("Application submitted! Your Applicant ID is " + newId);
      } catch (Exception e) {
         System.out.println("Failed to submit application: " + e.getMessage());
      }
   }

   public static void checkApplicationStatus(Scanner scan) {
      System.out.print("Enter your full name: ");
      String nameToSearch = scan.nextLine().trim().toLowerCase();
      boolean found = false;

      try {
         BufferedReader pass = new BufferedReader(new FileReader("Passed Application.txt"));
         String line;
         while ((line = pass.readLine()) != null) {
            if (line.toLowerCase().contains(nameToSearch)) {
               System.out.println("Application Status: PASSED");
               found = true;
               break;
            }
         }
         pass.close();

         if (!found) {
            BufferedReader fail = new BufferedReader(new FileReader("Failed Application.txt"));
            while ((line = fail.readLine()) != null) {
               if (line.toLowerCase().contains(nameToSearch)) {
                  System.out.println("Application Status: FAILED");
                  found = true;
                  break;
               }
            }
            fail.close();
         }

         if (!found) {
            System.out.println("No application submitted.");
         }
      } catch (Exception e) {
         System.out.println("Error checking status: " + e.getMessage());
      }
   }

   // ------------------ Admin Menu ------------------
   public static void adminMenu(Scanner scan) {
      while (true) {
         System.out.println("\n==== ADMIN MENU ====");
         System.out.println("1. Approve/Reject Applications");
         System.out.println("2. View Available Vehicles");
         System.out.println("0. Return to Main Menu");
         System.out.print("Your choice: ");
         int choice = Integer.parseInt(scan.nextLine());

         if (choice == 1) {
            processApplications(scan);
         } else if (choice == 2) {
            viewAvailableVehicles();
         } else if (choice == 0) {
            break;
         } else {
            System.out.println("Invalid choice.");
         }
      }
   }

   public static void viewAvailableVehicles() {
      try {
         BufferedReader reader = new BufferedReader(new FileReader("Vehicle Info.txt"));
         String line;
         boolean anyAvailable = false;
         System.out.println("=== Available Vehicles ===");
         while ((line = reader.readLine()) != null) {
            if (line.toLowerCase().contains("true")) {
               System.out.println(line);
               anyAvailable = true;
            }
         }
         reader.close();

         if (!anyAvailable) {
            System.out.println("No vehicles available!");
         }
      } catch (Exception e) {
         System.out.println("Error: " + e.getMessage());
      }
   }

   public static void processApplications(Scanner scan) {
      try {
         BufferedReader inVehicle = new BufferedReader(new FileReader("Vehicle Info.txt"));
         BufferedReader inAppList = new BufferedReader(new FileReader("Application List.txt"));
         PrintWriter outPass = new PrintWriter(new FileWriter("Passed Application.txt", true));
         PrintWriter outFail = new PrintWriter(new FileWriter("Failed Application.txt", true));

         Vehicle[] vehicles = new Vehicle[100];
         Applicant[] applicants = new Applicant[100];
         int i = 0;

         String inData1, inData2;

         while ((inData1 = inVehicle.readLine()) != null && (inData2 = inAppList.readLine()) != null) {
            if (inData1.trim().isEmpty() || inData2.trim().isEmpty()) continue;

            StringTokenizer st1 = new StringTokenizer(inData1, ",");
            StringTokenizer st2 = new StringTokenizer(inData2, ",");

            int applicantId = Integer.parseInt(st2.nextToken());
            String name = st2.nextToken();
            String contactNumber = st2.nextToken();
            String applicationDate = st2.nextToken();
            String vehicleNeeded = st2.nextToken();
            String reasonOfUsage = st2.nextToken();
            double distance = Double.parseDouble(st2.nextToken());
            applicants[i] = new Applicant(applicantId, name, contactNumber, applicationDate, vehicleNeeded, reasonOfUsage, distance);

            int vehicleId = Integer.parseInt(st1.nextToken());
            String vehicleType = st1.nextToken();
            boolean available = Boolean.parseBoolean(st1.nextToken());
            double fuelTank = Double.parseDouble(st1.nextToken());
            double maxDistance = Double.parseDouble(st1.nextToken());
            String brand = st1.nextToken();
            String plate = st1.nextToken();
            String color = st1.nextToken();

            if (vehicleType.equalsIgnoreCase("car")) {
               vehicles[i] = new Car(vehicleId, vehicleType, available, fuelTank, maxDistance, applicants[i], brand, plate, color);
            } else if (vehicleType.equalsIgnoreCase("van")) {
               vehicles[i] = new Van(vehicleId, vehicleType, available, fuelTank, maxDistance, applicants[i], brand, plate, color);
            } else if (vehicleType.equalsIgnoreCase("bus")) {
               vehicles[i] = new Bus(vehicleId, vehicleType, available, fuelTank, maxDistance, applicants[i], brand, plate, color);
            }
            i++;
         }

         int approvedCount = 0;
         for (int j = 0; j < i; j++) {
            if (applicants[j] == null || vehicles[j] == null) continue;

            System.out.println("\n" + applicants[j].toString());
            String decision;
            do {
               System.out.print("Enter decision (pass/fail): ");
               decision = scan.nextLine().trim().toLowerCase();
            } while (!decision.equals("pass") && !decision.equals("fail"));

            if (decision.equals("pass")) {
               outPass.println(applicants[j].toString());
               vehicles[j].setAvailable(false);
               approvedCount++;
               System.out.println("Application Passed.");
            } else {
               outFail.println(applicants[j].toString());
               System.out.println("Application Failed.");
            }
         }

         PrintWriter outVehicle = new PrintWriter(new FileWriter("Vehicle Info.txt", false));
         for (int k = 0; k < i; k++) {
            outVehicle.println(vehicles[k].getVehicleId() + "," + vehicles[k].getVehicleType() + "," +
                             vehicles[k].getAvailable() + "," + vehicles[k].getFuelTank() + "," +
                             vehicles[k].getMaxDistance());

            if (vehicles[k] instanceof Car) {
               Car car = (Car) vehicles[k];
               outVehicle.println("," + car.getCarBrand() + "," + car.getCarPlate() + "," + car.getCarColor());
            } else if (vehicles[k] instanceof Van) {
               Van van = (Van) vehicles[k];
               outVehicle.println("," + van.getVanBrand() + "," + van.getVanPlate() + "," + van.getVanColor());
            } else if (vehicles[k] instanceof Bus) {
               Bus bus = (Bus) vehicles[k];
               outVehicle.println("," + bus.getBusBrand() + "," + bus.getBusPlate() + "," + bus.getBusColor());
            }
         }

         outVehicle.close();
         outPass.close();
         outFail.close();
         inVehicle.close();
         inAppList.close();

         boolean allUnavailable = true;
         for (int k = 0; k < i; k++) {
            if (vehicles[k].getAvailable()) {
               allUnavailable = false;
               break;
            }
         }

         if (allUnavailable) {
            System.out.println("\nAll vehicles are now unavailable. Returning to Admin Menu.");
         } else {
            System.out.println("\nApplications processed successfully.");
         }

      } catch (Exception e) {
         System.out.println("Admin processing error: " + e.getMessage());
         e.printStackTrace();
      }
   }
}
