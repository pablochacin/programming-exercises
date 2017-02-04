package trains;

import java.util.List;

/*
 * Looks for the shortest route
 *
 */
public class ShortestRouteVisitor implements RouteVisitor{

 private Route route=null;
 private int shortestDistance = Integer.MAX_VALUE;

 public ShortestRouteVisitor(){
   this.route = null;
 }

 public void visit(Route route){
   
  if(route.getDistance() < this.shortestDistance){
   this.shortestDistance = route.getDistance();

   //TODO: check the safety of this assigment. Route could be modified 
   this.route = route;
  }
 
 }

 /*
  * Returns the current shortes route.
  * @throws InvalidStateExeption is none has been defined
 */
 public Route getRoute(){
  
  if(route == null)
    throw new IllegalStateException("No such route");

  return route;
 }
}
