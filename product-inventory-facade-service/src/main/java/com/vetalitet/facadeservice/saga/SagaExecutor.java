package com.vetalitet.facadeservice.saga;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class SagaExecutor {

    private final List<SagaStep> steps = new ArrayList<>();
    private final Deque<SagaStep> executedSteps = new ArrayDeque<>();

    public SagaExecutor addStep(Runnable action, Runnable compensation) {
        steps.add(new SagaStep(action, compensation));
        return this;
    }

    public void execute() {
        for (SagaStep step : steps) {
            try {
                step.execute();
                executedSteps.push(step);
            } catch (Exception e) {
                while (!executedSteps.isEmpty()) {
                    executedSteps.pop().compensate();
                }
                throw e;
            }
        }
    }

}
