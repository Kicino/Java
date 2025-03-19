package sk.uniza.fri.inf.pc.hardver;

public interface UsbZariadenie {
    String getNazov();
    String getVyrobca();
    String getInfo();
    void pripojDoUsbPortu(UsbPort usbPort);
    void odpojZUsbPortu();
    String getTypZariadenia();
    void vypisVsetkyKlavestnice();
    void vypisVsetkyMysky();
}
