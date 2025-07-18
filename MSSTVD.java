import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.time.LocalTime;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.control.Separator;
import javafx.geometry.Pos;
import javafx.application.Platform;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.*;


public class MSSTVD extends Application
{
	Thread t;
	public void start(Stage stage)
	{
		Label hour = new Label("00");
		hour.setStyle("-fx-font-size :50; -fx-border-width:2px; -fx-border-color:black; -fx-background-color:#83ceec;");
		Label minute = new Label("00");
		minute.setStyle("-fx-font-size :50; -fx-border-width:2px; -fx-border-color:black; -fx-background-color:#83ceec;");
		Label seconds = new Label("00");
		seconds.setStyle("-fx-font-size :50; -fx-border-width:2px; -fx-border-color:black; -fx-background-color:#83ceec;");
		
		Label title = new Label("Welcome to the Digital Clock");
		title.setStyle("-fx-font-size :50; -fx-text-fill:darkblue");
		HBox hb1 = new HBox(title);
		hb1.setAlignment(Pos.CENTER);
		hb1.setStyle("-fx-background-color: lightblue;");
		
		HBox hb2 = new HBox();
		Separator sep1 = new Separator();
		sep1.setPrefWidth(40);
		Separator sep2 = new Separator();
		sep2.setPrefWidth(40);
		hb2.getChildren().addAll(hour,sep1,minute,sep2,seconds);
		hb2.setAlignment(Pos.CENTER);
		hb2.setStyle("-fx-background-color: lightblue;");
		
		Label hlabel = new Label("HOURS");
		Label mlabel = new Label("MINUTES");
		Label slabel = new Label("SECONDS");
		HBox hb3 = new HBox();
		Separator sep11 = new Separator();   //for give the space for each label
		sep11.setPrefWidth(40);
		Separator sep22 = new Separator();
		sep22.setPrefWidth(40);
		hb3.getChildren().addAll(hlabel,sep11,mlabel,sep22,slabel);
		hb3.setAlignment(Pos.CENTER);
		hb3.setStyle("-fx-background-color: lightblue;");
		
		VBox vb = new VBox();
		vb.getChildren().addAll(hb1,hb2,hb3);   //put all the hbox into vbox from top to bottom
		vb.setSpacing(50);
		vb.setStyle("-fx-background-color: lightblue;");
		
		Media media = new Media(new File("audio/clocksound.wav").toURI().toString());
        MediaPlayer player = new MediaPlayer(media);
		
		t = new Thread(()->
		{
			while(true)
			{
				try
			    {
				Thread.sleep(1000);
			    }
			    catch(Exception e)
			    {
				System.out.println("Thread error ??");
			    }
				
				LocalTime l = LocalTime.now();
				String h = String.valueOf(l.getHour());
				String m = String.valueOf(l.getMinute());
				String s = String.valueOf(l.getSecond());
				
				Platform.runLater(()->
				{
					hour.setText(h);
					minute.setText(m);
					seconds.setText(s);
					player.seek(javafx.util.Duration.ZERO);    //reset every time
                    player.play();                              //playagin the audio
				});
			}
		});
		t.setDaemon(true);
		t.start();
				
				
		
		Scene s = new Scene(vb,400,400);     //add it to scene
		stage.setScene(s);
		stage.show();
	}
	public static void main(String[]args)
	{
		launch(args);
	}
}