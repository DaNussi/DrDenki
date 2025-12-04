package net.nussi.denki.bot.workflow.stage;

import net.nussi.denki.bot.workflow.step.Step;

import java.util.LinkedList;

public class StageBuilder {
    private static final LinkedList<Step> steps = new LinkedList<>();
    private String name;

    public StageBuilder(String name) {
        this.name = name;
    }

    public StageBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public StageBuilder addStep(Step step) {
        steps.add(step);
        return this;
    }

    public Stage build() {
        return new Stage(
                name,
                steps.toArray(new Step[steps.size()])
        );
    }

}
