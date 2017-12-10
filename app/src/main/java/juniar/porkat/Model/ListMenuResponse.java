package juniar.porkat.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Nicolas Juniar on 01/11/2017.
 */

public class ListMenuResponse {
    @SerializedName("listmenu")
    private ArrayList<MenuKateringModel> listmenu;

    public ListMenuResponse(ArrayList<MenuKateringModel> listmenu) {
        this.listmenu = listmenu;
    }

    public ArrayList<MenuKateringModel> getListmenu() {
        return listmenu;
    }

    public void setListmenu(ArrayList<MenuKateringModel> listmenu) {
        this.listmenu = listmenu;
    }
}
