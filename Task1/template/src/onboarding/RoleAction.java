package onboarding;

import model.Employee;

@FunctionalInterface
public interface RoleAction {
    void execute(Employee employee);
}
