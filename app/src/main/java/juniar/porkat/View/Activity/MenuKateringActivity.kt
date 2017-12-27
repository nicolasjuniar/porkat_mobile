package juniar.porkat.View.Activity

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.support.design.widget.Snackbar
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentTransaction
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.gson.Gson
import juniar.porkat.Model.KateringModel
import juniar.porkat.Model.PelangganModel
import juniar.porkat.R
import juniar.porkat.Utils.PreferenceHelper
import kotlinx.android.synthetic.main.activity_menukatering.*
import kotlinx.android.synthetic.main.app_bar_menu_katering.*
import kotlinx.android.synthetic.main.nav_header_menukatering.*
import kotlinx.android.synthetic.main.nav_header_menukatering.view.*

class MenuKateringActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    lateinit var preferences:PreferenceHelper
    var exit=false
    lateinit var fragmentTransaction: FragmentTransaction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menukatering)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle("Pengiriman Makanan")
        preferences= PreferenceHelper.getInstance(applicationContext)

        fab.setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                    .setAction("Action", null).show()
        }

        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()

        nav_view.menu.getItem(0).setChecked(true)
        loadPreferences()

        nav_view.setNavigationItemSelectedListener(this)
    }

    fun loadPreferences() {
        val katering = Gson().fromJson(preferences.getString("profile_katering", ""), KateringModel::class.java)
        val navView=nav_view.getHeaderView(0)
        navView.tv_fullname.setText(katering.nama_katering)
        navView.tv_username.setText(katering.id_pengguna)
        navView.tv_role.setText("katering")
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            if (exit) {
                finish()
            } else {
                Toast.makeText(this, "Tekan kembali lagi untuk keluar",
                        Toast.LENGTH_SHORT).show()
                exit = true
                Handler().postDelayed({ exit = false }, (3 * 1000).toLong())

            }
        }
    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        menuInflater.inflate(R.menu.menu_katering, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        when (item.itemId) {
//            R.id.action_settings -> return true
//            else -> return super.onOptionsItemSelected(item)
//        }
//    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        var fragment: Fragment? = null
        when (item.itemId) {
            R.id.nav_pengiriman -> {
                Toast.makeText(this@MenuKateringActivity,"pengiriman",Toast.LENGTH_SHORT).show()
                supportActionBar?.setTitle("Pengiriman Makanan")
            }
            R.id.nav_makanan -> {
                Toast.makeText(this@MenuKateringActivity,"makanan",Toast.LENGTH_SHORT).show()
                supportActionBar?.setTitle("Lihat Makanan")
            }
            R.id.nav_pesanan -> {
                Toast.makeText(this@MenuKateringActivity,"pesanan",Toast.LENGTH_SHORT).show()
                supportActionBar?.setTitle("Daftar Pesananan")
            }
            R.id.nav_menu -> {
                Toast.makeText(this@MenuKateringActivity,"menu",Toast.LENGTH_SHORT).show()
                supportActionBar?.setTitle("Kelola Menu")
            }
            R.id.nav_setting -> {
                Toast.makeText(this@MenuKateringActivity,"setting",Toast.LENGTH_SHORT).show()
                supportActionBar?.setTitle("Pengaturan")
            }
            R.id.nav_logout -> {
                AlertDialog.Builder(this@MenuKateringActivity)
                        .setTitle("Keluar")
                        .setMessage("Apa anda yakin ingin keluar")
                        .setPositiveButton(android.R.string.yes,DialogInterface.OnClickListener{
                            dialogInterface, i ->
                            preferences.putBoolean("session",false)
                            startActivity(Intent(this@MenuKateringActivity,HomeActivity::class.java))
                        })
                        .setNegativeButton(android.R.string.no,DialogInterface.OnClickListener{
                            dialogInterface, i ->
                        })
                        .show()
            }
        }



        if (fragment != null) {
            fragmentTransaction = supportFragmentManager.beginTransaction()
            fragmentTransaction.replace(R.id.container_body, fragment).commit()
        }

        drawer_layout.closeDrawer(GravityCompat.START)
        return true
    }
}
