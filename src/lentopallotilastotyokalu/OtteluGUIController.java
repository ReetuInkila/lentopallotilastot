package lentopallotilastotyokalu;

import fi.jyu.mit.fxgui.Dialogs;
import javafx.fxml.FXML;

/**
 * @author RInkila
 * @version 29.1.2021
 *
 */
public class OtteluGUIController {

    @FXML void HandlePoistaViimeisin() {
        Dialogs.showMessageDialog("Ei osata vielä poistaa tilastoja");
        // TODO: korvaa tilaston poistamisella
    }

    @FXML void handleTallenna() {
        Dialogs.showMessageDialog("Ei osata vielä tallentaa");
        // TODO: korvaa tilaston tallentamisella
    }

    @FXML void handleTallennaPoistu() {
        Dialogs.showMessageDialog("Ei osata tallentaa ja poistua");
        // TODO: korvaa ottelun tallentamisella ja sulkemisella
    }

}