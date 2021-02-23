package fxLentopallotilastotyokalu;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import lentopallotilastotyokalu.Joukkue;
import lentopallotilastotyokalu.Lentopallotilastotyokalu;
import lentopallotilastotyokalu.SailoException;
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
        uusiJoukkue();       
    }

    @FXML void handlePoistaJoukkue() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko joukkue: ....", "Kyllä", "Ei"); 
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

//===================================================================================================================================================================
// Ei suoraan käyttöliittymään liittyvää koodia
    
    /**
     * Lisätään tilastotyökaluun uusi juokkue
     */
    private void uusiJoukkue() {
        Joukkue uusi = new Joukkue();
        uusi.rekisteroi();
        uusi.taytaPuulaakiTiedoilla();//TODO: dialogista joukkueen nimi
        ///try {
            //lentopallotilastotyokalu.lisaaJoukkue(uusi);
        ///} catch (SailoException e) {
        ///    Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
        ///    return;
        ///}
    }
    
    /**
     * Luodaan joukkueenvalinta dialogi ja palautetaan siellä valittu nimi tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mitä nimeä näytetään oletuksena
     * @return null jos painetaan Cancel, muuten kirjoitettu nimi
     */
    public static String valitseNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(JoukkueenValintaController.class.getResource("JoukkueenValintaView.fxml"),"Valitse Joukkue", modalityStage, oletus);
    
}

}