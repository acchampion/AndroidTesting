package edu.osu.androidtesting;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

	@Test
	public void isFloatingPointNum() throws Exception {
		double myDouble = 10.0;
		assertTrue(Double.isFinite(myDouble) && !Double.isNaN(myDouble));
	}

	@Test
	public void isInvalidFloatNum() throws Exception {
    	double badDouble = Double.NaN;
    	assert(Double.isNaN(badDouble) && !Double.isFinite(badDouble));
	}

	@Test
	public void isZipCodeValid() throws Exception {
		String zip = "43210";
		assert((zip.length() == 5 || zip.length() == 9) && zip.matches("[0-9]+"));
	}

	@Test
	public void isBadZipCodeValid() throws Exception {
		String zip = "1123443210ABCD";
		assert(!(zip.length() == 5 || zip.length() == 9) || !zip.matches("[0-9]+"));
	}
}
