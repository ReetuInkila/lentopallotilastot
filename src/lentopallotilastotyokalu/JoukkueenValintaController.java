package lentopallotilastotyokalu;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;
/**
 * @author RInkila
 * @version Feb 12, 2021
 *
 */
public class JoukkueenValintaController {

    @FXML void handleAvaa() {
        Dialogs.showMessageDialog("Ei osata viel� avata");
        // TODO: korvaa joukkueen sivun avaamisella
    }

    @FXML void handleLisaaJoukkue() {
        Dialogs.showMessageDialog("Ei osata lis�t� joukkuetta");
        // TODO: korvaa joukkueen lis�yksell�
    }

    @FXML void handlePoistaJoukkue() {
        Dialogs.showMessageDialog("Ei osata poistaa joukkuetta");
        // TODO: korvaa joukkueen poistamisella
    }

}