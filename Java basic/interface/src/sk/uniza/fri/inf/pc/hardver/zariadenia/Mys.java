package sk.uniza.fri.inf.pc.hardver.zariadenia;

import sk.uniza.fri.inf.pc.hardver.UsbPort;
import sk.uniza.fri.inf.pc.hardver.UsbZariadenie;

public class Mys implements UsbZariadenie {

    private  String vyrobca;
    private  int pocetTlacidiel;
    private  boolean jeBezdrotova;
    private String nazov;
    private String typZariadenia;
    private UsbPort usbPort;

    public Mys(String nazov, String vyrobca, int pocetTlacidiel, boolean jeBezdrotova) {
        this.nazov = nazov;
        this.vyrobca = vyrobca;
        this.pocetTlacidiel = pocetTlacidiel;
        this.jeBezdrotova = jeBezdrotova;
        this.typZariadenia = "Mys";
        this.usbPort = null;
    }

    @Override
    public String getNazov() {
        return this.nazov;
    }

    @Override
    public String getVyrobca() {
        return this.vyrobca;
    }

    @Override
    public String getInfo() {
        return "(pocet tlacidiel: " + this.pocetTlacidiel + ", bezdrotova: " + this.returnBooleanInWords(this.jeBezdrotova) + ")";
    }

    private String returnBooleanInWords(boolean b) {
        if (b) {
            return "ano";
        } else {
            return "nie";
        }
    }

    public int getPocetTlacidiel() {
        return this.pocetTlacidiel;
    }

    public boolean jeBezdrotova() {
        return this.jeBezdrotova;
    }

    @Override
    public void pripojDoUsbPortu(UsbPort usbPort) {
        if (usbPort != null) {
            if (!usbPort.jeObsadeny()) {
                usbPort.setJeObsadeny(true);
                usbPort.setUsbZariadenie(this);
                this.usbPort = usbPort;
            }
        }
    }

    @Override
    public void odpojZUsbPortu() {
        this.usbPort.setJeObsadeny(false);
    }

    public String getTypZariadenia() {
        return "Mys";
    }

    @Override
    public void vypisVsetkyKlavestnice() {

    }

    @Override
    public void vypisVsetkyMysky() {

    }
}
