package com.example.workouttracker2.NutritionOverview

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.workouttracker2.MyAppApplication
import com.example.workouttracker2.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.firestore.FirebaseFirestore
import com.google.mlkit.vision.barcode.common.Barcode

class NutritionOverviewFragment : Fragment(R.layout.fragment_nutrition_overview),CameraPreviewDialogFragment.BarcodeListener {

    private lateinit var barcodeTextView: TextView
    private lateinit var firestoreDb: FirebaseFirestore

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnScan: Button = view.findViewById(R.id.btnScan)
        barcodeTextView = view.findViewById(R.id.barcodeTextView)

        //firestore-DB von Appliaktion klasse ziehen
        firestoreDb = (requireActivity().application as MyAppApplication).firestoreDb

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
        val scannedBarcode = barcode.rawValue
        if (scannedBarcode!=null){
            showAddFoodDialog(scannedBarcode)
        }
    }

    private fun showAddFoodDialog(scannedBarcode: String) {
        val builder = MaterialAlertDialogBuilder(requireContext())
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.add_food_dialog,null)
        builder.setView(dialogView)
        builder.setTitle("Lebensmittel hinzufügen")

        // barcode setzen
        val tvBarcode = dialogView.findViewById<TextView>(R.id.tvBarcode)
        tvBarcode.text= "Barcode: $scannedBarcode"

        // ref Eingabefelder
        val etName = dialogView.findViewById<EditText>(R.id.etName)
        val etKalorien = dialogView.findViewById<EditText>(R.id.etKalorien)
        val etProteine = dialogView.findViewById<EditText>(R.id.etProteine)
        val etFett = dialogView.findViewById<EditText>(R.id.etFett)
        val etKohlenhydrate = dialogView.findViewById<EditText>(R.id.etKohlenhydrate)
        val etServingSize = dialogView.findViewById<EditText>(R.id.etServingSize)
        val etUnit = dialogView.findViewById<EditText>(R.id.etUnit)

        builder.setPositiveButton("Speichern"){dialog,_ ->
            val name = etName.text.toString().trim()
            val kalorien = etKalorien.text.toString().toDoubleOrNull()
            val proteine = etProteine.text.toString().toDoubleOrNull()
            val fett = etFett.text.toString().toDoubleOrNull()
            val kohlenhydrate = etKohlenhydrate.text.toString().toDoubleOrNull()
            val servingSize = etServingSize.text.toString().toDoubleOrNull()
            val unit = etUnit.text.toString().trim()

            //Validierung:
            if (name.isEmpty()) {
                Toast.makeText(requireContext(), "Bitte geben Sie einen Namen ein", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            if (kalorien == null) {
                Toast.makeText(requireContext(), "Bitte geben Sie eine gültige Kalorienzahl an", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            if (proteine == null) {
                Toast.makeText(requireContext(), "Bitte geben Sie eine gültige Proteinzahl an", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            if (fett == null) {
                Toast.makeText(requireContext(), "Bitte geben Sie eine gültige Fettzahl an", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            if (kohlenhydrate == null) {
                Toast.makeText(requireContext(), "Bitte geben Sie eine gültige Kohlenhydratzahl an", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            if (servingSize == null) {
                Toast.makeText(requireContext(), "Bitte geben Sie eine gültige Portionsgröße an", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }
            if (unit.isEmpty()) {
                Toast.makeText(requireContext(), "Bitte geben Sie eine Einheit an", Toast.LENGTH_SHORT).show()
                return@setPositiveButton
            }

            val foodData = hashMapOf(
                "name" to name,
                "calories" to kalorien,
                "protein" to proteine,
                "fat" to fett,
                "carbs" to kohlenhydrate,
                "servingSize" to servingSize,
                "unit" to unit
            )

            firestoreDb.collection("lebensmittel")
                .document(scannedBarcode)
                .set(foodData)
                .addOnSuccessListener {
                    Toast.makeText(requireContext(), "Lebensmittel erfolgreich gespeichert", Toast.LENGTH_SHORT).show()
                }
                .addOnFailureListener { e ->
                    Log.e("NutritionOverview", "Fehler beim Speichern des Lebensmittels", e)
                    Toast.makeText(requireContext(), "Fehler beim Speichern", Toast.LENGTH_SHORT).show()
                }
        }
        builder.setNegativeButton("Abbrechen") { dialog, _ ->
            dialog.dismiss()
        }
        builder.create().show()
    }
}


