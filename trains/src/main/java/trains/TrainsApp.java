package trains;

import java.util.Arrays;
import java.util.NoSuchElementException;

public class TrainsApp {


 private Network network;

 private static void loadNetwork(Network network){
   network.addStation("A");
   network.addStation("B");
   network.addStation("C");
   network.addStation("D");
   network.addStation("E");

   network.addConnection("A","B",5);
   network.addConnection("B","C",4);
   network.addConnection("C","D",8);
   network.addConnection("D","C",8);
   network.addConnection("D","E",6);
   network.addConnection("A","D",5);
   network.addConnection("C","E",2);
   network.addConnection("E","B",3);
   network.addConnection("A","E",7);

 }
 
 public static void main(String[] args){

  
    Network network = new Network();
    loadNetwork(network);

    RoutePlanner planner = new RoutePlanner(network);

    int test=1;
    //The distance of the route A-B-C. 
    System.out.println("#" + test++ + " Distance A-B-C: "+planner.getDistance(Arrays.asList("A","B","C")));
    
    //The distance of the route A-D.
    System.out.println("#" + test++ + " Distance A-D: "+planner.getDistance(Arrays.asList("A","D")));

    //The distance of the route A-D-C.
    System.out.println("#" + test++ + " Distance A-D-C: "+planner.getDistance(Arrays.asList("A","D","C")));

    //The distance of the route A-E-B-C-D. No Such route!
    System.out.println("#" + test++ + " Distance A-E-B-C-D: " + planner.getDistance(Arrays.asList("A","E","B","C","D")));

    //The distance of the route A-E-D.
    try{
       System.out.print("#" + test++ + " Distance A-E-D: ");
       System.out.println(planner.getDistance(Arrays.asList("A","E","D")));
    }catch(NoSuchElementException e){
      System.out.println("No such route:" + e.getMessage());
    }


  //The number of trips starting at C and ending at C with a maximum of 3 stops.  In the sample data below, there are two such trips: C-D-C (2 stops). and C-E-B-C (3 stops).
  System.out.println("#" + test++ + " Num of trips from C to C with at most 3 stops: " + planner.countBoundedLegsTrips("C","C",3));

//The number of trips starting at A and ending at C with exactly 4 stops.  In the sample data below, there are three such trips: A to C (via B,C,D); A to C (via D,C,D); and A to C (via D,E,B).

  System.out.println("#" + test++ + " Num of trips from A to C with exactly 4 stops: " + planner.countBoundedLegsTrips("A","C",4,4));

//The length of the shortest route (in terms of distance to travel) from A to C.
System.out.println("#" + test++ + " Length of shortest route from A to C: " + planner.getShortestDistance("A","C"));

//The length of the shortest route (in terms of distance to travel) from B to B.
System.out.println("#" + test++ + " Length of shortest route from B to B: " + planner.getShortestDistance("B","B"));

//The number of different routes from C to C with a distance of less than 30.  In the sample data, the trips are: CDC, CEBC, CEBCDC, CDCEBC, CDEBC, CEBCEBC, CEBCEBCEBC.    
System.out.println("#" + test++ + " Number of routes from C to C with disrtance less than 30: " + planner.countBoundedDistanceTrips("C","C",30));
 }

}
