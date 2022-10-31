/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;


import java.util.Scanner;

import edu.ncsu.csc216.wolf_scheduler.course.Course;

/**
 * Reads Course records from text files.  Writes a set of CourseRecords to a file.
 * @author Xufeng Ce
 *
 */

public class CourseRecordIO {

	/**
     * Reads course records from a file and generates a list of valid Courses.  Any invalid
     * Courses are ignored.  If the file to read cannot be found or the permissions are incorrect
     * a File NotFoundException is thrown.
     * @param fileName file to read Course records from
     * @return a list of valid Courses
     * @throws FileNotFoundException if the file cannot be found or read
     */
	public static ArrayList<Course> readCourseRecords(String fileName) throws FileNotFoundException {
		Scanner fileReader = new Scanner(new FileInputStream(fileName));  //Create a file scanner to read the file
	    ArrayList<Course> courses = new ArrayList<Course>(); //Create an empty array of Course objects
	    while (fileReader.hasNextLine()) { //While we have more lines in the file
	        try { //Attempt to do the following
	            //Read the line, process it in readCourse, and get the object
	            //If trying to construct a Course in readCourse() results in an exception, flow of control will transfer to the catch block, below
	            Course course = readCourse(fileReader.nextLine()); 

	            //Create a flag to see if the newly created Course is a duplicate of something already in the list  
	            boolean duplicate = false;
	            //Look at all the courses in our list
	            for (int i = 0; i < courses.size(); i++) {
	                //Get the course at index i
	                Course current = courses.get(i);
	                //Check if the name and section are the same
	                if (course.getName().equals(current.getName()) &&
	                        course.getSection().equals(current.getSection())) {
	                    //It's a duplicate!
	                    duplicate = true;
	                    break; //We can break out of the loop, no need to continue searching
	                }
	            }
	            //If the course is NOT a duplicate
	            if (!duplicate) {
	                courses.add(course); //Add to the ArrayList!
	            } //Otherwise ignore
	        } catch (IllegalArgumentException e) {
	            //The line is invalid b/c we couldn't create a course, skip it!
	        }
	    }
	    //Close the Scanner b/c we're responsible with our file handles
	    fileReader.close();
	    //Return the ArrayList with all the courses we read!
	    return courses;
	}

	/**
	 * Read course record from a file, check out the course format, if format wrong throw IAE
     * @throws IllegalArgumentException if the course information is wrong or format is wrong in read file
	 * @param nextLine read the course info record in the file
	 * @return Course Course object
	 */
	private static Course readCourse(String nextLine) {
		//construct Scanner to process the nextLine parameter
		Scanner scan = new Scanner(nextLine);
		scan.useDelimiter(",");
			
			try {
			// read in tokens for name, title, section, credits, instructorId, meetingDays, startTime, and endTime
			// and store in local variables
				String name = scan.next();
				String title = scan.next();
				String section = scan.next();
				int credits = scan.nextInt();
				String instructorId = scan.next();
				String meetingDays = scan.next();
				int startTime = 0;
				int endTime = 0;
				
				// check if meeting days is arranged
				if(meetingDays.charAt(0) == 'A') {
					if(scan.hasNext()) {
						scan.close();
						throw new IllegalArgumentException("Invalid meeting days and times.");
					} else {
						scan.close();
					return new Course(name, title, section, credits, instructorId, meetingDays);
					}
				} else {
					startTime = scan.nextInt();
					endTime = scan.nextInt();
					
					if(scan.hasNext()) {
						scan.close();
						throw new IllegalArgumentException("Invalid course info");
					}
					scan.close();
					return new Course(name, title, section, credits, instructorId, meetingDays, startTime, endTime);
				}
			} catch (Exception e) {
				// catch any Exception and throw new IAE
				throw new IllegalArgumentException("Invalid course info");
			}
			
	}

}
