/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Course activity, a parent class for course schedule
 * @author Xufeng Ce
 *
 */
public abstract class Activity implements Conflict {

	/** Course's title. */
	private String title;
	/** Course's meeting days */
	private String meetingDays;
	/** Course's starting time */
	private int startTime;
	/** Course's ending time */
	private int endTime;

	/**
	 *  Constant values for methods in Activity class
	 * Upper Course's meeting time on hour
	 * Upper Course's meeting time on minutes
	 * */
	private static final int UPPER_HOUR = 23,
							 UPPER_MINUTE = 59;

	/**
	 * Create activity for Activity's title, meeting days, start time, end time.
	 * @param title Course title
	 * @param meetingDays Course meetingDays
	 * @param startTime Course startTime
	 * @param endTime Course endTime
	 */
	public Activity(String title, String meetingDays, int startTime, int endTime) {
		super();
		setTitle(title);
		setMeetingDaysAndTime(meetingDays, startTime, endTime);
	}
	/**
	 * return the short version the the information to the GUI
	 * @return String the String
	 */
	public abstract String[] getShortDisplayArray();
	/**
	 * return the long version of the information to the GUI
	 * @return String the String
	 */
	public abstract String[] getLongDisplayArray();
	/**
	 * return a boolean variable whether the course has already exist
	 * @param activity the activity to check duplicate
	 * @return true if duplicate
	 */
	public abstract boolean isDuplicate(Activity activity);
	
	/**
	 * Returns the Activity's title
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Sets the Activity's title
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		// Check the title is null or empty
		if(title == null || "".equals(title)) {
			throw new IllegalArgumentException("Invalid title.");
		}
		
		
		this.title = title;
	}

	/**
	 * Returns the Activity's meetingDays
	 * @return the meetingDays
	 */
	public String getMeetingDays() {
		return meetingDays;
	}

	/**
	 * return a string of meeting date, start times, and end times,
	 * for example (MW 2:00-3:00PM)
	 * @return return the string
	 */
	public String getMeetingString() {
		// break apart start time and end time into hours and minutes
		int startHour = this.startTime / 100;
		int endHour = this.endTime / 100;
		int startMin = this.startTime % 100;
		int endMin = this.endTime % 100;
		
		// string for start time and end time 
		String startString = "";
		String endString = "";
		
		// temporary string for minute if the minutes is < 10
		String startMinStr = "";
		String endMinStr = "";
		
		// if the time is over 12, convert to PM 
		if(startHour > 12) {
			startHour -= 12;
		}
		if(endHour > 12) {
			endHour -= 12;
		}
		
		// if the hour is 0, set the hour to 12 AM
		if(startHour == 0) {
			startHour = 12;
		}
		
		if(endHour == 0) {
			endHour = 12;
		}
		
		// if the minutes < 10, add leading 0 in front of the number
		if(startMin < 10) {
			 startMinStr = "0" + startMin;
		} else {
			 startMinStr = "" + startMin;
		}
		if(endMin < 10) {
			 endMinStr = "0" + endMin;
		} else {
			 endMinStr = "" + endMin;
		}
		
		// check if the time is PM or AM
		if(this.startTime >= 1200 ) {
			startString = startHour + ":" + startMinStr + "PM";
		}
		if(this.endTime >= 1200) {
			endString = endHour + ":" + endMinStr + "PM";
		} 
		if (this.startTime == 0 && this.endTime == 0 ){
			return "Arranged";
		}
		if(this.startTime < 1200) {
			startString = startHour + ":" + startMinStr + "AM";
		}
		if(this.endTime < 1200) {
			endString = endHour + ":" + endMinStr + "AM";
		}
		
		// convert successfully, return the final String with meeting time
		return this.meetingDays + " " + startString + "-" + endString;
		
	}

