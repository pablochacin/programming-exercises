package rovers;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

/*
 * Controls a set of rovers deployed on a region
 */
public class Mission {

     /*
      *  DTO with Information about a rover
      */
     public class RoverInfo {

       public Integer id;
       public Point position;
       public Region fence;
       public Orientation orientation;

       protected RoverInfo(Integer id,Rover rover){
         this.id = id;
         this.position = rover.getPosition();
         this.fence = rover.getFence();
         this.orientation=rover.getOrientation();

      }

      public String toString(){
  
	      StringBuffer string = new StringBuffer();
	      string.append("Id: "+ id);
	      string.append("Position: "+position);
	      string.append("Fence: " + fence);
	      string.append("Orientation" + orientation);
	      return string.toString();
      }

     }

     private Region region;
     private Map<Integer,Rover> rovers;
     private Integer roverId;

     public Mission(Region region){
       this.region = region;
       this.rovers = new HashMap<Integer,Rover>();
       this.roverId = 0;
     }

     /*
      *  Deploys a rover on the mission's region at a given point and orientation
      *  returns the id of the new rover
      */
     public Integer deployRover(Point position,Orientation facing){
          Rover rover = new Rover(position,region,facing);
          roverId++;
          rovers.put(roverId,rover);
          return roverId;
     }

   
    public void decomissionRover(Integer roverId){
         rovers.remove(roverId);
    }

    /*
     * Lists the existing rovers

     */
    public List<RoverInfo> listRovers() {
    
       List<RoverInfo> roverList = new ArrayList<RoverInfo>();

       for(Map.Entry<Integer,Rover> r : rovers.entrySet()){
          roverList.add(new RoverInfo(r.getKey(),r.getValue()));
          
       }

       return roverList;
    }

}
