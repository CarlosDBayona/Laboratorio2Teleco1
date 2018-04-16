package sample;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class Controller implements Initializable{
    @FXML
    Button input;
    @FXML
    TextField texto;
    @FXML
    BarChart graph;
    @FXML
    ListView listvalues;
    @FXML
    public void inputbuttonhanddler(){
        try {
            List<XYChart.Series> empty=new ArrayList<XYChart.Series>();
            listvalues.getItems().removeAll();
            graph.getData().setAll(empty);
            float m=0;
            if (! texto.getText().isEmpty()){
                m= Float.valueOf(texto.getText());
            }

            Process p = Runtime.getRuntime().exec("python bessel.py "+m);
            BufferedReader stdInput = new BufferedReader(new InputStreamReader(p.getInputStream()));
            System.out.println("Here is the standard output of the command:");
            String s=stdInput.readLine();
            s=s.replace("[","");
            s=s.replace("]","");
            s=s.replace(" ","");
            s=s.replace("'","");
            System.out.println(s);
            String valoresBessel[]=s.split(",");
            List<XYChart.Series> series=new ArrayList<XYChart.Series>();
            List<String> listview=new ArrayList<String>();
            for (int i=0;i<valoresBessel.length;i++){
                XYChart.Series serie = new XYChart.Series();
                serie.getData().add(new XYChart.Data("Frecuencia", Double.valueOf(valoresBessel[i])));
                serie.setName(String.valueOf(i));
                series.add(serie);
                listview.add("J_"+i+" "+valoresBessel[i]);
            }
            ObservableList<String> values=FXCollections.observableArrayList(listview);
            listvalues.getItems().setAll(values);
            graph.getData().addAll(series);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        graph.setTitle("Bessel Un Lado");
    }
}
