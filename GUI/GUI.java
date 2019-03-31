package GUI;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.BadLocationException;

import src.Question;
import src.Run;
import src.UserInput;

import java.awt.event.*;
import java.io.IOException;

public class GUI implements ActionListener {
	
	UserInput ui;
	
	JFrame frame;
	JPanel panel;
	JButton button;
	JTextArea MainDialogueArea;
	JTextField UserTextField;
	JScrollPane scroll;
	
	String userString;
	Question question;
	static boolean inputbool = false;
	
	
	public GUI(){
		frame = new JFrame();
		
		setFrame();
		setDialogue();
		
		scroll = new JScrollPane(MainDialogueArea);
		setUserText();
		setButton();
		
		frame.add(scroll);
		frame.add(MainDialogueArea);
		frame.add(UserTextField);
		frame.add(button);
		
		frame.setVisible(true);
		
		ui = new UserInput();
		
	}
	public void actionPerformed(ActionEvent e) {
		String user = UserTextField.getText();
		setUserText(user);
		ui.setInput(user);
		inputbool = true;
		MainDialogueArea.append("\nUser: "+user);
		UserTextField.setText(""); //clears text field
	}
	public void setUserText(String user) {
		userString = user;
	}
	public String getUserText() {
		return userString;
	}

	public static void main(String[] args) throws IOException, InterruptedException {
		GUI gui = new GUI();
		Run run = new Run(gui);
		gui.MainDialogueArea.append("ChatBot: Hello, I am Chatbot. I will be assisting you today.");
		run.initialize();
		run.initializeTree();
		setInputBool(false);
		run.runLoop();
		
	}
	public static void setInputBool(boolean b) {
		inputbool = b;
		
	}
	public void setBotOutput(Question q) {
		String bot = q.getQuestion();
		MainDialogueArea.append("\nChatBot: "+bot);
	}
	
	public boolean getInputBool() {
		return inputbool;
	}

	private void setFrame() {
		frame.setTitle("Chatbot.exe");
		
		frame.setSize(600,600);
		frame.setLayout(null);
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
	}
	private void setDialogue() {
		MainDialogueArea = new JTextArea();
		MainDialogueArea.setBounds(5,5,550,475);
		MainDialogueArea.setEditable(false);
	}
	private void setUserText() {
		UserTextField = new JTextField();
		UserTextField.setBounds(5,490,475,60);	
		UserTextField.addActionListener(this);
	}
	private void setButton() {
		button = new JButton("Send");
		button.setBounds(480,500,100,40);
		button.addActionListener(this);
	}
	
}
