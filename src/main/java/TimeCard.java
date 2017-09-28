import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/*
 * TimeCard class
 */
public class TimeCard {

    private List<Task> tasks = new ArrayList<Task>();
    private String notes;
    private Calendar startTime;
    private Calendar endTime;
    private Campus campus;

    public TimeCard(List<Task> tasks, String notes, Calendar startTime, Calendar endTime, Campus campus) {
        this.tasks = tasks;
        this.notes = notes;
        this.startTime = startTime;
        this.endTime = endTime;
        this.campus = campus;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Calendar getStartTime() {
        return startTime;
    }

    public void setStartTime(Calendar startTime) {
        this.startTime = startTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public Campus getCampus() {
        return campus;
    }

    public void setCampus(Campus campus) {
        this.campus = campus;
    }
}