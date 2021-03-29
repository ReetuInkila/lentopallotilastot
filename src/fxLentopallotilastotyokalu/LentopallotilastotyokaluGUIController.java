package fxLentopallotilastotyokalu;

import java.io.PrintStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import fi.jyu.mit.fxgui.TextAreaOutputStream;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import lentopallotilastotyokalu.Joukkue;
import lentopallotilastotyokalu.Lentopallotilastotyokalu;
import lentopallotilastotyokalu.Pelaaja;
import lentopallotilastotyokalu.SailoException;
import lentopallotilastotyokalu.Tilasto;


/**
 * Luokka lentopallotilastotyökalun käyttöliittymän tapahtumien hoitamiseksi.
 * @author Reetu Inkilä
 * @version Feb 9, 2021
 *
 */
public class LentopallotilastotyokaluGUIController implements Initializable  {


    @FXML private Label labelJoukkue;
    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private ScrollPane panelPelaaja;
    @FXML private ScrollPane panelTilastot;
    @FXML private ListChooser<Pelaaja> chooserPelaajat;

    
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    @FXML void handleLisaaPelaaja() {
        uusiPelaaja();
        // TODO: korvaa muokkattavalla pelaajalla 
    }
    
    @FXML void handlePoistaPelaaja() {
        poistaPelaaja();
    }
    
    @FXML void handleApua() {
        ModalController.showModal(LentopallotilastotyokaluGUIController.class.getResource("HelpView.fxml"), "Lentopallo tilastotyökalu", null, "");
        // TODO: tarkenna ohjeita tai linkitä sivulle
    }

    @FXML void handleLisaaOttelu() {
        ottelu();
    }

    @FXML void handlePoistaOttelu() {
        Dialogs.showMessageDialog("Ei osata poistaa ottelua");
        // TODO: korvaa ottelun poistolla 
    }

    @FXML void handlePoistu() {
        tallenna();
        Platform.exit();
        // TODO: korvaa tallenna ja poistu ominaisuudella
    }
    
    @FXML void handleAvaa() {
        avaa();
    }

    @FXML void handleTallenna() {
        tallenna();
        
    }
    
    @FXML void handleTietoja() {
        ModalController.showModal(LentopallotilastotyokaluGUIController.class.getResource("AboutView.fxml"), "Lentopallo tilastotyökalu", null, "");
        // TODO: tarkenna tietoja ikkunaan
    }

//=================================================================================================================================================================
// Tästä eteenpäin ei suoraan käyttöliittymään liittyvää koodia
    
    private Lentopallotilastotyokalu lentopallotilastotyokalu;
    private Pelaaja pelaajaKohdalla;
    private TextArea areaPelaaja = new TextArea();
    private TextArea areaTilastot = new TextArea();
    private Joukkue joukkue;
    
    
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }

    /**
     * Tekee tarvittavat alustukset, nyt vaihdetaan GridPanen tilalle
     * yksi iso tekstikenttä, johon voidaan tulostaa jäsenten tiedot.
     * Alustetaan myös pelaajalistan kuuntelija 
     */
    private void alusta() {
                
        panelPelaaja.setContent(areaPelaaja);
        areaPelaaja.setFont(new Font("Courier New", 12));
        panelPelaaja.setFitToHeight(true);
        panelPelaaja.setFitToWidth(true);
        
        panelTilastot.setContent(areaTilastot);
        areaTilastot.setFont(new Font("Courier New", 12));
        panelTilastot.setFitToHeight(true);
        panelTilastot.setFitToWidth(true);
        
        chooserPelaajat.clear();
        chooserPelaajat.addSelectionListener(e -> naytaPelaaja());
    }
    
    /**
     * Alustaa lentopallotilastotyokalun lukemalla joukkueeseen kuuluvat pelaajat tiedostosta
     */
    private void lueTiedosto() {
        String nimi = joukkue.getNimi();
        labelJoukkue.setText(nimi);
        setTitle("Lentopallo tilastotyökalu - " + nimi);
        hae(); // TODO: Pelaajien haku tiedostosta
    }

    
    /**
     * Avaa joukkueen valinta dialogin
     * @return true jos joukkueen löytyy muuten false
     */
    public boolean avaa() {
        try {
            lentopallotilastotyokalu.lueTiedostosta();
        } catch (SailoException e) {
            e.printStackTrace();
        }
        JoukkueenValintaController.setLentopallotilastotyokalu(lentopallotilastotyokalu);
        Joukkue avattava = JoukkueenValintaController.valitseJoukkue(null, joukkue);
        if (avattava == null) return false;
        joukkue = avattava;
        lueTiedosto();
        return true;
    }

   
    /**
     * Tietojen tallennus
     * @return null jos onnistuu, muuten virhe tekstinä
     */
    private String tallenna() {
        try {
            lentopallotilastotyokalu.tallenna();
            return null;
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Tallennuksessa ongelmia! " + ex.getMessage());
            return ex.getMessage();
        }
    }


    /**
     * tallentaa ja onnistuessaan palauttaa true
     * @return true jos tallennus onnistuu
     */
    public boolean voikoSulkea() {
        tallenna();
        return true;
    }

    /**
     * Avaa ottelu dialogin
     */
    private void ottelu() {
        OtteluController.setLentopallotilastotyokalu(lentopallotilastotyokalu);
        ModalController.showModal(JoukkueenValintaController.class.getResource("OtteluView.fxml"), "Ottelu", null, joukkue);
    }

    /**
     * Lisätään työkaluun uusi pelaaja
     */
    private void uusiPelaaja() {
        Pelaaja uusi = new Pelaaja();
        uusi.rekisteroi();
        uusi.taytaEsimerkkiTiedoilla();// TODO: omien tietojen syöttäminen
        uusi.asetaJId(joukkue.getTunnusNro());  
        try {
            lentopallotilastotyokalu.lisaaPelaaja(uusi);
        } catch (Exception e) {
            Dialogs.showMessageDialog("ongelmia pelaajan lisäämisessä");
        }
        hae();   
    }
    
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
    
    private void poistaPelaaja() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko pelaaja: " + pelaajaKohdalla.getNimi(), "Kyllä", "Ei"); 
        if (vastaus == true ) Dialogs.showMessageDialog("Ei osata poistaa pelaajaa");
        // TODO: pelaajan poistaminen
    }

    
    /**
     * Näyttää listasta valitun pelaajan tiedot ja tilastot, tilapäisesti kahteen isoon teksti-kenttään
     */
    @SuppressWarnings("resource")
    private void naytaPelaaja() {
        areaPelaaja.setText("");
        areaTilastot.setText("");
        pelaajaKohdalla = chooserPelaajat.getSelectedObject();
        if (pelaajaKohdalla == null) return;

        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaPelaaja)) {
            pelaajaKohdalla.tulosta(os);
        }
        List<Tilasto> tilastot = lentopallotilastotyokalu.annaTilastot(pelaajaKohdalla);
        for (Tilasto til: tilastot)
            til.tulosta(TextAreaOutputStream.getTextPrintStream(areaTilastot));
        //TODO: pelaajien tiedot niille tarkoitettuihin kenttiin
    }
    

    /** Asetetaan käytettävä lentopallotilastotyokalu
     * @param lentopallotilastotyokalu jota käytetään tässä käyttöliittymässä
     */
    public void setLentopallotilastotyokalu(Lentopallotilastotyokalu lentopallotilastotyokalu) {
        this.lentopallotilastotyokalu = lentopallotilastotyokalu;
    }
}
