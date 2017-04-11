package trains;

import java.util.Map;
import java.util.HashMap;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.stream.Stream;

/*
 * Offers information about routes
 *
 */
public class Network{

  public static enum SearchStrategy {FIRST, ALL};


  Map<String,Station> stations;

  public Network(){
    this.stations = new HashMap<String,Station>();
  }

  /*
   * Adds a Station to the network.
   * @throws IllegalArgumentException if the station already exists
   */
  public void addStation(String name){

    if(stations.containsKey(name)){
      throw new IllegalArgumentException("Station already exists: " + name);
    }

    Station station  = new Station(name);
    stations.put(name,station);
  }

 /**
  * @retun a List of  Strings with the names of the stations
  */
  public List<String> getStations(){
      return new ArrayList<String>(stations.keySet());
  }

  
  /*
   * Adds a link between two network with a given distance
   * @throws IllegalArgumentException if either  of the network doesn't exists
   */
  public void addConnection(String origin,String destination, int distance){

    if(!stations.containsKey(origin) || !stations.containsKey(destination)){
      throw new IllegalArgumentException("origin and destination must exists in a connection: " + origin + " --> " + destination);
    }

    Station originStation = stations.get(origin);
    originStation.addConnection(destination, distance);

  }


  /**
   * Returns the Connection between the two given stations, if exists.
   * @param origin String with the name of the origin station
   * @param destination String with the name of the destination station
   * @return the Connection that connects this two estations of null is none exists
   * @throws IllegalArgumentException if any of the two stations doesn't exists
   * @throws NoSuchElementException is there is no such connection 
   */
  public Connection getConnection(String origin, String destination){

    if(!stations.containsKey(origin) || !stations.containsKey(destination)){
      throw new IllegalArgumentException("origin and destination must exists in a connection: " + origin + " --> " + destination);
    }

    Station originStation = stations.get(origin);
    if(!originStation.connectsTo(destination)){
      throw new NoSuchElementException("There is no connection between "+origin + " and " + destination);
    }

    return originStation.connectionTo(destination);
  }



  /*
   * Builds a route (list of Connections) from a list of staton names
   * 
   * @param network a Network of Stations with their connecting Connections
   * @param stations list of station names
   * @returns a List of Connections representing the route
   * @throws IlegalArgumentException if 	the route is not valid:
   *    - has at least two different stations 
   *    - one station doesn't exists 
   *    - there is no connection between consecutive stations
   */
  public Route buildRoute(List<String> stationList){

    if(stationList.size() < 2)
      throw new IllegalArgumentException("Route must have at least one connection");

    //TODO: check that the route has at least two different stations

    Route route = new Route();

    String origin = stationList.get(0);
    for(String destination: stationList.subList(1,stationList.size())){
      Connection l = getConnection(origin,destination);
      route.addConnection(l);
      origin=destination;
    } 

    return route;
  }


  /* 
   * Gets routes between the given origin and destination that match the validation
   * criteria.
   * The algorithmm uses a modified iterative deep first search, that keeps track of 
   * visited nodes to generate the routes. For doing so, nodes of the pending seach 
   * stack are not removed inmediatelly but in a backtracking step. 
   * The process can follow one of two strategies: find the first one or find all.
   * @param origin: name of the origin station
   * @param destination: name of the destinatin station
   * @returns a boolean indicating if at least one of such routes exits.
   * @throws IllegalArgumentException if either origin or destination doesn't exist
   */ 
  public Stream<Route>  getRoutes(String origin,String destination){

    if(!stations.containsKey(origin) || !stations.containsKey(destination)){
      throw new IllegalArgumentException("origin and destination must exists in a route: " + origin + " --> " + destination);
    }


    Stream.Builder<Route> builder = Stream.builder();

    Stack<Connection> route = new Stack<Connection>();

    Stack<Connection> pending = new Stack<Connection>();

    Station station  = stations.get(origin);

    for(Connection c: station.getConnections()){
      pending.push(c);
    }

    while(!pending.isEmpty()){

      //get next pending station, but keep it in the pending stack for future backtracking
      Connection connection = pending.peek();

      //add to explored route
      route.push(connection);
      if (connection.getDestination().equals(destination)){
	  builder.add(Route.asRoute(route));
      }

      station = stations.get(connection.getDestination());

      for(Connection c: station.getConnections()){
	//prevent loops
	if(!pending.contains(c))
	  pending.push(c);
      }

      //backtrack to route and pending to previous unexplored branch
      while(!route.isEmpty() && !pending.isEmpty() && route.peek().equals(pending.peek())){
	route.pop();
	pending.pop();
      }
    }

    return builder.build();

  }


}

