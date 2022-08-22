package henry;

import command.Commands;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Task {
    protected String description;
    protected boolean isDone;
    private final Commands type;
    private final LocalDateTime date;

    public Task(Commands type, String description, LocalDateTime date) {
        this(type, description, date, false);
    }

    public Task(Commands type, String description, LocalDateTime date, boolean isDone) {
        this.type = type;
        this.description = description;
        this.date = date;
        this.isDone = isDone;
    }

    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]"; // mark done task with X
    }

    public void setComplete(boolean status) {
        this.isDone = status;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        switch (type) {
        case TODO:
            return "[T]" + getStatusIcon() + " " + description;
        case DEADLINE:
            return "[D]" + getStatusIcon() + " " + description + " (by: "
                   + date.format(formatter).replace("T", " ") + ")";
        default:
            return "[E]" + getStatusIcon() + " " + description + " (at: "
                   + date.format(formatter).replace("T", " ") + ")";
        }
    }

    public String toSimpleString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        description = description.trim();
        switch (type) {
        case TODO:
            return "T | " + (isDone ? 1 : 0) + " | " + description;
        case DEADLINE:
            return "D | " + (isDone ? 1 : 0) + " | " + description + " | (by: "
                   + date.format(formatter).replace("T", " ") + ")";
        default:
            return "E | " + (isDone ? 1 : 0) + " | " + description + " | (at: "
                   + date.format(formatter).replace("T", " ") + ")";
        }
    }
}
