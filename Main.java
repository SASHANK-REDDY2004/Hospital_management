import java.util.Scanner;

// ---------------- Base Class ----------------
class Person {
    protected String name;
    protected int age;

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Age: " + age);
    }
}

// ---------------- Doctor Class ----------------
class Doctor extends Person {
    private String specialization;

    Doctor(String name, int age, String specialization) {
        super(name, age);
        this.specialization = specialization;
    }

    public String getSpecialization() {
        return specialization;
    }

    @Override
    public void displayInfo() {
        System.out.println("Doctor: " + name + ", Age: " + age + ", Specialization: " + specialization);
    }

    // Show doctor's appointments
    public void viewSchedule(Appointment[] appointments, int appointmentCount) {
        System.out.println("\nSchedule for Dr. " + name + ":");
        boolean found = false;
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getDoctor() == this) {
                appointments[i].displayAppointment();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments scheduled.");
        }
    }
}

// ---------------- Patient Class ----------------
class Patient extends Person {
    private String illness;
    private Doctor assignedDoctor;

    Patient(String name, int age, String illness) {
        super(name, age);
        this.illness = illness;
    }

    public void assignDoctor(Doctor doctor) {
        this.assignedDoctor = doctor;
    }

    public Doctor getAssignedDoctor() {
        return assignedDoctor;
    }

    @Override
    public void displayInfo() {
        System.out.println("Patient: " + name + ", Age: " + age + ", Illness: " + illness);
        if (assignedDoctor != null) {
            System.out.println("Assigned Doctor: " + assignedDoctor.name);
        } else {
            System.out.println("No doctor assigned yet.");
        }
    }

    // Show patient's appointments
    public void viewSchedule(Appointment[] appointments, int appointmentCount) {
        System.out.println("\nSchedule for Patient " + name + ":");
        boolean found = false;
        for (int i = 0; i < appointmentCount; i++) {
            if (appointments[i].getPatient() == this) {
                appointments[i].displayAppointment();
                found = true;
            }
        }
        if (!found) {
            System.out.println("No appointments scheduled.");
        }
    }
}

// ---------------- Appointment Class ----------------
class Appointment {
    private Patient patient;
    private Doctor doctor;
    private String date;

    Appointment(Patient patient, Doctor doctor, String date) {
        this.patient = patient;
        this.doctor = doctor;
        this.date = date;
    }

    public Patient getPatient() {
        return patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void displayAppointment() {
        System.out.println("Appointment: " + patient.name + " with " + doctor.name + " on " + date);
    }
}

// ---------------- Billing Class ----------------
class Billing {
    private Patient patient;
    private double amount;

    Billing(Patient patient, double amount) {
        this.patient = patient;
        this.amount = amount;
    }

    public void generateBill() {
        System.out.println("\n---- BILL ----");
        System.out.println("Patient: " + patient.name);
        System.out.println("Amount: ₹" + amount);
        System.out.println("--------------\n");
    }
}

// ---------------- Main Class ----------------
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Doctor[] doctors = new Doctor[10];
        Patient[] patients = new Patient[100];
        Appointment[] appointments = new Appointment[100];

        int doctorCount = 0, patientCount = 0, appointmentCount = 0;

        // Predefined Doctors
        doctors[doctorCount++] = new Doctor("Dr. Ramesh", 45, "Cardiology");
        doctors[doctorCount++] = new Doctor("Dr. Neha", 38, "Neurology");

        int choice;
        do {
            System.out.println("\nHospital Management System");
            System.out.println("1. Register Patient");
            System.out.println("2. Assign Doctor");
            System.out.println("3. Schedule Appointment");
            System.out.println("4. Generate Bill");
            System.out.println("5. View All Patients");
            System.out.println("6. View Schedule");
            System.out.println("0. Exit");
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

                    patients[patientCount++] = new Patient(name, age, illness);
                    System.out.println("Patient registered.");
                    break;
                }
                case 2: {
                    if (patientCount == 0) {
                        System.out.println("No patients available.");
                        break;
                    }
                    System.out.println("Select patient:");
                    for (int i = 0; i < patientCount; i++) {
                        System.out.println(i + ". " + patients[i].name);
                    }
                    int pIndex = sc.nextInt();
                    sc.nextLine();

                    System.out.println("Select doctor:");
                    for (int i = 0; i < doctorCount; i++) {
                        System.out.println(i + ". " + doctors[i].name);
                    }
                    int dIndex = sc.nextInt();
                    sc.nextLine();

                    patients[pIndex].assignDoctor(doctors[dIndex]);
                    System.out.println("Doctor assigned.");
                    break;
                }
                case 3: {
                    System.out.print("Enter appointment date (DD-MM-YYYY): ");
                    String date = sc.nextLine();

                    System.out.println("Select patient:");
                    for (int i = 0; i < patientCount; i++) {
                        System.out.println(i + ". " + patients[i].name);
                    }
                    int pIndex = sc.nextInt();
                    sc.nextLine();

                    Doctor doc = patients[pIndex].getAssignedDoctor();
                    if (doc == null) {
                        System.out.println("Assign a doctor first.");
                        break;
                    }

                    appointments[appointmentCount++] = new Appointment(patients[pIndex], doc, date);
                    System.out.println("Appointment scheduled.");
                    break;
                }
                case 4: {
                    System.out.println("Select patient for billing:");
                    for (int i = 0; i < patientCount; i++) {
                        System.out.println(i + ". " + patients[i].name);
                    }
                    int pIndex = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter amount: ₹");
                    double amt = sc.nextDouble();
                    sc.nextLine();

                    new Billing(patients[pIndex], amt).generateBill();
                    break;
                }
                case 5: {
                    System.out.println("List of Patients:");
                    if (patientCount == 0) {
                        System.out.println("No patients registered.");
                    }
                    for (int i = 0; i < patientCount; i++) {
                        patients[i].displayInfo();
                    }
                    break;
                }
                case 6: {
                    System.out.println("1. View Doctor Schedule\n2. View Patient Schedule");
                    int subChoice = sc.nextInt();
                    sc.nextLine();

                    if (subChoice == 1) {
                        System.out.println("Select doctor:");
                        for (int i = 0; i < doctorCount; i++) {
                            System.out.println(i + ". " + doctors[i].name);
                        }
                        int dIndex = sc.nextInt();
                        sc.nextLine();
                        doctors[dIndex].viewSchedule(appointments, appointmentCount);
                    } else if (subChoice == 2) {
                        System.out.println("Select patient:");
                        for (int i = 0; i < patientCount; i++) {
                            System.out.println(i + ". " + patients[i].name);
                        }
                        int pIndex = sc.nextInt();
                        sc.nextLine();
                        patients[pIndex].viewSchedule(appointments, appointmentCount);
                    } else {
                        System.out.println("Invalid option.");
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
