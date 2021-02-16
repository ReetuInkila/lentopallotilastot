package fxLentopallotilastotyokalu;

import java.net.URL;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

/**
 * Luokka lentopallotilastoty�kalun k�ytt�liittym�n tapahtumien hoitamiseksi.
 * @author RInkila
 * @version Feb 9, 2021
 *
 */
public class LentopallotilastotyokaluGUIController implements Initializable  {

    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private Label labelVirhe;
    
    @FXML void handleLisaaPelaaja() {
        Dialogs.showMessageDialog("Ei osata lis�t� pelaajaa");
        // TODO: korvaa muokkauksella 
    }
    
    @FXML void handlePoistaPelaaja() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko pelaaja: ....", "Kyll�", "Ei"); 
        if (vastaus == true ) Dialogs.showMessageDialog("Ei osata poistaa pelaajaa");
    }
    
    @FXML void handleApua() {
        ModalController.showModal(LentopallotilastotyokaluGUIController.class.getResource("HelpView.fxml"), "Lentopallo tilastoty�kalu", null, "");
        // TODO: tarkenna ohjeita tai linkit� sivulle
    }

    @FXML Object handleLisaaOttelu() {
        return ModalController.showModal(
                JoukkueenValintaController.class.getResource("OtteluView.fxml"), "Ottelu", null, null);
    }
    
    @FXML private void handleHakuehto() {
        String hakukentta = cbKentat.getSelectedText();
        String ehto = hakuehto.getText(); 
        if ( ehto.isEmpty() )
            naytaVirhe(null);
        else
            naytaVirhe("Ei osata viel� hakea " + hakukentta + ": " + ehto);
    }


    


    @FXML void handlePoistaOttelu() {
        Dialogs.showMessageDialog("Ei osata poistaa ottelua");
        // TODO: korvaa ottelun poistolla 
    }

    @FXML void handlePoistu() {
        Dialogs.showMessageDialog("Ei osata tallentaa, mutta poistutaan.");
        Platform.exit();
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
        ModalController.showModal(LentopallotilastotyokaluGUIController.class.getResource("AboutView.fxml"), "Lentopallo tilastoty�kalu", null, "");
        // TODO: tarkenna tietoja ikkuna
    }
    
    
    private void naytaVirhe(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelVirhe.setText("");
            labelVirhe.getStyleClass().removeAll("virhe");
            return;
        }
        labelVirhe.setText(virhe);
        labelVirhe.getStyleClass().add("virhe");
    }
    
    
    /** 
     * Haetaan tiedostonnimi ja luetaan se
     * @return true jos onnistui false jos ei
     */
    public static boolean avaa() {
        String uusijoukkue = JoukkueenValintaController.valitseJoukkue(null, "VaLePa");
        if (uusijoukkue == null) return false;
        //lueTiedosto(uusijoukkue);
        return true;
        
    }

    /**
     * lukee tiedoston ja tuo sen tiedot k�ytt�liittym�lle
     * @param string luettavan tiedoston nimi
     */
    public static void lueTiedosto(String string) {
        // TODO: Lue tiedosto metodi
        
    }

    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        //      
    }

    

}
