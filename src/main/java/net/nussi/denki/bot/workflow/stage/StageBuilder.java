package net.nussi.denki.bot.workflow.stage;

import net.nussi.denki.bot.workflow.step.Step;
import net.nussi.denki.bot.workflow.step.StepsBuilder;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Consumer;

public class StageBuilder<C> {

    private final String name;
    private final List<Step<C>> steps;

    public StageBuilder(String name) {
        this.name = name;
        this.steps = new LinkedList<>();
    }

    public StageBuilder<C> addStep(Step<C> step) {
        this.steps.add(step);
        return this;
    }

    public StageBuilder<C> steps(Consumer<StepsBuilder<C>> stepBuilderConsumer) {
        StepsBuilder<C> stepsBuilder = new StepsBuilder<>();
        stepBuilderConsumer.accept(stepsBuilder);
        this.steps.addAll(stepsBuilder.build());
        return this;
    }

    public Stage<C> build() {
        return new SequentialStage<>(this.name, this.steps);
    }
}
