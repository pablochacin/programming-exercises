package trains;


import org.junit.Test;
import static junit.framework.Assert.assertTrue;
import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;


/**
 * Unit test for stations
 */
public class StationTest {

  /**
   * Test Station creation
   */
  @Test 
    public void createEmptyStation()
    {
      Station station = new Station("A");
      assertEquals("A",station.getName());
      assertTrue(station.getConnections().isEmpty());
    }


  /*
   *  Test adding a Connection creates a conection to the given destination
   */

  @Test 
    public void addConnection()
    {

      Station station = new Station("A");
      station.addConnection("B",1);
      assertTrue("Station is not connect to destination",station.connectsTo("B"));
      assertNotNull("The connection to the destionation is null",station.connectionTo("B"));
      Connection connection = station.connectionTo("B");
      assertTrue("Connection doesn't match provided parameters",
                 (connection.getDestination().equals("B")) && 
                 (connection.getOrigin().equals(station.getName())) &&
                 (connection.getDistance() == 1));
    } 


   /**
    * Test a connection to the same station is not possible
    */
   @Test(expected = IllegalArgumentException.class)
   public void connectSelf(){
     Station station = new Station("A");
     station.addConnection("A",1);
   }
   

  /**
   * Test adding a duplicated connection 
   */
   @Test(expected= IllegalArgumentException.class)
   public void connectDuplicated(){
     Station station = new Station("A");
     station.addConnection("B",1);
     station.addConnection("B",1); 
   }
}
