package juniar.porkat.View.Interface;

/**
 * Created by Nicolas Juniar on 09/12/2017.
 */

public interface TransaksiListener {
    void onResponseTransaksi(boolean error,String message,Throwable t);
}
