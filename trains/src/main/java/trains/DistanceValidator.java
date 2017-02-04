package trains;

import java.util.List;

/*
 * validates a route is less than a given distance
 */

public class DistanceValidator implements RouteValidator{

    private int maxDistance;
    public DistanceValidator(int distance){
      this.maxDistance=distance;
    }

    public boolean validate(Route route){
      return route.getDistance() < maxDistance;
    }
 }


