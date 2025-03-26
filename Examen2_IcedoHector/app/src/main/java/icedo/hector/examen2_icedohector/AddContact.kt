package icedo.hector.examen2_icedohector

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class AddContact : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_add_contact)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        findViewById<Button>(R.id.saveContact).setOnClickListener {
            val nombre = findViewById<EditText>(R.id.inputNombre).text.toString()
            val apellidos = findViewById<EditText>(R.id.inputApellidos).text.toString()
            val telefono = findViewById<EditText>(R.id.inputTelefono).text.toString()
            val email = findViewById<EditText>(R.id.inputEmail).text.toString()
            val compania = findViewById<EditText>(R.id.inputCompania).text.toString()

            if (nombre.isNotEmpty() && telefono.isNotEmpty()) {
                val colores = listOf(
                    Color.parseColor("contact_color1"),
                    Color.parseColor("contact_color2"),
                    Color.parseColor("contact_color3"),
                    Color.parseColor("contact_color4"),
                    Color.parseColor("contact_color5")
                )
                val colorAleatorio = colores.random()

                val newContact = Contact(nombre, apellidos, compania, telefono, email, colorAleatorio)
                val resultIntent = Intent()
                resultIntent.putExtra("new_contact", newContact)
                setResult(RESULT_OK, resultIntent)
                finish()
            } else {
                Toast.makeText(this, "Nombre y teléfono son obligatorios", Toast.LENGTH_SHORT).show()
            }

        }
    }
}