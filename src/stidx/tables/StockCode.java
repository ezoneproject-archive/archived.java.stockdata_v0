package stidx.tables;

import java.io.Serializable;

public class StockCode implements Serializable {

    /**
     * Serial ID
     */
    private static final long serialVersionUID = -3182792784505023822L;

    String stockCode = "";
    String stockName = "";
    String categoryName = "";

    public String getStockCode() {
        return stockCode;
    }

    public void setStockCode(String stockCode) {
        this.stockCode = stockCode;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

}
