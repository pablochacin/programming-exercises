package trains;

import java.util.Arrays;
import java.util.NoSuchElementException;
import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;


public class RoutePlannerTest {

  private Network loadNetwork(){

    Network network = new Network();

    network.addStation("A");
    network.addStation("B");
    network.addStation("C");
    network.addStation("D");
    network.addStation("E");

    network.addConnection("A","B",5);
    network.addConnection("B","C",4);
    network.addConnection("C","D",8);
    network.addConnection("D","C",8);
    network.addConnection("D","E",6);
    network.addConnection("A","D",5);
    network.addConnection("C","E",2);
    network.addConnection("E","B",3);
    network.addConnection("A","E",7);

    return network;
  }

  @Test
    public void TestDistanceABC(){

      Network network = loadNetwork();
      RoutePlanner planner = new RoutePlanner(network);

      assertEquals(" Distance A-B-C: must be 9",9,planner.getDistance(Arrays.asList("A","B","C")));

    }

  @Test
    public void TestDistanceAD(){

      Network network = loadNetwork();
      RoutePlanner planner = new RoutePlanner(network);


      assertEquals(" Distance A-D must be 5 ",5,planner.getDistance(Arrays.asList("A","D")));
    }


  @Test
    public void TestDistanceADC(){

      Network network = loadNetwork();
      RoutePlanner planner = new RoutePlanner(network);


      assertEquals(" Distance A-D-C must be 13",13,planner.getDistance(Arrays.asList("A","D","C")));
    }


  @Test
    public void TestDistanceAEBCD(){

      Network network = loadNetwork();
      RoutePlanner planner = new RoutePlanner(network);
      assertEquals(" Distance A-E-B-C-D must be  22 ",22,planner.getDistance(Arrays.asList("A","E","B","C","D")));
    }

  @Test(expected= NoSuchElementException.class)
    public void testNoSuchRouteAED(){

      Network network = loadNetwork();
      RoutePlanner planner = new RoutePlanner(network);
      planner.getDistance(Arrays.asList("A","E","D"));
    }


  /*
     Tests the number of trips starting at C and ending at C with a maximum of 3 stops.  
     In the sample data, there are two such trips: 
     C-D-C   (2 stops). 
     C-E-B-C (3 stops).
   */
  @Test
    public void testNumTripsCCLT3Stops(){

      Network network = loadNetwork();
      RoutePlanner planner = new RoutePlanner(network);
      assertEquals(" Num of trips from C to C with at most 3 stops must be 2",2,planner.countBoundedStopsTrips("C","C",3));
    }


  /*The number of trips starting at A and ending at C with exactly 4 stops.  
    In the sample data below, there are three such trips: 
    A,B,C,D,C; 
    A,D,C,D,C;
    A,D,E,B,C
   */
  @Test
    public void testNumTripsAC4Stops(){
      Network network = loadNetwork();
      RoutePlanner planner = new RoutePlanner(network);
      assertEquals(" Num of trips from A to C with exactly 4 stops must be  3",3,planner.countBoundedStopsTrips("A","C",4,4));
    }

  /*
     Testt the length of the shortest route (in terms of distance to travel) from A to C.
   */
  @Test
    public void testShortestAC(){

      Network network = loadNetwork();
      RoutePlanner planner = new RoutePlanner(network);
      assertEquals(" Length of shortest route from A to C must be 9 ",9,planner.getShortestDistance("A","C"));
    }

  /*
     Tests the length of the shortest route (in terms of distance to travel) from B to B.
   */
  @Test
    public void testShortestBB(){  
      Network network = loadNetwork();
      RoutePlanner planner = new RoutePlanner(network);
      assertEquals(" Length of shortest route from B to B must be 9 ",9,planner.getShortestDistance("B","B"));
    }


  /*
     Test the number of different routes from C to C with a distance of less than 30.  
     In the sample data, the trips are 7: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.
   */
  @Test 
    public void testCCLT30(){   
      Network network = loadNetwork();
      RoutePlanner planner = new RoutePlanner(network);
      assertEquals(" Number of routes from C to C with disrtance less than 30 must be 7",7,planner.countBoundedDistanceTrips("C","C",30));
    }

}
