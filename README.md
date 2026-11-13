Instructions on how to create multi module maven project using vscode:

Let’s say I want to create a maven multi module java project named myapp using VSCode. 
Below is the project structure I like to create:

```text
myapp/
├── myapp-cfg/        # Any configuration file like log4j2.xml
├── myapp-ear/        # EAR packaging module for deployment
├── myapp-ejb/        # EJB module for business logic and persistence
├── myapp-rest/       # JAX-RS REST layer
├── myapp-ng/         # Angular web application packaged as a WAR
│   └── ng/           # Angular workspace and source files
└── pom.xml           # Parent Maven build definition
```

STEP 1: Create root project (myapp)

a. Create a folder in your drive where you want the project to be created in your file system (For rxample: D:\works\myapp). 
b. CD to the folder using command prompt and run git init to initialize git if you want to publish your project to a remote tepository. 
c. From command prompt, type code . and hit enter to open VSCode IDE

In VSCode, select project from the project explorere, click add file and name it pom.xml and pase the following contents in the pom.xml file:

<project xmlns="http://maven.apache.org/POM/4.0.0">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.mycom.myapp</groupId>
    <artifactId>myapp</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>pom</packaging>
    <name>myapp</name>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <project.report.outputEncoding>UTF-8</project.report.outputEncoding>
        <java.version>21</java.version>
        <maven.compiler.source>${java.version}</maven.compiler.source>
        <maven.compiler.target>${java.version}</maven.compiler.target>
        <maven.compiler.release>${java.version}</maven.compiler.release>    
    </properties>

    <modules>
 
    </modules>
</project>

This will create myapp root project. 
Create another file in the root project .gitignore file with the contents as below:

# Maven build output
**/target/

# Angular and other generated output
**/dist/
**/.angular/
/out-tsc
/bazel-out
/tmp
**/coverage/

# Node dependencies and logs
**/node_modules/
myapp-ng/ng/node/
myapp-ng/node/
npm-debug.log
yarn-error.log

# IDE and editor files
.vscode/
**/.idea/
**/.settings/
**/.project
**/.classpath
.c9/
*.launch
*.sublime-workspace
**/.history/

# Miscellaneous
.sass-cache/
/connect.lock
/libpeerconnection.log
testem.log
/typings
__screenshots__/

# System files
.DS_Store
Thumbs.db

Optionally create another file README.md file to provide a summary of the project.

STEP 2. Create submodules:

In the project explorer, click/select myapp and then ctrl+shift+p to open command pallet, type Maven: New Module in the input filed (located at the top of the editor). You will see drop down from where select myapp as the root of the module. Then type the module name (for example myapp-ear) and hit enter. This will create myapp-ear as a submodule of the root project myapp, You will see, the root pom.xml has already been updated with this submodule (myapp-ear).

If you are to create more module, continue step 2.

