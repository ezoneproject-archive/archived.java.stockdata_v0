package stidx.dao;

public interface BulkInsertModule {

    public void checkHeader(String header) throws Exception;

    public void insertBulkRaw(String date, String time, String data) throws Exception;

}
