import java.io.IOException;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Controller {
	
	@FXML
	private BorderPane bpane;
	@FXML
	private MenuItem savetofile;
	@FXML
	private MenuItem about;
	@FXML
	private MenuItem exit;
	@FXML
	protected ChoiceBox<String> box1 = new ChoiceBox<>();
	@FXML
	protected ChoiceBox<String> box2 = new ChoiceBox<>();
	@FXML
	private TextField wynik;
	
	private Task<String> observer;
	
	public static Controller self;
	
	@FXML
	private void initialize() {
		
		box1.getItems().add("do 500");
		box1.getItems().add("powyzej 500 do 1000");
		box1.getItems().add("powyzej 1000 do 2000");
		box1.getItems().add("powyzej 2000 do 3000");
		box1.getItems().add("powyzej 3000 do 4000");
		box1.getItems().add("powyzej 4000 do 5000");
		box1.getItems().add("powyzej 5000");
		
		box2.getItems().add("do 200");
		box2.getItems().add("powyzej 200 do 500");
		box2.getItems().add("powyzej 500 do 1000");
		box2.getItems().add("powyzej 1000 do 2000");
		box2.getItems().add("powyzej 2000 do 4000");
		box2.getItems().add("powyzej 4000");
		
		observer = new Task<String>() {
			@Override
			protected String call() throws Exception {
				while (!isCancelled()) {
					String ob = "";
					updateMessage(showWynik());
					Thread.sleep(100);					
					if (ob != "")	
						return null;
				}
				return null;
			}
		};
		
		wynik.textProperty().bind(observer.messageProperty());
		
		Thread thread = new Thread(observer);
		thread.setDaemon(true);
		thread.start();		
		
		self = this;
			
	}
	
	public String showWynik() {		
		
	int spindicator = 0;
	int qdindicator = 0;
		
	String[][] table = {
			{"", "do 500", "powyzej 500 do 1000", "powyzej 1000 do 2000", "powyzej 2000 do 3000", "powyzej 3000 do 4000", "powyzej 4000 do 5000", "powyzej 5000"},
			{"do 200", "10", "10", "10", "10", "15", "15", "20"},
			{"powyzej 200 do 500", "10", "10", "10", "20", "20", "30", "30"},
			{"powyzej 500 do 1000", "10", "10", "20", "20", "30", "30", "40"},
			{"powyzej 1000 do 2000", "10", "20", "20", "30", "30", "40", "40"},
			{"powyzej 2000 do 4000", "20", "20", "30", "30", "40", "40", "50"},
			{"powyzej 4000", "20", "30", "30", "40", "40", "50", "60"}
	};
		
	for (int i=0; i < 8; i++) {
		if (box1.getValue() == table[0][i])
			spindicator = i;			
	}
	
	for (int i=0; i < 7; i++) {
		if (box2.getValue() == table[i][0])
			qdindicator = i;
	}
	
	return table[qdindicator][spindicator];		
	
	}
			
	public void aboutAction() {
		createAbout();
	}
	
	private void createAbout() {
		Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("About.fxml"));
            Stage stage = new Stage();
            stage.setTitle("About");
            stage.setScene(new Scene(root, 300, 400));
            stage.show();           
            }
        catch (IOException e) {
            e.printStackTrace();
        }							
	}  
	
	public void saveAction() {
		createSave();
	}
	
	public void createSave() {
		Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("Save.fxml"));            
            Stage stage = new Stage();
            stage.setTitle("Zapisz do pliku");
            stage.setScene(new Scene(root, 300, 150));
            stage.show();           
            }
        catch (IOException e) {
            e.printStackTrace();
        }		
	}
		
	public void closeAction() {
		Platform.exit();
	}	
	
}
