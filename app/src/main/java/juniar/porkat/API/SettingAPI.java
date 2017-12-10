package juniar.porkat.API;

import juniar.porkat.Model.ChangePasswordRequest;
import juniar.porkat.Model.ChangePasswordResponse;
import juniar.porkat.Model.EditProfileRequest;
import juniar.porkat.Model.EditProfileResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.PUT;

/**
 * Created by Nicolas Juniar on 05/11/2017.
 */

public interface SettingAPI {
    @PUT("pelanggan/update/password")
    Call<ChangePasswordResponse> changePassword(@Body ChangePasswordRequest requestBody);

    @PUT("pelanggan/update/profile")
    Call<EditProfileResponse> editProfile(@Body EditProfileRequest requestBody);
}
