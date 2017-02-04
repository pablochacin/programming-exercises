package trains;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class Station{

  String name;
  Map<String,Connection> connections;

  public Station(String name){
    this.name = name;
    this.connections = new HashMap<String,Connection>();
  }


  /**
   * Returns the name of the Station
   */
  public String getName(){
    return name;
  }
/*
 * Adds a connection stating at this station. 
 * @throws IllegalArgumentException if a connection to the destination already exists or the destination is the station
 */
 public void  addConnection(String destination,int distance) throws IllegalArgumentException{

   if(destination.equals(this.name)){
      throw new IllegalArgumentException("A station cannot be connected to itslf");
   }
   if (connections.containsKey(destination)){
     throw new IllegalArgumentException("A connection to " + destination + " already exists from " + this.name);
   }

   Connection connection = new Connection(this.name,destination,distance);

   connections.put(destination,connection);  
 }

 /*
  * Checks if a connection exists to the given destination
  *
  */
 public boolean connectsTo(String destination){
   return connections.containsKey(destination);
 }

 /*
  * Returns te connection bewteen this Station and another stations, if exists
  * @returns a Connection representing the connection
  * throws NoSuchElementException if there is no such connection exitd
  */
 public Connection connectionTo(String destination){

  if(!connections.containsKey(destination)){
    throw new NoSuchElementException("No connection exists to " + destination);
  }

  return connections.get(destination);
 }


 /**
  * @returns all connections from this station
  */
 public List<Connection> getConnections(){
   return new ArrayList(connections.values());
 }

}

