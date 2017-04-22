Gradle C
--------

This is a sample Gradle 2.x project for building native C language components.
It shows:

* How to organize the code in components and resolve dependencies
* How to use CUnit for unit testing
* How to setup the distribution using a tar file

In particular, the project addresses the CUnit issues with having an executable
(with a main function)  in the prject. To solve this issue, the project is split
in a subprojects with the components (libraries) to be tested and a main project
with the main executable.



