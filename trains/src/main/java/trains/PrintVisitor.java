
package trains;

import java.util.List;

/*
 * Print a route found by a planner
 *
 */
public class PrintVisitor implements RouteVisitor{



 public void visit(Route route){
   
 
   System.out.println("Route from " + route.getOrigin() + 
                      " to " + route.getDestination() + 
                      " distance: " + route.getDistance() + 
                      " connections: " +  route.numConnections());
 }

  
}
