package net.nussi.denki.bot.workflow.stage;

import net.nussi.denki.bot.workflow.step.Step;
import net.nussi.denki.bot.workflow.task.Task;
import net.nussi.denki.bot.workflow.task.TaskResult;
import net.nussi.denki.bot.workflow.workflow.WorkflowContext;

import java.util.HashMap;
import java.util.Optional;
import java.util.concurrent.*;

public class ParallelStage<C> extends Stage<C> {
    private ExecutorService executorService;

    public ParallelStage(String name) {
        super(name, "Stage (parallel)");
        this.executorService = Executors.newVirtualThreadPerTaskExecutor();
    }

    @Override
    protected TaskResult run(WorkflowContext<C> context) throws Exception {

        HashMap<Step<C>, Callable<TaskResult>> callables = new HashMap<>(steps.size());
        steps.forEach((step) -> {
            callables.put(step, () -> step.execute(context.deeper(), Optional.of(this)));
        });

        HashMap<Step<C>, Future<TaskResult>> futures = new HashMap<>(callables.size());
        callables.forEach((step, task) -> {
            Future<TaskResult> future = executorService.submit(task);
            futures.put(step, future);
        });

        HashMap<Step<C>, TaskResult> results = new HashMap<>(futures.size());
        futures.forEach((step, task) -> {
            TaskResult result;
            try {
                result = task.get();
            } catch (InterruptedException | CancellationException e) {
                result = TaskResult.notRun(e);
            } catch (ExecutionException e) {
                result = TaskResult.failure(e);
            }
            results.put(step, result);
        });

        for(TaskResult result : results.values()) {
            if(result instanceof TaskResult.Failure r) return r;
        }

        return TaskResult.success();
    }

    @Override
    public void onChildTaskFinished(Task<C> task, TaskResult result) {


    }
}
