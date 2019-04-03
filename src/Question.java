package src;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * Class: Question
 * Description: -	Body that contains info from individual question file
 * 				-	Contains some variables and methods not currently used 
 * 					in program, but could be useful later on
 * Authors: Robin MacDonald
 */
public class Question {
	public static int questionID = 0;			//Currently unused, but may be useful for ID purposes
	
	private ArrayList<Integer> branchID;		//Acts as path for sorting questions
	private int questionPriority;				//Used in conjunction with branchID to prioritize questions with same branchID
	private String question;					//Question to be asked
	private int inputRange;						//Number of possible inputs (number of children)
	private ArrayList<String> answers;			//ArrayList of possible answers to Question
	//private ArrayList<Question> parents;		//Currently unused
	private ArrayList<Question> children;		//Child nodes
	
	/*
	 * Constructor
	 * Description: -	Initializes branchID, answers, parents, and children ArrayLists
	 * 				-	Increments questionID
	 */
	public Question() {	
		this.branchID = new ArrayList<>();
		this.answers = new ArrayList<>();
		//this.parents = new ArrayList<>();
		this.children = new ArrayList<>(5);
		
		questionID++;
	}

	/*
	 * Setter Methods
	 * All currently used
	 */
	public void setQuestionPriority(int questionPriority) {this.questionPriority = questionPriority;}
	public void setQuestion(String question) {this.question = question;}
	public void setInputRange(int inputRange) {this.inputRange = inputRange;}
	
	/*
	 * Getter Methods
	 * Some unused
	 */
	public ArrayList<Integer> getBranchID() {return this.branchID;}
	public int getQuestionPriority() {return this.questionPriority;}
	public String getQuestion() {return this.question;}
	public int getInputRange() {return this.inputRange;}
	public ArrayList<String> getAnswers() {return this.answers;}
	//public ArrayList<Question> getParent() {return this.parents;}
	public ArrayList<Question> getChildren() {return this.children;}
	public Question getChild(int pos) {return this.children.get(pos);}
	
	/*
	 * Specific ArrayList Modification Methods
	 * Some unused, but could be useful
	 */	
	//BranchID
	public void addBranch(int id) {this.branchID.add(id);}
	public void removeBranch(int pos) {this.branchID.remove(pos);}
	public void replaceBranchID(int pos, int newBranchID) {this.branchID.set(pos, newBranchID);}
	
	//Answers
	public void addAnswer(String ans) {this.answers.add(ans);}
	public void setAnswer(String ans, int pos) {this.answers.set(pos, ans);}
	public void removeAnswer(int pos) {this.answers.remove(pos);}
	
	//Parents - Currently unused
	/*
	public void addParent(Question parent) {this.parents.add(parent);}
	public void setParent(int pos, Question parent) {this.parents.set(pos, parent);}
	public void removeParent(Question parent) {this.parents.remove(parent);}
	public void trimParents() {this.parents.trimToSize();}
	public void setRoot() {this.parents = null;}
	*/
	
	//Children
	public void addChild(Question child) {this.children.add(child);}
	public void setChild(int pos, Question child) {this.children.set(pos, child);}
	public void removeChild(Question child) {this.children.remove(child);}
	public void trimChildren() {this.children.trimToSize();}
	public void setEnd() {this.children = null;}
	
	/*
	 * Method: equals
	 * Input:		-	Question to be compared
	 * Output:		-	True if equal, false if not equal
	 * Description: -	Compares two questions to see if they are equal
	 * 				-	Used for unit testing
	 */
	public boolean equals(Question q) {
		boolean[] equalityArr = new boolean[5];
		
		Arrays.fill(equalityArr, false);
		
		if(q.getBranchID().toString().equals(this.branchID.toString()))
			equalityArr[0] = true;
		
		if(q.getQuestionPriority() == this.questionPriority)
			equalityArr[1] = true;
		
		if(q.getQuestion().equals(this.question))
			equalityArr[2] = true;
		
		if(q.getInputRange() == this.inputRange)
			equalityArr[3] = true;
		
		if(q.getAnswers().toString().equals(this.answers.toString()))
			equalityArr[4] = true;
		
		for(int i=0; i<equalityArr.length; i++) {
			if(equalityArr[i] == false)
				return false;
		}
		
		return true;
	}
	
	/*
	 * Method: printQuestion
	 * Description: -	Currently used for testing purposes
	 * 				-	Prints certain info regarding specific Question object
	 */
	public String printQuestion() {
		//System.out.println(this.question);
		return this.question;
	}
	
}
