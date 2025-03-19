package sk.uniza.fri.inf.pc.hardver.zariadenia;

import sk.uniza.fri.inf.pc.hardver.Pocitac;
import sk.uniza.fri.inf.pc.hardver.UsbPort;
import sk.uniza.fri.inf.pc.hardver.UsbZariadenie;

import java.util.ArrayList;
import java.util.Optional;

public class UsbHub implements UsbZariadenie {

    private String nazov;
    private String vyrobca;
    private int pocetPortov;
    private UsbPort usbPort;
    private ArrayList<UsbPort> usbPorty;
    private Pocitac pocitac;
    private String typZariadenia;

    public UsbHub(Pocitac pocitac, String nazov,String vyrobca, int pocetPortov) {
        this.nazov = nazov;
        this.vyrobca = vyrobca;
        this.pocetPortov = pocetPortov;
        this.usbPort = null;
        this.usbPorty = new ArrayList<>();
        this.pocitac = pocitac;
        this.typZariadenia = "UsbHub";
        this.pridajZariadenia();
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
        return "(pocet portov: " + this.pocetPortov + ")" + this.vypisVsetkyZariadenia();
    }

    public int getPocetPortov() {
        return this.pocetPortov;
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
        for (var usbPort : usbPorty) {
            if (usbPort.jeObsadeny()) {
                if (usbPort.getUsbZariadenie() instanceof Klavestnica || usbPort.getUsbZariadenie() instanceof KlavestnicaSMyskou) {
                    System.out.println("- " + usbPort.getUsbZariadenie().getTypZariadenia() + " " + usbPort.getUsbZariadenie().getVyrobca() + " " + usbPort.getUsbZariadenie().getNazov() + " " + usbPort.getUsbZariadenie().getInfo());
                } else if(usbPort.getUsbZariadenie() instanceof UsbHub) {
                    usbPort.getUsbZariadenie().vypisVsetkyKlavestnice();
                }
            }
        }
    }

    @Override
    public void vypisVsetkyMysky() {
        for (var usbPort : usbPorty) {
            if (usbPort.jeObsadeny()) {
                if (usbPort.getUsbZariadenie() instanceof Mys || usbPort.getUsbZariadenie() instanceof KlavestnicaSMyskou) {
                    System.out.println("- " + usbPort.getUsbZariadenie().getTypZariadenia() + " " + usbPort.getUsbZariadenie().getVyrobca() + " " + usbPort.getUsbZariadenie().getNazov() + " " + usbPort.getUsbZariadenie().getInfo());
                } else if(usbPort.getUsbZariadenie() instanceof UsbHub) {
                    usbPort.getUsbZariadenie().vypisVsetkyMysky();
                }
            }
        }
    }

    public UsbPort getUsbPort(int index) {
        return this.usbPorty.get(index);
    }

    public Optional<UsbPort> getVolnyUsbPort() {
        for (var usbPort : usbPorty) {
            if (!usbPort.jeObsadeny()) {
                return Optional.of(usbPort);
            }
        }
        return Optional.empty();
    }

    private ArrayList<UsbPort> pridajZariadenia() {
        for (int i = 0; i < this.pocetPortov; i++) {
            this.usbPorty.add(new UsbPort());
        }
        return this.usbPorty;
    }

    private String vypisVsetkyZariadenia() {

        var i = 0;
        var text = "";
        var spaces = "";
        for (var usbPort : usbPorty) {
            if (spaces.equals("")) {
                for (int j = 0; j <= this.pocitac.getPocetUsbHubov(); j++) {
                    spaces += "  ";
                }
            }
            if (usbPort.jeObsadeny()) {
                if (usbPort.getUsbZariadenie() instanceof UsbHub) {
                    this.pocitac.setPocetUsbHubov(1);
                }
                text += "\n" + spaces + "[" + i + "] " + usbPort.getUsbZariadenie().getTypZariadenia() + " " + usbPort.getUsbZariadenie().getVyrobca() + " " + usbPort.getUsbZariadenie().getNazov() + " " + usbPort.getUsbZariadenie().getInfo();
            } else {
                text += "\n" + spaces + "[" + i + "] (volny)";

            }
            i++;
        }
        return text;
    }
}
