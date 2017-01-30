package rovers;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;

/*
 *  Process commands to control a Rover Mission

 */
public class MissionControl{

  private Mission mission;

  private BufferedReader ins;
  private PrintStream outs;
  private PrintStream errs;

  /**
   *  Create a MissionControl to process commands from a input, output and error streams
   */
  public MissionControl(InputStream in, OutputStream out,OutputStream err){
    this.ins = new BufferedReader(new InputStreamReader(in));
    this.outs = new PrintStream(out);
    this.errs = new PrintStream(err);
  }  

  /**
   *  Convenience constructor with output used as error stream
   */  
  public MissionControl(InputStream ins, OutputStream outs){
     this(ins,outs,outs);
  }

  
/*
 * Helper function, prints usage help and terminates
 */
private void printHelpUsage(String message){
  if(message != null){
    errs.println(message);
  }

  errs.println("Controls the deployment of rovers on a restricted area");

  errs.println("usage: ");
  errs.println("\tProvide commands to create a mission and for deploying rovers and controlling their paths");
}

/**
 *  Print 
 */
private void printHelpMission(){
  errs.println("\tintroduce the area limits: x y");
}

private void printHelpRover(){
  errs.println("For each rover, provide a command describing:");
  errs.println("\tx y O CCCCCCCCCCC.....C");
  errs.println("\tx y are the initial position in the mission's region");
  errs.println("\tO is the orientation: (N)orth, (S)outh, (E)ast, (W)est");
  errs.println("\tC...C is a sequence of commands: rotate (L)eft, rotate (R), (M)ove one step ahead");

}


/*
 * Builds a mission from its desciption command:
 * x x defining the upper 
 */
private void processMissionCmd(String missionCmd){

 Scanner s = new Scanner(missionCmd);

 try{
  int x = s.nextInt();
  int y = s.nextInt();

  Point lb = new Point(0,0);
  Point ur = new Point(x,y);
  Region region = new Region(lb,ur);

  mission = new Mission(region);
 
 }catch(InputMismatchException | IllegalArgumentException e){
   errs.println("Invalid mission especification:" + missionCmd);
   printHelpMission();
 }catch(NoSuchElementException e){
   errs.println("Input stream has no mission definition");
 } finally{
   if(mission == null){
     System.exit(0);
   }
 }

}


/*
 * Process command deploying a rover and guiding it over a path
 */
private void processRoverCmd(String roverCmd){

 Scanner s = new Scanner(roverCmd);

 try{
  int x = s.nextInt();
  int y = s.nextInt();

  Point pos = new Point(x,y);
  Orientation o = Orientation.valueOf(s.next());

  Integer rover = mission.deployRover(pos,o);

  String path = s.next();   

  for(byte c :  path.getBytes()){
    String cmdName = String.valueOf((char)c);
    Rover.Command cmd = Rover.Command.valueOf(cmdName);
    mission.executeCmd(rover,cmd);
    
  }
   
  Mission.RoverInfo info = mission.getRoverInfo(rover);
  outs.println(info.position.getX() + " " + info.position.getY() + " " + info.orientation);
 }catch(IllegalArgumentException | NoSuchElementException e){
   errs.println("Invalid rover especification:"+roverCmd);
   printHelpRover();
 }catch(Exception e){
   errs.println("Unexpected error" + e.getMessage());
 } finally{
   if(mission == null){
     System.exit(0);
   }
 }
     
}


/*
 *  Process commands coming from standard input
 *  Simple logic, expects a mission command followed by a 
 *  series of rover deployment commands 
 */
public void processCmds(){
   
   try{ 
     String missionCmd = ins.readLine();
     processMissionCmd(missionCmd);

     while(true){

        String roverCmd = ins.readLine();
        if(roverCmd == null){
         return;
        }

      processRoverCmd(roverCmd);
     }

   } catch(IllegalArgumentException e){
       printHelpUsage("Invalid command format");
   } catch(IOException e){  
       errs.println("IOException processing commands");
   }

}

/*
 * Main function. Creates a mission control that reads commands
 * from stdin and send responses to stdout
 *
 */
public static void main(String[] args) throws FileNotFoundException{


 // set defauls streams and use those provided 
 InputStream in = System.in;
 OutputStream out = System.out;
 OutputStream err = System.err;
  
 if(args.length >= 3){
   err =  new FileOutputStream(args[2]);
 }
 
 if (args.length >=2){
   out =  new FileOutputStream(args[1]);
 }

 if(args.length >=1){
  in = new FileInputStream(args[0]);
 }

 MissionControl control = new MissionControl(in, out,err);

 control.processCmds();
}

}
