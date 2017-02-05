package trains;

import java.util.Arrays;
import java.util.NoSuchElementException;
import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

import java.util.List;
import java.util.ArrayList;

public class NetworkTest {

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

  /*
   * Tests that after adding an station to an empty Network
   * the station is returned as part of the Stations collection
   */
  @Test
  public void testAddStation(){

   Network n = new Network();
   n.addStation("A");

   List<String> stations = n.getStations();
  
   assertEquals("Stations List must have one element",1,stations.size());
   assertTrue("Stations list must contain the station A",stations.contains("A"));
  }

  @Test(expected=IllegalArgumentException.class)
  public void testDuplicatedStation(){
    Network n = new Network();
    n.addStation("A");
    n.addStation("A");
  }  


  /*
   * Adds a list of Stations and verifies they are returned correctly from
   * the network
   */
  @Test
  public void testAddMultipleStations(){
    Network n = new Network();
    
    List<String> stations = new ArrayList<String>(Arrays.asList("A","B"));
 
    for(String s: stations){
       n.addStation(s);
    }

    assertEquals("List of stations has wrong number of elements",2,n.getStations().size());
   assertTrue("List of stations does not contain all inserted stations",n.getStations().containsAll(stations));
  }

  /*
   * Test the connetion betwen two stations
   */
   @Test
   public void testConnection(){
     Network n = new Network();
     n.addStation("A");
     n.addStation("B");

     n.addConnection("A","B",1);

     Connection c = n.getConnection("A","B");
     assertTrue("Connection is not correct",c.getOrigin().equals("A") &&
                                            c.getDestination().equals("B") &&
                                            (c.getDistance() == 1));

    //TODO: use modern Junit exception assertions
    boolean thrown = false;
    try{
      n.getConnection("B","A");
    } catch(NoSuchElementException e){
      thrown = true;
    }
    assertTrue("Connections are not bidirectional",thrown);

  }

  /*
   * Test the connetion to the same station 
   */
   @Test(expected= IllegalArgumentException.class)
   public void testConnectionSelf(){
     Network n = new Network();
     n.addStation("A");
     n.addStation("A");

  }


  /*
   * Test duplicated connetions  
   */
   @Test(expected= IllegalArgumentException.class)
   public void testDuplicatedConnection(){
     Network n = new Network();
     n.addStation("A");
     n.addStation("B");
     n.addConnection("A","B",1);
     n.addConnection("A","B",1);

  }


  /*
   * Test non existent stations
   */
   @Test(expected= IllegalArgumentException.class)
   public void testConnectNonExistingStation(){
     Network n = new Network();
     n.addStation("A");
     n.addConnection("A","B",1);
   }

  
}
