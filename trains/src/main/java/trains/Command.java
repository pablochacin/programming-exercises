package trains;

public interface Command {

  public CmdArgs parse(String args);

  public CmdResult execute(RoutePlanner planner,CmdArgs args);
}
