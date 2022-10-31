/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.scheduler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import edu.ncsu.csc216.wolf_scheduler.course.Activity;
import edu.ncsu.csc216.wolf_scheduler.course.ConflictException;
import edu.ncsu.csc216.wolf_scheduler.course.Course;
import edu.ncsu.csc216.wolf_scheduler.course.Event;
import edu.ncsu.csc216.wolf_scheduler.io.ActivityRecordIO;
import edu.ncsu.csc216.wolf_scheduler.io.CourseRecordIO;

/**
 * This program is used for student to schedule their classes and events
 * @author Xufeng Ce
 *
 */

public class WolfScheduler {

	/** Course in catalog */
	private ArrayList<Activity> catalog;
	/** Course in student's schedule */
	private ArrayList<Activity> schedule;	
	/** Course's title */
	private String title = "";
	
	/**
	 * Load courses from file and then add course to catalog 
	 * file contain list of courses
	 * 
	 * @param filename the file to be read
	 * @throws IllegalArgumentException if unable to read the file
	 */
	public WolfScheduler(String filename) {
		// create arrayList for schedule and catalog, create title variable
		schedule = new ArrayList<Activity>();
		catalog = new ArrayList<Activity>();
		title = "My Schedule";
		try {
			catalog.addAll(CourseRecordIO.readCourseRecords(filename));
		} catch(FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to read file" + filename);
		}
		
	}
	/**
	 * add Course event to schedule
	 * @param eventTitle event Title in schedule 
	 * @param eventMeetingDays event meeting days in schedule 
	 * @param eventStartTime event start time in schedule 
	 * @param eventEndTime event end time in schedule 
	 * @param eventDetails event details in schedule
	 * @throws IllegalArgumentException if event is duplicate and meeting time is conflict with other activity
	 */
	public void addEventToSchedule(String eventTitle, String eventMeetingDays, int eventStartTime, int eventEndTime, String eventDetails) {
		Event event = new Event(eventTitle, eventMeetingDays, eventStartTime, eventEndTime, eventDetails);
		
		
		for (int i = 0; i < schedule.size(); i++) {

			if (event.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You have already created an event called " + eventTitle);
			} else {
				try {
					event.checkConflict(schedule.get(i));
				} catch(ConflictException e) {
					throw new IllegalArgumentException("The event cannot be added due to a conflict.");
				}
			}
		}
		
		schedule.add(event);
		
	}
	/**
	 * get the course info from the catalog
	 * @return String[][] return a 2D String array that contain 
	 * class's name, section and title
	 */
	public String[][] getCourseCatalog() {
		// create a 2D String to store the course info and return it
		String [][] catalogArray = new String[catalog.size()][4];
        for (int i = 0; i < catalog.size(); i++) {
            Course c = (Course) catalog.get(i);
            catalogArray[i] = c.getShortDisplayArray();
        }
        return catalogArray;
	}

	/**
	 * get the activity info from the student's schedule
	 * 
	 * @return return a 2D String array with short display
	 */
	public String[][] getScheduledActivities() {
		// create a 2D String to store the course info and return it
		String [][] catalogArray = new String[schedule.size()][4];
        for (int i = 0; i < schedule.size(); i++) {
            Activity c = schedule.get(i);
            catalogArray[i] = c.getShortDisplayArray();
        }
        return catalogArray;
	}

	/**
	 * get the complete course info from the student's schedule
	 * 
	 * @return return a 2D String array with full display
	 */
	public String[][] getFullScheduledActivities() {
		// create a 2D String to store the course info and return it
		String [][] catalogArray = new String[schedule.size()][7];
        for (int i = 0; i < schedule.size(); i++) {
            Activity c = schedule.get(i);

            catalogArray[i] = c.getLongDisplayArray();
        }
        return catalogArray;
	}

	/**
	 *  create a file for student's schedule
	 * @param filename file to be exported
	 * @throws IllegalArgumentException if IO have issues
	 */
	public void exportSchedule(String filename) {
		try {
			ActivityRecordIO.writeActivityRecords(filename, schedule);
		} catch (IOException e) {
			throw new IllegalArgumentException("The file cannot be saved...");
		}
		
	}

	/**
	 * get schedule's title
	 * @return String String of schedule title
	 */
	public String getScheduleTitle() {

		return title;
	}

	/**
	 * return the course from the catalog with this name and section
	 * @param name name of the course for searching
	 * @param section section of the course for searching
	 * @return Course catalog object
	 */
	public Course getCourseFromCatalog(String name, String section) {
		// loop through catalog arrayList return the course
		// it once find this name and section 
		for (int i = 0; i < catalog.size(); i++) {
			Course c = (Course) catalog.get(i);
			if(c.getName().equals(name) && c.getSection().equals(section)) {
				return c;
			}
		}
		
		return null;
	}

	/**
	 * search and add the course that have this name and section to schedule
	 * 
	 * @param name name of the course for searching
	 * @param section section of the course for searching
	 * @return true if the course exist in the catalog and has not been added in the schedule
	 * @throws IllegalArgumentException if course is duplicate and meeting time is conflict with other activity
	 */
	public boolean addCourseToSchedule(String name, String section) {
		// returns true for this course if
		// 1) the course exists in the catalog and 
		// 2) the course is successfully added to the student’s schedule.
		
		// check if the course exist in the catalog
		
		int idx = 0;
		boolean courseExsit = false;
		for(int i = 0; i < catalog.size(); i++) {
			Course c = (Course) catalog.get(i);
			if(c.getName().equals(name) && c.getSection().equals(section)) {
				 courseExsit = true;
				 idx = i;
			}
			
		}
		if(!courseExsit) {
			return false;
		}
		
		// check if the course has already been added into schedule
		for (int i = 0; i < schedule.size(); i++) {
			// create an object c and assign the course information found at idx 
			Course c = (Course) catalog.get(idx);
			// check if there exist an course in the schedule
			if (c.isDuplicate(schedule.get(i))) {
				throw new IllegalArgumentException("You are already enrolled in " + name);
			} else {
				try {
					c.checkConflict(schedule.get(i));
				} catch (ConflictException e) {
					throw new IllegalArgumentException("The course cannot be added due to a conflict.");
				}
			}
		}
		
		// add the course to schedule if both of condition above met
		schedule.add(schedule.size(), catalog.get(idx));
		// return true if the course can be added
		return true;
	}

	/**
	 * remove activity from the schedule
	 * @param idx index of the activity to be removed
	 * @return true if the course is in the schedule and removed successfully
	 */
	public boolean removeActivityFromSchedule(int idx) {
		try {
			schedule.remove(idx);
		} catch(IndexOutOfBoundsException e) {
			return false;
			
		}
		
		return true;
	}

	/**
	 * reset the schedule
	 */
	public void resetSchedule() {
		schedule.clear();
	}

	/**
	 * set the schedule title
	 * @param str string of title is going to be seted
	 * @throws IllegalArgumentException if trying to set the schedule to null
	 */
	public void setScheduleTitle(String str){

		if(str == null) {
			throw new IllegalArgumentException("Title cannot be null.");
		}
		title = str;
	}

}
