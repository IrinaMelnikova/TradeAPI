package APIRequest;

public class Rates {

    private Double high;
    private Double vol;
    private Double avg;
    private Double vol_cur;
    private Double last;
    private Double low;
    private Double buy;
    private Double sell;
    private Double updated;

    public Double getHigh() {
        return high;
    }
    public Double getVol() {
        return vol;
    }
    public Double getAvg() {
        return avg;
    }
    public Double getVol_cur() {
        return vol_cur;
    }
    public Double getLast() {
        return last;
    }
    public Double getLow() {
        return low;
    }
    public Double getBuy() {
        return buy;
    }
    public Double getSell() {
        return sell;
    }
    public Double getUpdated() {
        return updated;
    }

    public void setHigh(Double high) {
        this.high = high;
    }
    public void setVol(Double vol) {
        this.vol = vol;
    }
    public void setAvg(Double avg) {
        this.avg = avg;
    }
    public void setVol_cur(Double vol_cur) {
        this.vol_cur = vol_cur;
    }
    public void setLast(Double last) {
        this.last = last;
    }
    public void setLow(Double low) {
        this.low = low;
    }
    public void setBuy(Double buy) {
        this.buy = buy;
    }
    public void setSell(Double sell) {
        this.sell = sell;
    }
    public void setUpdated(Double updated) {
        this.updated = updated;
    }
}
