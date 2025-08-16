import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Doctor> doctors = new ArrayList<>();
        List<Patient> patients = new ArrayList<>();
        List<Appointment> appointments = new ArrayList<>();

        doctors.add(new Doctor("Dr. Ramesh", 45, "Cardiology"));
        doctors.add(new Doctor("Dr. Neha", 38, "Neurology"));

        int choice;
        do {
            System.out.println("\nHospital Management System");
            System.out.println("1. Register Patient\n2. Assign Doctor\n3. Schedule Appointment\n4. Generate Bill\n5. View All Patients\n0. Exit");
            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1: {
                    System.out.print("Enter patient name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter illness: ");
                    String illness = sc.nextLine();
                    patients.add(new Patient(name, age, illness));
                    System.out.println("Patient registered.");
                    break;
                }
                case 2: {
                    if (patients.isEmpty()) {
                        System.out.println("No patients available.");
                        break;
                    }
                    System.out.println("Select patient:");
                    for (int i = 0; i < patients.size(); i++) {
                        System.out.println(i + ". " + patients.get(i).name);
                    }
                    int pIndex = sc.nextInt();
                    sc.nextLine();
                    System.out.println("Select doctor:");
                    for (int i = 0; i < doctors.size(); i++) {
                        System.out.println(i + ". " + doctors.get(i).name);
                    }
                    int dIndex = sc.nextInt();
                    sc.nextLine();
                    patients.get(pIndex).assignDoctor(doctors.get(dIndex));
                    System.out.println("Doctor assigned.");
                    break;
                }
                case 3: {
                    System.out.print("Enter appointment date (DD-MM-YYYY): ");
                    String date = sc.nextLine();
                    System.out.println("Select patient:");
                    for (int i = 0; i < patients.size(); i++) {
                        System.out.println(i + ". " + patients.get(i).name);
                    }
                    int pIndex = sc.nextInt();
                    sc.nextLine();
                    Doctor doc = patients.get(pIndex).getAssignedDoctor();
                    if (doc == null) {
                        System.out.println("Assign a doctor first.");
                        break;
                    }
                    appointments.add(new Appointment(patients.get(pIndex), doc, date));
                    System.out.println("Appointment scheduled.");
                    break;
                }
                case 4: {
                    System.out.println("Select patient for billing:");
                    for (int i = 0; i < patients.size(); i++) {
                        System.out.println(i + ". " + patients.get(i).name);
                    }
                    int pIndex = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter amount: ₹");
                    double amt = sc.nextDouble();
                    sc.nextLine();
                    new Billing(patients.get(pIndex), amt).generateBill();
                    break;
                }
                case 5: {
                    System.out.println("List of Patients:");
                    for (Patient patient : patients) {
                        patient.displayInfo();
                    }
                    if (patients.isEmpty()) {
                        System.out.println("No patients registered.");
                    }
                    break;
                }
                case 0: 
                    System.out.println("Exiting...");
                    break;
                default: 
                    System.out.println("Invalid choice.");
                    break;
            }
        } while (choice != 0);
        sc.close();
    }
}
