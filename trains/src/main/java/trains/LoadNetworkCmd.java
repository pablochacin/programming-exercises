package trains;

import java.util.Set;
import java.util.HashSet;
import java.util.List;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class LoadNetworkCmd implements Command {

  private static class NetworkArgs implements CmdArgs{
  
     Set<String> stations;
     List<Connection> connections;

     protected NetworkArgs(){
         this.connections= new ArrayList<Connection>();
         this.stations = new HashSet<String>();
     }

     protected void addConnection(String origin, String destination, int distance){
        stations.add(origin);
        stations.add(destination);
        connections.add(new Connection(origin,destination,distance));
     }
  }

  public CmdArgs parse(String args){

     NetworkArgs nargs = new NetworkArgs();

     StringTokenizer tokenizer = new StringTokenizer(args);

     while(tokenizer.hasMoreTokens()){

      String connection = tokenizer.nextToken();


      try{
        String origin = connection.substring(1,2);
        String destination = connection.substring(2,3);
        int distance = Integer.valueOf(connection.substring(3)); 
       
        nargs.addConnection(origin,destination,distance);

     }catch (NumberFormatException e){
       throw new IllegalArgumentException("Connection''s distance must be integer:" + connection);
     }
     catch(IndexOutOfBoundsException i) {
       throw new IllegalArgumentException("Connections must specify origin, destination and distance");
     } 

    }
  
    return nargs;
  }

 
  public CmdResult execute(RoutePlanner planner,CmdArgs args){
    NetworkArgs gargs = (NetworkArgs)args;
    
    Network network = new Network();
    
    try{
      for(String s: gargs.stations){
        network.addStation(s);
       }

       for(Connection l: gargs.connections){
         network.addConnection(l.getOrigin(),l.getDestination(),l.getDistance());
        }

      planner.loadNetwork(network);
    }catch(Exception e){
       return CmdResult.reportError(e);
    }
   
    return CmdResult.reportSuccess();
  }
}

