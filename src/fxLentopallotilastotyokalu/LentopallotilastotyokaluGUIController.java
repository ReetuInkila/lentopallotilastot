package fxLentopallotilastotyokalu;

import javafx.scene.input.MouseEvent;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;
import fi.jyu.mit.fxgui.ComboBoxChooser;
import fi.jyu.mit.fxgui.Dialogs;
import fi.jyu.mit.fxgui.ListChooser;
import fi.jyu.mit.fxgui.ModalController;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import lentopallotilastotyokalu.Joukkue;
import lentopallotilastotyokalu.Lentopallotilastotyokalu;
import lentopallotilastotyokalu.Pelaaja;
import lentopallotilastotyokalu.SailoException;
import lentopallotilastotyokalu.Tilasto;


/**
 * Luokka lentopallotilastotyˆkalun k‰yttˆliittym‰n tapahtumien hoitamiseksi.
 * @author Reetu Inkil‰
 * @version Feb 9, 2021
 *
 */
public class LentopallotilastotyokaluGUIController implements Initializable  {

    @FXML private Label labelJoukkue;
    @FXML private TextField hakuehto;
    @FXML private ComboBoxChooser<String> cbKentat;
    @FXML private ScrollPane panelTilastot;
    @FXML private ListChooser<Pelaaja> chooserPelaajat;
    @FXML private TextField nimi;
    @FXML private TextField numero;
    @FXML private TextField pelipaikka;
    @FXML private Label labelHuomautus;
    @FXML private GridPane gridTilastot;

    
    @Override
    public void initialize(URL url, ResourceBundle bundle) {
        alusta();
    }
    
    
    @FXML private void handleLisaaPelaaja() {
        uusiPelaaja(); 
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
        avustus();
    }

    
    @FXML private void handleLisaaOttelu() {
        ottelu();
    }

    
    @FXML private void handlePoistaOttelu() {
        poistaOttelu();
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
        ModalController.showModal(LentopallotilastotyokaluGUIController.class.getResource("AboutView.fxml"), "Lentopallo tilastotyˆkalu", null, "");
    }

    
//=================================================================================================================================================================
// T‰st‰ eteenp‰in ei suoraan k‰yttˆliittym‰‰n liittyv‰‰ koodia
    
    private Lentopallotilastotyokalu lentopallotilastotyokalu;
    private Pelaaja pelaajaKohdalla;
    private static Pelaaja apupelaaja = new Pelaaja();
    private static Tilasto aputilasto = new Tilasto();
    private TextArea areaTilastot = new TextArea();
    private Joukkue joukkue;
    private String kansio = "tilastotyokalu";
    private int ottelunIndeksi = -1;
   
    
    /**
     * Asettaa ikkunalle otsikon
     * @param title
     */
    private void setTitle(String title) {
        ModalController.getStage(hakuehto).setTitle(title);
    }

    
    /**
     * Tekee tarvittavat alustukset,
     * Alustetaan pelaajalistan kuuntelija, hakuehto ikkuna ja tilastot kentt‰.
     */
    private void alusta() {
        chooserPelaajat.clear();
        chooserPelaajat.addSelectionListener(e -> naytaPelaaja());
        
        cbKentat.clear(); 
        for (int k = apupelaaja.ekaKentta(); k < apupelaaja.getKenttia(); k++) 
            cbKentat.add(apupelaaja.getKysymys(k), null); 
        cbKentat.getSelectionModel().select(1);
        
        panelTilastot = new ScrollPane();
        gridTilastot.getChildren().clear();
        for (int k = 0; k < aputilasto.getKenttia()-aputilasto.ekaKentta(); k++) 
            gridTilastot.add(new Label(aputilasto.getKysymys(k+aputilasto.ekaKentta())), 0, k); 
    }
    
    
    /**
     * Alustaa lentopallotilastotyokalun lukemalla joukkueeseen kuuluvat pelaajat tiedostosta
     */
    private void lueTiedosto() {
        labelJoukkue.setText(joukkue.getNimi());
        setTitle("Lentopallo tilastotyˆkalu - " + joukkue.getNimi());
        hae(-1);
    }

    
    /**
     * Avaa joukkueen valinta dialogin
     * @return true jos joukkueen lˆytyy muuten false
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
     * Tallentaa tiedot
     * @return null jos onnistuu, muuten virhe tekstin‰
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
     * Avaa varmistusdialogin ja poistaa valitun ottelun tilastotyˆkalusta
     */
    private void poistaOttelu() {
        if ( ottelunIndeksi < 1) {
           Dialogs.showMessageDialog("Valitse ensin ottelu!");
           return;
        }
        
        TextField paiva = new TextField();
        TextField vastustaja = new TextField();

        for (Node child : gridTilastot.getChildren()) {
            Integer r = GridPane.getRowIndex(child);
            Integer c = GridPane.getColumnIndex(child);
            if( c == ottelunIndeksi && r == 0) paiva = (TextField) child;
            if( c == ottelunIndeksi && r == 1) vastustaja =(TextField) child;   
        }
        boolean vastaus = Dialogs.showQuestionDialog("Poisto!", "Haluatko poistaa kaikki ottelun: " + paiva.getText() + " " + vastustaja.getText() + ", tiedot?", "Kyll‰", "Ei");
        if (vastaus == false ) {
            ottelunIndeksi = -1;
            return;
        }
        int poistettuja = lentopallotilastotyokalu.poistaOttelu(paiva.getText(), vastustaja.getText());
        if (poistettuja > 0) {
            ottelunIndeksi = -1;
            naytaPelaaja();
        }       
    }


