/*Source: https://stackoverflow.com/questions/3522454/java-tree-data-structure
 * uploaded by jjnguy
 * changed to read Strings and work with QuestionBuilder class and Hashmap class
 */
package src;

import java.util.HashMap;
/*
 * Class: Tree
 * Description: -	Creates a tree that holds the Questions
 * 				-	from a Folder of Questions
 * 				-	Questions are put into a HashMap
 * 				-	Node class initializes "children" and "qb" variables to null
 * 				
 * 	Dependencies- 	UserInput.java
 * 				-	QuestionBuilder.java
 *  			-	Question.java
 * 				-	Hashmap.java
 *
 * 	Parameters	-   name -> class -> (type)
 * 				-
 * 				- 	treeNumber -> (int)
 * 				-	qb -> (QuestionBuilder)
 * 				-	children -> getBuiltQuestions() -> (Hashmap)
 *
 * Authors: Daulton Baird
 */
public class Tree{
	    private static Node<String> root;
	    /*
		 * Constructor
		 * Inputs: 		-	treeNumber (int)
		 *
		 * Description: -	Takes treeNumber in to decided which tree to be built
		 *
		 *
		 */
		public Tree(int treeNumber) { //specific constructor for the 3 main trees
		  	if(treeNumber==0) {
		  		root = new Node<String>("Start");
		  		root.children = getNextQuestion();
		  	}
		  	else if(treeNumber==1) {
		  		root = new Node<String>("Internet");
		  		root.children = getNextQuestion();
		  	}
		  	else if(treeNumber==2) {
		  		root = new Node<String>("Phone");
		  		root.children = getNextQuestion();
		  	}
		  	else if(treeNumber==3) {
		  		root = new Node<String>("TV");
		  		root.children = getNextQuestion();
		  	}
		}

		/*
		 * Method: getNextQuestion
		 * Outputs:		-	Hashmap of Question values with String keys
		 *
		 * Description:	-	Runs the getBuiltQuestions method from the QuestionBuilder class
		 *
		 */

	    public HashMap<String, Question> getNextQuestion() {
	    	return root.qb.getBuiltQuestions(); //returns ArrayList of built questions
	    }

	    /* Class: Node
	    	* Description:  -	Creates a Node (generic type)
	     	* 				-	Creates QuestionBuilder qb
	     	* 				-	Creates Hashmap with Question values and String key children
	     	*
	     	* Constructor:  -	Initializes new root node with Folder name as the arguement
	     	* 				-	Initializes qb to new QuestionBuilder(FolderName)
	     	* 				-	Runs the buildQuestions method from QuestionBuilder Class
	     	* 				-	Loads questions into children
	     	*
	     */

	    public static class Node<T> {
	        private QuestionBuilder qb;
	        @SuppressWarnings("unused")
			private  HashMap<String, Question> children;

	        public Node(String folderName) {
	        	qb = new QuestionBuilder(folderName);
		  		qb.buildQuestions();
	        }
	    }
}
