package juniar.porkat.View.Interface;

/**
 * Created by Nicolas Juniar on 17/10/2017.
 */

public interface LoginListener {
    void onLoginResponse(boolean error, boolean success,String message, Throwable t);
}
