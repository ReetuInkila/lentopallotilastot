package lentopallotilastotyokalu;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;
/**
 * @author RInkila
 * @version Feb 12, 2021
 *
 */
public class AloitusGUIController {

    @FXML void onHandleAvaa() {
        Dialogs.showMessageDialog("Ei osata viel� avata");
        // TODO: korvaa joukkueen sivun avaamisella
    }

    @FXML void onHandleLisaaJoukkue() {
        Dialogs.showMessageDialog("Ei osata lis�t� joukkuetta");
        // TODO: korvaa joukkueen lis�yksell�
    }

    @FXML void onHandlePoistaJoukkue() {
        Dialogs.showMessageDialog("Ei osata poistaa joukkuetta");
        // TODO: korvaa joukkueen poistamisella
    }

}