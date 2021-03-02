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
    private String vastaus = null;
    
    @FXML void handleAvaa() {
        avaaJoukkue();
    }

    @FXML void handleLisaaJoukkue() {
        uusiJoukkue();       
    }

    @FXML void handlePoistaJoukkue() {
        poistaJoukkue();
    }   

///==================================================================================================================================================================
// Ei suoraan k�ytt�liittym��n liittyv�� koodia
    
    private static Lentopallotilastotyokalu lentopallotilastotyokalu;
    private Joukkue joukkueKohdalla;
    
    /**
     * Valitsee klikatun joukkueen
     */
    protected void valitseJoukkue() {
        chooserJoukkueet.addSelectionListener(null);
        joukkueKohdalla = chooserJoukkueet.getSelectedObject();
        if (joukkueKohdalla == null) return;
    }
    
    /**
     * Avaa valitun joukkueen tilastoty�kalussa
     */
    private void avaaJoukkue() {
        valitseJoukkue();
        vastaus = joukkueKohdalla.getNimi();
        LentopallotilastotyokaluGUIController.joukkueId = joukkueKohdalla.getId();
        ModalController.closeStage(chooserJoukkueet);
    }
    
    /**
     * Lis�t��n tilastoty�kaluun uusi juokkue
     */
    private void uusiJoukkue() {
        Joukkue uusi = new Joukkue();
        uusi.rekisteroi();
        uusi.taytaPuulaakiTiedoilla();//TODO: dialogista joukkueen nimi
        try {
            lentopallotilastotyokalu.lisaaJoukkue(uusi);
         } catch (SailoException e) {
             Dialogs.showMessageDialog("Ongelmia uuden luomisessa " + e.getMessage());
             return;
         }
        hae(uusi.getTunnusNro());
    }
    
    /**
     * Avaa varmistus dialogin ja poistaa valittuna olevan joukkeen 
     */
    private void poistaJoukkue() {
        valitseJoukkue();
        boolean poistetaanko = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko joukkue: " + joukkueKohdalla.getNimi(), "Kyll�", "Ei"); 
        if (poistetaanko == true ) Dialogs.showMessageDialog("Ei osata poistaa joukkuetta");
        // TODO: lis�� joukkueen poistaminen
    }
    
    
    /**
     * Hakee joukkueiden tiedot listaan
     * @param jnro joukkueen numero, joka aktivoidaan haun j�lkeen
     */
    protected void hae(int jnro) {
        chooserJoukkueet.clear();

        int index = 0;
        for (int i = 0; i < lentopallotilastotyokalu.getJoukkueita(); i++) {
            Joukkue joukkue = lentopallotilastotyokalu.annaJoukkue(i);
            if (joukkue.getTunnusNro() == jnro) index = i;
            chooserJoukkueet.add(joukkue.getNimi(), joukkue);
        }
        chooserJoukkueet.setSelectedIndex(index);
    }

    
    /**Asetetaan k�ytett�v� lentopallotilastotyokalu
     * @param tyokalu jota k�ytet��n t�ss� k�ytt�liittym�ss�
     */
    public static void setLentopallotilastotyokalu(Lentopallotilastotyokalu tyokalu) {
        lentopallotilastotyokalu = tyokalu;
    }
    
    /**
     * Luodaan joukkueenvalinta dialogi ja palautetaan siell� valittu nimi tai null
     * @param modalityStage mille ollaan modaalisia, null = sovellukselle
     * @param oletus mit� nime� n�ytet��n oletuksena
     * @return null jos painetaan Cancel, muuten kirjoitettu nimi
     */
    public static String valitseNimi(Stage modalityStage, String oletus) {
        return ModalController.showModal(JoukkueenValintaController.class.getResource("JoukkueenValintaView.fxml"),"Valitse Joukkue", modalityStage, oletus);   
    }

    @Override
    public String getResult() {
        return vastaus;
    }

    /**
     * Mit� tehd��n kun dialogi on n�ytetty
     */
    @Override
    public void handleShown() {
        chooserJoukkueet.requestFocus();
        
    }

    @Override
    public void setDefault(String oletus) {
        chooserJoukkueet.setRivit(oletus);  
    }

}