package deltaanalytics.gui.math;

/** ToDo get parameters from GUI
 *  base class for one molecule
 *  vector of initial parameters for vectors pin, dp, minValues and maxValues:  6 or 8 items
 *  nlCorr = false (parameter length 6)
 *  - offset, resolution, FOV, mixing ratio, baseline scale, wavenumber shift
 *  nlCorr = true (parameter length 8)
 *  - offset, resolution, FOV, mixing ratio, baseline scale, wavenumber shift, 2 polynomial constants... e.g. -1e-5; 1e-10;
*/
public class LevenbergMarquardtParameters {
    private int molecule;
    private double[] dp;  // initial fractional change in parameter
    private double[] pin;  // initial parameter input
    private double[] minValues;  // max constraint
    private double[] maxValues;  // min constraint
    private double stol;  // tolerance criterium of aborting fit
    private int niter;  // max number of iterations
    private boolean nlCorr;  // nonlinear correction for spectrum
    
    public LevenbergMarquardtParameters(int molecule){
        this.molecule = molecule;
        this.dp = new double[] {0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01, 0.01};
        this.pin = new double[] {1e-4, 1.0, 22.5*1e-3, 2e-6, 0.0, 0.0, -1e-5, 1e-10};
        this.minValues = new double[] {-0.1, 0.5, 5*1e-3, 0.0, 0.0, -0.1, -1e-2, 0};
        this.maxValues = new double[] { 0.1, 2.0, 40*1e-3, 1e-3, 1.0, 0.1, 0.0, 1e-2};
        this.stol = 1e-4;
        this.niter = 15;
        this.nlCorr = false;        
    }

    public double[] getDp() {
        return dp;
    }

    public void setDp(double[] dp) {
        this.dp = dp;
    }

    public double[] getPin() {
        return pin;
    }

    public void setPin(double[] pin) {
        this.pin = pin;
    }

    public double[] getMinValues() {
        return minValues;
    }

    public void setMinValues(double[] minValues) {
        this.minValues = minValues;
    }

    public double[] getMaxValues() {
        return maxValues;
    }

    public void setMaxValues(double[] maxValues) {
        this.maxValues = maxValues;
    }

    public double getStol() {
        return stol;
    }

    public void setStol(double stol) {
        this.stol = stol;
    }

    public int getNiter() {
        return niter;
    }

    public void setNiter(int niter) {
        this.niter = niter;
    }

    public boolean isNlCorr() {
        return nlCorr;
    }

    public void setNlCorr(boolean nlCorr) {
        this.nlCorr = nlCorr;
    }

    public int getMolecule() {
        return molecule;
    }

    public void setMolecule(int molecule) {
        this.molecule = molecule;
    }


}