    /**
     * tallentaa ja onnistuessaan palauttaa true
     * @return true jos tallennus onnistuu
     */
    public boolean voikoSulkea() {
        String s = tallenna();
        if (s == null) return true;
        return false;
    }

    
    /**
     * Avaa ottelu dialogin
     */
    private void ottelu() {
        OtteluController.setLentopallotilastotyokalu(lentopallotilastotyokalu);
        ModalController.showModal(JoukkueenValintaController.class.getResource("OtteluView.fxml"), "Ottelu", null, joukkue);
    }

    
    /**
     * Lis‰t‰‰n tyˆkaluun uusi pelaaja
     */
    private void uusiPelaaja() {
        Pelaaja uusi = new Pelaaja();
        uusi.rekisteroi();
        uusi.asetaJId(joukkue.getTunnusNro());  
        try {
            lentopallotilastotyokalu.lisaaPelaaja(uusi);
        } catch (Exception e) {
            Dialogs.showMessageDialog("ongelmia pelaajan lis‰‰misess‰");
        }
        hae(Integer.valueOf(uusi.getKentta(0)));
        muokkaaPelaajaa();
    }
    
    
    /**
     * Hakee pelaajien tiedot listaan
     * @param id pelaajan id-numero, joka aktivoidaan haun j‰lkeen  
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
                if (Integer.valueOf(pelaaja.getKentta(0)) == pid) index = i;
                if (Integer.valueOf(pelaaja.getKentta(1)) == joukkue.getTunnusNro() )chooserPelaajat.add(pelaaja.getNimi(), pelaaja);
                i++;
            }
        } catch (SailoException ex) {
            Dialogs.showMessageDialog("J‰senen hakemisessa ongelmia! " + ex.getMessage());
        }
        chooserPelaajat.setSelectedIndex(index); // t‰st‰ tulee muutosviesti joka n‰ytt‰‰ j‰senen
    }

       
    /**
     * Avaa varmistus dialogin ja poistaa valitun pelaajan tilastotyˆkalusta
     */
    private void poistaPelaaja() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko pelaaja: " + pelaajaKohdalla.getNimi(), "Kyll‰", "Ei"); 
        if (vastaus == false ) return;
        int poistettu = lentopallotilastotyokalu.poista(pelaajaKohdalla);
        Dialogs.showMessageDialog("Poistettuja pelaajia:" + poistettu);
        hae(-1);
    }

    
    /**
     * N‰ytt‰‰ listasta valitun pelaajan tiedot 
     */
    private void naytaPelaaja() {
        areaTilastot.setText("");
        pelaajaKohdalla = chooserPelaajat.getSelectedObject();
        if (pelaajaKohdalla == null) return;
        
        nimi.setText(pelaajaKohdalla.getNimi());
        numero.setText(String.valueOf(pelaajaKohdalla.getPelinumero()));
        pelipaikka.setText(pelaajaKohdalla.getPelipaikka());
        naytaTilastot(pelaajaKohdalla);
    }
    
    
    /**
     * N‰ytt‰‰ listasta valitun pelaajan tilastot ryhmiteltyin‰ vastustajan ja p‰iv‰n mukaan
     * @param pelaaja jonka tilastot n‰ytet‰‰n
     */
    private void naytaTilastot(Pelaaja pelaaja) {
        ottelunIndeksi = -1;
        gridTilastot.getChildren().clear();
        for (int k = 0; k < aputilasto.getKenttia()-aputilasto.ekaKentta(); k++) 
            gridTilastot.add(new Label(aputilasto.getKysymys(k+aputilasto.ekaKentta())), 0, k);
        
        List<Tilasto> tilastot = lentopallotilastotyokalu.annaTilastot(pelaaja);
        if (tilastot.isEmpty()) return;
        String paiva = tilastot.get(0).getKentta(1);
        String vastustaja = tilastot.get(0).getKentta(2);
        int syottoja = 0;
        int assia = 0;
        int nostoja = 0;
        int pisteita = 0;
        int virheita = 0;
        int i = 1;
        
        for (Tilasto til: tilastot) {
            if (til.getKentta(1).contains(paiva) && til.getKentta(2).contains(vastustaja)) {
                syottoja += Integer.parseInt(til.getKentta(3));
                assia += Integer.parseInt(til.getKentta(4));
                nostoja += Integer.parseInt(til.getKentta(5));
                pisteita += Integer.parseInt(til.getKentta(6));
                virheita += Integer.parseInt(til.getKentta(7));
            }else {
                TextField tp = new TextField(paiva);
                tp.setEditable(false);
                tp.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(tp)));
                gridTilastot.add(tp, i, 0);
                TextField tv = new TextField(vastustaja);
                tv.setEditable(false);
                tv.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(tv)));
                gridTilastot.add(tv, i, 1);
                TextField ts = new TextField(Integer.toString(syottoja));
                ts.setEditable(false);
                ts.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(ts)));
                gridTilastot.add(ts, i, 2);
                TextField ta = new TextField(Integer.toString(assia));
                ta.setEditable(false);
                ta.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(ta)));
                gridTilastot.add(ta, i, 3);
                TextField tn = new TextField(Integer.toString(nostoja));
                tn.setEditable(false);
                tn.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(tn)));
                gridTilastot.add(tn, i, 4);
                TextField tpi = new TextField(Integer.toString(pisteita));
                tpi.setEditable(false);
                tpi.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(tpi)));
                gridTilastot.add(tpi, i, 5);
                TextField tvi = new TextField(Integer.toString(virheita));
                tvi.setEditable(false);
                tvi.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(tvi)));
                gridTilastot.add(tvi, i, 6); 
                i++;
                paiva = til.getKentta(1);
                vastustaja = til.getKentta(2);
                syottoja = 0;
                syottoja += Integer.parseInt(til.getKentta(3));
                assia =  0; 
                assia += Integer.parseInt(til.getKentta(4));
                nostoja = 0; 
                nostoja += Integer.parseInt(til.getKentta(5));
                pisteita = 0; 
                pisteita += Integer.parseInt(til.getKentta(6));
                virheita = 0; 
                virheita += Integer.parseInt(til.getKentta(7));
            }
            TextField tp = new TextField(paiva);
            tp.setEditable(false);
            tp.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(tp)));
            gridTilastot.add(tp, i, 0);
            TextField tv = new TextField(vastustaja);
            tv.setEditable(false);
            tv.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(tv)));
            gridTilastot.add(tv, i, 1);
            TextField ts = new TextField(Integer.toString(syottoja));
            ts.setEditable(false);
            ts.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(ts)));
            gridTilastot.add(ts, i, 2);
            TextField ta = new TextField(Integer.toString(assia));
            ta.setEditable(false);
            ta.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(ta)));
            gridTilastot.add(ta, i, 3);
            TextField tn = new TextField(Integer.toString(nostoja));
            tn.setEditable(false);
            tn.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(tn)));
            gridTilastot.add(tn, i, 4);
            TextField tpi = new TextField(Integer.toString(pisteita));
            tpi.setEditable(false);
            tpi.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(tpi)));
            gridTilastot.add(tpi, i, 5);
            TextField tvi = new TextField(Integer.toString(virheita));
            tvi.setEditable(false);
            tvi.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> valitseOttelu(GridPane.getColumnIndex(tvi)));
            gridTilastot.add(tvi, i, 6);             
        } 
    }
    
    
    /**
     * Valitsee klikatun tilaston indeksin ottelun indeksiksi
     * @param ottelunI
     */
    private void valitseOttelu(int ottelunI) {
        ottelunIndeksi = ottelunI;
    }
    
    
    /**
     * Mahdollistetaan pelaajan tietojen muokkaus ja lis‰t‰‰n labeliin muistutus tallentamisesta
     */
    private void muokkaaPelaajaa() {
        nimi.setEditable(true);
        numero.setEditable(true);
        pelipaikka.setEditable(true);
        naytaHuomautus("Muista tallentaa pelaaja");
    }
    
    
    /**
     * Tallentaa pelaajan tietoihin tehdyt muutokset, poistaa mahdollisuuden muokata tietoja ja poistaa labelin tallentamisen muistutuksesta
     */
    private void tallennaMuutokset() {
        pelaajaKohdalla = chooserPelaajat.getSelectedObject();
        pelaajaKohdalla.aseta(3, nimi.getText());
        nimi.setEditable(false);
        pelaajaKohdalla.aseta(2, numero.getText());
        numero.setEditable(false);
        pelaajaKohdalla.aseta(4, pelipaikka.getText());
        pelipaikka.setEditable(false);
        lentopallotilastotyokalu.pelaajiaMuokattu();
        tallenna();
        hae(pelaajaKohdalla.getTunnusNro());
        naytaHuomautus(null);
    }
    
    
    /**
     * N‰ytt‰‰ huomautuksen labelissa
     * @param virhe huomautus kentt‰‰n tuotava teksti, jos null poistetaan huomautuksesta punainen taustav‰ri
     */
    private void naytaHuomautus(String virhe) {
        if ( virhe == null || virhe.isEmpty() ) {
            labelHuomautus.setText("");
            labelHuomautus.getStyleClass().clear();
            return;
        }
        labelHuomautus.setText(virhe);
        labelHuomautus.getStyleClass().add("huomautus");
    }

    
    /**
     * N‰ytet‰‰n ohjelman suunnitelma erillisess‰ selaimessa.
     */
    private void avustus() {
        Desktop desktop = Desktop.getDesktop();
        try {
            URI uri = new URI("https://tim.jyu.fi/view/kurssit/tie/ohj2/2021k/ht/lrtinkiv");
            desktop.browse(uri);
        } catch (URISyntaxException e) {
            return;
        } catch (IOException e) {
            return;
        }
    }

    

    /** Asetetaan k‰ytett‰v‰ lentopallotilastotyokalu
     * @param lentopallotilastotyokalu jota k‰ytet‰‰n t‰ss‰ k‰yttˆliittym‰ss‰
     */
    public void setLentopallotilastotyokalu(Lentopallotilastotyokalu lentopallotilastotyokalu) {
        this.lentopallotilastotyokalu = lentopallotilastotyokalu;
    }
}
