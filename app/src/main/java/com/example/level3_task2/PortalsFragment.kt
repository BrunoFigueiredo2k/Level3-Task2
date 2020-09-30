package com.example.level3_task2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.browser.customtabs.CustomTabsIntent
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_portals.*


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class PortalsFragment : Fragment() {
    private val portals = arrayListOf<Portal>()
    private val portalsAdapter = PortalAdapter(portals, { portalItem : Portal -> portalItemClicked(portalItem) })

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    private fun portalItemClicked(portal : Portal) {
        // check is chrom available
        val packageName = customTabHelper.getPackageNameToUse(this, portal.url)
        if (packageName == null)
            // if chrome not available open in web view
        else {
            customTabsIntent.intent.setPackage(packageName) customTabsIntent.launchUrl(this, Uri.parse(portal.url))
        }
//        Toast.makeText(this.context, "Clicked: ${portal.title}", Toast.LENGTH_LONG).show()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
    }

    private fun initViews() {
        // Initialize the recycler view with a linear layout manager, adapter
        rvPortals.layoutManager =
            LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        rvPortals.adapter = portalsAdapter
        observeAddPortalResult()
    }

    private fun observeAddPortalResult() {
        setFragmentResultListener(REQ_PORTAL_KEY) { key, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_PORTAL_KEY)?.let {
                // TODO: don't know if these parameters work... Data is not being added to each view in recycler view
                val portal = it

                portals.add(portal)
                portalsAdapter.notifyDataSetChanged()
            } ?: Log.e("PortalsFragment", "Request triggered, but empty portal text!")
        }
    }
}