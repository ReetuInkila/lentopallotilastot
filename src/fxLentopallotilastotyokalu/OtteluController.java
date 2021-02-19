package fxLentopallotilastotyokalu;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

/**
 * Luokka ottelu ikkunan toimintojen toteuttamiseksi
 * @author RInkila
 * @version 29.1.2021
 *
 */
public class OtteluController implements ModalControllerInterface<String>  {

    @FXML private TextField textVastustaja;
    
    @FXML void HandlePoistaViimeisin() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko viimeisin tilasto: Sauli Sinkkonen, Ässä", "Kyllä", "Ei"); 
        if (vastaus == true ) Dialogs.showMessageDialog("Ei osata vielä poistaa tilastoja");
        // TODO: korvaa tilaston poistamisella
    }

    @FXML void handleTallenna() {
        Dialogs.showMessageDialog("Ei osata vielä tallentaa");
        // TODO: korvaa tilaston tallentamisella
    }

    @FXML void handleTallennaPoistu() {
        Dialogs.showMessageDialog("Ei osata vielä tallentaa, mutta poistutaan");
        ModalController.closeStage(textVastustaja);

    }

    @Override
    public String getResult() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void handleShown() {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void setDefault(String arg0) {
        // TODO Auto-generated method stub
        
    }

}