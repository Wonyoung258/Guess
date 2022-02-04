// This is a starter file for QuestionsGame.
//
// You should delete this comment and replace it with your class
// header comment.

import java.util.*;

public class QuestionsGame1 {
   private Scanner console;
   private QuestionNode overallRoot;
    
   public QuestionsGame1() {
      overallRoot = new QuestionNode("computer");
      console = new Scanner(System.in);
   }
   
   public void write(PrintStream output) {
      write(output, overallRoot);
   }
   
   public void write(PrintStream output, QuestionNode root) {
      if (root != null) {
         if (root.left == null && root.right == null) {
            output.println("A:");
            output.println(root.data);
         } else {
            output.println("Q:");
            output.println(root.data);
         }
         write(output, root.left);
         write(output, root.right);
      }
   }
   
   public void read(Scanner input) {
      overallRoot = read(input);
   }
   
   private QuestionNode read(Scanner input) {
      String type = input.nextLine();
      String data = input.nextLine();
      QuestionNode root = new QuestionNode(data);
      
      if (type.equals("Q:")) {
         root.left = read(input);
         root.right = read(input);
      }
      return root;
   }
   
   public void askQuestions() {
      overallRoot = askQuestions(overallRoot);
   }
   
   private QuestionNode askQuestions(QuestionNode root) {
      if (root.left == null && root.right == null) { // in leaf node
         if (yesTo("Would your object happen to be " + root.data + "? (y/n)? ") {
            System.out.println("Great, I got it right!");
         } else {
            System.out.print("What is the name of your object? ");
            QuestionNode object = new QuestionNode(console.nextLine());
            System.out.println("Please give me a yes/no question that");
            System.out.println("distinguishes between your object");
            System.out.print("and mine--> ");
            String question = console.nextLine();
            System.out.print("And what is the answer for your object? (y/n)? ");
            String answer = console.nextLine();
            if (yesTo("And what is the naswer for your object? (y/n?)?")) {
               root = new QuestionNode(question, object, root.data);
            } else {
               root = new QuestionNode(question, root.data, object);
            }
         }
      } else { // not a leaf node
         if (yesTo(root.data)) {
            root.left = askQuestions(root.left);
         } else {
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

   private static class QuestionNode {
      // Your code here
      public String data;
      public QuestionNode left;
      public QuestionNode right;
        
      public QuestionNode(String data) {
         this(data, null, null);
      }
      
      public QuestionNode(String data, QuestionNode left,
                           QuestionNode right) {
         this.data = data;
         this.left = left;
         this.right = right;                    
      }
   }
}
