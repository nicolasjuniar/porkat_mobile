package juniar.porkat.View.Interface;

import juniar.porkat.Model.LoginResponse;
import retrofit2.Response;

/**
 * Created by Nicolas Juniar on 17/10/2017.
 */

public interface LoginView {
    void onLoginResponse(boolean error, boolean success,String message, Throwable t);
}
