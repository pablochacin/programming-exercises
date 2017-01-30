PROBLEM STATEMENT
-----------------

Design a system to control squads of robotic rovers. 

A squad of rovers is deployed in missions to recognize a rectangular zone, so that their
on-board cameras can get a view of the terrain.

A rover's position and location is represented by a combination of x and y co-ordinates and a
letter representing the direction the rover is facing to, as one of the four cardinal compass 
points. The zone is divided up into a grid to simplify navigation. An example position might 
be 0, 0, N, which means the rover is in the bottom left corner and facing North.

In order to control a rover, the mission control sends a simple string of commands to indicate the
action to execute. The possible commands are 'L', 'R' and 'M'. 'L' and 'R' makes the rover spin 90 
degrees left or right respectively, without moving from its current position. 'M' means move forward 
one grid point, and maintains the same heading.

INPUT: 

The program will receive as input a file describing the mission. The first line of input is the 
upper-right coordinates of the zone, the lower-left coordinates are assumed to be 0,0. The rest of 
the input is information pertaining to the rovers that have been deployed. Each rover has two lines 
of input. The first line gives the rover's initial coordinates and facing direction, and the second 
line is a series of commands telling the rover how to explore the zone. 

Assume each rover will be finished sequentially, which means that the second rover won't start 
to move until the first one has finished moving.


SOLUTION PROPOSAL
-----------------

This document offers an introduction to the solution of the rovers problem. The code is organized in the 
following classes:
Point:  Represents a vector in a 2D Coordinate
Region: A rectangular area defined by its bottom left and upper right point, which “fances” a rover’s 
movement
Rover: an entity capable of executing commands that change its position and orientation in a region
Orientation: Encodes the information about the behavior of a rover depending of its orientation, such as 
the result of rotating left and right, or moving ahead
Mission: defines a region on which a set of rovers is deployed

DESIGN
======

The main concept of the design is the encoding of the Rover’s behavior in the Orientation class, following 
the State pattern. This decision greatly simplifies the resulting code, which becomes almost free of 
conditional processing.

Another interesting aspect of the design is the introduction of the Region class, which gives room for 
extending the notion of polygonal regions, as the logic of defining boundaries is encapsulated in this
class.   

Finally, the Mission class encapsulates the rules for creating the regions and controlling the rover d
eployment. It allows sophisticated  control, like sending commands at any time to the rovers.


TODO
====

Implement the interface to receive and process commands
