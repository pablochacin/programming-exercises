package trains;

public class ValueCmdResult<T> extends CmdResult {
    
    private T value;

    private ValueCmdResult(T value){ 
      super(Result.SUCCEED);
      this.value = value;
    }

    public T getValue(){
     if(!succeeded())
        throw new IllegalStateException("Command failed. No value available");
     return value;
    }

    public static <T> ValueCmdResult<T> reportValue(T value){
       return new ValueCmdResult<T>(value);
    }

    
   
 }

