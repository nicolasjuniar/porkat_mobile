package juniar.porkat.API;

import juniar.porkat.Model.ChangePasswordPelangganRequest;
import juniar.porkat.Model.ChangePasswordResponse;
import juniar.porkat.Model.EditProfilePelangganRequest;
import juniar.porkat.Model.EditProfileResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.PUT;

/**
 * Created by Nicolas Juniar on 05/11/2017.
 */

public interface SettingPelangganAPI {
    @PUT("pelanggan/update/password")
    Call<ChangePasswordResponse> changePassword(@Body ChangePasswordPelangganRequest requestBody);

    @PUT("pelanggan/update/profile")
    Call<EditProfileResponse> editProfile(@Body EditProfilePelangganRequest requestBody);
}
