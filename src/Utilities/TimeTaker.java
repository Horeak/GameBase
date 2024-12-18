package Utilities;

import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class TimeTaker {
	private static final HashMap<String, Long> startTimers = new HashMap<>();
	
	public static void startTimeTaker( String classID ) {
		startTimeTaker(classID, System.currentTimeMillis());
	}
	
	public static void startTimeTaker( String classID, Long time ) {
		if (startTimers.containsKey(classID)) {
			startTimers.remove(classID);
		}
		
		startTimers.put(classID, time);
	}
	
	public static void clearTimeTaker() {
		startTimers.clear();
	}
	
	public static void removeTimeTaker( String classID ) {
		startTimers.remove(classID);
	}
	
	public static long getStartTime( String classID ) {
		if (startTimers.containsKey(classID)) {
			return startTimers.get(classID);
		}
		
		return -1;
	}
	
	public static long getTime( String classID ) {
		if (startTimers.containsKey(classID)) {
			return System.currentTimeMillis() - startTimers.get(classID);
		}
		
		return -1;
	}
	
	public static String getText( String classID, String formate, boolean includeZero ) {
		return getText(classID, 0, formate, includeZero);
	}
	
	public static String getText( String classID, long starFrom, String formate, boolean includeZero ) {
		return getText(classID, starFrom, getTime(classID) - starFrom, formate, includeZero);
	}
	
	public static String getText( String classID, long starFrom, long time, String formate, boolean includeZero ) {
		long timeUsed = time;
		
		long secs = TimeUnit.MILLISECONDS.toSeconds(timeUsed);
		long mins = TimeUnit.MILLISECONDS.toMinutes(timeUsed);
		long hours = TimeUnit.MILLISECONDS.toHours(timeUsed);
		long days = TimeUnit.MILLISECONDS.toDays(timeUsed);
		
		if (days > 0) {
			hours -= TimeUnit.DAYS.toHours(days);
			mins -= TimeUnit.DAYS.toMinutes(days);
			secs -= TimeUnit.DAYS.toSeconds(days);
		}
		
		if (hours > 0) {
			mins -= TimeUnit.HOURS.toMinutes(hours);
			secs -= TimeUnit.HOURS.toSeconds(hours);
		}
		
		if (mins > 0) {
			secs -= TimeUnit.MINUTES.toSeconds(mins);
		}
		
		String text = formate;
		
		if (includeZero || timeUsed > 0) {
			text = text.replace("<ms>", timeUsed + "ms ");
		}
		
		if (includeZero || secs > 0) {
			text = text.replace("<secs>", secs + "s ");
		}
		
		if (includeZero || mins > 0) {
			text = text.replace("<mins>", mins + "m ");
		}
		
		if (includeZero || hours > 0) {
			text = text.replace("<hours>", hours + "h ");
		}
		
		if (includeZero || days > 0) {
			text = text.replace("<days>", days + "d ");
		}
		
		
		text = text.replace("<ms>", "");
		text = text.replace("<secs>", "");
		text = text.replace("<mins>", "");
		text = text.replace("<hours>", "");
		text = text.replace("<days>", "");
		
		return text;
	}
}
