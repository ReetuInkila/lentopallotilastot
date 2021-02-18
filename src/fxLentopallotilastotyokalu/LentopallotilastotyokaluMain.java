package fxLentopallotilastotyokalu;


import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.fxml.FXMLLoader;


/**
 * Pääohjelma Lentopallotilastotyokalu-ohjelman käyttämiseksi
 * @author RInkila
 * @version 29.1.2021
 *
 */
public class LentopallotilastotyokaluMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("LentopallotilastotyokaluGUIView.fxml"));
            final Pane root = ldr.load();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("lentopallotilastotyokalu.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Lentopallo tilastotyokalu");
            primaryStage.show();

            primaryStage.setOnCloseRequest((event) -> {
                if ( !LentopallotilastotyokaluGUIController.voikoSulkea() ) event.consume();
            });
            LentopallotilastotyokaluGUIController.avaa();

        } catch(Exception e) {
            e.printStackTrace();
        }        
    }

    /**Käynnistetään käyttöliittymä
     * @param args Komentorivin parametrit ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}