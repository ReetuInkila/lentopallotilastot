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
 * @author Reetu Inkilä
 * @version Feb 12, 2021
 *
 */
public class JoukkueenValintaController implements ModalControllerInterface<Joukkue> {

    @FXML private ListChooser<Joukkue> chooserJoukkueet;
    private Joukkue vastaus;
    
    @FXML private void handleAvaa() {
        avaaJoukkue();
    }

    @FXML private void handleLisaaJoukkue() {
        uusiJoukkue();       
    }

    @FXML private void handlePoistaJoukkue() {
        poistaJoukkue();
    }   

///==================================================================================================================================================================
// Ei suoraan käyttöliittymään liittyvää koodia
    
    private static Lentopallotilastotyokalu lentopallotilastotyokalu;
    private Joukkue joukkueKohdalla;
    
    /**
     * Valitsee klikatun joukkueen
     */
    private void valitseJoukkue() {
        joukkueKohdalla = chooserJoukkueet.getSelectedObject();
        if (joukkueKohdalla == null) return;
    }
    
    /**
     * Avaa valitun joukkueen tilastotyökalussa
     */
    private void avaaJoukkue() {
        valitseJoukkue();
        if (joukkueKohdalla == null)return;
        vastaus = joukkueKohdalla;
        ModalController.closeStage(chooserJoukkueet);
    }
    
    /**
     * Lisätään tilastotyökaluun uusi joukkue
     */
    private void uusiJoukkue() {
        String uusiNimi = Dialogs.showInputDialog("Anna joukkueen nimi", "");
        if ( uusiNimi == null ) return;
        Joukkue uusi = new Joukkue(uusiNimi);
        uusi.rekisteroi();
        try {
            lentopallotilastotyokalu.lisaaJoukkue(uusi);
         } catch (SailoException e) {
             Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
             return;
         }
        hae();
    }
    
    /**
     * Avaa varmistus dialogin ja poistaa valittuna olevan joukkeen 
     */
    private void poistaJoukkue() {
        valitseJoukkue();
        boolean poistetaanko = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko joukkue: " + joukkueKohdalla.getNimi(), "Kyllä", "Ei"); 
        if (poistetaanko == true ) Dialogs.showMessageDialog("Ei osata poistaa joukkuetta");
        // TODO: lisää joukkueen poistaminen
    }
    
    
    /**
     * Hakee joukkueiden tiedot listaan

     */
    private void hae() {
        chooserJoukkueet.clear();
        for (int i = 0; i < lentopallotilastotyokalu.getJoukkueita(); i++) {
            Joukkue joukkue = lentopallotilastotyokalu.annaJoukkue(i);
            chooserJoukkueet.add(joukkue.getNimi(), joukkue);
        }
    }

    
    /** Asetetaan käytettävä lentopallotilastotyokalu
     * @param tyokalu jota käytetään tässä käyttöliittymässä
     */
    public static void setLentopallotilastotyokalu(Lentopallotilastotyokalu tyokalu) {
        lentopallotilastotyokalu = tyokalu;
    }
    
    
    /**
     * Luodaan joukkueenvalinta dialogi ja palautetaan siellä valittu nimi tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus joukkue null.
     * @return null jos painetaan Cancel, muuten valittu joukkue
     */
    public static Joukkue valitseJoukkue(Stage modalityStage, Joukkue oletus) {
        return ModalController.showModal(JoukkueenValintaController.class.getResource("JoukkueenValintaView.fxml"),"Valitse Joukkue", modalityStage, oletus);   
    }

    /**
     * Mitä tehdään kun dialogi on näytetty
     */
    @Override
    public void handleShown() {
        hae();
    }
    
    /**
     * Mahdollinen alustustieto dialogin sisälle
     */
    @Override
    public void setDefault(Joukkue oletus) {
        //       
    }

    /**
     *Kutsuu tätä kun dialogi on piilotettu ja tulos pitää palauttaa
     */
    @Override
    public Joukkue getResult() {
        return vastaus;
    }

}