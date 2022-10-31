/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Event object to be used in the WolfScheduler Contains a 
 * Course's title, meetingDays, startTime, endTime, and eventDetails
 * information in this project
 * @author Xufeng Ce
 *
 */
public class Event extends Activity {

	/** Course event details*/
	private String eventDetails;
	
	/**
	 * 
	 * Constructs a Event object with values for the following fields.
	 * @param title Event title
	 * @param meetingDays Event meeting days
	 * @param startTime Event start time
	 * @param endTime Event end time
	 * @param eventDetails Event details
	 */
	public Event(String title, String meetingDays, int startTime, int endTime, String eventDetails) {
		super(title, meetingDays, startTime, endTime);
		setEventDetails(eventDetails);

	}
	
	/** 
	 * Set up the meeting days and time for a event
	 * this override method only handle with event meeting days and time
	 * @param meetingDays the meeting day to set
	 * @param startTime the start time to set
	 * @param endTime the end time to set
	 */
	@Override
	public void setMeetingDaysAndTime(String meetingDays, int startTime, int endTime) {
		// check whether the meeting days is null or empty
		if(meetingDays == null || meetingDays.length() == 0) {
			throw new IllegalArgumentException("Invalid meeting days and times.");
		}
		
		//check if meeting days is arranged
		if(meetingDays.charAt(0) == 'A') {

			throw new IllegalArgumentException("Invalid meeting days and times.");

		// if the meeting days is not arranged, call setMeetingDaysAndTime in Activity and check what is the meeting date
		} else {
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
		
		
		
	}
	/**
	 * Event String format: title,meetingDays,startTime,endTime,details
	 * return String string of event information
	 */
	
	@Override //this override method only handle with course event information
	public String toString() {
		String s = getTitle() + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime() + "," + getEventDetails();
		return s;
	}



	/**
	 * Returns the Course's eventDetails
	 * @return the eventDetails
	 */
	public String getEventDetails() {
		return eventDetails;
	}



	/**
	 * Sets the Course's eventDetails
	 * @param eventDetails the eventDetails to set
	 */
	public void setEventDetails(String eventDetails) {
		if (eventDetails == null) {
			throw new IllegalArgumentException("Invalid event details.");
		}
		this.eventDetails = eventDetails;
	}


	/**
	 * return an array of length 4 containing the 
	 * Course name, section, title, and meeting string 
	 * @return String[]
	 */
	@Override // this override method handle only Event's information
	public String[] getShortDisplayArray() {
		String [] s = new String[4];
		s[0] = "";
		s[1] = "";
		s[2] = getTitle();
		s[3] = getMeetingString();
		return s;
	}
	/**
	 * return an array of length 7 containing the 
	 * Course name, section, title, credits, instructorId, meeting and empty string
	 * @return String[]
	 */
	@Override // this override method handle only Event's information
	public String[] getLongDisplayArray() {
		String [] s = new String[7];
		s[0] = "";
		s[1] = "";
		s[2] = getTitle();
		s[3] = "";
		s[4] = "";
		s[5] = getMeetingString();
		s[6] = getEventDetails();
		return s;

	}
	
	/**
	 * check if the object is belong to Event class
	 * if yes check if there are duplicate course exist
	 * @param activity activity to check duplicate
	 * @return true if the course has already exist 
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Event) {
			Event other = (Event) activity;
			return this.getTitle().equals(other.getTitle());
		}
		return false;
		
	}
}
