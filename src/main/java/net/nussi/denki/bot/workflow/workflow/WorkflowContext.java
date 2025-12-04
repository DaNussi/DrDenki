package net.nussi.denki.bot.workflow.workflow;

public class WorkflowContext<T> {
    private final T data;
    private final int depth;

    public WorkflowContext(T data) {
        this.data = data;
        this.depth = 0;
    }

    public WorkflowContext(T data, int depth) {
        this.data = data;
        this.depth = depth;
    }

    public WorkflowContext<T> deeper() {
        return new WorkflowContext<>(data, depth + 1);
    }
}
