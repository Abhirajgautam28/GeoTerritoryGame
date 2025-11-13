package com.example.geoterritory

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import com.google.android.gms.maps.MapView
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var tvSteps: TextView
    private lateinit var btnStart: Button
    private lateinit var btnStop: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)
        tvSteps = findViewById(R.id.tvSteps)
        btnStart = findViewById(R.id.btnStart)
        btnStop = findViewById(R.id.btnStop)

        mapView.onCreate(savedInstanceState)

        // ---------- PREMIUM UI ANIMATIONS ----------
        val fadeIn = AnimationUtils.loadAnimation(this, R.anim.fade_in)
        val slideUp = AnimationUtils.loadAnimation(this, R.anim.slide_up)

        findViewById<View>(R.id.topBar).startAnimation(fadeIn)
        findViewById<View>(R.id.stepsContainer).startAnimation(fadeIn)
        findViewById<View>(R.id.bottomControls).startAnimation(slideUp)

        // ---------- FAB BOTTOM SHEET ----------
        val fab = findViewById<View>(R.id.fabDetails)
        fab.setOnClickListener {
            val sheetView = layoutInflater.inflate(R.layout.bottom_sheet_details, null)
            val dialog = BottomSheetDialog(this)
            dialog.setContentView(sheetView)

            // Update values dynamically
            sheetView.findViewById<TextView>(R.id.tvDistance).text = "Distance: 125 m"
            sheetView.findViewById<TextView>(R.id.tvArea).text = "Captured Area: 92 sqm"

            dialog.show()
        }

        // ---------- PERMISSIONS ----------
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
            != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1
            )
        }

        // ---------- BUTTON LOGIC ----------
        btnStart.setOnClickListener {
            tvSteps.text = "Steps: 0"
        }

        btnStop.setOnClickListener {
            tvSteps.text = "Steps: 0"
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }
}