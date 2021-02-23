package fxLentopallotilastotyokalu;

import javafx.application.Application;
import javafx.stage.Stage;
import lentopallotilastotyokalu.Lentopallotilastotyokalu;
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
            final Pane root = (Pane)ldr.load();
            final LentopallotilastotyokaluGUIController tyokaluCtrl = (LentopallotilastotyokaluGUIController)ldr.getController();
            
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("lentopallotilastotyokalu.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Lentopallo tilastotyokalu");
           

            primaryStage.setOnCloseRequest((event) -> {
                    if ( !LentopallotilastotyokaluGUIController.voikoSulkea() ) event.consume();
                });
            
            String joukkueenNimi = JoukkueenValintaController.valitseNimi(null, "");
            System.out.println(joukkueenNimi);
            Lentopallotilastotyokalu lentopallotilastotyokalu = new Lentopallotilastotyokalu();  
            tyokaluCtrl.setLentopallotilastotyokalu(lentopallotilastotyokalu);
            primaryStage.show();


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