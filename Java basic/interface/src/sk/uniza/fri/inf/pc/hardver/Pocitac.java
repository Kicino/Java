package sk.uniza.fri.inf.pc.hardver;

import sk.uniza.fri.inf.pc.hardver.zariadenia.Klavestnica;
import sk.uniza.fri.inf.pc.hardver.zariadenia.KlavestnicaSMyskou;
import sk.uniza.fri.inf.pc.hardver.zariadenia.Mys;
import sk.uniza.fri.inf.pc.hardver.zariadenia.UsbHub;

import java.util.ArrayList;
import java.util.Optional;

public class Pocitac {

    private int pocetUsbPortov;
    private ArrayList<UsbPort> usbPorty;
    private int pocetUsbHubov;

    public Pocitac(int pocetUsbPortov) {
        this.pocetUsbPortov = pocetUsbPortov;
        this.usbPorty = new ArrayList<>();
        this.pocetUsbHubov = 0;
        this.pridajZariadenia();
    }

    public int getPocetUsbPortov() {
        return this.pocetUsbPortov;
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

    public void vypisVsetkyZariadenia() {
        var i = 0;
        for (var usbPort : usbPorty) {
            if (usbPort.jeObsadeny()) {
                if (usbPort.getUsbZariadenie() instanceof UsbHub) {
                    this.pocetUsbPortov++;
                }
                System.out.println("[" + i + "] " + usbPort.getUsbZariadenie().getTypZariadenia() + " " + usbPort.getUsbZariadenie().getVyrobca() + " " + usbPort.getUsbZariadenie().getNazov() + " " + usbPort.getUsbZariadenie().getInfo());
            } else {
                System.out.println("[" + i + "] (volny)");
            }
            i++;
        }
        System.out.println("\n---------------------------------------");
        this.setPocetUsbHubov(-this.getPocetUsbHubov());
    }

    private void pridajZariadenia() {
        for (int i = 0; i < this.pocetUsbPortov; i++) {
            this.usbPorty.add(new UsbPort());
        }
    }

    public void vypisVsetkyMysky() {
        System.out.println();
        System.out.println("Pripojene mysky:");
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

    public void vypisVsetkyKlavesnice() {
        System.out.println();
        System.out.println("Pripojene klavestnice:");
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

    public int getPocetUsbHubov() {
        return this.pocetUsbHubov;
    }

    public void setPocetUsbHubov(int pocetUsbHubov) {
        this.pocetUsbHubov += pocetUsbHubov;
    }
}
