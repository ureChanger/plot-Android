package com.project.mobo.dialog

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.project.mobo.R
import com.project.mobo.main_page.MainPageActivity
import kotlinx.android.synthetic.main.popup_fail_final.*

class FailDialogActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.popup_fail_final)

        btnFailOk.setOnClickListener{
            val choice = Intent(this, MainPageActivity::class.java)
            startActivity(choice)
            finish()
        }
    }
}