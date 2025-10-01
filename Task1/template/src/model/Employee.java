package model;

public class Employee {
    private String name;
    private String role;
    private String email;
    private String workstation;

    public Employee(String name, String role) {
        this.name = name;
        this.role = role;
    }

    public String getName() { return name; }
    public String getRole() { return role; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getWorkstation() { return workstation; }
    public void setWorkstation(String workstation) { this.workstation = workstation; }
}
