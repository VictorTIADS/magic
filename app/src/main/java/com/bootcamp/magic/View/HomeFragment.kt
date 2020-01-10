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
import com.bootcamp.magic.R
import com.bootcamp.magic.ViewModel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(),RecycleViewInterface {

    lateinit var viewModel: HomeFragmentViewModel
    var mAdapter: CardsAdapter = CardsAdapter(Cards(arrayListOf()))

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSets()
        setObservable()
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

    companion object {
        fun newInstance() = HomeFragment()
    }

    override fun GoToDetails(card: Cards) {
        viewModel.dataCard.value?.data?.cards
    }
}



