package icedo.hector.examen2_icedohector

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: AdaptadorContactos
    private var contacts = ArrayList<Contact>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        agregaContactos()
        val listView: ListView = findViewById(R.id.litview)
        adapter = AdaptadorContactos(this, contacts)
        listView.adapter = adapter

        findViewById<Button>(R.id.addContact).setOnClickListener {
            val intent = Intent(this, AddContact::class.java)
            startActivity(intent)
        }

        listView.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(this, ContactDetail::class.java)
            intent.putExtra("contact", contacts[position])
            startActivity(intent)
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 1 && resultCode == RESULT_OK) {
            val newContact = data?.getSerializableExtra("new_contact") as Contact
            contacts.add(newContact)
            adapter.notifyDataSetChanged()
        }
    }


    fun agregaContactos(){
        contacts.add(Contact("Juan","Perez","Pemex","6441302402","juanperez@gmail.com", Color.RED))

    }

    private class AdaptadorContactos(var contexto: Context, var contactos: ArrayList<Contact>) : BaseAdapter() {
        override fun getCount(): Int = contactos.size
        override fun getItem(position: Int): Any = contactos[position]
        override fun getItemId(position: Int): Long = position.toLong()
        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val inflador = LayoutInflater.from(contexto)
            val vista = inflador.inflate(R.layout.contact_view, null)
            val nombre = vista.findViewById<TextView>(R.id.contact_name)
            val company = vista.findViewById<TextView>(R.id.contact_company)
            val icono = vista.findViewById<ImageView>(R.id.contact_color)
            val deleteBtn = vista.findViewById<Button>(R.id.contact_delete)

            val contacto = contactos[position]
            nombre.text = "${contacto.nombre} ${contacto.apellidos}"
            company.text = contacto.compañia
            icono.setColorFilter(contacto.color)

            deleteBtn.setOnClickListener {
                contactos.removeAt(position)
                notifyDataSetChanged()
            }
            return vista
        }
    }


}