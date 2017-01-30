package rovers;

import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertEquals;

import java.util.List;

public class MissionTest {


	@Test
		public void TestDeploy(){

			Region region = new Region(new Point(0,0),new Point(10,10));
			Point pos = new Point(5,5);
			Mission m = new Mission(region);
			m.deployRover(pos,Orientation.N);

			List<Mission.RoverInfo> rovers = m.listRovers();

			assertEquals("List is not of the expected size",1,rovers.size());
			Mission.RoverInfo r = rovers.get(0);
			assertEquals("Id of rover is not he expected",Integer.valueOf(1),r.id);
			assertTrue("Rover fence is not the region",region.equals(r.fence));
			assertTrue("Rover position is not the expected",pos.equals(r.position));
		}


	@Test
		public void TestDecomission(){

			Region region = new Region(new Point(0,0),new Point(10,10));
			Mission m = new Mission(region);
			m.deployRover(new Point(0,0),Orientation.N);
			m.deployRover( new Point(10,10),Orientation.S);

			m.decomissionRover(1);  

			List<Mission.RoverInfo> rovers = m.listRovers();
			assertEquals("List is not of the expected size",1,rovers.size());
			Mission.RoverInfo r = rovers.get(0);
			assertEquals("Id of rover is not he expected",Integer.valueOf(2),r.id);
		}

}
