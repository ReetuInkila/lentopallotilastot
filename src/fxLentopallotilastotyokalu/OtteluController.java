package fxLentopallotilastotyokalu;


import java.time.format.DateTimeFormatter;

import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.ModalControllerInterface;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import lentopallotilastotyokalu.Joukkue;
import lentopallotilastotyokalu.Lentopallotilastotyokalu;
import lentopallotilastotyokalu.Pelaaja;
import lentopallotilastotyokalu.SailoException;
import lentopallotilastotyokalu.Tilasto;

/**
 * Luokka ottelu ikkunan toimintojen toteuttamiseksi
 * @author Reetu Inkil�
 * @version 29.1.2021
 *
 */
public class OtteluController implements ModalControllerInterface<Joukkue>  {

    @FXML private ListChooser<Pelaaja> chooserPelaajat;
    @FXML private ListChooser<String> chooserTilastot;
    @FXML private TextField textVastustaja;
    @FXML private Label labelViimeisin;
    @FXML private DatePicker pickPaiva;
    
    
    @FXML private void HandlePoistaViimeisin() {
        poistaTilasto();
    }

    
    @FXML private void handleTallenna() throws SailoException {
        tallennaTilasto();
    }

    
    @FXML private void handleTallennaPoistu() {
        try {
            tallenna();
        } catch (SailoException e) {
            boolean vastaus = Dialogs.showQuestionDialog("poistutaanko?" ,"Tallentaminen ep�onnistui. Poistutaanko?", "Kyll�", "Ei");
            if (vastaus == false) return;
        }
        ModalController.closeStage(textVastustaja);
    }

    
///=================================================================================================================================================================
/// T�st� eteenp�in ei suoraan k�ytt�liittym��n liittyv�� koodia
    
    private static Lentopallotilastotyokalu lentopallotilastotyokalu;
    private static Joukkue joukkue;
    private static Pelaaja pelaajaKohdalla;  
    
    
    /**
     * Tilaston tallentamiseen k�ytett�v�t muuttujat
     */
    private int pId;
    private String paiva;
    private String vastustaja;
    private String suorite;

    
    /**
     * Hakee pelaajien tiedot listaan
     */
    private void hae() {
        chooserPelaajat.clear();
        for (int i = 0; i < lentopallotilastotyokalu.getPelaajia(); i++) {
            Pelaaja pelaaja = lentopallotilastotyokalu.annaPelaaja(i);
            if (pelaaja.getJId() == joukkue.getTunnusNro() )chooserPelaajat.add(pelaaja.getNimi(), pelaaja);
        }
    }
    
    
    /**
     * Lis�t��n valittu tilasto
     * @throws SailoException jos tilaston lis��misess� ongelmia
     */
    private void tallennaTilasto() throws SailoException { 
        try {
            pId = pelaajaKohdalla.getTunnusNro();
            vastustaja = textVastustaja.getText();
            paiva = pickPaiva.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            suorite = chooserTilastot.getSelectedObject();
            if (vastustaja.equals("") || paiva.equals("") || suorite.equals("")) Dialogs.showMessageDialog("Tarkista vastustaja, p�iv� ja suorite!");
        }catch ( java.lang.NullPointerException e) {
            Dialogs.showMessageDialog("Tilastoa ei voitu tallentaa.");
            return;
        }        
        Tilasto uusi = new Tilasto(pId, paiva, vastustaja, suorite);
        lentopallotilastotyokalu.lisaaTilasto(uusi);
        String viimeisin = lentopallotilastotyokalu.annaIdPelaaja(uusi.getPelaajaId()).getPelinumero() + " " + lentopallotilastotyokalu.annaIdPelaaja(uusi.getPelaajaId()).getNimi() + " " + uusi.getSuorite();
        labelViimeisin.setText(viimeisin);
    }
    
    
    /**
     * Avataan varmistus ikkuna viimeisimm�n tilaston poistamiselle ja siirryt��n tilaston poistamiseen jos poistaminen valittu
     */
    private void poistaTilasto() {
        String tilasto = labelViimeisin.getText();
        if ( tilasto == "") return;
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko viimeisin tilasto: " + tilasto, "Kyll�", "Ei"); 
        if (vastaus == true ) poista();   
    }
    
    
    /**
     * Poistetaan viimeinen tilasto
     */
    private void poista() {
        lentopallotilastotyokalu.poistaViimeisinTilasto();
        labelViimeisin.setText("");
    }
    
    
    /**
     * Valitsee chooserissa klikatun pelaajan
     */
    private void valitsePelaaja() {
        pelaajaKohdalla = chooserPelaajat.getSelectedObject();
    }
    
    
    /**
     * Tallentaa tilastot
     * @throws SailoException jos tallentaminen ei onnistu
     */
    private void tallenna() throws SailoException {
        lentopallotilastotyokalu.tallenna();
    }
    
    
    /**Asetetaan k�ytett�v� lentopallotilastotyokalu
     * @param tyokalu lentopallotilastotyokalu jota k�ytet��n t�ss� k�ytt�liittym�ss�
     */
    public static void setLentopallotilastotyokalu(Lentopallotilastotyokalu tyokalu) {
        lentopallotilastotyokalu = tyokalu;
    }

    
    /**
     * Kun dialogi on avattu lis�t��n kuuntelijat list choosereille ja lis�t��n String oliot suorite listaan
     */
    @Override
    public void handleShown() {
        hae();
        chooserPelaajat.addSelectionListener(e -> valitsePelaaja());
        chooserTilastot.clear();
        chooserTilastot.add("Sy�tt�");
        chooserTilastot.add("�ss�");
        chooserTilastot.add("Nosto");
        chooserTilastot.add("Piste");
        chooserTilastot.add("Virhe");
    }
    
    
    /**
     * Mit� palautetaan dialogista GUIControllerille
     */
    @Override
    public Joukkue getResult() {
        return null;
    }

    
    /**
     * Mit� tehd��n tuodulla parametrilla
     */
    @Override
    public void setDefault(Joukkue avattuJoukkue) {
        joukkue = avattuJoukkue;     
    }
}