package juniar.porkat.API

import io.reactivex.Observable
import juniar.porkat.Model.ChangePasswordKateringRequest
import juniar.porkat.Model.ChangePasswordResponse
import juniar.porkat.Model.EditProfileKateringRequest
import juniar.porkat.Model.EditProfileResponse
import retrofit2.http.Body
import retrofit2.http.PUT

/**
 * Created by Nicolas Juniar on 02/01/2018.
 */
interface SettingKateringAPI
{
    @PUT("katering/update/profile")
    fun updateProfile(@Body requestBody:EditProfileKateringRequest):Observable<EditProfileResponse>

    @PUT("katering/update/password")
    fun changePassword(@Body requestBody:ChangePasswordKateringRequest):Observable<ChangePasswordResponse>
}