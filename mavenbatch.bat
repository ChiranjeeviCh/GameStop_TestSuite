set ProjectPath=C:\Users\chich\git\repository\BizOpsTestSuite
echo %ProjectPath%
cd %ProjectPath%
set classpath=%ProjectPath%\bin;%ProjectPath%\lib\*
java org.testng.TestNG %projectPath%\testng.xml
pause