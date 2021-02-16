package fxLentopallotilastotyokalu;


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
public class LentopallotilastotyokaluMain extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            FXMLLoader ldr = new FXMLLoader(getClass().getResource("LentopallotilastotyokaluGUIView.fxml"));
            final Pane root = ldr.load();
            //final LentopallotilastotyokaluGUIController lentopallotilastotyokaluCtrl = (LentopallotilastotyokaluGUIController) ldr.getController();
            Scene scene = new Scene(root);
            scene.getStylesheets().add(getClass().getResource("lentopallotilastotyokalu.css").toExternalForm());
            primaryStage.setScene(scene);
            primaryStage.setTitle("Lentopallo tilastotyokalu");
            primaryStage.show();
        } catch(Exception e) {
            e.printStackTrace();
        }
        Application.Parameters params = getParameters(); 
        if ( params.getRaw().size() > 0 ) 
            LentopallotilastotyokaluGUIController.lueTiedosto(params.getRaw().get(0));  
        else
            if ( !LentopallotilastotyokaluGUIController.avaa() );
        
    }

    /**
     * @param args Ei käytössä
     */
    public static void main(String[] args) {
        launch(args);
    }
}