package net.nussi.denki.bot.workflow.step;

import net.nussi.denki.bot.workflow.task.Task;

public abstract class Step<C> extends Task<C> {
    public Step(String name, String type) {
        super(name, type);
    }

    public Step(String name, String type, boolean isCleanup) {
        super(name, type, isCleanup);
    }

    public StepsBuilder<C> builder() {
        return new StepsBuilder<>();
    }
}
