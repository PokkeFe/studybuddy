/**
 * Question Interface for declaring the methods of any class that implements Question
 * 
 * @author pott6366
 *
 */
public interface Question {
	
	/**
	 * Gets the question as a String
	 * @return Question text
	 */
	String getQuestion();
	
	/**
	 * Checks if a provided answer is correct
	 * @param ans Answer to check
	 * @return The correctness of the answer
	 */
	boolean isCorrect(String ans);
	
	/**
	 * Gets the correct answer for the question
	 * @return
	 */
	String getCorrectAns();
	
	/**
	 * Gets the point value of the question
	 * @return Points
	 */
	int getPoints();
}
