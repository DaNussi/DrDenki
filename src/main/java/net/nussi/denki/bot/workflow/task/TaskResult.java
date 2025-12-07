package net.nussi.denki.bot.workflow.task;

import lombok.Getter;

import java.util.Optional;

@Getter
public class TaskResult {
    protected final Type type;

    protected TaskResult(Type type) {
        this.type = type;
    }

    public static TaskResult success() {
        return new Success();
    }

    public static TaskResult success(String message) {
        return new Success(message);
    }

    public static TaskResult failure(Exception exception) {
        return new Failure(exception);
    }

    public static TaskResult failure(TaskResult.Failure failure) {
        return new Failure(failure);
    }

    public static TaskResult notRun(Exception e) {
        return new NotRun(e.getMessage());
    }

    @Getter
    public static class NotRun extends TaskResult {
        public String reason;

        public NotRun(String reason) {
            super(Type.NOT_RUN);
            this.reason = reason;
        }
    }

    @Getter
    public static class Success extends TaskResult {
        private final Optional<String> message;

        public Success() {
            super(Type.SUCCESS);
            this.message = Optional.empty();
        }

        public Success(String message) {
            super(Type.SUCCESS);
            this.message = Optional.of(message);
        }
    }

    @Getter
    public static class Failure extends TaskResult {
        private final Exception exception;

        public Failure(Exception exception) {
            super(Type.FAILURE);
            this.exception = exception;
        }

        public Failure(TaskResult.Failure failure) {
            super(Type.FAILURE);
            this.exception = failure.exception;
        }
    }

    public enum Type {
        SUCCESS,
        NOT_RUN,
        FAILURE
    }
}
