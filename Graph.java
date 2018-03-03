import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
public class Graph extends Application {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        Application.launch(args);
	}
	public void start(Stage stage_1) throws Exception {
		stage_1.setTitle("Dining Philosophers");
		NumberAxis x = new NumberAxis();
        x.setLabel("Number of Philosophers");
        NumberAxis y = new NumberAxis();
        y.setLabel("Time (s)");
        LineChart chart = new LineChart(x, y);
        XYChart.Series philosopher_data1 = new XYChart.Series();
        XYChart.Series philosopher_data2 = new XYChart.Series<>();
        
        philosopher_data1.setName("Mac OS High Sierra");
        philosopher_data2.setName("PC Windows 10");
        
        philosopher_data1.getData().add(new XYChart.Data( 2, 0.4786));
        philosopher_data1.getData().add(new XYChart.Data( 3, 0.5644));
        philosopher_data1.getData().add(new XYChart.Data(4, 0.5716));
        philosopher_data1.getData().add(new XYChart.Data(5, 0.7111));
        philosopher_data1.getData().add(new XYChart.Data(6, 0.8519));
        philosopher_data1.getData().add(new XYChart.Data(7, 0.9685));
        philosopher_data1.getData().add(new XYChart.Data(8, 1.0413));
        
        philosopher_data2.getData().add(new XYChart.Data(2, 0.39194));
        philosopher_data2.getData().add(new XYChart.Data(3, 0.5893));
        philosopher_data2.getData().add(new XYChart.Data(4, 0.60124));
        philosopher_data2.getData().add(new XYChart.Data(5, 0.61304));
        philosopher_data2.getData().add(new XYChart.Data(6, 0.72449));
        philosopher_data2.getData().add(new XYChart.Data(7, 0.83201));
        philosopher_data2.getData().add(new XYChart.Data(8, 0.91605));
        
        chart.getData().add(philosopher_data1);
        chart.getData().add(philosopher_data2);
        
        VBox box = new VBox(chart);
        Scene scene_philosophers = new Scene(box, 500, 300);
        stage_1.setScene(scene_philosophers);
        stage_1.setHeight(600);
        stage_1.setWidth(700);
        stage_1.show();
	}
}
