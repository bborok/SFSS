import java.util.ArrayList;
import java.util.List;

/*
 * Schedule class
 */
public class Schedule {

    private TimePeriod timePeriod;
    private List<Shift> shifts = new ArrayList<Shift>();

    public Schedule(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public TimePeriod getTimePeriod() {
        return timePeriod;
    }

    public void setTimePeriod(TimePeriod timePeriod) {
        this.timePeriod = timePeriod;
    }

    public List<Shift> getAllShifts() {
        return shifts;
    }

    public List<Shift> getShiftsByRole(Role role) {
        List<Shift> list = new ArrayList<Shift>();

        for (Shift shift : shifts) {
            if (shift.getWorkerRole() == role) {
                list.add(shift);
            }
        }

        return list;
    }
}