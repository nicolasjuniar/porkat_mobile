package juniar.porkat.View.Interface;

import java.util.ArrayList;
import juniar.porkat.Model.KateringModel;

/**
 * Created by Nicolas Juniar on 30/10/2017.
 */

public interface ListKateringView {
    public void onGetListKateringResponse(boolean error, ArrayList<KateringModel> ListKatering,Throwable t);
}
