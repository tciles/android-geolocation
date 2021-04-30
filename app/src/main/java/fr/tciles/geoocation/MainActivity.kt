package fr.tciles.geoocation

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import fr.tciles.geoocation.adapter.AddressAdapter
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private var inputAddress: EditText? = null
    private var addresses: List<Address>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputAddress = findViewById<EditText>(R.id.editText) as EditText
        val submitAddress: Button = findViewById<Button>(R.id.button) as Button

        submitAddress.setOnClickListener { view -> handleOnClick(view) }
    }

    /**
     *
     */
    private fun handleOnClick(view: View) {
        when (view.id) {
            R.id.button -> {
                val userText: String = this.inputAddress?.text.toString()

                if (userText.isNotEmpty()) {
                    listLocationFromName(userText)
                }
            }
        }
    }

    /**
     *
     */
    private fun listLocationFromName(userText: String) {
        val geocoder: Geocoder = Geocoder(this)

        try {
            addresses = geocoder.getFromLocationName(userText, 5)

            val listView: ListView = findViewById(R.id.listView)
            listView.adapter = AddressAdapter(
                this,
                R.layout.row_address,
                addresses!!
            )

            listView.setOnItemClickListener { parent, view, position, id ->
                handleOnItemClick(
                    parent,
                    view,
                    position,
                    id
                )
            }

            Log.d(TAG, "COUNT__ ${addresses!!.size}")
            Log.d(TAG, addresses.toString())
        } catch (e: IOException) {
            Log.e(TAG, e.localizedMessage!!.toString())
        }
    }

    /**
     *
     */
    private fun handleOnItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val address: Address = addresses!![position]
        Log.d(TAG, "__POSITION__ => ${position.toString()}, ${address.toString()}")
        Log.d(TAG, "__ADDRESS => ${address.toString()}")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
