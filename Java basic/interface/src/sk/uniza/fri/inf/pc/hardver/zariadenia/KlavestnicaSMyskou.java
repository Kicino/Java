package sk.uniza.fri.inf.pc.hardver.zariadenia;

import sk.uniza.fri.inf.pc.hardver.UsbPort;
import sk.uniza.fri.inf.pc.hardver.UsbZariadenie;

public class KlavestnicaSMyskou implements UsbZariadenie {

    private String nazov;
    private String vyrobca;
    private UsbPort usbPort;
    private boolean maNumerickuCast;
    private boolean jeBezdrotova;
    private int pocetTlacidielMysi;
    private String typZariadenia;

    public KlavestnicaSMyskou(String nazov, String vyrobca, boolean maNumerickuCast, int pocetTlacidielMysi, boolean jeBezdrotova) {
        this.nazov = nazov;
        this.vyrobca = vyrobca;
        this.maNumerickuCast = maNumerickuCast;
        this.jeBezdrotova = jeBezdrotova;
        this.pocetTlacidielMysi = pocetTlacidielMysi;
        this.typZariadenia = "Klavestnica s myskou";
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
        return "(ma numericku cast: " + this.returnBooleanInWords(this.maNumerickuCast) + ", pocet tlacidiel na mysi: " + this.pocetTlacidielMysi + ", bezdrotova: " + this.returnBooleanInWords(this.jeBezdrotova) + ")";
    }

    private String returnBooleanInWords(boolean b) {
        if (b) {
            return "ano";
        } else {
            return "nie";
        }
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

    @Override
    public String getTypZariadenia() {
        return this.typZariadenia;
    }

    @Override
    public void vypisVsetkyKlavestnice() {

    }

    @Override
    public void vypisVsetkyMysky() {

    }
}
