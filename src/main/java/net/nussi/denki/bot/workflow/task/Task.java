package net.nussi.denki.bot.workflow.task;

import net.nussi.denki.bot.workflow.workflow.WorkflowContext;

public interface Task {
    void execute(WorkflowContext<?> context);
}
