package com.challenge.pulsus


import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.challenge.pulsus.models.Data
import com.challenge.pulsus.utils.EXTRA_CATEGORY
import com.challenge.pulsus.utils.EXTRA_DATA
import kotlinx.android.synthetic.main.activity_detail.*


class DetailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        val intent = intent
        val name = intent.getStringExtra(EXTRA_CATEGORY)
        val data = intent.getParcelableExtra<Data>(EXTRA_DATA)

        txtCategory.text = name
        txtUrl.text = data?.url
        txtValue.text = data?.value
    }
}