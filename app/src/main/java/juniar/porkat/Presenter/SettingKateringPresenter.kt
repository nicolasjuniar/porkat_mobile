package juniar.porkat.Presenter

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import juniar.porkat.API.SettingKateringAPI
import juniar.porkat.Model.ChangePasswordKateringRequest
import juniar.porkat.Model.EditProfileKateringRequest
import juniar.porkat.Utils.NetworkConfig
import juniar.porkat.Utils.PreferenceHelper
import juniar.porkat.View.Interface.SettingListener

/**
 * Created by Nicolas Juniar on 02/01/2018.
 */
class SettingKateringPresenter(listener:SettingListener,preferences:PreferenceHelper)
{
    var listener:SettingListener
    var preferences:PreferenceHelper
    init {
        this.listener=listener
        this.preferences=preferences
    }

    fun changePassword(requestBody:ChangePasswordKateringRequest)
    {
        NetworkConfig.createService(SettingKateringAPI::class.java)
                .changePassword(requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe (
                        {result-> listener.onUpdateProfile(false,result.message,null)},
                        {error-> listener.onUpdateProfile(true,null,error.cause)}
                )
    }

    fun updateProfile(requestBody:EditProfileKateringRequest)
    {
        NetworkConfig.createService(SettingKateringAPI::class.java)
                .updateProfile(requestBody)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        {result-> listener.onUpdateProfile(false,result.message,null)},
                        {error-> listener.onUpdateProfile(true,null,error.cause)}
                )
    }
}