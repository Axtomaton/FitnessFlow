# E-Store:  Parkour Gym
# A family run gym in Rochester, NY. The gym sells Online Lessons, Instructor Taught lessons, and Gym passes.
# Modify this document to expand any and all sections that are applicable for a better understanding from your users/testers/collaborators (remove this comment and other instructions areas for your FINAL release)

An online E-store system built in Java 8=>11
  
## Team

- Thomas Garcia 
- Ivan Lin
- JianZhuang Jiang 
- Aagman Relan



## Prerequisites

- Java 8=>11 (Make sure to have correct JAVA_HOME setup in your environment)
- Maven
- Angular


## How to run it

1. Go to the estore-api folder, then open the terminal and execute "mvn compile exec:java"
2. Go to estore-ui/estore-ui directory, open a new terminal window into this directory and execute "ng serve --open"
3. Open the web browser and go to "http://localhost:4200"

## How to test it

The Maven build script provides hooks for run unit tests and generate code coverage
reports in HTML.

To run tests on all tiers together do this:

1. Execute `mvn clean test jacoco:report`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/index.html`

To run tests on a single tier do this:

1. Execute `mvn clean test-compile surefire:test@tier jacoco:report@tier` where `tier` is one of `controller`, `model`, `persistence`
2. Open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/{controller, model, persistence}/index.html`

To run tests on all the tiers in isolation do this:

1. Execute `mvn exec:exec@tests-and-coverage`
2. To view the Controller tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
3. To view the Model tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`
4. To view the Persistence tier tests open in your browser the file at `PROJECT_API_HOME/target/site/jacoco/model/index.html`

*(Consider using `mvn clean verify` to attest you have reached the target threshold for coverage)
  
  
## How to generate the Design documentation PDF

1. Access the `PROJECT_DOCS_HOME/` directory
2. Execute `mvn exec:exec@docs`
3. The generated PDF will be in `PROJECT_DOCS_HOME/` directory



## License

MIT License

See LICENSE for details.
