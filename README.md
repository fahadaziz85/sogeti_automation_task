# sogeti_automation_task
# Steps to run the tests
1. You need the following tools to run the test

		a: Eclipse IDE
		b: TestNG plugin for eclipse
		c: Maven plugin for eclipse
    
2: Clone or Download the project in your local machine

3: Import the project in Eclipse IDE

4: Right click on the project SogetiTestAutomationTask and click Maven -> update project

5: Open to the java file /src/com/sogeti/basetestframework/BaseClass.java and change the path of the chrome driver to your local machine chrome webdriver on line 17 and save the     file

6: Right click on testng.xml file and choose Run As -> TestNG Suite, it will start executing tests
