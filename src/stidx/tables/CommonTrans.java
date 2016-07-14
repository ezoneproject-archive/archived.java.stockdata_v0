package stidx.tables;

import java.io.Serializable;

public class CommonTrans implements Serializable {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = -688406744563671889L;

    String gsDate = "";
    String gsTime = "";
    String stockCode = "";

    int todayForeign = 0;
    int todayInvest = 0;

    float yestRate = 0.0f;
    float yestTransRate = 0.0f;
    int subtotForeign = 0;
    int subtotInvest = 0;
    int yestForeign = 0;
    int yestInvest = 0;
    float rateReserve = 0.0f;
    float EV_EBITDA = 0.0f;

    public String getGsDate() {
        return gsDate;
    }

    public void setGsDate(String gsDate) {
        this.gsDate = gsDate;
    }

    public String getGsTime() {
        return gsTime;
    }

    public void setGsTime(String gsTime) {
        this.gsTime = gsTime;
    }

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public int getTodayForeign() {
        return todayForeign;
    }

    public void setTodayForeign(int todayForeign) {
        this.todayForeign = todayForeign;
    }

    public int getTodayInvest() {
        return todayInvest;
    }

    public void setTodayInvest(int todayInvest) {
        this.todayInvest = todayInvest;
    }

    public float getYestRate() {
        return yestRate;
    }

    public void setYestRate(float yestRate) {
        this.yestRate = yestRate;
    }

    public float getYestTransRate() {
        return yestTransRate;
    }

    public void setYestTransRate(float yestTransRate) {
        this.yestTransRate = yestTransRate;
    }

    public int getSubtotForeign() {
        return subtotForeign;
    }

    public void setSubtotForeign(int subtotForeign) {
        this.subtotForeign = subtotForeign;
    }

    public int getSubtotInvest() {
        return subtotInvest;
    }

    public void setSubtotInvest(int subtotInvest) {
        this.subtotInvest = subtotInvest;
    }

    public int getYestForeign() {
        return yestForeign;
    }

    public void setYestForeign(int yestForeign) {
        this.yestForeign = yestForeign;
    }

    public int getYestInvest() {
        return yestInvest;
    }

    public void setYestInvest(int yestInvest) {
        this.yestInvest = yestInvest;
    }

    public float getRateReserve() {
        return rateReserve;
    }

    public void setRateReserve(float rateReserve) {
        this.rateReserve = rateReserve;
    }

    public float getEV_EBITDA() {
        return EV_EBITDA;
    }

    public void setEV_EBITDA(float eV_EBITDA) {
        EV_EBITDA = eV_EBITDA;
    }

}