	/** Set up the meeting days and time for an activity
	 * this method handle the common checks and final assignment
	 * @param meetingDays the meeting day to set
	 * @param startTime the start time to set
	 * @param endTime the end time to set
	 */
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// check whether the meeting days is null or empty
		if(meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//check if meeting days is arranged
		if(meetingDays.charAt(0) == 'A') {
			if(startTime != 0 || endTime != 0) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			this.meetingDays = meetingDays;
			this.startTime = 0;
			this.endTime = 0;
		// if the meeting days is not arranged, check what is the meeting date
		} else {
			
			// if the meeting date is not M, T, W, H, F, throw an exception
			for(int i = 0; i < meetingDays.length(); i++) {
				if(!(meetingDays.charAt(i) == 'M' || 
				   meetingDays.charAt(i) == 'T' ||
				   meetingDays.charAt(i) == 'W' ||
				   meetingDays.charAt(i) == 'H' ||
				   meetingDays.charAt(i) == 'F' ||
				   meetingDays.charAt(i) == 'S' ||
				   meetingDays.charAt(i) == 'U')) {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			 //compare each char see if there is duplicate meeting days
			for(int i = 0; i < meetingDays.length(); i++) {
				for(int j = i + 1; j < meetingDays.length(); j++) {
					if(meetingDays.charAt(i) == meetingDays.charAt(j)) {
						throw new IllegalArgumentException("Invalid meeting days and times.");
					}
				}
			}
			
			
			// break the time into hours and minutes 
			int startHour = startTime / 100;
			int endHour = endTime / 100;
			int startMin = startTime % 100;
			int endMin = endTime % 100;
			
			// check the start hour, start minute, end hour, and end minute to see they are
			// between for hours:0-23, for minutes: 0-59.
			// if not in between then throw IAE
			if(startHour < 0 || startHour > UPPER_HOUR || startMin < 0 || startMin > UPPER_MINUTE ||
			   endHour < 0 || endHour > UPPER_HOUR || endMin < 0 || endMin > UPPER_MINUTE ||
			   endTime < startTime) {
				throw new IllegalArgumentException("Invalid meeting days and times.");
			}
			
			this.meetingDays = meetingDays;
			this.startTime = startTime;
			this.endTime = endTime;
			
		}
		
		
		
	}

	/**
	 * Returns the Activity's startTime
	 * @return the startTime
	 */
	public int getStartTime() {
		return startTime;
	}

	/**
	 * Returns the Activity's endTime
	 * @return the endTime
	 */
	public int getEndTime() {
		return endTime;
	}
	
	
	/**
	 * check for conflict through activity
	 * @param possibleConflictingActivity any conflicting activities
	 * @throws ConflictException if there is a conflict between classes or events
	 */
	@Override
	public void checkConflict(Activity possibleConflictingActivity) throws ConflictException {
		boolean sameDay = false;
		
		// check if the course has the same meeting day
		// if the course meeting day is Arranged, it should not have conflict with any other course
		for (int i = 0; i < possibleConflictingActivity.getMeetingDays().length(); i++) {
			String s = "" + possibleConflictingActivity.getMeetingDays().charAt(i);
			if(!(this.getMeetingDays().contains("A") || possibleConflictingActivity.getMeetingDays().contains("A")) 
				&& this.getMeetingDays().contains(s)) {
				sameDay = true;
			}			
			
		}
		// if the two course have the same meeting time throw ConflictException
		if(sameDay && 
			(possibleConflictingActivity.getStartTime() >= this.startTime && possibleConflictingActivity.getStartTime() <= this.endTime
			|| possibleConflictingActivity.getEndTime() >= this.startTime && possibleConflictingActivity.getEndTime() <= this.endTime)
		  ) {
			throw new ConflictException();
		}
		if (sameDay && 
			(this.startTime >= possibleConflictingActivity.getStartTime() && this.startTime <= possibleConflictingActivity.getEndTime()
			|| this.endTime >= possibleConflictingActivity.getStartTime() && this.endTime <= possibleConflictingActivity.getEndTime())){
			throw new ConflictException();
		}
		
	}
	
	/**
	 *	Generates a hashCode for Activity using endTime, meetingDays, startTime, title fields.
	 * @return hashCode for Course
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + endTime;
		result = prime * result + ((meetingDays == null) ? 0 : meetingDays.hashCode());
		result = prime * result + startTime;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}
	/**
	 * Compares a given object to this object for equality on endTime, meetingDays, startTime, title fields.
	 * @param obj the Object to compare
	 * @return true if the objects are the same on endTime, meetingDays, startTime, title fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Activity other = (Activity) obj;
		if (endTime != other.endTime)
			return false;
		if (meetingDays == null) {
			if (other.meetingDays != null)
				return false;
		} else if (!meetingDays.equals(other.meetingDays))
			return false;
		if (startTime != other.startTime)
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
	
	
	
}