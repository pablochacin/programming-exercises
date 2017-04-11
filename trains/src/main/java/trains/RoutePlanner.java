package trains;

import java.util.List;
import java.util.Optional;

/*
 * Offers methods to find routes according to certain criteria
 *
 */
public class RoutePlanner {

    Network network;

    public RoutePlanner(){
        //initialize with an empty network, to avoid
        //null pointer exceptions
        this.network = new Network();
    }

   public RoutePlanner(Network network){
        this.network = network;
    }

    /**
     * sets the network for the planner
     *
     */
    public void loadNetwork(Network network){
        this.network = network;
    }


    /**
     * Gets the distance of the route given by a list of stations
     * @param itinerary a List of names of stations
     * @returns the distance of the given route
     * @throws IllegalArgumentException if the route is not valid:
     *   - One station does not exist
     *   - there is no connection between consecutive stations
     *   - the route is has at least two different stations
     */ 
    public int getDistance(List<String>itinerary){

        Route route = network.buildRoute(itinerary);

        return route.getDistance(); 
    }


    /**
     * Counts the number of trips from an origin to a destination within a
     * number of .
     * @Returns the number of trips that match the criteria
     * @thorws IllegalArgumentException if either station doesn't exist
     */
    public int countBoundedStopsTrips(String origin,String destination, int minStops,int maxStops){

        return (int)(network.getRoutes(origin,destination)
            .filter(r-> (r.numConnections() >= minStops) && (r.numConnections() <= maxStops))
            .count());

    }

    /**
     * Counts the number of trips from an origin to a destination within a
     * maximum distance.
     * @Returns the number of routes that match the criteria
     * @thorws IllegalArgumentException if either station doesn't exist
     */
    public int countBoundedDistanceTrips(String origin,String destination, int maxDistance){

        return (int)(network.getRoutes(origin,destination)
            .filter(r-> r.getDistance() < maxDistance)
            .count());

    }

    /**
     * Convenience method to find trips without minimun number of stops
     * @see countBoundedStopsTrips(String,String,int,int)
     */
    public int countBoundedStopsTrips(String origin,String destination, int maxStops){
        return countBoundedStopsTrips(origin,destination,1,maxStops);
    }


    /**
     * Gets the shortest route between two stations, if exists
     * 
     * @returns an Optional with the minimum distance route, if any, ot empty
     *          if no route exists
     * @throws IllegalArgumentException if any of the stations does not exists
     */
    public  Optional<Route> getShortestRoute(String origin,String destination){

        return network.getRoutes(origin,destination)
                      .min( (r1,r2)->r1.getDistance()-r2.getDistance());

    }



    /**
     * Counts all possible routes between two stations.
     */
    public int countAllRoutes(String origin, String destination){

        return (int)(network.getRoutes(origin,destination)
            .count());

    }
}
