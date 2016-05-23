# Timeclock Project #

[Jira Board](https://catalystit.atlassian.net/projects/TICKTOCK/summary)  
[Confluence Page](https://catalystit.atlassian.net/wiki/display/CLK)

## Technologies ##

    * Build Tool               - Maven               - 3.3
    * Server Code              - Java                - 1.8
    * Server Framework         - Spring              - 4.2
    * Embedded Database        - hsqldb              - 2.3
    * Logging                  - log4j               - 1.2
    * Unit Testing             - JUnit               - 4.12
    * Mocking                  - Mockito             - 1.10
    
    * Javascript Helper        - lodash              - 4.12
    * Browser Framework        - Angular             - 1.5
    * Browser Routing          - Angular UI Router   - 0.2
    * CSS Baseline             - Bootstrap           - 3.3

## Local Development ##
To build the project run the following command from the root directory.

    mvn clean install
    
This will execute all tests and setup the server.  Which is defaulted to [http://localhost:8080](http://localhost:8080)
The server is setup to scan the classpath for any changes every 5 seconds.

## Setup : TODO ##
1. Debug java instructions.
2. Add [Jasmine](http://jasmine.github.io/2.4/introduction.html) unit testing.
3. Add [Selenium](http://www.seleniumhq.org/) e2e testing.
4. [Optional] JS dependency manager.
5. [Optional] CSS pre-processor.
6. [Optional] JS and CSS packaging.
7. [Optional] Links to technologies