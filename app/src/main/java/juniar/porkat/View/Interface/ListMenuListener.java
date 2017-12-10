package juniar.porkat.View.Interface;

import java.util.ArrayList;

import juniar.porkat.Model.MenuKateringModel;

/**
 * Created by Nicolas Juniar on 01/11/2017.
 */

public interface ListMenuListener {
    void onGetListMenuResponse(boolean error, ArrayList<MenuKateringModel> ListMenu,Throwable t);
}
