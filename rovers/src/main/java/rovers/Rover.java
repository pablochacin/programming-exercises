package rovers;


/*
 * Maintains the position and orientation of a rover over a grid, with a given fancing limits
 * 
 * This implementation uses enums to represent orientation of the rover. This makes the code more
 * concise, as take advantaje of enum's build-in features, and allows for compile time validatin of
 * arguments, but has the drawback of requiring a recompilation to extend the code for, for example, 
 * more orientations like SW, NE, etcetera. However, the structure of the solution could be replicated
 * using regular classes instead of enums.
 * 
 */
public class Rover {

    //available commands to control the Rover    
    public enum Command {L,R,M};
    
    
    private Point pos;
    private Region fence;
    private Orientation orientation;
    
    
    /*
     * Creates a new rover with a fancing limints
     */
    public Rover(Point pos,Region fence,Orientation orientation){
       
       if((pos.getX() < 0) || (pos.getY() < 0)){
           throw new IllegalArgumentException("coordinates cannot be negative: x=" + pos.getX()  + " y="+pos.getY());
       }
        
        
     //TODO: print the fence limits in the messaga
     if(! fence.isWithin(pos)){
           throw new IllegalArgumentException("Right Up limits must be greater than Left Down fance limits");
     }
  
                    
       this.pos = pos;
       this.fence = fence;
       this.orientation = orientation;
     }
        
     

    public Region getFence(){
        return fence;
    }
   
    public void setFance(Region fence){
     if(!fence.isWithin(pos)){
           throw new IllegalArgumentException("Current position is outside new fance");
      }
      
      this.fence = fence;
    }

 
  
    // rotates the rover 
    private void rotateLeft(){
 
      orientation = orientation.rotateLeft();
            
    } 

    // rotates the rover 
    private void rotateRight(){
 
      orientation = orientation.rotateRight();
            
    } 

    
    /*
     *  Moves rover one position in the current facing direction.
     *  If resulting position is outside the region, move will have no effect
     */    
    private void move(){
    
      Point newPos = pos.add(orientation.getMove());
       if(fence.isWithin(newPos)){
          pos=newPos;
       }
    }
        
    public Point getPosition(){
      return this.pos;
    }
    
    public Orientation getOrientation(){
      return orientation;
    }

     
    /* 
     * Executes one of the defined commands
     * Returns a refence to the rover to allow for a fluent api modeñ
     * 
     */
    public Rover doCommand(Command cmd){
        
      switch (cmd){
         case M: 
                 move();
                 break;
         case L: 
                 rotateLeft();
                 break;
         case R: 
                 rotateRight();
                 break;
      }

      return this;
    }


   public Rover doCommand(String cmdStr){
       Command cmd = Command.valueOf(cmdStr);
       return doCommand(cmd);
   }


}





