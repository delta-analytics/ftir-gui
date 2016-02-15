package deltaanalytics.gui.math;

/** ToDo get parameters from GUI
 *  vector of initial parameters for use of Hitran data base
*/
public class HitranParameters {
    private boolean[] callHitran;  // which moleclue to investigate
    private double[] lowWN;  // low wavenumbers
    private double[] highWN;  // high wavenumbers
    private int molecule;  // molecule 1=H2O  2=CO2  3=N2O  4=CO  5=CH4  6=NO  7=NO2
    private int baselineStep;  // range of wavenumbers
    private double stp;;  // frequency spacing in cm-1, corresponds to 0.04 resolution in cm-1
    private double intensThres1; // intensity threshold1 
    private int isotopo1;  //default 1
    private double intensThres2; // intensity threshold2 
    private int isotopo2;  // default 2
    private double sf;  // scaling factor isotopo2
    private double Temp;  // Temperature 40 °C
    private double Patm;  // total Pressure in ATM units
    private int Dd;  // wings of each line in wavenumbers of cm-1
    
    public HitranParameters(){
        this.callHitran = new boolean[] {true, true, true, true, false, false, true};
        this.lowWN = new double[] {3860, 3470, 2500, 2080, 2900, 3730, 2840};
        this.highWN = new double[] {3965, 3760, 2600, 2141, 3165, 3780, 2940};
        this.molecule = 4;
        this.baselineStep = 0;
        this.stp = 0.02;
        this.intensThres1 = 1e-25;
        this.isotopo1 = 1;
        this.intensThres2 = 1e-25;
        this.isotopo2 = 2;
        this.sf = 1;
        this.Temp = 313;
        this.Patm = 1;
        this.Dd = 5;
    }

    public boolean[] getCallHitran() {
        return callHitran;
    }

    public void setCallHitran(boolean[] callHitran) {
        this.callHitran = callHitran;
    }

    public double[] getLowWN() {
        return lowWN;
    }

    public void setLowWN(double[] lowWN) {
        this.lowWN = lowWN;
    }

    public double[] getHighWN() {
        return highWN;
    }

    public void setHighWN(double[] highWN) {
        this.highWN = highWN;
    }

    public int getMolecule() {
        return molecule;
    }

    public void setMolecule(int molecule) {
        this.molecule = molecule;
    }

    public int getBaselineStep() {
        return baselineStep;
    }

    public void setBaselineStep(int baselineStep) {
        this.baselineStep = baselineStep;
    }

    public double getStp() {
        return stp;
    }

    public void setStp(double stp) {
        this.stp = stp;
    }

    public double getIntensThres1() {
        return intensThres1;
    }

    public void setIntensThres1(double intensThres1) {
        this.intensThres1 = intensThres1;
    }

    public int getIsotopo1() {
        return isotopo1;
    }

    public void setIsotopo1(int isotopo1) {
        this.isotopo1 = isotopo1;
    }

    public double getIntensThres2() {
        return intensThres2;
    }

    public void setIntensThres2(double intensThres2) {
        this.intensThres2 = intensThres2;
    }

    public int getIsotopo2() {
        return isotopo2;
    }

    public void setIsotopo2(int isotopo2) {
        this.isotopo2 = isotopo2;
    }

    public double getSf() {
        return sf;
    }

    public void setSf(double sf) {
        this.sf = sf;
    }

    public double getTemp() {
        return Temp;
    }

    public void setTemp(double Temp) {
        this.Temp = Temp;
    }

    public double getPatm() {
        return Patm;
    }

    public void setPatm(double Patm) {
        this.Patm = Patm;
    }

    public int getDd() {
        return Dd;
    }

    public void setDd(int Dd) {
        this.Dd = Dd;
    }

    
    
}
