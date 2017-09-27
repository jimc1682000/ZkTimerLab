package tw.studio.jimmy.lab;

import java.util.Date;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zhtml.Messagebox;

public class JimmyLab {
	private Integer dalay = 1000;
	private Integer count = 0;
	private Integer total = 10;
	private Boolean isRunning = true;

	@NotifyChange({ "date", "progress", "count", "isRunning", "runningStatus" })
	@Command
	public void tickTack() {
		if (!getIsRunning())
			return;
		if (count < getTotal()) {
			count = count + 1;
		}
		if (count == getTotal()) {
			Messagebox.show("Time's up!");
			isRunning = false;
		}
	}

	public Integer getProgress() {
		if (count == 0) {
			return 0;
		}
		return (int) ((count * 100.0f) / total);
	}

	@NotifyChange({ "isRunning", "runningStatus" })
	@Command
	public void start() {
		isRunning = true;
	}

	@NotifyChange({ "isRunning", "runningStatus" })
	@Command
	public void stop() {
		isRunning = false;
	}

	@NotifyChange({ "date", "progress", "count", "isRunning", "runningStatus" })
	@Command
	public void reload() {
		// Method 1
		// Redirect to same page
		// Executions.getCurrent().sendRedirect("");
		// Method 2
		count = 0;
		isRunning = true;
	}

	public String getDate() {
		return new Date().toString();
	}

	public Integer getCount() {
		return count;
	}

	public Boolean getIsRunning() {
		return isRunning;
	}

	public String getRunningStatus() {
		return isRunning ? "Running" : "Stop";
	}

	public Integer getDalay() {
		return dalay;
	}

	public Integer getTotal() {
		return total;
	}

	@NotifyChange({ "count", "isRunning", "progress", "runningStatus" })
	public void setTotal(Integer total) {
		// reset count
		count = 0;
		// stop
		isRunning = false;
		this.total = total;
	}
}
