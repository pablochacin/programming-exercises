package rovers;

//helper class to store pair of integer values
 public class Point{
   
   /*
    *  Constants that represent the move in one direction
    */     
   public static Point UP = new Point(0,1);
   public static Point DOWN = new Point(0,-1);
   public static Point LEFT = new Point(-1,0);
   public static Point RIGHT = new Point(1,0);
        
        private int x;
        private int y;
        
        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
        
        public int getX(){
          return this.x;
        }
        
        public int getY(){
          return this.y;
        }
        
       public Point add(Point other){
           return new Point(this.x + other.x,this.y + other.y);
        }
           

       /*
        * returns this point scaled up or down by a factor
        */
       public Point scale(double factor){
          return new Point((int)((double)x*factor),(int)((double)y*factor));
       }
        
       public String toString(){
          return "(" + x +","+y+")";
       }

      public boolean equals(Object other){
          Point otherPoint = (Point)other;
          return ((x == otherPoint.getX()) && (y == otherPoint.getY()));
     } 

     public int hashCode(){
        //FIXME: worst hash function of all times! Shame on me :-(
        return (x<<16 & 0xff00)| y;
     }   
 }

