/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * A custom exception class that extends Exception,
 * A conflict exists if there is a time conflict with
 * course and event, or the event meeting time end earlier
 * than start
 * @author Xufeng Ce
 *
 */
public class ConflictException extends Exception {

	/** ID used for serialization. */
	private static final long serialVersionUID = 1L;
	
	
	/**
	 * ConflictExcetion constructor
	 * @param message conflict message 
	 */
	 public ConflictException(String message) {
		 super(message);
	 }
	 /**
	  * ConflictExcetion constructor with no value passed in
	  */
	 public ConflictException() {
		 this("Schedule conflict.");
	 }
}
