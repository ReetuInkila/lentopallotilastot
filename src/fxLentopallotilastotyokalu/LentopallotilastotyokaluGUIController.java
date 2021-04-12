package fxLentopallotilastotyokalu;

import java.net.URL;
import java.util.Collection;
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
    @FXML private TextField nimi;
    @FXML private TextField numero;
    @FXML private TextField pelipaikka;

    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    
    @FXML private void handleLisaaPelaaja() {
        uusiPelaaja();
        // TODO: korvaa muokkattavalla pelaajalla 
    }
    
    
    @FXML private void handleHakuehto() {
        hae(0); 
    }

    
    
    @FXML private void handleMuokkaaPelaajaa() {
        muokkaaPelaajaa();
    }
    
    
    @FXML private void handlePoistaPelaaja() {
        poistaPelaaja();
    }
    
    
    @FXML private void handleTallennaPelaaja() {
        tallennaMuutokset();
    }
    
    
    @FXML private void handleApua() {
        ModalController.showModal(LentopallotilastotyokaluGUIController.class.getResource("HelpView.fxml"), "Lentopallo tilastotyökalu", null, "");
        // TODO: tarkenna ohjeita tai linkitä sivulle
    }

    
    @FXML private void handleLisaaOttelu() {
        ottelu();
    }

    
    @FXML private void handlePoistaOttelu() {
        Dialogs.showMessageDialog("Ei osata poistaa ottelua");
        // TODO: korvaa ottelun poistolla 
    }

    
    @FXML private void handlePoistu() {
        tallenna();
        Platform.exit();
    }
    
    
    @FXML private void handleAvaa() {
        avaa();
    }

    
    @FXML private void handleTallenna() {
        tallenna();    
    }
    
    
    @FXML private void handleTietoja() {
        ModalController.showModal(LentopallotilastotyokaluGUIController.class.getResource("AboutView.fxml"), "Lentopallo tilastotyökalu", null, "");
        // TODO: tarkenna tietoja ikkunaan
    }

    
