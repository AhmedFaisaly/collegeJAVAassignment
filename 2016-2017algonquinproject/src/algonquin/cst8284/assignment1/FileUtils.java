package algonquin.cst8284.assignment1;

import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
/**
 * Author: TAO WANG;
 * course number:cst8284 300 2016F;
 * Professor: DAVID B HOUTMAN;
 * 
 */
public class FileUtils {

	private static String QuizFileAbsolutePathAndName = "";
	public final static boolean ON = true, OFF = false;

	public static File getFileHandle(Stage primaryStage) {
		FileChooser fc = new FileChooser();
		fc.setTitle("Open Quiz File");
		fc.getExtensionFilters().addAll(new ExtensionFilter("Quiz Files", "*.quiz"),
				new ExtensionFilter("All Files", "*.*"));
		File quizFile = fc.showOpenDialog(primaryStage);

		setFileName(quizFile);
		return (quizFile);
	}

	public static QA[] getQAArray(String absPath, int numObjects) throws IOException, ClassNotFoundException,FileNotFoundException{
		FileInputStream fis= new FileInputStream(absPath);
		ObjectInputStream ois=new ObjectInputStream(fis);
		QA[] qa=new QA[numObjects];
		for(int i=0;i<qa.length;i++){
			qa[i] = (QA)ois.readObject();}
		fis.close();
		ois.close();

		return qa;}
	// TODO: Open the file as a FileInputStream and load the first numObjects
	// into a
	// new Array of type QA. Then return an array of QA objects to the caller
	// }

	private static void setFileName(File f) {
		QuizFileAbsolutePathAndName = (FileExists(f)) ? f.getAbsolutePath() : "";
	}

	public static String getFileName() {
		return QuizFileAbsolutePathAndName;
	}

	public static String getFileName(File f) {
		return f.getAbsolutePath();
	}

	private static Boolean FileExists(File f) {
		return (f != null && f.exists() && f.canRead() && f.isFile());
	}

	public static void waitAndShow(boolean b) {
		while (b) {
		}
		; // loop until b is OFF
	}

}
