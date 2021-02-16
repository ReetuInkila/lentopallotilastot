package fxLentopallotilastotyokalu;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.stage.Stage;
/**
 * Luokka tilastotyökalussa avattavan joukkueen valitsemisen tapahtumien hoitamiseksi
 * @author RInkila
 * @version Feb 12, 2021
 *
 */
public class JoukkueenValintaController implements ModalControllerInterface<String> {

    @FXML void handleAvaa() {
        Dialogs.showMessageDialog("Avataan esimerkki joukkue");
        Platform.exit();
        // TODO: korvaa joukkueen sivun avaamisella
    }

    @FXML void handleLisaaJoukkue() {
        Dialogs.showMessageDialog("Ei osata lisätä joukkuetta");
        // TODO: korvaa joukkueen lisäyksellä
    }

    @FXML void handlePoistaJoukkue() {
        Dialogs.showMessageDialog("Ei osata poistaa joukkuetta");
        // TODO: korvaa joukkueen poistamisella
    }

    /**
     * Luodaan joukkueen valinta dialogi ja palautetaan sieltä valittu nimi tai null
     * @param modalityStage mille ollaan modaalisia null = sovellukselle
     * @param oletus mitä nimeä käytetään oletuksena
     * @return null jos painetaan cancel muuten valittu joukkue
     */
    public static String valitseJoukkue(Stage modalityStage, String oletus) {
        return ModalController.showModal(
                JoukkueenValintaController.class.getResource("JoukkueenValintaView.fxml"), "Valitse joukkue", modalityStage, oletus);
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
    public void setDefault(String oletus) {
        // TODO Auto-generated method stub
        
    }

}