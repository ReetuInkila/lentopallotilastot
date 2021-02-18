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


/**
 * Luokka lentopallotilastotyökalun käyttöliittymän tapahtumien hoitamiseksi.
 * @author RInkila
 * @version Feb 9, 2021
 *
 */
public class LentopallotilastotyokaluGUIController implements Initializable  {

    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> cbKentat;
    private static String joukkueennimi = "Puulaaki";
    
    @FXML void handleLisaaPelaaja() {
        Dialogs.showMessageDialog("Ei osata lisätä pelaajaa");
        // TODO: korvaa muokkauksella 
    }
    
    @FXML void handlePoistaPelaaja() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko pelaaja: ....", "Kyllä", "Ei"); 
        if (vastaus == true ) Dialogs.showMessageDialog("Ei osata poistaa pelaajaa");
        // TODO: pelaajan poistaminen
    }
    
    @FXML void handleApua() {
        ModalController.showModal(LentopallotilastotyokaluGUIController.class.getResource("HelpView.fxml"), "Lentopallo tilastotyökalu", null, "");
        // TODO: tarkenna ohjeita tai linkitä sivulle
    }

    @FXML void handleLisaaOttelu() {
        ottelu();
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
        tallenna();
        
    }
    
    @FXML void handleTietoja() {
        ModalController.showModal(LentopallotilastotyokaluGUIController.class.getResource("AboutView.fxml"), "Lentopallo tilastotyökalu", null, "");
        // TODO: tarkenna tietoja ikkuna
    }
    
   
    /**
     * Tietojen tallennus
     */
    private static void tallenna() {
        Dialogs.showMessageDialog("Tallennetetaan! Mutta ei toimi vielä");
        // TODO: korvaa tallentamisella
    }

    /**
     * tallentaa ja onnistuessaan palauttaa true
     * @return true jos tallennus onnistuu
     */
    public static boolean voikoSulkea() {
        tallenna();
        return true;
    }

    /**
     * Avaa ottelu dialogin
     */
    private void ottelu() {
        ModalController.showModal(JoukkueenValintaController.class.getResource("OtteluView.fxml"), "Ottelu", null, "");     
    }

    /**
     *  Avaa joukkueen valinta dialogin
     */
    public static void avaa() {
        ModalController.showModal(LentopallotilastotyokaluGUIController.class.getResource("JoukkueenValintaView.fxml"), "Valitse joukkue", null, "");   
    }
    
    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        // TODO Auto-generated method stub   
    }
 
}
