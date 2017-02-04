
package trains;

import java.util.List;

/*
 * validates a route has not more than a given number of stops
 */

public class NumStopsValidator implements RouteValidator{

    private int minStops;
    private int maxStops;

    public NumStopsValidator(int minStops,int maxStops){
       this.minStops=minStops;
       this.maxStops=maxStops;
    }

    /**
     * Convenience constructor with default min connections 
     * @see NumStopsValidator(int, int)
     */
    public NumStopsValidator(int maxStops){
      this(1,maxStops);
    }
 
   
    public boolean validate(Route route){
      
        return ((route.numConnections() >= minStops) && (route.numConnections() <= maxStops));
    }

 }


