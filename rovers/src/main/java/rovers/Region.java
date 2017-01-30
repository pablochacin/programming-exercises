package rovers;


   /* 
    *  Represents a (square) area defined by two corners: lower left and upper right)
    * This class can be easily extended to handle more complex areas such as polygons using a list of points
    */
   public class Region{
         
         private Point leftDown;
         private Point rightUp;
         
         public Region(Point leftDown,Point rightUp){
        
            if((rightUp.getX() <= leftDown.getX()) || (rightUp.getY() <= leftDown.getY())){
                throw new IllegalArgumentException("Right Up limit must be bigger than left botton limit");
            }
            
            this.leftDown = leftDown;
            this.rightUp = rightUp;
         }
         
         public Region(Point rightUp){
             this(new Point(0,0),rightUp);
             
         }
    
    
         public boolean isWithin(Point point){
          
            if((point.getX() < leftDown.getX()) || (point.getY() < leftDown.getY()) || (point.getX() > rightUp.getX()) || (point.getY() > rightUp.getY())){
               return false;
            }
            
            return true;
         }    

       public Point getLeftDown(){
         return leftDown;
       }

       public Point getRightUp() {
         return rightUp;
       }

       public String toString(){
         StringBuffer buffer = new StringBuffer();
         buffer.append("LeftDown: " + leftDown);
         buffer.append("RightUp: " + rightUp);
         return buffer.toString();
       }

       public boolean equals(Object other){
         Region or = (Region)other;
         
         return leftDown.equals(or.getLeftDown()) && rightUp.equals(or.getRightUp());
       }

       public int hashCode(){
        
        //FIXME: check this method
        int hash = 1; 
        hash = hash*31 + leftDown.hashCode();
        hash = hash*31 + rightUp.hashCode();
        return hash;
       }

    }
