package trains;

import java.util.List;

/*
 * Print a route found by a planner
 *
 */
public class DetailedPrintVisitor extends PrintVisitor{


 public void visit(Route route){
 
  super.visit(route);
  System.out.print("Legs:");
  for(String s: route.getItinerary()){
    System.out.print(s);  
  }
  System.out.println();

  
}

}
