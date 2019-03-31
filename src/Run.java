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
	
	public void initialize(){
		Tree start = new Tree(0);
		ArrayList<Question> initial = new ArrayList<>(start.getNextQuestion().values());
		setSelection(0);
		gui.setBotOutput(initial.get(0));
		convo.push("Chatbot: "+initial.get(0).getQuestion());
		setUI(new UserInput());
		int loopcounter = 0;
		while(true) {
			input = gui.getInputBool();
			loopcounter++;
			if(loopcounter%11==0)
			System.out.println("Boolean is false");
			if(input) {
			ui.setInput(gui.getUserText());
			setUser(ui.getInput2());
			convo.push("User: "+getUser());
			if(getUser().contains("internet")) {setSelection(1); break; }
			else if(getUser().contains("phone")) {setSelection(2); break; }
			else {System.out.println("Entry invalid, try again");}
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
	
	public void initializeTree() throws FileNotFoundException {
		System.out.println("Made to initializetree");
		Tree bot = new Tree(getSelection());
		setFile("0-0.txt");
		fileStack.push(getFile());
		setQuestions(bot.getNextQuestion());
		setDecisionMatrix(new DecisionMatrix());
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
	
	public void runLoop() throws IOException {
		System.out.println("Made it to runLoop");
		input = false;
		int loopcounter = 0;
		while (true) {
			if(getFile().equals("loop-0.txt")){
				break;
			}else if(getFile().equals("end-0.txt")){
				getQuestions().get(getFile()).printQuestion();
				convo.push("Chatbot: "+getQuestions().get(getFile()).getQuestion());
				sh.conToFile();
				sh.pathToFile();
				System.exit(0);
			}
			//getQuestions().get(getFile()).printQuestion();
			gui.setBotOutput(getQuestions().get(getFile()));
			convo.push("Chatbot: "+getQuestions().get(getFile()).getQuestion());
			input = gui.getInputBool();
			loopcounter++;
			if(loopcounter%11==0)
			System.out.println("false");
				if(input) {
				System.out.println("true");
				setUser(ui.getInput2());
				convo.push("User: "+getUser());
				file = d.Decision(getUser(), getFile(), getSelection());
				fileStack.push(getFile());
				}
		}
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
