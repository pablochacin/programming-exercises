package trains;

import java.util.List;

/*
 * counts the routes found by a planner
 *
 */
public class CountingVisitor implements RouteVisitor{

 private int counter;

 public CountingVisitor(){
   this.counter = 0;
 }

 public void visit(Route route){
   counter++;
 }

 public int getCount(){

   return this.counter;
 }
  
}
