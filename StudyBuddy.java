import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * StudyBuddy takes a list of questions and quizzes the user. It also handles any logic associated with input handling and text displaying.
 * 
 * @author pott6366
 *
 */
public class StudyBuddy {
	
	/** ArrayList of study questions loaded from file */
	
	private ArrayList<Question> loadedQuestions;
	
	/**
	 * Starts the study program.
	 */
	public void study() {
		QuestionMaker questionMaker;
		Scanner scan;
		int questionsToAnswer;
		String rawInput;
		ArrayList<Question> activeQuestions;
		Random rand;
		int totalPoints;
		int correctCount;
		int attemptCount;
		
		scan = new Scanner(System.in);
		rand = new Random();
		questionMaker = new QuestionMaker();
		activeQuestions = new ArrayList<Question>();
		totalPoints = 0;
		correctCount = 0;
		attemptCount = 0;
		
		//Load question file
		
		System.out.println("Welcome to StudyBuddy! Press enter when you are ready to select the file holding your study questions.");
	
		scan.nextLine();
		
		try {
			loadedQuestions = questionMaker.getQuestions();
		} catch (Error err) {
			System.out.println("An input file was not selected. Have a nice day!");
			scan.close();
			return;
		}
		
		// Get number of questions to answer
		
		questionsToAnswer = -1;
		while(questionsToAnswer < 0) {
			
			System.out.println("How many questions would you like to answer?");
			rawInput = scan.nextLine();
			
			if(!isNum(rawInput)) {
				questionsToAnswer = -1;
			} else {
				questionsToAnswer = Integer.parseInt(rawInput);
			}
			
			//Make sure input is not > # of loaded questions
			
			if(questionsToAnswer > loadedQuestions.size() || questionsToAnswer < 0) {
				System.out.printf("Please input a valid number of questions. There are %d loaded questions.%n", loadedQuestions.size());
				questionsToAnswer = -1;
			}
		}
		
		// Load random questions
		
		int questionIndex;
		while(activeQuestions.size() < questionsToAnswer) {
			questionIndex = rand.nextInt(questionsToAnswer);
			if(!activeQuestions.contains(loadedQuestions.get(questionIndex))) {
				activeQuestions.add(loadedQuestions.get(questionIndex));
			}
		}
		
		// Start asking questions
		
		while(activeQuestions.size() > 0) {
			Question curQuestion = activeQuestions.get(0);
			System.out.printf("Points: %d%n", curQuestion.getPoints());
			System.out.printf("%s%n", curQuestion.getQuestion());
			rawInput = scan.nextLine().toLowerCase();
			
			if(rawInput.equals("delay")) {
				activeQuestions.add(activeQuestions.remove(0));
				System.out.println("Ok. I will ask that one later.\n");
			} else if(rawInput.equals("pass")) {
				activeQuestions.remove(0);
				System.out.println("Ok, Let's skip that one.\n");
			} else {
				if(curQuestion.isCorrect(rawInput)) {
					System.out.printf("Correct! You get %d points!%n%n", curQuestion.getPoints());
					totalPoints = totalPoints + curQuestion.getPoints();
					correctCount = correctCount + 1;
					activeQuestions.remove(0);
				} else {
					System.out.printf("Incorrect, the answer was %s. You lose %d points.%n%n", curQuestion.getCorrectAns(), curQuestion.getPoints());
					totalPoints = totalPoints - curQuestion.getPoints();
					activeQuestions.remove(0);
				}
				attemptCount = attemptCount + 1;
			}
		}
		
		System.out.printf("Of the %d questions attempted, you got %d correct.%n", attemptCount, correctCount);
		System.out.printf("You earned a total of %d points.%n", totalPoints);
		
		// Close scanner
		
		scan.close();
		
	}
	
	/**
	 * Checks if all the characters in the input string are all numerical.
	 * @param input String to check
	 * @return True if all characters are numerical and string is less than 10 digits.
	 */
	private boolean isNum(String input) {
		if(input.length() <= 0 || input.length() > 9) {
			return false;
		}
		for(int index = 0; index < input.length(); index++) {
			if(input.charAt(index) < '0' || input.charAt(index) > '9') {
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Main method to launch StudyBuddy
	 */
	public static void main(String[] args) {
		StudyBuddy buddy = new StudyBuddy();
		buddy.study();
	}
}
