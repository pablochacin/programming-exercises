package trains;

import java.util.List;
import java.util.ArrayList;

 /*
  * Applies  a series of validators to a given route.
  * If any of the validations fails, returns false.
  * If all validations succeed, returns true.
  * An empty list of validators always succeeds,
  * 
  */ 
public class CompositeValidator implements RouteValidator{
  
  private List<RouteValidator> validators;
  
  public CompositeValidator(){
   this.validators = new ArrayList<RouteValidator>();
  }

  public CompositeValidator addValidator(RouteValidator validator){
    validators.add(validator);
    return this;
  }

  public boolean validate(Route route){
  
   for(RouteValidator v: validators){ 
     if(!v.validate(route)){
        return false;
     }
    }
   
   return true;
  }
 }


