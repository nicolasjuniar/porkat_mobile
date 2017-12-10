package juniar.porkat.View.Interface;

import juniar.porkat.Model.InsertUlasanResponse;
import juniar.porkat.Model.UpdateUlasanResponse;

/**
 * Created by Nicolas Juniar on 21/11/2017.
 */

public interface RefreshUlasanListener {
    public void onInsertedUlasan(InsertUlasanResponse response);
    public void onUpdatedUlasan(UpdateUlasanResponse response);
    public void onDeletedUlasan();
}
