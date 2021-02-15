package lentopallotilastotyokalu;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author RInkila
 * @version 31.1.2021
 *
 */
public class EipoistettavaaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("EipoistettavaaGUIView.fxml"));
            final Pane root = ldr.load();
            //final EipoistettavaaGUIController eipoistettavaaCtrl = (EipoistettavaaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("lentopallotilastotyokalu.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Eipoistettavaa");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei k�yt�ss�
     */
    public static void main(String[] args) {
        launch(args);
    }
}