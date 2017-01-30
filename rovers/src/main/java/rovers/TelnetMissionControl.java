package rovers;

import java.io.*;
import java.net.*;


public class TelnetMissionControl extends MissionControl {

  public TelnetMissionControl(InputStream in, OutputStream out,OutputStream err){
   
  super(in,out,err);

}

public static void main(String[] args){

   if (args.length != 1) {
       System.err.println("Usage: java  TelnetMissionControl  <port number>");
       System.exit(1);
   }
   
   int port = Integer.parseInt(args[0]);

   try{

     ServerSocket serverSocket = new ServerSocket(port);
     Socket client = serverSocket.accept();
     MissionControl mission = new MissionControl(client.getInputStream(),client.getOutputStream());
     mission.processCmds();
   } catch(IOException e){
      System.err.println("Error in client connection"+e.getMessage());
   }   
}

}
