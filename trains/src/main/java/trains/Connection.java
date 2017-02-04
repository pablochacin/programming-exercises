package trains;

public class Connection {

  public String origin;
  public String destination;
  public int distance;

  public Connection (String origin, String destination, int distance){
    this.origin=origin;
    this.destination=destination;
    this.distance=distance;
  }

  public String getDestination(){
   return this.destination;
  }

  public String getOrigin(){
   return this.origin;
  }

  public int getDistance(){
    return this.distance;
  }

  @Override
  public boolean equals(Object other){
     Connection otherConn = (Connection)other;

     return  (this.origin.equals(otherConn.origin) &&
              this.destination.equals(otherConn.destination)); 
  }

  @Override
  public int hashCode(){
    //TODO: implement a better hash code
    return origin.hashCode() ^ destination.hashCode(); 
  }
}

