package com.bootcamp.magic.View


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bootcamp.magic.Models.Card
import com.bootcamp.magic.Models.Cards

import com.bootcamp.magic.R
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        clickListeners()
    }

    fun clickListeners() {
        detail.setOnClickListener {
            //TODO REMOVE THIS MOCKED LIST
            val cardsList = Cards(
                arrayListOf(
                    Card(
                        1,
                        "A",
                        "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                        "A",
                        arrayListOf()
                    ),
                    Card(
                        1,
                        "A",
                        "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                        "A",
                        arrayListOf()
                    ),
                    Card(
                        1,
                        "A",
                        "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                        "A",
                        arrayListOf()
                    ),
                    Card(
                        1,
                        "A",
                        "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                        "A",
                        arrayListOf()
                    ),
                    Card(
                        1,
                        "A",
                        "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                        "A",
                        arrayListOf()
                    ),
                    Card(
                        1,
                        "A",
                        "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                        "A",
                        arrayListOf()
                    ),
                    Card(
                        1,
                        "A",
                        "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                        "A",
                        arrayListOf()
                    ),
                    Card(
                        1,
                        "A",
                        "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                        "A",
                        arrayListOf()
                    )
                )
            )
            val action = HomeFragmentDirections.actionGoToDetail(cardsList, 0)
            findNavController().navigate(action)
            (requireActivity() as MainActivity).hideComponentsWhenGoToDetail()
        }
    }

}
