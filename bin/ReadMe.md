COSC310_Lab2_G16


The purpose of the program we created is to assist users in solving their technology problems. This chatbot is similar to ones you would see on websites such as TELUS where the chatbot leads the user through a series of questions in order to attempt to solve the users tech problems. In order to run the program in eclipse you will need to complete the following steps:


1.	Download and install Eclipse.
2.	In your browser, open the URL for the github repository , click accept, click the URL then click Clone and copy the URL
3.	In Eclipse, File - import - git - projects from Git - clone URI then enter the URI.


To run the chatbot you need to run Run.java. There are 1 classes in this project : DecisionMatrix.java, EdgeBuilder.java, Question.java, QuestionBuilder.java, QuestionBuilderTest.java, Run.java, StackHandler.java, Testing.java, Tree.java, and UserInput.java. DecisionMatrix.java is the program that decides what the program does for a given input. For example, if the user answers a question in a way that the program does not understand, it will respond by saying, “Did not understand that, please try again”. It also determines whether or not the program is finished or needs to keep going. EdgeBuilder.java Takes an ArrayList of unlinked questions (children nodes empty), and links all of the nodes together using branchID and questionPriority. Question.java contains info from individual question file. QuestionBuilder.java gets all question files in a specified directory and reads them into new Question objects while QuestionBuilderTest.java tests it. StackHandler.java ensures that the stack we have created runs smoothly. Testing.java is the main testing class. Tree.java creates a tree that holds the questions. UserInput.java is all the answers that the user inputs. Finally, Run.java is the main class that runs the chatbot.
