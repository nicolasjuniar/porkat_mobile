package juniar.porkat.View.Activity

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import com.google.gson.Gson
import juniar.porkat.Model.ChangePasswordKateringRequest
import juniar.porkat.Model.KateringModel
import juniar.porkat.Presenter.SettingKateringPresenter
import juniar.porkat.R
import juniar.porkat.Utils.PreferenceHelper
import juniar.porkat.View.Interface.SettingListener
import kotlinx.android.synthetic.main.activity_changepassword.*

/**
 * Created by Nicolas Juniar on 02/01/2018.
 */
class ChangePasswordKateringActivity:AppCompatActivity(),SettingListener
{

    lateinit var preferences:PreferenceHelper
    lateinit var katering: KateringModel
    lateinit var presenter:SettingKateringPresenter

    val DONT_TOUCH = WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_changepassword)

        preferences= PreferenceHelper.getInstance(applicationContext)
        katering=Gson().fromJson(preferences.getString("profile_katering",""),KateringModel::class.java)
        presenter= SettingKateringPresenter(this,preferences)
        toolbar.setOnClickListener { onBackPressed() }

        progressbar.indeterminateDrawable.setColorFilter(resources.getColor(R.color.colorAccent),android.graphics.PorterDuff.Mode.SRC_IN)
        et_username.setText(katering.id_pengguna)
        btn_update.isEnabled=false
        btn_update.setOnClickListener {
            window.setFlags(DONT_TOUCH, DONT_TOUCH)
            progressbar.visibility= View.VISIBLE
            val requestBody=ChangePasswordKateringRequest(katering.id_katering,et_password.text.toString())
            presenter.changePassword(requestBody)
        }

    }

    override fun onUpdateProfile(error: Boolean, message: String?, t: Throwable?) {
        window.clearFlags(DONT_TOUCH)
        progressbar.visibility=View.GONE
        if(!error)
        {
            Toast.makeText(this@ChangePasswordKateringActivity,message,Toast.LENGTH_SHORT).show()
        }
        else
        {
            Toast.makeText(this@ChangePasswordKateringActivity,t?.message,Toast.LENGTH_SHORT).show()
        }
    }
}