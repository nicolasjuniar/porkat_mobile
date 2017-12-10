package juniar.porkat.View.Interface;

import java.util.ArrayList;

import juniar.porkat.Model.GetTransaksiModel;

/**
 * Created by Nicolas Juniar on 10/12/2017.
 */

public interface ListTransaksiListener {
    void onGetListTransaksi(boolean error, ArrayList<GetTransaksiModel> ListTransaksi,Throwable t);
}
