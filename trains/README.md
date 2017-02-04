PROBLEM STATEMENT
-----------------

Train Scheduler System

Consider a local commuter railroad that services a number of towns.
Because of monetary concerns, all of the tracks are 'one-way.'  
That is, a route from station A to stations B does not imply the 
existence of a route from B to A.  In fact, even if both of these 
routes do happen to exist, they are distinct and are not necessarily 
the same distance!

The purpose of this problem is to help the railroad provide its customers
with information about the routes. In particular, you will compute the 
distance along a certain route, the number of different routes between two
towns, and the shortest route between two towns.

Input  
=====

The program will reive two kind of inputs:
* A list of connections between stations with its distance. A given 
connection will never appear more than once, and for a given connection, the
starting and ending stations will not be the same.
	
* A list of queries about the routes between stations
** distance? <station>,<station>[,...<station>]: calculates the distance for 
a given route. For example: "distance? A,D,C"
** trips?  <station>,<station> <condition>: calculates the number of possible
 trips between two stations which satisfy a given condition. Conditions are 
expressed with a combination of a metric, and operator and a value. Metrics
can be "stops" or "distance". Operators can be  "=" or "<". So for example: 
"trips?  A,B stops < 5" means "calculate the number of trips between A and B 
which have less than 5 stops.
** shortest? <station>,<station>: finds the shortest distance between two 
stations (i.e. "shortest? A,E")

If for any inquire there's no route that satisfies the conditions, a 
"No such route" message should be returned.


SOLUTION
========

Design

The main concept of the design is the separation of the train network
structure and search algorithm in a flexible framework that supports the
implementation of multiple (complex) queries easily by composing pre-
defined elements.

The Network class encapsulates the representation and traversal of the
information about the stations and the connections  between them. 
It also offers a method for searching routes between an origing and a 
destionation. The current implementation uses an iterative depth first
search with some minor modifications to use connections (edges) instead of 
stations (vertices) when storing the search state. This modification
facilitates the construction of the routes and also checking for loops. 

The flexibility of the framework comes from the utilization of the visitor
design pattern which separates data structure traversal from the data 
structure implementation. This visitors are used by the route search 
algorithm for two purposes:
 * To validate each generated route against a series of criteria, like 
number of stops or distance (RouteValidator) 

 * To process each generated route like  printing it, or counting it 
(RouteVisitor) 

The main objective has been the maintenability of the solution, more than
the efficiency of the execution. However, due to the proper isolation
of concerns, execution could be improved without modifying the overall 
design.

The second relevant design decision was the implementation of a the class 
RoutePlaner, which translates the more common queries into the corresponding
assembly of building blocks (visitors, validators) to adapt the search 
algorithm and process the resulting routes. This class makes simple to
create multiple interfaces to the system, such a cli interfaces, or text
files that scripts such interactions.

2. Todos and improvements

Presently, routes are validated once they are completed. One main
improvement that could be prune from the search space those incomplete
routes that already don't satisfy the validation criteria. For example,
makes no sense to keep exploring variations of a route that exceeds the
maximum allowed distance before reaching the destination. 

The present implementation doesn't handle input/output properly. However,
the implementation of an interface to load the network and process the
queries seams simple, due to the separation of concerns.


How to use it

The solution was implemented using maven. The following commands allow
to compile, package as jar and execute the application.

$ mvn compile
$ mvn package
$ java -cp target/trains-1.0-SNAPSHOT.jar trains.TrainsApp
