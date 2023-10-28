package com.example.ucn_conectme

import android.Manifest
import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.ImageButton
import android.widget.PopupMenu
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.viewpager.widget.ViewPager

import com.google.android.material.tabs.TabLayout
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    private val auth= FirebaseAuth.getInstance();

    companion object {
        private const val REQUEST_CAMERA_PERMISSION = 1001
        private const val REQUEST_IMAGE_CAPTURE = 1002
    }

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager: ViewPager = findViewById(R.id.viewPager)
        val tabLayout: TabLayout = findViewById(R.id.tab)
        val menu = findViewById<ImageButton>(R.id.menumain)
        val btncamera = findViewById<ImageButton>(R.id.camaraactivity)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(grupo_work_fragment(), "Comunity")
        adapter.addFragment(ChatFragment(), "Chat")
        adapter.addFragment(HistoryFragment(), "History")
        adapter.addFragment(GruposFragment(), "Grupos")
        adapter.addFragment(TaskFragment(), "Task")

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.getTabAt(0)?.setIcon(adapter.getIconResource(0))

        menu.setOnClickListener {
            val popupMenu = PopupMenu(this, it)
            popupMenu.menuInflater.inflate(R.menu.menumain, popupMenu.menu)
            popupMenu.setOnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.activityConfiguracion -> {
                        val configuracionactivity= Intent(this, ConfigActivity::class.java)
                        startActivity(configuracionactivity)
                        true
                    }
                    R.id.menu_item2 -> {

                        true
                    }
                    R.id.menu_item3 -> {
                        val IntLog =  Intent(this,LoginActivity::class.java)
                        auth.signOut();
                        startActivity(IntLog)
                        true
                    }
                    else -> false
                }
            }
            popupMenu.show()
        }

        btncamera.setOnClickListener {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(Manifest.permission.CAMERA),
                    REQUEST_CAMERA_PERMISSION
                )
            } else {
                dispatchTakePictureIntent()
            }
        }
    }

    private fun dispatchTakePictureIntent() {
        Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
            takePictureIntent.resolveActivity(packageManager)?.also {
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            REQUEST_CAMERA_PERMISSION -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    dispatchTakePictureIntent()
                }
                return
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageUri: Uri? = data?.data
            // Aquí puedes realizar operaciones con la imagen capturada
        }
    }
}
