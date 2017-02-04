package trains;

import java.util.List;
import java.util.ArrayList;

 /*
  * Callss a chain of validators to a given route.
  * 
  */ 
public class CompositeVisitor implements RouteVisitor{
  
  private List<RouteVisitor> visitors;
  
  public CompositeVisitor(){
   this.visitors = new ArrayList<RouteVisitor>();
  }

  public CompositeVisitor addVisitor(RouteVisitor visitor){
    visitors.add(visitor);
    return this;
  }

  public void visit(Route route){
  
   for(RouteVisitor v: visitors){ 
     v.visit(route);
    }
   
  }
 }


