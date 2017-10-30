package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nicolas Juniar on 30/10/2017.
 */

public class ListKateringResponse {
    @SerializedName("listkatering")
    ArrayList<KateringModel> listkatering;

    public ListKateringResponse(ArrayList<KateringModel> listkatering) {
        this.listkatering = listkatering;
    }

    public ArrayList<KateringModel> getListkatering() {
        return listkatering;
    }

    public void setListkatering(ArrayList<KateringModel> listkatering) {
        this.listkatering = listkatering;
    }
}
