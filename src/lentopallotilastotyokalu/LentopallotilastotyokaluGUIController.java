package lentopallotilastotyokalu;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.fxml.FXML;
/**
 * @author RInkila
 * @version Feb 9, 2021
 *
 */
public class LentopallotilastotyokaluGUIController {

    @FXML void handleLisaaPelaaja() {
        Dialogs.showMessageDialog("Ei osata lis�t� pelaajaa");
        // TODO: korvaa muokkauksella 
    }
    
    @FXML void handlePoistaPelaaja() {
        Dialogs.showMessageDialog("Ei osata poistaa pelaajaa");
        // TODO: korvaa muokkauksella 
    }
    
    @FXML void handleApua() {
        Dialogs.showMessageDialog("Ei osata n�ytt�� apua ikkunaa");
        // TODO: korvaa apua ikkunan n�ytt�misell� 
    }

    @FXML Object handleLisaaOttelu() {
        return ModalController.showModal(
                JoukkueenValintaController.class.getResource("OtteluView.fxml"), "Ottelu", null, null);
    }

    @FXML void handlePoistaOttelu() {
        Dialogs.showMessageDialog("Ei osata poistaa ottelua");
        // TODO: korvaa ottelun poistolla 
    }

    @FXML void handlePoistu() {
        Dialogs.showMessageDialog("Ei osata poistua");
        // TODO: korvaa tallenna ja poistu ominaisuudella
    }
    
    @FXML void handleAvaa() {
        avaa();
    }

    

    @FXML void handleTallenna() {
        Dialogs.showMessageDialog("Ei osata Tallentaa");
        // TODO: korvaa tallentamisella
    }

    @FXML void handleTietoja() {
        Dialogs.showMessageDialog("Ei osata avata tietoja ikkunaa");
        // TODO: korvaa tietoja ikkunan n�ytt�misell�
    }
    
    
    /** Haetaan tiedostonnimi ja luetaan se
     * @return true jos onnistui false jos ei
     */
    public static boolean avaa() {
        String uusijoukkue = JoukkueenValintaController.valitseJoukkue(null, "VaLePa");
        if (uusijoukkue == null) return false;
        //lueTiedosto(uusijoukkue);
        return true;
        
    }

    public static void lueTiedosto(String string) {
        // TODO Auto-generated method stub
        
    }
}
