package net.nussi.denki.bot.workflow.step;

import java.time.Duration;
import java.util.LinkedList;
import java.util.List;

public class StepsBuilder<C> {
    private List<Step<C>> steps;

    public StepsBuilder() {
        this.steps = new LinkedList<>();
    }

    public StepsBuilder<C> helloWorld() {
        steps.add(new HelloWorldStep<>());
        return this;
    }

    public StepsBuilder<C> delay(Duration delay) {
        steps.add(new DelayStep<>(delay));
        return this;
    }

    public List<Step<C>> build() {
        return steps;
    }

    public StepsBuilder<C> step(Step<C> step) {
        steps.add(step);
        return this;
    }
}
