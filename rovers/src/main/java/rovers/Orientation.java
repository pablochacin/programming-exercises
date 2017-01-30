package rovers;


 /*
   * Represents each possible orientation and the two adjacent (left, right) orientations
   *
   * This implementation uses enums to represent orientation of the rover. This makes the code more
   * concise, as take advantaje of enum's build-in features, and allows for compile time validatin of
   * arguments, but has the drawback of requiring a recompilation to extend the code for, for example, 
   * more orientations like SW, NE, etcetera. However, the structure of the solution could be replicated
 * using regular classes instead of enums.
    * 
    * Structure of adjacent orientations is encoded in the emum as two fields, for implicity. 
    * Alternatively, arithmetic modulo caould be used for a more generic solution:
    *   if direction = -1 for left, and +1 for right, then
    *   adjacemt = Orientation.values()[(this.ordinal() +direction + Orientation.values().length) & Orientation.values().length]
     */
    public enum Orientation {
        N(3,1,Point.UP),E(0,2,Point.RIGHT),S(1,3,Point.DOWN),W(2,0,Point.LEFT);
        
        private int left;
        private int right;
        private Point move;
        
        Orientation (int left,int right,Point move){
            this.left = left;
            this.right = right;
            this.move = move;
        }
    
    
        public Orientation rotateLeft(){
           return Orientation.values()[this.left];
        }
        
        
        public Orientation rotateRight(){
         return Orientation.values()[this.right];
        }
       
        /*
         * Returns the vector that defines a move in this orientation
         */ 
        public Point getMove(){
          return move;
        }
            
    };
 
