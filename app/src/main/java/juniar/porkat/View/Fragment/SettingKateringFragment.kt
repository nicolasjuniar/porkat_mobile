package juniar.porkat.View.Fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.CardView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import juniar.porkat.R
import juniar.porkat.View.Activity.ChangePasswordActivity
import juniar.porkat.View.Activity.EditProfileActivity
import kotlinx.android.synthetic.main.fragment_setting.*

/**
 * Created by Nicolas Juniar on 02/01/2018.
 */
class SettingKateringFragment : Fragment()
{

    override fun onAttach(activity: Activity) {
        super.onAttach(activity)

        // This makes sure that the container activity has implemented
        // the callback interface. If not, it throws an exception
        try {
//            listener = activity as MenuPelangganListener
        } catch (e: ClassCastException) {
            throw ClassCastException(activity.toString() + " must implement listener ")
        }

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_setting, container, false)

        val cv_changepassword=view.findViewById<CardView>(R.id.cv_changepassword)
//
//        cv_editprofile!!.setOnClickListener {
//            val intent = Intent(activity, EditProfileActivity::class.java)
//            startActivityForResult(intent, 1)
//        }
//
        cv_changepassword.setOnClickListener { startActivity(Intent(activity, ChangePasswordActivity::class.java)) }

        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
//                listener.onUpdateProfile(data.getStringExtra("nama_lengkap"))
            }
        }
    }
}