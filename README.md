# futureTransactions
This is a simple maven based java application. Parse an input file and create an output.csv file based on business rules.

## Scope of this application?
1. The java application parse an input text (.txt) file from futureTransactions/Input_Folder directory.
2. Read the input text file and generate a output.csv file in the futureTransactions/OutputFile_Folder directory
3. Application activities can be monitored from the generated log file (futureTransactions/futureTransactions.log)

## What is the minimum requirement to run this application?
1. JDK1.8 (minimum) and JDK path is set correctly
2. Install Maven
3. Any command line application (CMD/ power shell/ terminal (for Mac))

### Which Operating systems are supported?
Windows 64/ Linux / Mac OS

## What are the steps to execute the application?
1. Do a git clone of this project by executing the following command:
    - $ git clone https://github.com/rsabhi/futureTransactions.git
   
2. $ cd futureTransactions
3. In command line application / or Terminal run the following commands:

    - $ mvn clean install
  
This command will download the required dependencies, build and run the Test cases and generate a test output file.

    - $ mvn exec:java -Dexec.mainClass="com.abn.futuretransactions.FutureTransactions"
  
This command will run the application and generate output.csv file in futureTransactions/OutputFile_Folder directory.

## How this project is Organised?
![futureTransactions](img_folder/Project_structure.png)

## Troubleshooting
Tested on both windows and mac machines and it worked out of the box. So make sure to configure the JDK path correctly to before running the mvn commands.
