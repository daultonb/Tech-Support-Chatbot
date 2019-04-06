package src;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import GUI.GUI;

/*
 * Class: Run
 * Description: -	Main class to run the Chatbot
 * 				-	Incorporates all other classes (directly or indirectly) 
 * 				
 * 	Dependencies- 	UserInput.java	
 * 				-	DecisionMatrix.java
 * 				-	Hashmap.java
 * 
 * 	Parameters	-   name -> class -> (type)
 * 				-
 * 				- 	selection -> (int)
 * 				-	user -> UserInput -> (String)
 * 				-	ui -> (UserInput)
 * 				-	file -> (String) = File Name
 * 				-	questions -> QuestionBuilder -> (Hashmap)
 * 				-	d -> (DecisionMatrix)
 * 	
 * Authors: Daulton Baird
 */

public class Run {
		int selection;
		String user;
		UserInput ui;
		String file;
		HashMap<String, Question> questions;
		DecisionMatrix d;
		StackHandler sh;
		Stack<String> convo;
		Stack<String> fileStack;
		GUI gui;
		
		boolean input = false;

		public Run(GUI gui) {
			sh = new StackHandler();
			convo = sh.initConversationLog();
			fileStack = sh.initFileLog();
			this.gui = gui;
		}
/*
 * Method: initialize
 * Outputs:		-	Initial Tree
 * 
 * Description:	-	Creates Tree that asks the first Question
 * 				-	sets int selection to 0
 * 				-	while loop makes method loop until user gives correct input (Defensive Programming)
 * 				-	Prints the First Question
 * 				-	Assigns UserInput ui to new UserInput
 *  			-	Assigns String user to the user's input
 * 				-	Assigns selection based on the new Tree to be built
 * 				-	If input is invalid print that it is invalid				
 */
	
	public void initialize() throws IOException, InterruptedException{
		Tree start = new Tree(0);
		ArrayList<Question> initial = new ArrayList<>(start.getNextQuestion().values());
		setSelection(0);
		setUI(new UserInput());
		int counter = 0;
		
		while(true) {
			if(counter>0) {
				gui.setBotOutput(gui.getErrorMessage());
			}
			gui.setBotOutput(initial.get(0));
			counter++;
			convo.push("Chatbot: "+initial.get(0).getQuestion());
			Thread.sleep(4500); //give user 4.5 secs to respond
			input = gui.getInputBool(); //boolean storing whether user has inputted
			if(input) { //makes sure user input is not null
			ui.setInput(gui.getUserText());
			setUser(ui.getInput2(gui));
			convo.push("User: "+getUser());
			if(getUser().contains("internet")) {setSelection(1); initializeTree();break; }
			else if(getUser().contains("phone")) {setSelection(2); initializeTree(); break; }
			else {gui.setBotOutput(gui.getErrorMessage()); counter=0;}
			}
		}
	}
	
	/*
	 * Method: initializeTree
	 * Outputs:		-	"Internet" Tree or "Phone" Tree
	 * 
	 * Description:	-	Creates Tree based on input from initialize Method
	 * 				-	Sets File to the initial file of the Folder 
	 * 				-	Sets Hashmap questions via the nextQuestion method from the Tree
	 * 				-	Sets DecisionMatrix d to new DecisionMatrix			
	 */
	
	public void initializeTree() throws IOException {
		Tree bot = new Tree(getSelection());
		setFile("0-0.txt");
		fileStack.push(getFile());
		setQuestions(bot.getNextQuestion());
		setDecisionMatrix(new DecisionMatrix(this));
	}
	
	/*
	 * Method: runLoop
	 * Outputs:		-	Chatbot and User Conversation
	 * 
	 * Description:	-	while loop to continue until solution is found
	 * 				-	If the current file is the loop file, break out of the while loop (goes back to top of outer while loop)
	 * 				-	If the current file is the end file, print the "Thank you" string and then exit the program
	 * 				-	Otherwise Print current question
	 * 				-	Set String user to the user's input
	 *  			-	Decide the next file via DecisionMatrix d			
	 */
	
	/*This is my main loop
	 * loop-0.txt- is set as the file when the user has more problems (resets the loop)
	 * end-0.txt- is the final file to read when ending the program. It prints "Thank you for using the Bot."
	 * getInputBool()- returns whether the user has entered some text to the bot through the GUI
	 * Bot()- prints the question for the file, and logs in to my conversation log file
	 * I sleep the thread to give the user 4.5 seconds to enter a response (prevents question spamming)
	 * User()- reads user input by scanning the JTextField
	 * File()- Runs my Decision Matrix and decides next file to go to based on User's input.
	 * I then set UserText() to a blank string so the previous answer is not reused in the next iteration of the while loop.
	 */
	
	public void runLoop() throws IOException, InterruptedException {
		input = false;
		while (true) {
			if(getFile().equals("loop-0.txt")){
				initialize(); //restarts the process
				
			}else if(getFile().equals("end-0.txt")){
				gui.setBotOutput(getQuestions().get(getFile()).getQuestion()); //set bot to print question
				convo.push("Chatbot: "+getQuestions().get(getFile()).getQuestion()); //adds bot output to log
				sh.conToFile(); //sends chat log to file
				sh.pathToFile();//sends file log to file
				gui.exit(); //runs exit process
			}
			input = gui.getInputBool();//boolean storing whether user has inputted
			if(input) {//makes sure user input is not null
				Bot(); //prints bot response, write to log
				Thread.sleep(4500); //give user 4.5 seconds to answer
				User(); //get user input, write to log
				File(); //get next file (question) from decision matrix, add to filelog
				gui.setUserText(""); //set user to blank so that previous answere isn't reused
			}
		}
	}
	
	void Bot() {
		gui.setBotOutput(getQuestions().get(getFile())); //print question
		convo.push("Chatbot: "+getQuestions().get(getFile()).getQuestion()); //add bot output to convo log
	}
	private void User() {
		setUser(ui.getInput2(gui)); //read user input
		convo.push("User: "+getUser());//add to convo log
	}
	
	private void File() throws FileNotFoundException, IOException {
		setFile(d.Decision(gui, getUser(), getFile(), getSelection())); //decide next question
		fileStack.push(getFile()); //add to file log
	}
	
	
	//setters (only used locally)
	private void setSelection(int selection) {this.selection=selection;}
	private void setUser(String user) {this.user= user;}
	private void setUI(UserInput ui) {this.ui=ui;}	
	private void setFile(String file) {this.file= file;}
	private void setQuestions(HashMap<String, Question> questions) {this.questions=questions;}
	private void setDecisionMatrix(DecisionMatrix decisionMatrix) {this.d=decisionMatrix;}	
	
	//getters (only used locally)
	private int getSelection() {return this.selection;}
	private String getUser() {return this.user;}
	private String getFile() {return this.file;}
	private HashMap<String, Question> getQuestions(){return this.questions;}	
	
	
}
