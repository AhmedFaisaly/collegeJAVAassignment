package algonquin.BI;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.distribution.NormalDistribution;

public class Lab3 extends Application{
	private NormalDistribution  ndNull ;
	private NormalDistribution  ndAlt;
	private double mean, median, deviation, wAve; 
	private ArrayList<Double> list;
	private ObservableList<Double> data;
	private ListView<Double> listV ;
	private Node linenull;
	private Node linealt;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Button go=new Button("Go!");
		go.setMaxWidth(800);

		TextField tMean=new TextField();
		TextField tMean1=new TextField();
		tMean.setPromptText("Null Hypothesis ");
		tMean1.setPromptText("Alternative Hypothesis");
		TextField tDeviation=new TextField();
		TextField tDeviation1=new TextField();
		tDeviation.setPromptText("Null Hypothesis ");
		tDeviation1.setPromptText("Alternative Hypothesis");
		TextField tN=new TextField();

		HBox.setMargin(tMean, new Insets(0,10,10,30));
		HBox.setMargin(tDeviation, new Insets(0,10,10,8));
		HBox.setMargin(tN, new Insets(0,10,10,50));

		HBox h1=new HBox();
		HBox.setHgrow(tMean, Priority.ALWAYS);
		HBox.setHgrow(tMean1, Priority.ALWAYS);
		HBox h2=new HBox();
		HBox.setHgrow(tDeviation, Priority.ALWAYS);
		HBox.setHgrow(tDeviation1, Priority.ALWAYS);
		HBox h3=new HBox();
		HBox.setHgrow(tN, Priority.ALWAYS);

		h1.getChildren().addAll(new Text("Enter Mean:"),tMean,tMean1);
		h2.getChildren().addAll(new Text("Enter Deviation:"),tDeviation,tDeviation1);
		h3.getChildren().addAll(new Text("Enter   N:"),tN);

		VBox.setMargin(h1, new Insets(0,10,5,10));
		VBox.setMargin(h2, new Insets(0,10,5,10));
		VBox.setMargin(h3, new Insets(0,10,5,10));

		VBox vb=new VBox();
		VBox.setMargin(go, new Insets(10,0,10,0));
		vb.getChildren().addAll(go,h1,h2,h3);

		go.setOnAction(e -> {


			ndNull=new NormalDistribution(Double.parseDouble(tMean.getText()),Double.parseDouble(tDeviation.getText()));
			ndAlt=new NormalDistribution(Double.parseDouble(tMean1.getText()),Double.parseDouble(tDeviation1.getText()));
			TextField tf = new TextField();
			double pValue=ndNull.cumulativeProbability(ndAlt.getMean());
			tf.setText(String.format("Mean1: %f  Sigma1: %f  Mean2:%f  p-value:%f" ,ndNull.getMean(), ndNull.getStandardDeviation(), ndAlt.getMean(),pValue));

			final NumberAxis xAxis = new NumberAxis();
			final NumberAxis yAxis = new NumberAxis();
			xAxis.setLabel("Number of Month");
			//creating the chart
			final LineChart<Number,Number> lineChart = 
					new LineChart<Number,Number>(xAxis,yAxis);

			lineChart.setTitle("Deviation of 2 data sets");

			XYChart.Series series = new XYChart.Series();
			series.setName("Set1");

			double lower = ndNull.getMean() - (3* ndNull.getStandardDeviation());
			double upper = ndNull.getMean() + (3* ndNull.getStandardDeviation());
			for(double i = lower;	 i < upper;	i += (upper - lower) / 30 )
			{    series.getData().add(new XYChart.Data(i, ndNull.density(i)));      }


			XYChart.Series series1 = new XYChart.Series();
			series1.setName("Set2");
			double lower1 = ndAlt.getMean() - (3* ndAlt.getStandardDeviation());
			double upper1 = ndAlt.getMean() + (3* ndAlt.getStandardDeviation());
			for(double i = lower1;	 i < upper1;	i += (upper1 - lower1) / 30 )
			{    series1.getData().add(new XYChart.Data(i, ndAlt.density(i)));   
			     System.out.print(ndAlt.density(i));
			}

			lineChart.getData().addAll(series,series1);
			
			 if(pValue<0.025 || pValue>0.975){

				linenull = series.getNode().lookup(".chart-series-line");        
				linenull.setStyle("-fx-stroke: #FF00FFB0;-fx-stroke-width: 2px;");
				linealt = series1.getNode().lookup(".chart-series-line");       
				linealt.setStyle("-fx-stroke: #00FF00FF;-fx-stroke-width: 10px;");

			}
			else{

				linenull = series.getNode().lookup(".chart-series-line");       
				linenull.setStyle("-fx-stroke: #0000FFFF;-fx-stroke-width: 10px;");
				linealt= series1.getNode().lookup(".chart-series-line");        
				linealt.setStyle("-fx-stroke: #FF0000B0;-fx-stroke-width: 2px;"); 
			}

			Stage newStage = new Stage();
			VBox vb2=new VBox();
			vb2.getChildren().addAll(tf,lineChart);

			Scene newScene = new Scene(vb2, 800,500);
			newStage.setScene(newScene);
			newStage.show(); 


		});

		Scene scene = new Scene(vb, 800, 200);
		primaryStage.setTitle("calculation");
		primaryStage.setScene(scene);
		primaryStage.show();

	}



	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Application.launch(args);
	}

	public double getMean(List<Double> list) {
		double sum = 0.0;
		for(double a : list)
			sum += a;
		mean=sum/list.size();
		return mean;
	}



	public double getMedian(List<Double> list) {

		if (list.size() % 2 == 0) 
		{
			median= (list.get((list.size() / 2) - 1 )+ list.get(list.size() / 2)) / 2.0;
		} 
		else{
			median=list.get(list.size()/2);
		}
		return median;
	}

	public  double getVariance(List<Double> list)
	{
		double mean = getMean(list);
		double temp = 0;
		for(double a :list)
			temp += (a-mean)*(a-mean);
		return temp/list.size();
	}
	public double getDeviation(List<Double> list) {
		deviation=Math.sqrt(getVariance(list));
		return deviation;
	}

	public double getwAve(List<Double> list) {
		double sum=0.0;
		for(int i=0,n=10; i<10;i++,n--){
			sum+=n*list.get(i);
		}
		wAve=sum/55;
		return wAve;
	}


}



