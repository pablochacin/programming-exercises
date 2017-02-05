package trains;

import java.util.List;
import java.util.ArrayList;

/*
 * Represents a route (a series of stations)
 * TODO: make this class an inner class of Network, and protect constructor, to ensure it is
 *  created only from within Network methods. This will prevent for instance empty routes. 
 */
public class Route{

  List<Connection> connections;
  public Route(){
    this.connections = new ArrayList();
  }

  /*
   * creates a Route initialized with a list of connections
   */
  private Route(List<Connection>connections){
    this();
    this.connections.addAll(connections);
  }


  /*
   * Convenienve method. Creates a Route from a list of connections
   */
  public static Route asRoute(List<Connection>connections){
    return new Route(connections);
  }

  /**
   * Adds a connection to the route
   * @throws IllegalArgumentException if the connection already exists in the route
   */
  public void addConnection(Connection connection){

    if(connections.contains(connection)){
      throw new IllegalArgumentException("Connection between " + connection.getOrigin() + " and " + connection.getDestination() + " already exists in the route");
    }

    connections.add(connection);
  }

  /*
   * Builds a list of stations names in a route
   * @returns a List with the names of the stations in the route
   */
  public List<String> getItinerary(){
    List<String>itinerary = new ArrayList<String>();

    itinerary.add(connections.get(0).getOrigin());
    for(Connection c: connections){
      itinerary.add(c.destination);
    }

    return itinerary;
  }


  /*
   * gets the distance of the route
   * @returns the sum of the distance of the connections
   * @throws IllegalStateException if the route is empty (has no connections)
   */
  public int getDistance(){

    if(connections.size() == 0){
      throw new IllegalStateException("Route is empty");
    }

    int distance = 0;

    for(Connection c: connections){
      distance+=c.getDistance();
    }

    return distance;
  } 


  /**
   * gets the origin station of the route
   *
   * @returns a String with the name of the origin station
   * @throws IllegalStateException if the route is empty
   */
  public String getOrigin(){
    if(connections.isEmpty()){
      throw new IllegalStateException("Route is empty");
    }

    return connections.get(0).getOrigin();
  }

  /**
   * gets the destination station of the route
   *
   * @returns a String with the name of the destinatin station
   * @throws IllegalStateException if the route is empty
   */
  public String getDestination(){
    if(connections.isEmpty()){
      throw new IllegalStateException("Route is empty");
    }

    return connections.get(connections.size()-1).getDestination();
  }


  /* 
   * returns the number of connections in the route
   */
  public int numConnections(){

    //TODO: if route is empty, throw an exception
    return connections.size();
  }


  /**
   * Compares if the route is equal to another Route. Two routes are equal  if
   * they have the same list of Connections. 
   * Assumes the Connection class properly implements the equals method
   * @param obj an Object that points to the other Route
   * @returns true if both routes are equals
   */
  @Override 
  public boolean equals(Object other){
 
   return connections.equals(((Route)other).connections); 
  }


  /**
   * Returns the hash code for the Route. 
   * Relies on the implementation of hashCode from the List of Connections
   */
  @Override
  public int hashCode(){
     return connections.hashCode();
  }
}
