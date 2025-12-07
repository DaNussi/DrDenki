package net.nussi.denki.bot.workflow.stage;

import net.nussi.denki.bot.workflow.step.Step;
import net.nussi.denki.bot.workflow.task.Task;

import java.util.LinkedList;
import java.util.List;

public abstract class Stage<C> extends Task<C> {
    protected final List<Step<C>> steps;

    public Stage(String name, String type) {
        super(name, type);
        this.steps = new LinkedList<>();
    }

    public Stage(String name, String type, List<Step<C>> steps) {
        super(name, type);
        this.steps = steps;
    }

    public Stage<C> addStep(Step<C> step) {
        steps.add(step);
        return this;
    }

    public static <D> StageBuilder<D> builder(String name) {
        return new StageBuilder<D>(name);
    }
}
