package com.reetika.customdialog

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {

    lateinit var btnUpdate: Button
    lateinit var tvName: TextView
    lateinit var tvAddress: TextView
    lateinit var tvGender: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnUpdate = findViewById(R.id.btnUpdate)
        tvName = findViewById(R.id.tvName)
        tvAddress = findViewById(R.id.tvAddress)
        tvGender = findViewById(R.id.tvGender)
        btnUpdate.setOnClickListener {

            var dialogBinding = CustomLayoutBinding.inflate(layoutInflater)
            var dialog = Dialog(this)
            dialog.setContentView(dialogBinding.root)
            val layout = dialog.window?.setLayout(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            dialogBinding.DiaName.setText(tvName.text.toString())
            dialogBinding.DiaAddress.setText(tvAddress.text.toString())


            dialogBinding.diaUpdate.setOnClickListener {
                dialogBinding.rgGender.setOnCheckedChangeListener { radioGroup, id ->
                    when (id) {
                        R.id.rbOthers -> {
                            Toast.makeText(
                                this,
                                resources.getString(R.string.others),
                                Toast.LENGTH_LONG
                            ).show()
                            dialogBinding.etOtherName.visibility = View.VISIBLE
                        }
                        else -> {
                            dialogBinding.etOtherName.visibility = View.GONE
                        }
                    }
                }

                if (dialogBinding.DiaName.text.toString().isNullOrEmpty()) {
                    Toast.makeText(this, "enter name", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else if (dialogBinding.DiaAddress.text.toString().isNullOrEmpty()) {
                    Toast.makeText(this, "enter address", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                } else if ((dialogBinding.etOtherName.visibility == View.VISIBLE) && (dialogBinding.etOtherName.text.toString()
                        .isNullOrEmpty())
                ) {
                    Toast.makeText(this, "enter other gender", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener

                } else {
                    tvName.setText(dialogBinding.DiaName.text.toString())
                    tvAddress.setText(dialogBinding.DiaAddress.text.toString())
                    if (dialogBinding.rbHe.isChecked) {
                        tvGender.setText("Male")
                    } else if (dialogBinding.rbShe.isChecked) {
                        tvGender.setText("Female")
                    } else
                        tvGender.setText(dialogBinding.etOtherName.text.toString())
                }
                dialog.dismiss()
            }
            dialog.show()
        }
    }
}