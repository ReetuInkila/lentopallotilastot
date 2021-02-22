package fxLentopallotilastotyokalu;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import lentopallotilastotyokalu.Joukkue;
import fi.jyu.mit.fxgui.ModalController;
/**
 * Luokka joukkuuen valinta ikkunan toimintojen toteuttamiseksi 
 * @author RInkila
 * @version Feb 12, 2021
 *
 */
public class JoukkueenValintaController implements ModalControllerInterface<String> {

    @FXML private ListChooser<Joukkue> chooserJoukkueet;
    private Joukkue joukkue = null;

    
    @FXML void handleAvaa() {
        //vastaus = joukkue.getNimi();
        Dialogs.showMessageDialog("Avataan esimerkkijoukkue");
        ModalController.closeStage(chooserJoukkueet);
        // TODO: korvaa joukkueen valitsemisella
    }

    @FXML void handleLisaaJoukkue() {
        String uusiJoukkue = Dialogs.showInputDialog("Anna joukkueen nimi", "");
        if (uusiJoukkue != null) Dialogs.showMessageDialog("Ei osata lis‰t‰ joukkuetta: " + uusiJoukkue);
        // TODO: korvaa joukkueen lis‰yksell‰
    }

    @FXML void handlePoistaJoukkue() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko joukkue: ....", "Kyll‰", "Ei"); 
        if (vastaus == true ) Dialogs.showMessageDialog("Ei osata poistaa joukkuetta");
        // TODO: korvaa joukkueen poistamisella
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

    /**
     * Luodaan joukkueenvalinta dialogi ja palautetaan siell‰ valittu nimi tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mit‰ nime‰ n‰ytet‰‰n oletuksena
     * @return null jos painetaan Cancel, muuten kirjoitettu nimi
     */
    public static String kysyNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(JoukkueenValintaController.class.getResource("JoukkueenValintaView.fxml"),"Valitse Joukkue", modalityStage, oletus);
    
}

}