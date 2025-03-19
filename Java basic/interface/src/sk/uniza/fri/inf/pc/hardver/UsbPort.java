package sk.uniza.fri.inf.pc.hardver;

public class UsbPort{

    private boolean jeObsadeny;
    private UsbZariadenie usbZariadenie;

    public UsbPort() {
        this.jeObsadeny = false;
        this.usbZariadenie = null;
    }

    public boolean jeObsadeny() {
        return this.jeObsadeny;
    }

    public UsbZariadenie getUsbZariadenie() {
        return this.usbZariadenie;
    }

    public void setUsbZariadenie(UsbZariadenie usbZariadenie) {
        this.usbZariadenie = usbZariadenie;
    }

    public void setJeObsadeny(boolean jeObsadeny) {
        this.jeObsadeny = jeObsadeny;
    }

}
