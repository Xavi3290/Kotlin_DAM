package com.example.activitat7_grupa

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.activitat7_grupa.databinding.ActivityEnviarEmailBinding

class EnviarEmail : AppCompatActivity() {
    private lateinit var binding: ActivityEnviarEmailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEnviarEmailBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val actionbar = supportActionBar

        val usuari = intent.getStringExtra("nomusuari").toString()
        actionbar?.title = usuari
        actionbar?.setDisplayHomeAsUpEnabled(true)

        binding.btenviarmail.setOnClickListener {
            val direccions = arrayOf(binding.direccioemail.text.toString())
            val assumpte = (binding.assumpte.text.toString() + usuari)
            val cos = binding.cos.text.toString()

            val intent = Intent(Intent.ACTION_SENDTO)
            intent.putExtra(Intent.EXTRA_EMAIL, direccions)
            intent.putExtra(Intent.EXTRA_TEXT, cos)
            intent.putExtra(Intent.EXTRA_SUBJECT, assumpte)
            intent.data = Uri.parse("mailto:")

            startActivity(intent)
        }

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home)
            finish()
        return super.onOptionsItemSelected(item)
    }
}