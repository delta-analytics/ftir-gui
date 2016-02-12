package deltaanalytics.gui.login;

import deltaanalytics.bruker.data.entity.BrukerDataEntity;
import deltaanalytics.bruker.hardware.CommandRunner;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by Willi on 15.12.2015.
 */
public class Checken {
    private boolean result;
    private boolean brukerGetVersion;
    private boolean brukerPingCheck;
    private boolean brukerVerbindung;
    private boolean juekeVerbindung;
    private boolean dbconnect;

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
        if (isBrukerVerbindung()
                && isJuekeVerbindung()
                    && isDbconnect() ) {
            setResult(ok);
            return true;
        } else {
            setResult(ok);
            return false;
        }

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
        CommandRunner commandRunner = new CommandRunner();
        String getverionstr;
        try {
           getverionstr = commandRunner.getVersion("localhost",5005) ;
            if (commandRunner.getVersion("localhost",5005).equals(""))
            return true;
        } catch (Exception e) {
           // e.printStackTrace();
            e.getMessage();
            logger.warn(e.getMessage());
            return false;
        }
        logger.info("getversion: " + getverionstr);
        return true;
    }

    public  boolean isDbconnect() {
        return dbconnect;
    }

    public  void setDbconnect(boolean dbconnect) {
        this.dbconnect = dbconnect;
    }

    public  boolean isResult() {
        return result;
    }

    public  void setResult(boolean result) {
        result = result;
    }

    public  boolean isBrukerGetVersion() {
        return brukerGetVersion;
    }

    public  void setBrukerGetVersion(boolean brukerGetVersion) {
        brukerGetVersion = brukerGetVersion;
    }

    public  boolean isBrukerPingCheck() {
        return brukerPingCheck;
    }

    public  void setBrukerPingCheck(boolean brukerPingCheck) {
        brukerPingCheck = brukerPingCheck;
    }

    public  boolean isBrukerVerbindung() {
        return brukerVerbindung;
    }

    public  void setBrukerVerbindung(boolean bruker) {
        brukerVerbindung = bruker;
    }

    public  boolean isJuekeVerbindung() {
        return juekeVerbindung;
    }

    public  void setJuekeVerbindung(boolean juekeVerbindung) {
        this.juekeVerbindung = juekeVerbindung;
    }
}

