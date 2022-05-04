import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.JFrame;

/**
 * FileReader handles file input by loading the file via a dialog box and processing it through a Scanner
 * 
 * @author pott6366
 *
 */
public class FileReader {
	
	/** File Scanner for processing lines */
	private Scanner fileScanner;
	
	/**
	 * Creates a FileReader instance, and immediately attempts to load a file via a dialog box prompt. Throws an Error if a file is not selected or could not be read.
	 */
	public FileReader(){
		JFileChooser chooser = new JFileChooser();
		
		JFrame frame = new JFrame();
		
		frame.add(chooser);
		frame.setVisible(false);
		frame.setAlwaysOnTop(true);
        
        try {   
            if (chooser.showOpenDialog(frame) != JFileChooser.APPROVE_OPTION){
                frame.dispose();
                throw new Error("Input file not selected");
            }
            frame.dispose();
            File questionFile = chooser.getSelectedFile();
            fileScanner = new Scanner(questionFile);
          
        } catch (FileNotFoundException e) {
            System.err.println("Data file not found.");
        } catch (Exception e) {
            System.err.println("A mysterious error occurred.");
            e.printStackTrace(System.err);
        }
	}
	
	/**
	 * Checks if the input file has a next line.
	 * @return True if the file has next line, false otherwise.
	 */
	public boolean fileHasNextLine(){
		return this.fileScanner.hasNextLine();
	}
	
	
	/**
	 * Gets the next line of the input file
	 * @return The next line of the file
	 */
	public String getNextLineOfFile(){
		return this.fileScanner.nextLine();
	}
	
	
	/**
	 * Attempts to close scanner
	 */
	public void finalize(){
		try{
			this.fileScanner.close();
		}catch(Exception ex){
			 System.err.println("A mysterious error occurred on closing Scanner.");
	         ex.printStackTrace(System.err);
		}
	}
}
