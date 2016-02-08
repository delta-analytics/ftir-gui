package deltaanalytics.gui.login;

import deltaanalytics.bruker.data.entity.BrukerDataEntity;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Willi on 15.12.2015.
 */
public class Checken {
    private static boolean result;
    private static boolean brukerGetVersion;
    private static boolean brukerPingCheck;
    private static boolean brukerVerbindung;
    private static boolean juekeVerbindung;
    private static boolean dbconnect;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(Checken.class);


    public boolean resultermitteln() {
        Boolean ok;
        brukerVerbindung();
        juke();
        datenbankCheck();

        if (isBrukerVerbindung()) {
            ok = true;
            if (isJuekeVerbindung()) {
                ok = true;
                if (isDbconnect()) {
                    ok = true;
                } else
                    ok = false;
            } else
                ok = false;
        } else {
            ok = false;
        }
        setResult(ok);
        return ok;
    }

    private void brukerVerbindung() {
        String  host = new String("192.168.178.11");
        if (brukerGetVersion()) {
            setBrukerGetVersion(true);
        } else {
            setBrukerGetVersion(false);
        }

        if (brukerPingCheck(host)) {
            setBrukerPingCheck(true);
        } else {
            setBrukerPingCheck(false);
        }
        if (isBrukerGetVersion()
                && (isBrukerPingCheck())) {
            setBrukerVerbindung(true);
        } else {
            setBrukerVerbindung(false);
        }
    }



    private void juke() {
        if (jukeStartCom()) {
            logger.info("No USB connecntion to gas management");//26 Bytes zu poll
            setJuekeVerbindung(true);
        } else {
            setJuekeVerbindung(false);
        }
    }

    private void datenbankCheck() {
        setDbconnect(true);
    }

   /* private boolean brukerPingCheck(String host) {
        String pingadresse = "10.10.0.5";
        int i;

        logger.info("wenn keine Verbindung, dann Error-message:No LAN connection " +
                "to spectrometer ==> ausgabe false");
            try{
                String strCommand = "";

                    // construct command for Windows Operating system
                    strCommand = "ping -n 1 " + host;
                logger.info("Ping: " + strCommand);
                // Execute the command constructed
                Process myProcess = Runtime.getRuntime().exec(strCommand);


                myProcess.getErrorStream().close();
                myProcess.getInputStream().close();
                myProcess.getOutputStream().close();

                i = myProcess.waitFor();
             //  logger.info("" + myProcess.exitValue());
              //  if (myProcess.waitFor() == 0)
                if( i == 0) {
                    return true;
                } else {
                    return false;
                }
            } catch( Exception e ) {
                e.printStackTrace();
                return false;
            }
        }*/


      private boolean brukerPingCheck(String host) {
          Boolean pingcheck = false;

          try {
              String strCommand = "";
                host = "192.168.178.11";
              // construct command for Windows Operating system
              strCommand = "ping -n 1 " + host;
              logger.info("Ping: " + strCommand);
              Process p = Runtime.getRuntime().exec(strCommand);
              BufferedReader inputStream = new BufferedReader(
                      new InputStreamReader(p.getInputStream()));
              BufferedReader stdError = new BufferedReader(
                      new InputStreamReader(p.getErrorStream()));

              String s = "";
              // reading output stream of the command
              while ((s = inputStream.readLine()) != null)  {
                  logger.info("ping string:" + s);
                  if ((s.contains("nicht erreichbar"))
                  || (s.contains("berschreitung"))) {
                      pingcheck = false; }
                  else {
                      pingcheck  = true;
                  }
              }
          } catch (Exception e) {
              e.printStackTrace();
              pingcheck = false;
          }
          return pingcheck;
      }



    private boolean jukeStartCom() {
        return true;
    }

    private boolean brukerGetVersion() {
        BrukerDataEntity brukerDataEntity;

        return true;
    }

    public static boolean isDbconnect() {
        return dbconnect;
    }

    public static void setDbconnect(boolean dbconnect) {
        Checken.dbconnect = dbconnect;
    }

    public static boolean isResult() {
        return result;
    }

    public static void setResult(boolean result) {
        Checken.result = result;
    }

    public static boolean isBrukerGetVersion() {
        return brukerGetVersion;
    }

    public static void setBrukerGetVersion(boolean brukerGetVersion) {
        Checken.brukerGetVersion = brukerGetVersion;
    }

    public static boolean isBrukerPingCheck() {
        return brukerPingCheck;
    }

    public static void setBrukerPingCheck(boolean brukerPingCheck) {
        Checken.brukerPingCheck = brukerPingCheck;
    }

    public static boolean isBrukerVerbindung() {
        return brukerVerbindung;
    }

    public static void setBrukerVerbindung(boolean bruker) {
        Checken.brukerVerbindung = bruker;
    }

    public static boolean isJuekeVerbindung() {
        return juekeVerbindung;
    }

    public static void setJuekeVerbindung(boolean juekeVerbindung) {
        Checken.juekeVerbindung = juekeVerbindung;
    }
}

