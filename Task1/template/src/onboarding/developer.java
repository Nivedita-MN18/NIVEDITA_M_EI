package onboarding;

public class developer extends employee {

    public developer(model.Employee employee) {
        super(employee);
    }

    @Override
    protected void additionalSteps() {
        System.out.println("Developer-specific setup: Installing IDE, version control access, dev environment setup.");
    }
}
