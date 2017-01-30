package rovers;

import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertEquals;


/**
 * Unit test for simple App.
 */
public class PointTest {

	/**
	 * Test pair creation
	 */
	@Test 
		public void createPoint()
		{
			int x = 0;
			int y=1;
			Point pair = new Point(x,y);
			assertTrue((pair.getX() == x) && (pair.getY() == y) );
		}


	/*
	 *  Test adding two pairs
	 */

	@Test 
		public void addPoints()
		{

			Point x = new Point(1,0);
			Point y = new Point(0,1);
			Point z =  x.add(y);

			assertTrue((z.getX() == 1) && (z.getY() == 1));
		} 


	@Test 
		public void equalItself(){
			Point x = new Point(0,0);
			assertTrue("It is not equal to itself",x.equals(x));
		}

	@Test 
		public void equalsToOther(){
			Point x = new Point(1,1);
			Point y = new Point(1,1);
			assertTrue("equal fails ",x.equals(y));

		}

	@Test 
		public void notEquals(){

			Point x = new Point(1,1);
			Point y = new Point(1,2);
			assertFalse("Fail to compare different points",x.equals(y));
		}


	@Test 
		public void hashEquals(){
			Point x = new Point(1,2);
			Point y = new Point(1,2);

			assertTrue("Hash is not the same",x.hashCode() == y.hashCode());
		}


	/*
	 * test scaling up a pair (factor >= 1)
	 */
	@Test 
		public void scaleUp()
		{
			Point x = new Point(1,0);
			Point y = x.scale(2.0);
			assertTrue("Scaling up by two",(y.getX() == x.getX()*2) && (y.getY() == x.getY()*2));
		}

	/*
	 *  test scaling by a fractional value  
	 */
	@Test 
		public void scaleDownInteger()
		{
			Point x = new Point(2,2);
			Point y = x.scale(0.5);

			assertTrue((y.getX() == 1) && (y.getY() == 1));
		}

	/*
	 *  test scaling  by a fraction that doesn¡t give an integer value
	 */
	@Test 
		public void scaleFraction(){
			Point x = new Point(3,4);

		}

	/*
	 * Test scaling by one return the same pair
	 */
	@Test 
		public void scaleByOne(){

			Point x = new Point(0,1);
			Point y = x.scale(1.0);
			assertTrue(x.equals(y));
		}


}
