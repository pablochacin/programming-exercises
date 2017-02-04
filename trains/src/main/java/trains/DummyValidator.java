package trains;

import java.util.List;

/*
 *  Always accepts a route
 */

public class DummyValidator implements RouteValidator{


    public boolean validate(Route route){
     return true;
    }
 }


