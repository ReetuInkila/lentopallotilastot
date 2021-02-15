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
public class OletkoVarmaMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("OletkoVarmaView.fxml"));
            final Pane root = ldr.load();
            //final OletkovarmaGUIController oletkovarmaCtrl = (OletkovarmaGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("lentopallotilastotyokalu.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Oletkovarma");
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