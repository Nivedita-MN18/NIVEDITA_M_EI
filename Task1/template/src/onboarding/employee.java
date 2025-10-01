package onboarding;

import model.Employee;

public class employee {
    private Employee employee;
    private RoleAction roleAction;

    public employee(Employee employee, RoleAction roleAction) {
        this.employee = employee;
        this.roleAction = roleAction;
    }

    // Template method
    public void onboard() {
        collectInfo();
        setupEmail();
        assignWorkstation();
        scheduleOrientation();
        roleAction.execute(employee); // flexible role-specific step
    }

    private void collectInfo() {
        System.out.println("Collecting info for " + employee.getName());
    }

    private void setupEmail() {
        String email = employee.getName().toLowerCase().replace(" ", ".") + "@company.com";
        employee.setEmail(email);
        System.out.println("Email assigned: " + email);
    }

    private void assignWorkstation() {
        String ws = "WS-" + (int)(Math.random() * 1000);
        employee.setWorkstation(ws);
        System.out.println("Workstation assigned: " + ws);
    }

    private void scheduleOrientation() {
        System.out.println("Orientation scheduled for " + employee.getName());
    }
}
