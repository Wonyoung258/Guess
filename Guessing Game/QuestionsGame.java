// Wonyoung Kim
// CSE 143
// Section AR
// Assignment 7: 20 Questions
import java.io.*;
import java.util.*;

// Implements a QuestionGame of yes/no using trees
public class QuestionsGame {
   private Scanner console;
   private QuestionNode overallRoot;
    
   // Constructs a new questiongame object. 
   // First leaf node is "computer"
   public QuestionsGame() {
      overallRoot = new QuestionNode("computer");
      console = new Scanner(System.in);
   }
   
   // Outputs the current tree into given output by calling the write helper method
   public void write(PrintStream output) {
      writeHelper(output, overallRoot);
   }
   
   // The format of the output is a pair of lines
   // First line has either Q: or A: to differentiate between questions and answers
   // Second line contains the question or the answer
   // Printed in pre-order
   // Recurses until all of the tree is output
   private void writeHelper(PrintStream output, QuestionNode root) {
      if (root != null) {
         if (root.left == null && root.right == null) {
            output.println("A:");
            output.println(root.data);
         } else {
            output.println("Q:");
            output.println(root.data);
         }
         writeHelper(output, root.left);
         writeHelper(output, root.right);
      }
   }
   
   // Calls the read helper method to read a tree
   // Replaces the current tree by reading tree from input scanner
   // Assumes the file is valid
   public void read(Scanner input) {
      overallRoot = readHelper(input);
   }
   
   // Returns the updated root tree
   // Scans the input for the type and data to recurse 
   // until the end
   private QuestionNode readHelper(Scanner input) {
      String type = input.nextLine();
      String data = input.nextLine();
      QuestionNode root = new QuestionNode(data);
      
      if (type.equals("Q:")) {
         root.left = readHelper(input);
         root.right = readHelper(input);
      }
      return root;
   }
   
   // Calls the askQuestions helper method to play the complete guessing game.
   public void askQuestions() {
      overallRoot = askQuestions(overallRoot);
   }
   
   // Returns the current root
   // Plays a complete guessing game using current tree
   // Asks yes/no questions until computer guesses right
   // if computer guesses wrong, the user updates what the object was,
   // a question to distinguish the object, and the answer for that question
   private QuestionNode askQuestions(QuestionNode root) {
      if (root.left == null && root.right == null) { // in leaf node
         if (yesTo("Would your object happen to be " + root.data + "?")) { // correctly guessed
            System.out.println("Great, I got it right!");
         } else {
            System.out.print("What is the name of your object? ");
            String answer = console.nextLine();
            QuestionNode object = new QuestionNode(answer);
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            String question = console.nextLine();
            
            if (yesTo("And what is the answer for your object?")) { // if yes to custom Q
               root = new QuestionNode(question, object, root);
            } else {    // if no to custom Q
               root = new QuestionNode(question, root, object);
            }
            
         }
      } else { // not a leaf node
         if (yesTo(root.data)) {    // if Yes to question
            root.left = askQuestions(root.left);
         } else {    // if no to question
            root.right = askQuestions(root.right);
         }
      }
      return root;
   }

   // post: asks the user a question, forcing an answer of "y" or "n";
   //       returns true if the answer was yes, returns false otherwise
   public boolean yesTo(String prompt) {
      System.out.print(prompt + " (y/n)? ");
      String response = console.nextLine().trim().toLowerCase();
      while (!response.equals("y") && !response.equals("n")) {
      System.out.println("Please answer y or n.");
      System.out.print(prompt + " (y/n)? ");
      response = console.nextLine().trim().toLowerCase();
      }
      return response.equals("y");
   }

   // Class for creating and implementing QuestionNode
   private static class QuestionNode {
      // Your code here
      public String data;
      public QuestionNode left;
      public QuestionNode right;
        
      // Creates a single QuestionNode without any children
      // Can be used to create leaf nodes, or nodes which contains the answer objects
      // for questiongame.
      public QuestionNode(String data) {
         this(data, null, null);
      }
      
      // Creates a QuestionNode with 2 children nodes
      // Used to contain the question and their pathways for yes/no
      // in questiongame.
      public QuestionNode(String data, QuestionNode left,
                           QuestionNode right) {
         this.data = data;
         this.left = left;
         this.right = right;                    
      }
   }
}
