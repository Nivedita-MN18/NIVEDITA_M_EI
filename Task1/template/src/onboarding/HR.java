package onboarding;

public class HR extends employee {

    public HR(model.Employee employee) {
        super(employee);
    }

    @Override
    protected void additionalSteps() {
        System.out.println("HR-specific setup: Access to HR portal, payroll system, recruitment tools.");
    }
}
