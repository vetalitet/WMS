package com.vetalitet.facadeservice.saga;

public class SagaStep {

    private final Runnable action;
    private final Runnable compensation;

    public SagaStep(Runnable action, Runnable compensation) {
        this.action = action;
        this.compensation = compensation;
    }

    public void execute() {
        action.run();
    }

    public void compensate() {
        compensation.run();
    }
}
