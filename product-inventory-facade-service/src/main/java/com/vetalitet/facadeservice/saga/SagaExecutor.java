package com.vetalitet.facadeservice.saga;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SagaExecutor {

    private final List<SagaStep> steps = new ArrayList<>();

    public SagaExecutor addStep(Runnable action, Runnable compensation) {
        steps.add(new SagaStep(action, compensation));
        return this;
    }

    public void execute() {
        List<SagaStep> executedSteps = new ArrayList<>();

        try {
            for (SagaStep step : steps) {
                step.action().run();
                executedSteps.add(step);
            }
        } catch (Exception e) {
            for (int i = executedSteps.size() - 1; i >= 0; i--) {
                executedSteps.get(i).compensation().run();
            }
            throw e;
        }
    }

    private record SagaStep(Runnable action, Runnable compensation) {}
}
