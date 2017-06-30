import java.util.ArrayList;
import java.util.List;

public class Currency {
    private List high = new ArrayList<Double>();
    private List vol = new ArrayList<Double>();
    private List avg = new ArrayList<Double>();
    private List volCur = new ArrayList<Double>();
    private List last = new ArrayList<Double>();
    private List low = new ArrayList<Double>();
    private List buy = new ArrayList<Double>();
    private List sell = new ArrayList<Double>();
    private List updated = new ArrayList<Double>();
    public void addValue(List<Double> array ,Object value) {
        if(value instanceof Long) {
             value = ((Long) value).doubleValue();
        }
        array.add((Double) value);
    }
    public List getHigh() {
        return high;
    }
    public List getVol() {
        return vol;
    }
    public List getAvg() {
        return avg;
    }
    public List getVolCur() {
        return volCur;
    }
    public List getLast() {
        return last;
    }
    public List getLow() {
        return low;
    }
    public List getBuy() {
        return buy;
    }
    public List getSell() {
        return sell;
    }
    public List getUpdated() {
        return updated;
    }
}
