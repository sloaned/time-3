# Timeclock Project #

[Jira Board](https://catalystit.atlassian.net/projects/TIME2/summary)  
[Confluence Page](https://catalystit.atlassian.net/wiki/display/TIME2)

## Technologies ##

    * Build Tool                       - Maven               - 3.3
    * Server Code                      - Java                - 1.8
    * Server Framework                 - Spring              - 4.2
    * Embedded Database                - hsqldb              - 2.3
    * Logging                          - log4j               - 1.2
    * Unit Testing                     - JUnit               - 4.12
    * Mocking                          - Mockito             - 1.10
    * E2E Testing                      - Selenium            - 2.53
    
    * JavaScript Helper                - lodash              - 4.12
    * Browser Framework                - Angular             - 1.5
    * Browser Routing                  - Angular UI Router   - 0.2
    * CSS Baseline                     - Bootstrap           - 3.3
    * JavaScript Test Runner           - Karma               - 0.13
    * JavaScript Test Framework        - Jasmine             - 2.4
    * JavaScript Dependency Injection  - Webpack             - 1.13
    * JavaScript Packaging             - Webpack             - 1.13

## Local Development ##
To watch the project run the following command from the root directory.

    mvn clean install -Pwatch

Features of this build:

* The tomcat server is set to refresh when changes on the classpath are detected.  This scan 
should be made every 5 seconds.
* Any changes in the *src/main/javascript* will cause the javascript to be re-packaged.  
The developer will still need to manually refresh the browser.     
* Default local web address: [http://localhost:8080](http://localhost:8080)

Press **[CTRL]+C** in the command line window to stop the server and watch.

Another helpful command is to just run the unit tests.

    mvn clean test
    
To run all test, including E2E tests run the following command.

    mvn clean install

## Setup : TODO ##
1. Debug java instructions.
2. [Optional] CSS pre-processor.
3. [Optional] CSS packaging.
4. [Optional] Links to technologies