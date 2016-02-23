package deltaanalytics.gui.math;

import java.util.ArrayList;
import java.util.List;

public class LevenbergMarquardtParameterList {
    private static List<LevenbergMarquardtParameters> levenbergMarquardtParameterList; 
    
    public static List<LevenbergMarquardtParameters> getLevenbergMarquardtParameterList(List<Integer> moleculeList){
        if(levenbergMarquardtParameterList == null) {
            List<LevenbergMarquardtParameters> lmParameterList = new ArrayList<>();

            moleculeList
                    .stream()
                    .forEach(i -> lmParameterList.add(new LevenbergMarquardtParameters(i)));  // @ToDo call GUI
            
            return lmParameterList;            
        } else {
            return levenbergMarquardtParameterList;
        }
    }
    
}
