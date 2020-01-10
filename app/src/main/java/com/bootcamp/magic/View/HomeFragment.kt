package com.bootcamp.magic.View

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.GridLayoutManager
import com.bootcamp.magic.Interface.RecycleViewInterface
import com.bootcamp.magic.Models.BaseModel
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.adapter.CardsAdapter
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bootcamp.magic.Models.Card
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.R
import kotlinx.android.synthetic.main.fragment_home.*
import com.bootcamp.magic.ViewModel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),RecycleViewInterface {

    lateinit var viewModel: HomeFragmentViewModel
    var mAdapter: CardsAdapter = CardsAdapter(Cards(arrayListOf()),this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSets()
        setObservable()
        clickListeners()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel = ViewModelProviders.of(this).get(HomeFragmentViewModel::class.java)
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun setObservable() {
        viewModel.dataCard.observe(this, Observer {
            when (it.status) {
                BaseModel.Companion.STATUS.SUCCESS -> {
                    it.data?.let { it1 -> configureCardAdapter(it1) }


                }
                BaseModel.Companion.STATUS.ERROR -> {

                }
            }
        })
        viewModel.dataSet.observe(this, Observer {
            when (it.status) {
                BaseModel.Companion.STATUS.SUCCESS -> {

                    it.data?.let { viewModel.getCards(viewModel.getSetCodeAtPosition(0)) }

                }
                BaseModel.Companion.STATUS.ERROR -> {

                }
            }
        })
    }

    private fun configureCardAdapter(card: Cards) {
        recycleCards.adapter = mAdapter
        recycleCards.layoutManager = GridLayoutManager(requireContext(), 3)
        mAdapter.addItems(card)
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

    override fun GoToDetails(card: Cards,index:Int) {
        viewModel.dataCard.value?.data?.cards
    }

}



