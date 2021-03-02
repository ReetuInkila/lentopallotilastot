package fxLentopallotilastotyokalu;

import java.io.PrintStream;
import java.net.URL;
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
import lentopallotilastotyokalu.Lentopallotilastotyokalu;
import lentopallotilastotyokalu.Pelaaja;


/**
 * Luokka lentopallotilastotyökalun käyttöliittymän tapahtumien hoitamiseksi.
 * @author RInkila
 * @version Feb 9, 2021
 *
 */
public class LentopallotilastotyokaluGUIController implements Initializable  {


    @FXML private Label labelJoukkue;
    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private ScrollPane panelPelaaja;
    @FXML private ListChooser<Pelaaja> chooserPelaajat;

    
    private String joukkueenNimi = "";
    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();   
    }
    
    @FXML void handleLisaaPelaaja() {
        uusiPelaaja();
        // TODO: korvaa muokkattavalla pelaajalla 
    }
    
    @FXML void handlePoistaPelaaja() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko pelaaja: ....", "Kyllä", "Ei"); 
        if (vastaus == true ) Dialogs.showMessageDialog("Ei osata poistaa pelaajaa");
        // TODO: pelaajan poistaminen
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
        Dialogs.showMessageDialog("Ei osata tallentaa, mutta poistutaan.");
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
        // TODO: tarkenna tietoja ikkuna
    }

//=================================================================================================================================================================
// Tästä eteenpäin ei suoraan käyttöliittymään liittyvää koodia
    
    private Lentopallotilastotyokalu lentopallotilastotyokalu;
    private Pelaaja pelaajaKohdalla;
    private TextArea areaPelaaja = new TextArea();
    static int joukkueId;
    
    /**
     * Tekee tarvittavat alustukset, nyt vaihdetaan GridPanen tilalle
     * yksi iso tekstikenttä, johon voidaan tulostaa jäsenten tiedot.
     * Alustetaan myös jäsenlistan kuuntelija 
     */
    protected void alusta() {
        panelPelaaja.setContent(areaPelaaja);
        areaPelaaja.setFont(new Font("Courier New", 12));
        panelPelaaja.setFitToHeight(true);
        labelJoukkue.setText(joukkueenNimi);
        
        chooserPelaajat.clear();
        chooserPelaajat.addSelectionListener(e -> naytaPelaaja());
    }
    
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }

    
    /**
     * Alustaa kerhon lukemalla sen valitun nimisestä tiedostosta
     * @param nimi tiedosto josta kerhon tiedot luetaan
     */
    protected void lueTiedosto(String nimi) {
        joukkueenNimi = nimi;
        setTitle("Lentopallo tilastotyökalu - " + joukkueenNimi);
        String virhe = "Ei osata lukea vielä";  // TODO: tähän oikea tiedoston lukeminen
        // if (virhe != null) 
            Dialogs.showMessageDialog(virhe);
    }

    
    /**
     * Avaa joukkueen valinta dialogin
     * @return true jos onnistui, false jos ei
     */
    public boolean avaa() {
        String uusinimi = JoukkueenValintaController.valitseNimi(null, joukkueenNimi);
        if (uusinimi == null) return false;
        lueTiedosto(uusinimi);
        joukkueenNimi = uusinimi;
        labelJoukkue.setText(uusinimi);
        return true;
    }


   
    /**
     * Tietojen tallennus
     */
    private static void tallenna() {
        Dialogs.showMessageDialog("Tallennetetaan! Mutta ei toimi vielä");
        // TODO: korvaa tallentamisella
    }

    /**
     * tallentaa ja onnistuessaan palauttaa true
     * @return true jos tallennus onnistuu
     */
    public static boolean voikoSulkea() {
        tallenna();
        return true;
    }

    /**
     * Avaa ottelu dialogin
     */
    private void ottelu() {
        ModalController.showModal(JoukkueenValintaController.class.getResource("OtteluView.fxml"), "Ottelu", null, "");
    }

    /**
     * Lisätään työkaluun uusi pelaaja
     */
    private void uusiPelaaja() {
        Pelaaja uusi = new Pelaaja();
        uusi.rekisteroi();
        uusi.taytaEsimerkkiTiedoilla();// TODO: omien tietojen syöttäminen
        uusi.asetaJId(joukkueId);  
        try {
            lentopallotilastotyokalu.lisaaPelaaja(uusi);
        } catch (Exception e) {
            Dialogs.showMessageDialog("ongelmia pelaajan lisäämisessä");
        }
        hae(uusi.getTunnusNro());   
    }
    
    /**
     * Hakee pelaajien tiedot listaan
     * @param jnro pelaajan numero, joka aktivoidaan haun jälkeen
     */
    private void hae(int jnro) {
        chooserPelaajat.clear();
        int index = 0;
        for (int i = 0; i < lentopallotilastotyokalu.getPelaajia(); i++) {
            Pelaaja pelaaja = lentopallotilastotyokalu.annaPelaaja(i);
            if (pelaaja.getTunnusNro() == jnro) index = i;
            if (pelaaja.getjId() == joukkueId )chooserPelaajat.add(pelaaja.getNimi(), pelaaja);
        }
        chooserPelaajat.setSelectedIndex(index); // tästä tulee muutosviesti joka näyttää Pelaajan
    }
    
    /**Asetetaan käytettävä lentopallotilastotyokalu
     * @param lentopallotilastotyokalu jota käytetään tässä käyttöliittymässä
     */
    public void setLentopallotilastotyokalu(Lentopallotilastotyokalu lentopallotilastotyokalu) {
        this.lentopallotilastotyokalu = lentopallotilastotyokalu;
    }
    
    /**
     * Näyttää listasta valitun jäsenen tiedot, tilapäisesti yhteen isoon teksti-kenttään
     */
    private void naytaPelaaja() {
        pelaajaKohdalla = chooserPelaajat.getSelectedObject();

        if (pelaajaKohdalla == null) return;

        areaPelaaja.setText("");
        try (PrintStream os = TextAreaOutputStream.getTextPrintStream(areaPelaaja)) {
            pelaajaKohdalla.tulosta(os);
        }
    }
}
