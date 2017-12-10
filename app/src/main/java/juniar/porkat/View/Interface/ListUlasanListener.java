package juniar.porkat.View.Interface;

import juniar.porkat.Model.GetUlasanResponse;

/**
 * Created by Nicolas Juniar on 21/11/2017.
 */

public interface ListUlasanListener {
    public void onGetUlasanResponse(boolean error, GetUlasanResponse response, Throwable t);
}
