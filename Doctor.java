public class Doctor extends Person {
    private String specialization;

    public Doctor(String name, int age, String specialization) {
        super(name, age);
        this.specialization = specialization;
    }

    @Override
    public void displayInfo() {
        System.out.println("Doctor: " + name + ", Age: " + age + ", Specialization: " + specialization);
    }
}
