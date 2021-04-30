package fr.tciles.geoocation

import android.location.Address
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import fr.tciles.geoocation.adapter.AddressAdapter
import fr.tciles.geoocation.databinding.ActivityMainBinding
import java.io.IOException

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var addresses: List<Address>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            button.setOnClickListener {
                val userText: String = editText.text.toString()

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
        val geocoder = Geocoder(this)

        try {
            addresses = geocoder.getFromLocationName(userText, 5)

            binding.apply {
                listView.adapter = AddressAdapter(
                    this@MainActivity,
                    R.layout.row_address,
                    addresses
                )

                listView.setOnItemClickListener { parent, view, position, id ->
                    handleOnItemClick(parent, view, position, id)
                }
            }

            Log.d(TAG, "COUNT__ ${addresses.size}")
            Log.d(TAG, addresses.toString())
        } catch (e: IOException) {
            Log.e(TAG, e.localizedMessage!!.toString())
        }
    }

    private fun handleOnItemClick(parent: AdapterView<*>, view: View, position: Int, id: Long) {
        val address: Address = addresses[position]
        Log.d(TAG, "__POSITION__ => ${position}, $address")
        Log.d(TAG, "__ADDRESS => $address")
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
