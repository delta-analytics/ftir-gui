package deltaanalytics.gui.login;

import org.slf4j.LoggerFactory;

/**
 * Created by Willi on 15.12.2015.
 */
public class Checken {
    private boolean result = false;
    private boolean brukerGetVersion = false;
    private boolean brukerPingCheck = false;
    private boolean brukerVerbindung = false;
    private boolean juekeVerbindung = false;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Checken.class);


    public boolean resultermitteln() {
        if (brukerVerbindung()
                && (jukeVerbindung())
                && (datenbankCheck()))
            return true;
        else {
            return false;
        }
    }

    private boolean brukerVerbindung() {

        if (brukerGetVersion()) {
            setBrukerGetVersion(true);
        } else {
            setBrukerGetVersion(false);
        }

        if (brukerPingCheck()) {
            setBrukerPingCheck(true);
        } else {
            setBrukerPingCheck(false);
        }
        if (isBrukerGetVersion()
                && (isBrukerPingCheck())) {
            setBrukerVerbindung(true);
            return true;
        } else {
            setBrukerVerbindung(false);
            return false;
        }
    }

    private boolean jukeVerbindung() {
        if (jukeStartCom()) {
            logger.info("No USB connecntion to gas management");//26 Bytes zu poll
            setJuekeVerbindung(true);
            return true;
        } else {
            setJuekeVerbindung(false);
            return false;
        }
    }

    private boolean datenbankCheck() {
        return true;
    }


    private boolean brukerPingCheck() {
        String pingadresse = "10.10.0.5";
        logger.info("wenn keine Verbindung, dann Error-message:No LAN connection\n" +
                "to spectrometer ==> ausgabe false");

        return true;
    }

    private boolean brukerGetVersion() {
        return true;
    }

    private boolean jukeStartCom() {
        return true;
    }

    public void setResult(boolean result) {
        this.result = result;
    }

    public boolean isResult() {
        return result;
    }

    public boolean isBrukerGetVersion() {
        return brukerGetVersion;
    }

    public void setBrukerGetVersion(boolean brukerGetVersion) {
        this.brukerGetVersion = brukerGetVersion;
    }

    public boolean isBrukerPingCheck() {
        return brukerPingCheck;
    }

    public void setBrukerPingCheck(boolean brukerPingCheck) {
        this.brukerPingCheck = brukerPingCheck;
    }

    public boolean isBrukerVerbindung() {
        return brukerVerbindung;
    }

    public void setBrukerVerbindung(boolean brukerVerbindung) {
        this.brukerVerbindung = brukerVerbindung;
    }

    public boolean isJuekeVerbindung() {
        return juekeVerbindung;
    }

    public void setJuekeVerbindung(boolean juekeVerbindung) {
        this.juekeVerbindung = juekeVerbindung;
    }
}
