/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

/**
 * Check the conflict for activity
 * @author Xufeng Ce
 */


public interface Conflict {
	/**
	 * Create a custom conflict exception
	 * @param possibleConflictingActivity is a conflicting activity
	 * @throws ConflictException if there are any conflicts
	 */
	void checkConflict(Activity possibleConflictingActivity) throws ConflictException;

}
