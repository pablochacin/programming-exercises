package trains;

import java.util.List;

/*
 * inverts the result of a given validator
 */

public class InverseValidator implements RouteValidator{

    private RouteValidator validator;

    public  InverseValidator(RouteValidator validator){
      this.validator = validator;
    }

    public boolean validate(Route route){
     return !validator.validate(route);
    }
 }


