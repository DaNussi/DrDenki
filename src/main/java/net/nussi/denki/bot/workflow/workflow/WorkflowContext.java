package net.nussi.denki.bot.workflow.workflow;

import java.util.HashMap;
import java.util.Objects;

public class WorkflowContext<T> {
    public final T data;
    private final int depth;
    private HashMap<String, Object> variables;

    public WorkflowContext(T data) {
        this.data = data;
        this.depth = 0;
        this.variables = new HashMap<>();
    }
    public WorkflowContext(T data, HashMap<String, Object> variables) {
        this.data = data;
        this.depth = 0;
        this.variables = variables;
    }

    public WorkflowContext(T data, int depth) {
        this.data = data;
        this.depth = depth;
        this.variables = new HashMap<>();
    }

    public WorkflowContext<T> deeper() {
        return new WorkflowContext<>(data, depth + 1);
    }

    public String loggingPrefixStart() {
        return "║ ".repeat(depth) + "╔═ ";
    }

    public String loggingPrefix() {
        return "║ ".repeat(depth) + "║ ";
    }

    public String loggingPrefixEnd() {
        return "║ ".repeat(depth) + "╚═ ";
    }
}
