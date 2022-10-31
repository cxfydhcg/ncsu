/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * 
 * Course object to be used in the WolfScheduler Contains a 
 * Course's name, title, section, credit hours, instructorId, and meeting time
 * information in this project
 * 
 * @author Xufeng Ce
 *
 */
public class Course extends Activity {
	/** Course's name. */
	private String name;
	/** Course's section. */
	private String section;
	/** Course's credit hours */
	private int credits;
	/** Course's instructor */
	private String instructorId;
	/**
	 *  Constant values for methods in Course class
	 * 
	 * Minimum Course's name length
	 * Maximum Course's name length
	 * Minimum Course's name letter length
	 * Maximum Course's name letter length
	 * Course's name digit length
	 * Course's section length
	 * Minimum Course's credits
	 * Maximum Course's credits
	 * Upper Course's meeting time on hour
	 * Upper Course's meeting time on minutes
	 * */
	private static final int MIN_NAME_LENGTH = 5,
							 MAX_NAME_LENGTH = 8,
							 MIN_LETTER_COUNT = 1,
							 MAX_LETTER_COUNT = 4,
							 DIGIT_COUNT = 3,
							 SCTION_LENGTH = 3,
							 MIN_CREDITS = 1,
							 MAX_CREDITS = 5;
	
	/**
	 * Returns the Course's name
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * Sets the Course's name.  If the name is null, has a length less than 5 or more than 8,
	 * does not contain a space between letter characters and number characters, has less than 1
	 * or more than 4 letter characters, and not exactly three trailing digit characters, an
	 * IllegalArgumentException is thrown.
	 * @param name the name to set
	 * @throws IllegalArgumentException if the name parameter is invalid
	 */
	private void setName(String name) {
		
		
		//Throw exception if the name is null
		//Throw exception if the name is an empty string
	    //Throw exception if the name contains less than 5 character or greater than 8 characters
		if(name == null || name.length() < MIN_NAME_LENGTH || name.length() > MAX_NAME_LENGTH || name.length() == 0) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		//Check for pattern of L[LLL] NNN
//		private boolean spaceFlag = false;
		int countLetter = 0, countDig = 0;
		boolean spaceFlag = false;
		
		for(int numLetters = 0; numLetters < name.length(); numLetters++) {
			if(!spaceFlag) {
				if(Character.isLetter(name.charAt(numLetters))){
					countLetter++;
				} else if(name.charAt(numLetters) == ' ') {
					spaceFlag = true;
				} else {
					throw new IllegalArgumentException("Invalid course name.");
				}
			} else if(spaceFlag) {

				if(Character.isDigit(name.charAt(numLetters))) {
				countDig++;
				
				} else {
				throw new IllegalArgumentException("Invalid course name.");
				}
			} else {
				throw new IllegalArgumentException("Invalid course name.");
			}
		}
		
		//Check that the number of letters is correct
		if(countLetter < MIN_LETTER_COUNT || countLetter > MAX_LETTER_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		//Check that the number of digits is correct
		if(countDig != DIGIT_COUNT) {
			throw new IllegalArgumentException("Invalid course name.");
		}
		
		this.name = name;
	}
	/**
	 * Returns the Course's section
	 * @return the section
	 */
	public String getSection() {
		return section;
	}
	/**
	 * Sets the Course's section
	 * @param section the section to set
	 */
	public void setSection(String section) {
		
		//check whether if section is null or does not have 3 char
		if(section == null || section.length() != SCTION_LENGTH) {
			throw new IllegalArgumentException("Invalid section.");
		}
		// loop throw section String
		for(int i = 0; i < section.length(); i++) {
			// if char at i is not digit, throw exception	
			if (!Character.isDigit(section.charAt(i))) {
				throw new IllegalArgumentException("Invalid section.");
			}
		}
		this.section = section;
	}
	/**
	 * Returns the Course's credits
	 * @return the credits
	 */
	public int getCredits() {
		return credits;
	}
	/**
	 * Sets the Course's credits
	 * @param credits the credits to set
	 */
	public void setCredits(int credits) {
		if(credits < MIN_CREDITS || credits > MAX_CREDITS) {
			throw new IllegalArgumentException("Invalid credits.");
		}
		this.credits = credits;
	}
	/**
	 * Returns the Course's instructorId
	 * @return the instructorId
	 */
	public String getInstructorId() {
		return instructorId;
	}
	/**
	 * Sets the Course's instructorId
	 * @param instructorId the instructorId to set
	 */
	public void setInstructorId(String instructorId) {
		if(instructorId == null || instructorId.length() == 0) {
			throw new IllegalArgumentException("Invalid instructor id.");
		}
		
		this.instructorId = instructorId;
	}

	
	/**
	 * Returns a comma separated value String of all Course fields.
	 * @return String representation of Course
	 */
	@Override // This override method only handles Course string
	public String toString() {
	    if ("A".equals(getMeetingDays())) {
	        return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays();
	    }
	    return name + "," + getTitle() + "," + section + "," + credits + "," + instructorId + "," + getMeetingDays() + "," + getStartTime() + "," + getEndTime(); 
	}
	
	
	
	/**
	 *	Generates a hashCode for Course using credits, instructorId, name, section fields.
	 * @return hashCode for Course
	 */
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + credits;
		result = prime * result + ((instructorId == null) ? 0 : instructorId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((section == null) ? 0 : section.hashCode());
		return result;
	}
	/**
	 * Compares a given object to this object for equality on credits, instructorId, name, section fields.
	 * @param obj the Object to compare
	 * @return true if the objects are the same on credits, instructorId, name, section fields.
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Course other = (Course) obj;
		if (credits != other.credits)
			return false;
		if (instructorId == null) {
			if (other.instructorId != null)
				return false;
		} else if (!instructorId.equals(other.instructorId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (section == null) {
			if (other.section != null)
				return false;
		} else if (!section.equals(other.section))
			return false;
		return true;
	}
	
	/**
	 * Constructs a Course object with values for all fields.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 * @param startTime start time for Course
	 * @param endTime end time for Course
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays,
	        int startTime, int endTime) {
	    super(title, meetingDays, startTime, endTime);
		setName(name);
//	    setTitle(title);
	    setSection(section);
	    setCredits(credits);
	    setInstructorId(instructorId);
//	    setMeetingDaysAndTime(meetingDays, startTime, endTime);

	}
	
	
	
	/**
	 * Creates a Course with the given name, title, section, credits, instructorId, and meetingDays for 
	 * courses that are arranged.
	 * @param name name of Course
	 * @param title title of Course
	 * @param section section of Course
	 * @param credits credit hours for Course
	 * @param instructorId instructor's unity id
	 * @param meetingDays meeting days for Course as series of chars
	 */
	public Course(String name, String title, String section, int credits, String instructorId, String meetingDays) {
		
	    this(name, title, section, credits, instructorId, meetingDays, 0, 0);
	}
	
	/** Set up the meeting days and time for a course
	 * @param meetingDays the meeting day to set
	 * @param startTime the start time to set
	 * @param endTime the end time to set
	 */
	@Override //this override method only handle with Course's meeting days and time
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

			super.setMeetingDaysAndTime(meetingDays, 0, 0);
		// if the meeting days is not arranged, check what is the meeting date
		} else {
			
			// if the meeting date is not M, T, W, H, F, throw an exception
			for(int i = 0; i < meetingDays.length(); i++) {
				if(!(meetingDays.charAt(i) == 'M' || 
				   meetingDays.charAt(i) == 'T' ||
				   meetingDays.charAt(i) == 'W' ||
				   meetingDays.charAt(i) == 'H' ||
				   meetingDays.charAt(i) == 'F')) {
					throw new IllegalArgumentException("Invalid meeting days and times.");
				}
			}
			super.setMeetingDaysAndTime(meetingDays, startTime, endTime);
		}
		
		
		
	}
	
	/**
	 * return an array of length 4 containing the 
	 * Course name, section, title, and meeting string 
	 * @return String[]
	 */
	@Override // this override method handle only Course's information
	public String[] getShortDisplayArray() {
		String [] s = new String[4];
		s[0] = getName();
		s[1] = getSection();
		s[2] = getTitle();
		s[3] = getMeetingString();
		return s;
	}
	/**
	 * return an array of length 7 containing the 
	 * Course name, section, title, credits, instructorId, meeting and empty string
	 * @return String[]
	 */
	@Override // this override method handle only Course's information
	public String[] getLongDisplayArray() {
		String [] s = new String[7];
		s[0] = getName();
		s[1] = getSection();
		s[2] = getTitle();
		s[3] = getCredits() + "";
		s[4] = getInstructorId();
		s[5] = getMeetingString();
		s[6] = "";
		return s;
	}
	/**
	 * check if the object is belong to Course class
	 * if yes check if there are duplicate course exist
	 * @param activity activity of the course object
	 * @return true if the course has already exist 
	 */
	public boolean isDuplicate(Activity activity) {
		if (activity instanceof Course) {
			Course other = (Course) activity;
			return this.getName().equals(other.getName());
		}
		return false;
		
	}
	
}
