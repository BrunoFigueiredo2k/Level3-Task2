package com.example.level3_task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_add_portal.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
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

        addBtn.setOnClickListener {
//            navigateToPortalsList()
            findNavController().navigate(R.id.action_AddPortalFragment_to_PortalsFragment)
        }
    }

//    private fun navigateToPortalsList() {
//
//        val args = Bundle()
////        args.putFloat(ARG_GAME_RATING, ratingBar.rating)
////        args.putString(ARG_GAME_NAME, txt_game.text.toString())
//
////        findNavController().navigate(R.id.action_AddPortalFragment_to_PortalsFragment, args)
//    }
}