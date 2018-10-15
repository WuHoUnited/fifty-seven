# fifty-seven

Clojure implementations for problems from the book Exercises for
Programmers 57 Challenges to Develop Your Coding Skills.

# Build

To build, navigate to the project root directory and run `lein
uberjar`

# Run

To run, you must have first built the uberjar and be in the project
root directory.

Running from another directory will not work for many problems because
they have configuration in the config directory.

You must provide the number of the exercise you wish to run in the
format `java -jar target/fifty-seven-0.1.0-SNAPSHOT-standalone.jar
<problem-number>`

For instance, to run the 3rd exercise you would run `java -jar
target/fifty-seven-0.1.0-SNAPSHOT-standalone.jar 3`