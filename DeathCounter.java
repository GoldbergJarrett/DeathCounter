import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DeathCounter extends Application{
    
    Scene mainScene;
    Stage mainStage;
    int paneWidth = 400;
    int paneHeight = 200;
    int counter;

    	
    public static void main(String[] args){
        launch(args);
    }

    public void start(Stage stage) throws Exception{
        mainStage = stage;
        mainStage.getIcons().add(new Image("icon.png"));
        Pane mainPane = new Pane();
        mainPane.setStyle("-fx-background-color: #000000;");
        
        File file = new File("countFile.txt");
        
        try {
        	
        	if(file.createNewFile()){
            	System.out.println("File created.");
            	FileWriter writer = new FileWriter(file);
            	writer.write("0");
            	writer.close();
            }
            
            else{
            	System.out.println("File exists.");
            }
        	
			Scanner input = new Scanner(file);
    		counter = input.nextInt();
    		input.close();
    		
    	}
        
        catch(Exception ex) {
    		ex.printStackTrace();
    	}
        
        Label countLabel = new Label("" + counter);
        countLabel.setTextFill(Color.RED);
        countLabel.setStyle("-fx-font-size: 4em;");
        countLabel.setLayoutX(190);
        countLabel.setLayoutY(50);
        
        Button add = new Button(" + ");
        add.setLayoutX(290);
        add.setLayoutY(150);
        add.setStyle("-fx-font-size: 2em; ");
        
        Button minus = new Button(" - ");
        minus.setLayoutX(90);
        minus.setLayoutY(150);
        minus.setStyle("-fx-font-size: 2em; ");
        
        mainPane.getChildren().addAll(add, minus, countLabel);
        mainScene = new Scene(mainPane, paneWidth, paneHeight);
        mainStage.setTitle("Death Counter");
        mainStage.setScene(mainScene);
        mainStage.setResizable(false);
        mainStage.show();
        
        add.setOnAction(e -> {
        	
        	try{
				Scanner input = new Scanner(file);
        		counter = input.nextInt();
            	counter++;
            	countLabel.setText("" + counter);
            	
            	PrintWriter output = new PrintWriter(file);
            	output.println(counter);
            	output.close();
            	input.close();
        	}
        	
        	catch(Exception ex){
        		ex.printStackTrace();
        	}
        });
        
        minus.setOnAction(e -> {
        	try {
				Scanner input = new Scanner(file);
        		counter = input.nextInt();
            	counter--;
            	countLabel.setText("" + counter);
            	
            	PrintWriter output = new PrintWriter(file);
            	output.println(counter);
            	output.close();
            	input.close();
        	}
        	
        	catch(Exception ex) {
        		ex.printStackTrace();
        	}
        });
    }
}