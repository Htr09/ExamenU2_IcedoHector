package icedo.hector.examen2_icedohector


import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class ContactDetail : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_contact_detail)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val contact = intent.getSerializableExtra("contact") as? Contact

        if (contact != null) {
            findViewById<TextView>(R.id.contact_name).text =
                "${contact.nombre} ${contact.apellidos}"
            findViewById<TextView>(R.id.contact_company).text = contact.compañia
            findViewById<TextView>(R.id.contact_email).text = contact.email
            findViewById<TextView>(R.id.contact_phone).text = contact.telefono

            val callButton = findViewById<Button>(R.id.call_button)
            callButton.text = "Llamar a ${contact.nombre}"
            callButton.setOnClickListener {
                val intent = Intent(this, Call::class.java)
                intent.putExtra("contact_name", contact.nombre)
                startActivity(intent)
            }

        }
    }
}
