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
import lentopallotilastotyokalu.Joukkue;
import lentopallotilastotyokalu.Lentopallotilastotyokalu;
import lentopallotilastotyokalu.Pelaaja;


/**
 * Luokka lentopallotilastoty�kalun k�ytt�liittym�n tapahtumien hoitamiseksi.
 * @author Reetu Inkil�
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
                "Poistetaanko pelaaja: ....", "Kyll�", "Ei"); 
        if (vastaus == true ) Dialogs.showMessageDialog("Ei osata poistaa pelaajaa");
        // TODO: pelaajan poistaminen
    }
    
    @FXML void handleApua() {
        ModalController.showModal(LentopallotilastotyokaluGUIController.class.getResource("HelpView.fxml"), "Lentopallo tilastoty�kalu", null, "");
        // TODO: tarkenna ohjeita tai linkit� sivulle
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
        ModalController.showModal(LentopallotilastotyokaluGUIController.class.getResource("AboutView.fxml"), "Lentopallo tilastoty�kalu", null, "");
        // TODO: tarkenna tietoja ikkuna
    }

//=================================================================================================================================================================
// T�st� eteenp�in ei suoraan k�ytt�liittym��n liittyv�� koodia
    
    private Lentopallotilastotyokalu lentopallotilastotyokalu;
    private Pelaaja pelaajaKohdalla;
    private TextArea areaPelaaja = new TextArea();
    private Joukkue joukkue;
    
    /**
     * Tekee tarvittavat alustukset, nyt vaihdetaan GridPanen tilalle
     * yksi iso tekstikentt�, johon voidaan tulostaa j�senten tiedot.
     * Alustetaan my�s j�senlistan kuuntelija 
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
     * Alustaa kerhon lukemalla sen valitun nimisest� tiedostosta
     * @param uusiJid joukkueen numero mink� tietoja haetaan
     */
    protected void lueTiedosto(int uusiJid) {
        String nimi = lentopallotilastotyokalu.getJNimi(uusiJid);
        labelJoukkue.setText(nimi);
        joukkueenNimi = nimi;
        setTitle("Lentopallo tilastoty�kalu - " + nimi);
        Dialogs.showMessageDialog("Ei osata lukea viel�"); // TODO: Pelaajien haku tiedostosta
    }

    
    /**
     * Avaa joukkueen valinta dialogin
     * @return true jos joukkueen l�ytyy muuten false
     */
    public boolean avaa() {
        JoukkueenValintaController.setLentopallotilastotyokalu(lentopallotilastotyokalu);
        Joukkue avattava = JoukkueenValintaController.valitseJoukkue(null, joukkue);
        if (avattava == null) return false;
        lueTiedosto(avattava.getId());
        return true;
    }


   
    /**
     * Tietojen tallennus
     */
    private static void tallenna() {
        Dialogs.showMessageDialog("Tallennetetaan! Mutta ei toimi viel�");
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
        OtteluController.setLentopallotilastotyokalu(lentopallotilastotyokalu);
        ModalController.showModal(JoukkueenValintaController.class.getResource("OtteluView.fxml"), "Ottelu", null, "");
    }

    /**
     * Lis�t��n ty�kaluun uusi pelaaja
     */
    private void uusiPelaaja() {
        Pelaaja uusi = new Pelaaja();
        uusi.rekisteroi();
        uusi.taytaEsimerkkiTiedoilla();// TODO: omien tietojen sy�tt�minen
        //uusi.asetaJId(joukkueId);  
        try {
            lentopallotilastotyokalu.lisaaPelaaja(uusi);
        } catch (Exception e) {
            Dialogs.showMessageDialog("ongelmia pelaajan lis��misess�");
        }
        hae(uusi.getTunnusNro());   
    }
    
    /**
     * Hakee pelaajien tiedot listaan
     * @param jnro pelaajan numero, joka aktivoidaan haun j�lkeen
     */
    private void hae(int jnro) {
        chooserPelaajat.clear();
        int index = 0;
        for (int i = 0; i < lentopallotilastotyokalu.getPelaajia(); i++) {
            Pelaaja pelaaja = lentopallotilastotyokalu.annaPelaaja(i);
            if (pelaaja.getTunnusNro() == jnro) index = i;
            //if (pelaaja.getJId() == joukkueId )chooserPelaajat.add(pelaaja.getNimi(), pelaaja);
        }
        chooserPelaajat.setSelectedIndex(index); // t�st� tulee muutosviesti joka n�ytt�� Pelaajan
    }
    
    /** Asetetaan k�ytett�v� lentopallotilastotyokalu
     * @param lentopallotilastotyokalu jota k�ytet��n t�ss� k�ytt�liittym�ss�
     */
    public void setLentopallotilastotyokalu(Lentopallotilastotyokalu lentopallotilastotyokalu) {
        this.lentopallotilastotyokalu = lentopallotilastotyokalu;
    }
    
    /**
     * N�ytt�� listasta valitun j�senen tiedot, tilap�isesti yhteen isoon teksti-kentt��n
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
