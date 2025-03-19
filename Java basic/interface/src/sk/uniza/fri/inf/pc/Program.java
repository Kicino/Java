package sk.uniza.fri.inf.pc;

import sk.uniza.fri.inf.pc.hardver.Pocitac;
import sk.uniza.fri.inf.pc.hardver.zariadenia.Klavestnica;
import sk.uniza.fri.inf.pc.hardver.zariadenia.KlavestnicaSMyskou;
import sk.uniza.fri.inf.pc.hardver.zariadenia.Mys;
import sk.uniza.fri.inf.pc.hardver.zariadenia.UsbHub;

public class Program {
    public static void main(String[] args) {
        var mojNotebook = new Pocitac(4);

        var usbPort0 = mojNotebook.getVolnyUsbPort();
        var klavesnica = new Klavestnica("G910 Orion Spectrum", "Logitech", true);
        klavesnica.pripojDoUsbPortu(usbPort0.get());

        var usbPort2 = mojNotebook.getUsbPort(2);
        var mys = new Mys("M705 Marathon Mouse", "Logitech", 5, true);;
        mys.pripojDoUsbPortu(usbPort2);

        var usbPort3 = mojNotebook.getUsbPort(3);
        var usbHub = new UsbHub(mojNotebook,"HUE-SA7BP", "AXAGON", 7);
        usbHub.pripojDoUsbPortu(usbPort3);

        klavesnica.odpojZUsbPortu();
        klavesnica.pripojDoUsbPortu(usbHub.getUsbPort(5));

        var usbHubMaly = new UsbHub(mojNotebook, "HUE-S2B USB 3.0", "AXAGON", 4);
        usbHubMaly.pripojDoUsbPortu(usbHub.getUsbPort(2));

        var usbPort4 = mojNotebook.getUsbPort(1);
        var klavestnicaSMyskou = new KlavestnicaSMyskou("All-in-one Media Keyboard", "Microsoft", false, 2, true);
        klavestnicaSMyskou.pripojDoUsbPortu(usbPort4);

        mojNotebook.vypisVsetkyZariadenia();
        mojNotebook.vypisVsetkyMysky();
        mojNotebook.vypisVsetkyKlavesnice();
    }
}