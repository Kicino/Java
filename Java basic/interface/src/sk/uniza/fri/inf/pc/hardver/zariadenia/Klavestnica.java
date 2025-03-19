package sk.uniza.fri.inf.pc.hardver.zariadenia;

import sk.uniza.fri.inf.pc.hardver.UsbPort;
import sk.uniza.fri.inf.pc.hardver.UsbZariadenie;

public class Klavestnica implements UsbZariadenie{

    private String vyrobca;
    private boolean maNumerickuCast;
    private String nazov;
    private String typZariadenia;
    private UsbPort usbPort;

    public Klavestnica(String nazov, String vyrobca, boolean maNumerickuCast) {
        this.nazov = nazov;
        this.vyrobca = vyrobca;
        this.maNumerickuCast = maNumerickuCast;
        this.typZariadenia = "Klavestnica";
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
        return "(ma numericku cast: " + this.returnBooleanInWords(this.maNumerickuCast) + ")";
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
