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
import androidx.navigation.fragment.findNavController
import com.bootcamp.magic.R
import kotlinx.android.synthetic.main.fragment_home.*
import com.bootcamp.magic.ViewModel.HomeFragmentViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), RecycleViewInterface {

    private val viewModel: HomeFragmentViewModel by viewModel()
    var mAdapter: CardsAdapter = CardsAdapter(Cards(arrayListOf()), this)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getSets()
        setObservable()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    private fun setObservable() {
        viewModel.dataCard.observe(this, Observer {
            when (it.status) {
                BaseModel.Companion.STATUS.SUCCESS -> {
                    it.data?.let { it1 -> configureCardAdapter(it1) }


                }
                BaseModel.Companion.STATUS.ERROR -> {
                    navigateToErrorFragment()
                }
            }
        })
        viewModel.dataSet.observe(this, Observer {
            when (it.status) {
                BaseModel.Companion.STATUS.SUCCESS -> {

                    it.data?.let { viewModel.getCards(viewModel.getSetCodeAtPosition(0)) }

                }
                BaseModel.Companion.STATUS.ERROR -> {
                    navigateToErrorFragment()
                }
            }
        })
    }

    private fun navigateToErrorFragment(){
        findNavController().navigate(HomeFragmentDirections.actionGoToError())
        callMainAnimationHideBottomTab()
    }

    private fun configureCardAdapter(card: Cards) {
        recycleCards.adapter = mAdapter
        recycleCards.layoutManager = GridLayoutManager(requireContext(), 3)
        mAdapter.addItems(card)
    }


    override fun GoToDetails(card: Cards, index: Int) {
        viewModel.dataCard.value?.data?.cards
        val action = HomeFragmentDirections.actionGoToDetail(
            viewModel.getCardsList() ?: Cards(
                arrayListOf()
            ), 0
        )
        findNavController().navigate(action)
        callMainAnimationHideBottomTab()

    }

    fun callMainAnimationHideBottomTab(){
        if ((requireActivity() as MainActivity) != null ){
            (requireActivity() as MainActivity).hideComponentsWhenGoToDetail()
        }
    }


}



