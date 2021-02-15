package lentopallotilastotyokalu;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.stage.Stage;
/**
 * @author RInkila
 * @version Feb 12, 2021
 *
 */
public class JoukkueenValintaController implements ModalControllerInterface<String> {

    @FXML void handleAvaa() {
        Dialogs.showMessageDialog("Ei osata viel‰ avata");
        // TODO: korvaa joukkueen sivun avaamisella
    }

    @FXML void handleLisaaJoukkue() {
        Dialogs.showMessageDialog("Ei osata lis‰t‰ joukkuetta");
        // TODO: korvaa joukkueen lis‰yksell‰
    }

    @FXML void handlePoistaJoukkue() {
        Dialogs.showMessageDialog("Ei osata poistaa joukkuetta");
        // TODO: korvaa joukkueen poistamisella
    }

    /**
     * Luodaan joukkueen valinta dialogi ja palautetaan sielt‰ valittu nimi tai null
     * @param modalityStage mille ollaan modaalisia null = sovellukselle
     * @param oletus mit‰ nime‰ k‰ytet‰‰n oletuksena
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