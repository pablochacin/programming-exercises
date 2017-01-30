package rovers;

import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertEquals;

public class RoverTest {


	@Test
		public void testCreateRoverRegion(){

			Region region = new Region(new Point(0,0),new Point(10,10));
			Point pos = new Point(1,1);
			Orientation o = Orientation.N;

			Rover rover = new Rover(pos,region,o);

			assertTrue("region is not the given region",rover.getFence().equals(region));
			assertTrue("Position is not the given position",rover.getPosition().equals(pos));
			assertTrue("Orientation is not the given orientaton",rover.getOrientation().equals(o));
		}



	@Test(expected=IllegalArgumentException.class)
		public void testPositionNotInRegion(){
			Region region = new Region(new Point(0,0),new Point(1,1));
			Point pos = new Point(2,2);
			Orientation o = Orientation.N;

			Rover rover = new Rover(pos,region,o);
		}

	/*
	 * Test moving from the (0,0) position in each direction 
	 */
	@Test
		public void testMove(){

			Region region = new Region(new Point(-1,-1), new Point(1,1));
			Point position = new Point(0,0);
			for(Orientation o: Orientation.values()){
				Rover r = new Rover(position,region,o);
				r.doCommand(Rover.Command.M);
				assertTrue("Failed move in direction "+o.toString(),r.getPosition().equals(o.getMove())); 
			}
		}


	/*
	 * When on the limits of the fence, rover sholdn't move
	 */
	@Test
		public void testMoveOnBorder(){

			Region region = new Region(new Point(0,0),new Point(1,1));
			Point pos = new Point(1,1);
			Orientation o = Orientation.N; 
			Rover rover = new Rover(pos,region,o); 

			rover.doCommand(Rover.Command.M);

			assertTrue("Rover changes position",rover.getPosition().equals(pos));

		}
}
