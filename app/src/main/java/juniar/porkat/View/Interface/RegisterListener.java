package juniar.porkat.View.Interface;

/**
 * Created by Nicolas Juniar on 26/10/2017.
 */

public interface RegisterListener {
    void onRegisterResponse(boolean error, boolean success,String message, Throwable t);
}
