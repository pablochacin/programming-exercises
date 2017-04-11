package trains;

public class CmdResult {

   enum Result {SUCCEED,ERROR};

   private Result result;

   private Throwable error;

   protected CmdResult(Result result){
    this.result = result;
   }

   protected CmdResult(Throwable error){
     this.result = Result.ERROR;
     this.error = error;
   }

   
   public boolean succeeded(){

     return this.result.equals(Result.SUCCEED);
   }

   public Throwable getError(){
     if(!result.equals(Result.ERROR))
        throw new IllegalStateException("Command succeded. No error available");

     return error;
   }


  public static CmdResult reportSuccess(){
       return new CmdResult(Result.SUCCEED);
  }

  public static CmdResult reportError(Throwable error){
     return new CmdResult(error);
  } 
}


