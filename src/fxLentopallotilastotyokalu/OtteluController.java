package fxLentopallotilastotyokalu;


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
 * @author Reetu Inkil‰
 * @version 29.1.2021
 *
 */
public class OtteluController implements ModalControllerInterface<Joukkue>  {

    @FXML private ListChooser<Pelaaja> chooserPelaajat;
    @FXML private ListChooser<String> chooserTilastot;
    @FXML private TextField textVastustaja;
    @FXML private Label labelViimeisin;
    @FXML private DatePicker pickPaiva;
    
    @FXML void HandlePoistaViimeisin() {
        boolean vastaus = Dialogs.showQuestionDialog("Poisto?",
                "Poistetaanko viimeisin tilasto: " + labelViimeisin.getText(), "Kyll‰", "Ei"); 
        if (vastaus == true ) Dialogs.showMessageDialog("Ei osata viel‰ poistaa tilastoja");
        // TODO: korvaa tilaston poistamisella
    }

    @FXML void handleTallenna() throws SailoException {
        tallennaTilasto();
        // TODO: korvaa tilaston tallentamisella
    }

    @FXML void handleTallennaPoistu() {
        Dialogs.showMessageDialog("Ei osata viel‰ tallentaa, mutta poistutaan");
        ModalController.closeStage(textVastustaja);

    }


 
    
///=================================================================================================================================================================
/// T‰st‰ eteenp‰in ei suoraan k‰yttˆliittym‰‰n liittyv‰‰ koodia
    
    private static Lentopallotilastotyokalu lentopallotilastotyokalu;
    private static Joukkue joukkue;
    private static Pelaaja pelaajaKohdalla;
    private String suorite;

    
    
    /**
     * Hakee pelaajien tiedot listaan
     */
    private void hae() {
        chooserPelaajat.clear();
        for (int i = 0; i < lentopallotilastotyokalu.getPelaajia(); i++) {
            Pelaaja pelaaja = lentopallotilastotyokalu.annaPelaaja(i);
            if (pelaaja.getJId() == joukkue.getId() )chooserPelaajat.add(pelaaja.getNimi(), pelaaja);
        }
    }
    
    /**
     * Lis‰t‰‰n valittu tilasto
     * @throws SailoException jos tilaston lis‰‰misess‰ ongelmia
     */
    private void tallennaTilasto() throws SailoException {
        int pId;
        String vastustaja;
        try {
            pId = pelaajaKohdalla.getpId();
            vastustaja = textVastustaja.getText();
            if (vastustaja.contains(""))return;
        }catch ( java.lang.NullPointerException e) {
            return;
        }
        
        Tilasto uusi = new Tilasto(pId, vastustaja, suorite);
        lentopallotilastotyokalu.lisaaTilasto(uusi);
        labelViimeisin.setText(uusi.toString());
    }
    
    /**
     * Valitsee chooserissa klikatun pelaajan
     */
    private void valitsePelaaja() {
        pelaajaKohdalla = chooserPelaajat.getSelectedObject();
        if (pelaajaKohdalla == null) return;
    }
    
    /**
     * Valitsee chooserissa klikatun tilastotyypin 
     */
    private void valitseSuorite() {
        suorite = chooserTilastot.getSelectedObject();
        if (suorite == null) return;
    }
    
    /**Asetetaan k‰ytett‰v‰ lentopallotilastotyokalu
     * @param tyokalu lentopallotilastotyokalu jota k‰ytet‰‰n t‰ss‰ k‰yttˆliittym‰ss‰
     */
    public static void setLentopallotilastotyokalu(Lentopallotilastotyokalu tyokalu) {
        lentopallotilastotyokalu = tyokalu;
    }

    /**
     * Kun dialogi on avattu lis‰t‰‰n kuuntelijat list choosereille ja lis‰t‰‰n String oliot suorite listaan
     */
    @Override
    public void handleShown() {
        hae();
        chooserPelaajat.addSelectionListener(e -> valitsePelaaja());
        chooserTilastot.clear();
        chooserTilastot.add("Syˆttˆ");
        chooserTilastot.add("ƒss‰");
        chooserTilastot.add("Nosto");
        chooserTilastot.add("Piste");
        chooserTilastot.add("Virhe");
        chooserTilastot.addSelectionListener(e -> valitseSuorite());
    }
    
    /**
     * Mit‰ palautetaan dialogista GUIControllerille
     */
    @Override
    public Joukkue getResult() {
        return null;
    }

    /**
     * Mit‰ tehd‰‰n tuodulla parametrilla
     */
    @Override
    public void setDefault(Joukkue avattuJoukkue) {
        joukkue = avattuJoukkue;     
    }
}