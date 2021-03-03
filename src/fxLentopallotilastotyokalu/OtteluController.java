package fxLentopallotilastotyokalu;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import lentopallotilastotyokalu.Lentopallotilastotyokalu;
import lentopallotilastotyokalu.Pelaaja;

/**
 * Luokka ottelu ikkunan toimintojen toteuttamiseksi
 * @author RInkila
 * @version 29.1.2021
 *
 */
public class OtteluController implements ModalControllerInterface<String>  {

    @FXML private ListChooser<Pelaaja> chooserPelaajat;
    @FXML private TextField textVastustaja;
    
    @FXML void HandlePoistaViimeisin() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko viimeisin tilasto: Sauli Sinkkonen, Ässä", "Kyllä", "Ei"); 
        if (vastaus == true ) Dialogs.showMessageDialog("Ei osata vielä poistaa tilastoja");
        // TODO: korvaa tilaston poistamisella
    }

    @FXML void handleTallenna() {
        Dialogs.showMessageDialog("Ei osata vielä tallentaa");
        // TODO: korvaa tilaston tallentamisella
    }

    @FXML void handleTallennaPoistu() {
        Dialogs.showMessageDialog("Ei osata vielä tallentaa, mutta poistutaan");
        ModalController.closeStage(textVastustaja);

    }

    @Override
    public String getResult() {
        return null;
    }

    /**
     * Mitä tehdään kun dialogi on näytetty
     */
    @Override
    public void handleShown() {
        chooserPelaajat.requestFocus();
        
    }

    @Override
    public void setDefault(String oletus) {
        chooserPelaajat.setRivit(oletus);  
    }
    
///=================================================================================================================================================================
/// Tästä eteenpäin ei suoraan käyttöliittymään liittyvää koodia
    
    private static Lentopallotilastotyokalu lentopallotilastotyokalu;
    @SuppressWarnings("unused")
    private Pelaaja pelaajaKohdalla;
    //private Tilasto tilastokohdalla;
    
    
    /**
     * Hakee pelaajien tiedot listaan
     * @param jnro joukkueen numero, joka aktivoidaan haun jälkeen
     */
    protected void hae(int jnro) {
        chooserPelaajat.clear();
        int index = 0;
        for (int i = 0; i < lentopallotilastotyokalu.getPelaajia(); i++) {
            Pelaaja pelaaja = lentopallotilastotyokalu.annaPelaaja(i);
            if (pelaaja.getTunnusNro() == jnro) index = i;
            chooserPelaajat.add(pelaaja.getNimi(), pelaaja);
        }
        chooserPelaajat.setSelectedIndex(index); // tästä tulee muutosviesti joka näyttää jäsenen
    }
    
    /**Asetetaan käytettävä lentopallotilastotyokalu
     * @param tyokalu lentopallotilastotyokalu jota käytetään tässä käyttöliittymässä
     */
    public static void setLentopallotilastotyokalu(Lentopallotilastotyokalu tyokalu) {
        lentopallotilastotyokalu = tyokalu;
    }
}