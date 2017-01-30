package rovers;

import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertEquals;

public class RegionTest {

	@Test(expected=IllegalArgumentException.class)
		public void createRegionZeroSize(){
			Point x = new Point(0,0);

			Region r = new Region(x,x);
		}


	@Test(expected=IllegalArgumentException.class)
		public void testNegativeSize(){
			Point x= new Point(1,1);
			Point y = new Point(0,0);
			Region r = new Region(x,y);

		}

	@Test
		public void testCreateRegion(){

			Point x = new Point(0,0);
			Point y= new Point(1,1);
			Region r = new Region(x,y);

			assertTrue("Corners are not ok",r.getLeftDown().equals(x) && r.getRightUp().equals(y));
		}

	@Test  
		public void testPointInRegion(){
			Point x = new Point(0,0);
			Point y= new Point(2,2);
			Point z = new Point(1,1);

			Region r = new Region(x,y);

			assertTrue(r.isWithin(z));
		}

	@Test
		public void testPointInBorder(){
			Point x = new Point(0,0);
			Point y= new Point(2,2);

			Region r = new Region(x,y);

			assertTrue("Test of border point in region failed",r.isWithin(x));
		}


	@Test
		public void testPointNotInRegion(){

			Point x = new Point(0,0);
			Point y= new Point(2,2);
			Point z = new Point(3,3);

			Region r = new Region(x,y);

			assertFalse("Check of outside point in region failed",r.isWithin(z));
		}

	@Test
		public void testEquals(){
			Point x = new Point(0,0);
			Point y= new Point(2,2);

			Region r1 = new Region(x,y);
			Region r2 = new Region(x,y);

			assertTrue("Regions are not equal",r1.equals(r2) && r2.equals(r1));
			assertTrue("Hash codes are not equal",r1.hashCode()== r2.hashCode());
		}

	@Test 
		public void testNotEqual(){ 
			Point x = new Point(0,0);
			Point y= new Point(2,2);
			Point z = new Point(3,3);

			Region r1 = new Region(x,y);
			Region r2 = new Region(x,z);

			assertFalse(r1.equals(r2) | r2.equals(r1)); 
		}

}

