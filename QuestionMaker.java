import java.util.ArrayList;

/**
 * QuestionMaker loads questions from a file, and sorts them into the appropriate classes with the appropriate values
 * @author pott6366
 *
 */
public class QuestionMaker {
	
	
	/**
	 * Reads study questions from a file reader and parses the results into the different types of questions.
	 * @return Questions
	 */
	public ArrayList<Question> getQuestions(){
		
		ArrayList<Question> questions;
		questions = new ArrayList<Question>();
		
		FileReader questionFile;
		questionFile = new FileReader();
		
		String fileLine;
		String[] lineSplit;

		// For each line within the file
		while(questionFile.fileHasNextLine()) {
			fileLine = questionFile.getNextLineOfFile();

			// Split the line by semicolons
			lineSplit = fileLine.split(";");

			// As long as the line contains enough fields to populate a question, parse it by the type field.
			if(lineSplit.length >= 4) {
				switch(lineSplit[0].toLowerCase()) {
					case "tf":
						questions.add(parseQuestionTF(lineSplit));
						break;
					case "mc":
						questions.add(parseQuestionMC(lineSplit));
						break;
					case "fb":
						questions.add(parseQuestionFB(lineSplit));
						break;
					default:
						System.out.println("Couldn't read question type field. Skipping...");
				}
			}
		}
		
		return questions;
	}
	
	/**
	 * Parses True/False questions from a split text line
	 * @param line Text line to parse
	 * @return True/False question object
	 */
	private QuestionTF parseQuestionTF(String[] line) {
		return new QuestionTF("True/False: " + line[1], line[2], Integer.parseInt(line[3]));
	}
	
	/**
	 * Parses Multiple Choice questions from a split text line
	 * @param line Text line to parse
	 * @return Multiple-choice question
	 */
	private QuestionMC parseQuestionMC(String[] line) {
		String question;
		int numAnswers;
		char[] alphabet;
		
		alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
		question = line[1];
		numAnswers = Integer.parseInt(line[2]);
		
		for(int counter = 0; counter < numAnswers; counter++) {
			question = question + "\n" + alphabet[counter] + ") " + line[counter + 3];
		}
		
		return new QuestionMC("Multiple Choice: " + question,line[line.length - 2], Integer.parseInt(line[line.length - 1]));
	}
	
	/**
	 * Parses Fill-in-the-blank questions from a split text line
	 * @param line Text line to parse
	 * @return Fill-in-the-blank question
	 */
	private QuestionFB parseQuestionFB(String[] line) {
		return new QuestionFB("Fill in the blank: " + line[1], line[2], Integer.parseInt(line[3]));
	}
}
