/**
 * 
 */
package edu.ncsu.csc216.wolf_scheduler.course;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * Test for Activity class
 * @author Xufeng Ce
 *
 */
class ActivityTest {
	/**
	 *  check no conflict activities
	 */
	@Test
	public void testCheckConflict() {
		
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "TH", 1330, 1445);
		Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "A");
		Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "002", 3, "sesmith5", "A");

		assertDoesNotThrow(() -> a1.checkConflict(a2));
		assertDoesNotThrow(() -> a2.checkConflict(a1));
		
		assertDoesNotThrow(() -> a3.checkConflict(a4));
		assertDoesNotThrow(() -> a4.checkConflict(a3));
	}
	/**
	 * check conflict activities
	 */
	@Test
	public void testCheckConflictWithConflict() {
		
		Activity a1 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1330, 1445);
		Activity a2 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);
		Activity a3 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "MW", 1400, 1430);
		Activity a4 = new Course("CSC 216", "Software Development Fundamentals", "001", 3, "sesmith5", "M", 1330, 1445);

		
		
		Exception e1 = assertThrows(ConflictException.class, () -> a1.checkConflict(a2));
		assertEquals("Schedule conflict.", e1.getMessage());
		
		Exception e2 = assertThrows(ConflictException.class, () -> a2.checkConflict(a1));
		assertEquals("Schedule conflict.", e2.getMessage());
		
		Exception e3 = assertThrows(ConflictException.class, () -> a3.checkConflict(a4));
		assertEquals("Schedule conflict.", e3.getMessage());
		
		Exception e4 = assertThrows(ConflictException.class, () -> a4.checkConflict(a3));
		assertEquals("Schedule conflict.", e4.getMessage());
	}

}
