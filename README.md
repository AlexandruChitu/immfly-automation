# immfly-automation
Selenium test task

This is a short demonstration of automating test cases with Selenium TestNG Java. 

In order to run:

1. Download Selenium Grid jar - https://www.selenium.dev/downloads/
2. Download Chromedriver - https://chromedriver.chromium.org/downloads
3. Start Selenium Grid local server with the following commands:
    - java -jar selenium-server-standalone-3.141.59.jar -role hub -port 4444
    - java -jar selenium-server-standalone-3.141.59.jar -role node -hub http://localhost:4444/grid/register -browser browserName=chrome,webdriver.chrome.driver=/Users/alexchitu/Documents/qa-automation/chromedriver,maxInstances=5,platform=MAC
5. Once Selenium Grid server is running, run TestRunner mainclass to start test suite. 
