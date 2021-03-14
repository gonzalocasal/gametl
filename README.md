# gamETL

A commandline application to parse, process and export games results.  

### Prerequisites
* Apache Maven 3.6.3
* Java 8

### Libraries & Frameworks
* Springboot 2.4.3
* Lombok 1.18.4
* Junit 4.13

### Install
```
git clone https://github.com/gonzalocasal/gametl.git
```

### Build
```
cd gametl && mvn clean package
```

### Execute
```
java -jar target/gametl-1.0.0.jar
```
The default input and output file paths are relative: 

* src/main/resources/bowling_example
* src/main/resources/bowling_example_out

You can override one or both paths, and execute the application with custom absolute paths:
```
java -jar target/gametl-1.0.0.jar --file.in.path=/home/user/in/bowling_example   --file.out.path=/home/user/out/bowling_example_out
```
