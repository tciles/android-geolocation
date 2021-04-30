package fr.tciles.geoocation.adapter

import android.content.Context
import android.location.Address
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import fr.tciles.geoocation.holder.AddressViewHolder
import fr.tciles.geoocation.R

class AddressAdapter(context: Context, resource: Int, objects: List<Address>) :
    ArrayAdapter<Address>(context, resource, objects) {
    private var layoutInflater: LayoutInflater =
        context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater

    /**
     *
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val viewHolder: AddressViewHolder
        val rowView: View

        if (convertView == null) {
            rowView = layoutInflater.inflate(R.layout.row_address, parent, false) as View

            viewHolder = AddressViewHolder()
            viewHolder.address = rowView.findViewById(R.id.address) as TextView
            viewHolder.lat = rowView.findViewById(R.id.lat) as TextView
            viewHolder.lon = rowView.findViewById(R.id.lon) as TextView

            rowView.tag = viewHolder
        } else {
            viewHolder = convertView.tag as AddressViewHolder
            rowView = convertView
        }

        val address: Address = getItem(position) as Address

        viewHolder.address?.text = address.getAddressLine(0)
        viewHolder.lat?.text = "Latitude: ${address.latitude}"
        viewHolder.lon?.text = "Longitude: ${address.longitude}"

        return rowView
    }
}