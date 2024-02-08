package com.example.workouttracker2.NutritionOverview

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.workouttracker2.R
import com.google.mlkit.vision.barcode.common.Barcode

class NutritionOverviewFragment : Fragment(R.layout.fragment_nutrition_overview),CameraPreviewDialogFragment.BarcodeListener {

    private lateinit var barcodeTextView: TextView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnScan: Button = view.findViewById(R.id.btnScan)
        barcodeTextView = view.findViewById(R.id.barcodeTextView)

        btnScan.setOnClickListener {
            showCameraPreviewDialog()
        }
    }
    private fun showCameraPreviewDialog() {
        val dialog = CameraPreviewDialogFragment()
        dialog.setBarcodeListener(this)
        dialog.show(childFragmentManager, "CameraPreviewDialogFragment")
    }
    override fun onBarcodeDetected(barcode: Barcode) {
        // Hier den Barcode-Text in das entsprechende Feld setzen
        barcodeTextView.text = barcode.rawValue
    }
}


