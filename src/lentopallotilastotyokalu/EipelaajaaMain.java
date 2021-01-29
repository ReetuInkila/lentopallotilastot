package lentopallotilastotyokalu;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * @author RInkila
 * @version 29.1.2021
 *
 */
public class EipelaajaaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("EipelaajaaGUIView.fxml"));
            final Pane root = ldr.load();
            //final EipelaajaaGUIController eipelaajaaCtrl = (EipelaajaaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("eipelaajaa.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Eipelaajaa");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}