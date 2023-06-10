# backEnd

Unsupported class file major version 64 Error 해결법

IntelliJ 
1. File -> Settings -> Build, Execution, Deployment -> Build Tools -> Gradle
   Build and Run -> Build and Run Using, Run Tests Using 항목 IntelliJ IDEA로 변경

2. Gradle -> Gradle JVM 을 JDK 17버전으로 변경

3. File -> Project Structure -> Project Settings -> Project -> SDK 항목 JDK 17로 변경
