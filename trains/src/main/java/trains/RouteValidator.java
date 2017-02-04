package trains;

import java.util.List;

/**
 * validates a route agains a criteria
 */

public interface  RouteValidator{
    /*
     * checks is the given route fits the crieria
     *  @returns true is the route fits the criteria, false otherwiese
     */
    public boolean validate(Route route);

}