//=================================================================================================================================================================
// Tästä eteenpäin ei suoraan käyttöliittymään liittyvää koodia
    
    private Lentopallotilastotyokalu lentopallotilastotyokalu;
    private Pelaaja pelaajaKohdalla;
    private static Pelaaja apupelaaja = new Pelaaja();
    private TextArea areaTilastot = new TextArea();
    private Joukkue joukkue;
    private String kansio = "tilastotyokalu";
    
    
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }

    
    /**
     * Tekee tarvittavat alustukset, nyt vaihdetaan GridPanen tilalle
     * yksi iso tekstikenttä, johon voidaan tulostaa jäsenten tiedot.
     * Alustetaan myös pelaajalistan kuuntelija 
     */
    private void alusta() {
        panelTilastot.setContent(areaTilastot);
        areaTilastot.setFont(new Font("Courier New", 12));
        panelTilastot.setFitToHeight(true);
        panelTilastot.setFitToWidth(true);
        
        chooserPelaajat.clear();
        chooserPelaajat.addSelectionListener(e -> naytaPelaaja());
        
        cbKentat.clear(); 
        for (int k = apupelaaja.ekaKentta(); k < apupelaaja.getKenttia(); k++) 
            cbKentat.add(apupelaaja.getKysymys(k), null); 
        cbKentat.getSelectionModel().select(1);


    }
    
    
    /**
     * Alustaa lentopallotilastotyokalun lukemalla joukkueeseen kuuluvat pelaajat tiedostosta
     */
    private void lueTiedosto() {
        labelJoukkue.setText(joukkue.getNimi());
        setTitle("Lentopallo tilastotyökalu - " + joukkue.getNimi());
        hae(-1);
    }

    
    /**
     * Avaa joukkueen valinta dialogin
     * @return true jos joukkueen löytyy muuten false
     */
    public boolean avaa() {
        try {
            lentopallotilastotyokalu.lueTiedostosta(kansio);
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
        hae(uusi.getTunnusNro());   
    }
    
    
    /**
     * Hakee pelaajien tiedot listaan
     * @param id pelaajan id-numero, joka aktivoidaan haun jälkeen  
     */
    protected void hae(int id) {
        int pid = id;
        if ( pid <= 0 ) { 
            Pelaaja kohdalla = pelaajaKohdalla; 
            if ( kohdalla != null ) pid = kohdalla.getTunnusNro(); 
        }
        
        int k = cbKentat.getSelectionModel().getSelectedIndex() + apupelaaja.ekaKentta();; 
        String ehto = hakuehto.getText(); 
        if (ehto.indexOf('*') < 0) ehto = "*" + ehto + "*"; 
        
        chooserPelaajat.clear();

        int index = 0;
        Collection<Pelaaja> pelaajat;
        try {
            pelaajat = lentopallotilastotyokalu.etsi(ehto, k);
            int i = 0;
            for (Pelaaja pelaaja:pelaajat) {
                if (pelaaja.getTunnusNro() == pid) index = i;
                if (pelaaja.getJId() == joukkue.getTunnusNro() )chooserPelaajat.add(pelaaja.getNimi(), pelaaja);
                i++;
            }
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("Jäsenen hakemisessa ongelmia! " + ex.getMessage());
        }
        chooserPelaajat.setSelectedIndex(index); // tästä tulee muutosviesti joka näyttää jäsenen
    }

        
    
    
    private void poistaPelaaja() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko pelaaja: " + pelaajaKohdalla.getNimi(), "Kyllä", "Ei"); 
        if (vastaus == false ) return;
        int poistettu = lentopallotilastotyokalu.poista(pelaajaKohdalla);
        Dialogs.showMessageDialog("Poistettuja pelaajia:" + poistettu);
        hae(-1);
    }

    
    /**
     * Näyttää listasta valitun pelaajan tiedot ja tilastot, tilapäisesti kahteen isoon teksti-kenttään
     */
    @SuppressWarnings("resource")
    private void naytaPelaaja() {
        areaTilastot.setText("");
        pelaajaKohdalla = chooserPelaajat.getSelectedObject();
        if (pelaajaKohdalla == null) return;
        
        nimi.setText(pelaajaKohdalla.getNimi());
        numero.setText(String.valueOf(pelaajaKohdalla.getPelinumero()));
        pelipaikka.setText(pelaajaKohdalla.getPelipaikka());
        
        List<Tilasto> tilastot = lentopallotilastotyokalu.annaTilastot(pelaajaKohdalla);
        for (Tilasto til: tilastot)
            til.tulosta(TextAreaOutputStream.getTextPrintStream(areaTilastot));
    }
    
    
    /**
     * Mahdollistetaan pelaajan tietojen muokkaus
     */
    private void muokkaaPelaajaa() {
        nimi.setEditable(true);
        numero.setEditable(true);
        pelipaikka.setEditable(true);
    }
    
    
    /**
     * Tallentaa pelaajan tietoihin tehdyt muutokset
     */
    private void tallennaMuutokset() {
        pelaajaKohdalla = chooserPelaajat.getSelectedObject();
        pelaajaKohdalla.setNimi(nimi.getText());
        nimi.setEditable(false);
        pelaajaKohdalla.setPelinumero(Integer.valueOf(numero.getText()));
        numero.setEditable(false);
        pelaajaKohdalla.setPelipaikka(pelipaikka.getText());
        pelipaikka.setEditable(false);
        lentopallotilastotyokalu.pelaajiaMuokattu();
        tallenna();
        hae(pelaajaKohdalla.getTunnusNro());
    }
    

    /** Asetetaan käytettävä lentopallotilastotyokalu
     * @param lentopallotilastotyokalu jota käytetään tässä käyttöliittymässä
     */
    public void setLentopallotilastotyokalu(Lentopallotilastotyokalu lentopallotilastotyokalu) {
        this.lentopallotilastotyokalu = lentopallotilastotyokalu;
    }
}
