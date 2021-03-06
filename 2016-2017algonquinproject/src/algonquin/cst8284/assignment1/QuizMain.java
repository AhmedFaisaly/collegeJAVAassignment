package algonquin.cst8284.assignment1;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Author: TAO WANG; course number:cst8284 300 2016F; Professor: DAVID B
 * HOUTMAN; 
 * References: 
 * 1.Wendi Jollymore (2015).Alert Dialogs.[webpage].retrieved data from
  http://www-acad.sheridanc.on.ca/~jollymor/prog24178/javafx7.html
 *2. Timosy Cope(2015).JavaFX open new window.[webpage].retrieved data from
   http://stackoverflow.com/questions/15041760/javafx-open-new-window
 *3.Jakob Jenkov(2015).JavaFX GridPane.[webpage].retrieved data from 
 *http://tutorials.jenkov.com/javafx/gridpane.html
 *
 */
public class QuizMain extends Application {

	QA[] QAList;
	public final int MAX_QA_SIZE = 5;
	private static int countCorrect;
	private static int iCount = 0;

	@Override
	public void start(Stage primaryStage) {

		primaryStage.setTitle("Quiz Master");
		Scene scene = new Scene(getSplashPane("Welcome to QuizMaster"), 1024, 512);
		primaryStage.setScene(scene);
		primaryStage.show();

		File f = FileUtils.getFileHandle(primaryStage);
		String str = FileUtils.getFileName();
		//  http://www-acad.sheridanc.on.ca/~jollymor/prog24178/javafx7.html

		if (str == "") {
			Alert alert = new Alert(AlertType.CONFIRMATION);
			alert.setTitle("No File Name Enterd");
			alert.setContentText("No file name entered,Do you wish exit(ok) or continue(cancel)");
			ButtonType ok = new ButtonType("ok");
			ButtonType cancel = new ButtonType("cancel");
			alert.getButtonTypes().setAll(ok, cancel);
			Optional<ButtonType> result = alert.showAndWait();
			if (result.get() == ok) {
				Platform.exit();
				System.exit(0);
			} else {
				start(primaryStage);
			}
		} else {
			try {
				QAList = FileUtils.getQAArray(FileUtils.getFileName(f), MAX_QA_SIZE);
			} catch (ClassNotFoundException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			displayQA(0, QAList, primaryStage);
		}
	}

	private void displayQA(int i, QA[] QAList, Stage stage) {

		Scene scene = new Scene(getCurrentQAPane(QAList[i]), 1200, 400);
		stage.setScene(scene);
	}

	private Pane getQAResults(QA[] qAList2) {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder("Your quiz result shown below: \n");
		int i = 1;
		for (QA qa : qAList2) {
			sb.append("Question number " + (i++) + " : " + (qa.isCorrect() ? "Correct" : "Wrong") + "\n");

		}
		sb.append("You made  " + countCorrect + "  correct answers and " + (MAX_QA_SIZE - countCorrect)
				+ " incorrect answers.\n" + "Your marks is  " + countCorrect + "/" + MAX_QA_SIZE);

		GridPane pane = new GridPane();
		pane.setPrefSize(300, 500);
		pane.setStyle("-fx-background-color: #ffffff;" + "-fx-border-style:solid inside;" + "-fx-border-width:3;");
		pane.setHgap(10);
		pane.setVgap(10);
		Text text = new Text(sb.toString());
		text.setStyle("-fx-font: 25px Tahoma; -fx-stroke: black; -fx-stroke-width: 1;");

		Pane tPane = new Pane();
		tPane.getChildren().add(text);
		pane.add(tPane, 7, 7, 2, 6);
		return pane;
	}

	private Pane getCurrentQAPane(QA currentQA) {
		// TODO Auto-generated method stub
		
		GridPane pane = new GridPane();

		pane.setStyle("-fx-color:purple;");
		HBox questionPane = new HBox();
		questionPane.setAlignment(Pos.CENTER_LEFT);

		questionPane.getChildren().add(new Text(currentQA.getQuestion()));
		questionPane.setPrefSize(600, 50);
		questionPane.setStyle("-fx-font-size:22");
		pane.setHgap(2);
		pane.setVgap(2);

		Button bNext = new Button("next question");
		Button bCheck = new Button("check answer");
		bNext.setPadding(new Insets(10, 10, 10, 10));

		AnswerPane ap = new AnswerPane(currentQA.getAnswers());
		bNext.setDisable(true);
		VBox vb = ap.getAnswerPane();
		GridPane gPane = new GridPane();
		gPane.setHgap(5);
		gPane.setVgap(5);
		gPane.setPrefSize(1000, 300);
		gPane.setStyle("-fx-background-color: #ffffee;" + "-fx-border-style:solid inside;" + "-fx-border-width:3;"
				+ "-fx-font-size:22;");

		gPane.add(vb, 2, 10, 2, 2);
		gPane.add(bCheck, 4, 12, 2, 2);
		GridPane.setMargin(bCheck, new Insets(10, 10, 10, 10));

		pane.add(questionPane, 2, 10, 2, 2);
		pane.add(gPane, 2, 12, 2, 3);
		pane.add(bNext, 5, 15, 5, 5);
		GridPane.setMargin(bNext, new Insets(10, 0, 10, 10));

		bCheck.setOnAction((e) -> {
			if (currentQA.getAnswers().length == (ap.getButtonSelected() - 1)) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("No Answer selected");
				alert.setContentText("You must choose one answer before you click [check answer] button");
				@SuppressWarnings("unused")
				Optional<ButtonType> result = alert.showAndWait();
			} else {
				Text text = new Text("Correct answer is choice number :  " + currentQA.getCorrectAnswerNumber());
				gPane.add(text, 2, 12);
				vb.setDisable(true);
				bCheck.setDisable(true);
				Text text1 = new Text("Explanation : " + currentQA.getExplanation());
				text1.setFont(Font.font(16));
				GridPane.setMargin(text1, new Insets(10, 15, 10, 10));
				pane.add(text1, 2, 15, 1, 1);
				bNext.setDisable(false);
				if (currentQA.getCorrectAnswerNumber() == ap.getButtonSelected()) {
					currentQA.setResult(true);
					countCorrect++;
				}
			}
		});
		bNext.setOnAction(e -> {
			
			iCount++;
			if (iCount < QAList.length) {
				Stage stage = new Stage();
				stage = (Stage) bNext.getScene().getWindow();
				displayQA(iCount, QAList, stage);
			} else {
				Stage stage = new Stage();
				stage = (Stage) bNext.getScene().getWindow();
				stage.setScene(new Scene(getQAResults(QAList), 800, 500));
			}
		});

		return pane;
	}

	private Pane getSplashPane(String str) {
		Text text = new Text(str);
		text.setStyle("-fx-font: 40px Tahoma; -fx-stroke: black; -fx-stroke-width: 1;");
		BorderPane splashPane = new BorderPane();
		splashPane.setCenter(text);
		return splashPane;
	}

	public static void main(String[] args) {
		Application.launch(args);
	}
}