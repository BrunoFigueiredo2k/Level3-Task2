package com.example.level3_task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.setFragmentResult
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */

const val REQ_PORTAL_KEY = "req_portal"
const val BUNDLE_PORTAL_KEY = "bundle_portal"

class AddPortalFragment : Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_portal, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // On click add portal button
        addBtn.setOnClickListener {
            onAddPortal()
        }
    }

    private fun onAddPortal(){
        // Get the user inputs
        val portalTitle = title.text.toString()
        val portalUrl = url.text.toString()

        // Check if submitted input is not empty
        if (portalTitle.isNotBlank() && portalUrl.isNotBlank()){
            //set the data as fragmentResult, listening for REQ_PORTAL_KEY in PortalsFragment
            val portalData = Portal(portalTitle, portalUrl)
            setFragmentResult(REQ_PORTAL_KEY, bundleOf(Pair(BUNDLE_PORTAL_KEY, portalData)))

            // Destroy current fragment to go back to home fragment
            findNavController().popBackStack()
        } else {
            Toast.makeText(activity, R.string.not_valid_portal, Toast.LENGTH_SHORT).show()
        }
    }
}