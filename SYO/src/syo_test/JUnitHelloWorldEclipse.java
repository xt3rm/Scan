package syo_test;

import org.junit.Test;
import static org.junit.Assert.*;
import junit.framework.JUnit4TestAdapter;



	public class JUnitHelloWorldEclipse {
		@Test
		public void testHelloWorld() {
			String s = "HelloWorld";
			assertEquals("Just a test to see if everything works...", "HelloWorld", s);
		}

		public static junit.framework.Test suite() {
			return new JUnit4TestAdapter(JUnitHelloWorldEclipse.class);
		}
	}

