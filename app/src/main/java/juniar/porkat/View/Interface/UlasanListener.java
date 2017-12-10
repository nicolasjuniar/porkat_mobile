package juniar.porkat.View.Interface;

import juniar.porkat.Model.DeleteUlasanResponse;
import juniar.porkat.Model.GetUlasanResponse;
import juniar.porkat.Model.InsertUlasanResponse;
import juniar.porkat.Model.UlasanModel;
import juniar.porkat.Model.UpdateUlasanResponse;

/**
 * Created by Nicolas Juniar on 16/11/2017.
 */

public interface UlasanListener {
    public void onInsertUlasanResponse(boolean error, InsertUlasanResponse response, Throwable t);
    public void onUpdateUlasanResponse(boolean error, UpdateUlasanResponse response, Throwable t);
    public void onDeleteUlasanResponse(boolean error, DeleteUlasanResponse response, Throwable t);
}
