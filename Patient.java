public class Patient extends Person {
    private String illness;
    private Doctor assignedDoctor;

    public Patient(String name, int age, String illness) {
        super(name, age);
        this.illness = illness;
    }

    public void assignDoctor(Doctor doctor) {
        this.assignedDoctor = doctor;
    }

    @Override
    public void displayInfo() {
        System.out.println("Patient: " + name + ", Age: " + age + ", Illness: " + illness);
        if (assignedDoctor != null) {
            System.out.print("Assigned ");
            assignedDoctor.displayInfo();
        }
    }

    public Doctor getAssignedDoctor() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getAssignedDoctor'");
    }
}
