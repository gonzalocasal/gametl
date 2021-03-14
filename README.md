# gamETL

A commandline application to parse, process and export games results.  


### Prerequisites
* Apache Maven 3.6.3
* Java 8

### Install
```
 git clone https://github.com/gonzalocasal/gametl.git
```

### Build
```
 mvn clean package
```

### Execute
```
java -jar target/gametl-1.0.0.jar
```
The default file input and output are relative paths: **src/main/resources/bowling_example** and **src/main/resources/bowling_example_out**.
You can override these paths and execute the application with custom absolute paths
```
java -jar target/gametl-1.0.0.jar --file.in.path=/home/user/in/bowling_example   --file.out.path=/home/user/out/bowling_example
```
