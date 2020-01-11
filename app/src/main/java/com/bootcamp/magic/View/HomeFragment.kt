package com.bootcamp.magic.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bootcamp.magic.Interface.RecycleViewInterface
import com.bootcamp.magic.Models.BaseModel
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.adapter.CardsAdapter
import com.bootcamp.magic.R
import com.bootcamp.magic.ViewModel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(), RecycleViewInterface {

    private lateinit var mAdapter:CardsAdapter
    private val viewModel: HomeFragmentViewModel by viewModel()



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setObservable()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mAdapter = CardsAdapter(Cards(arrayListOf()),this)
        recycleCards.adapter = mAdapter
        recycleCards.layoutManager = GridLayoutManager(requireContext(), 3)
    }

    private fun setObservable() {
        viewModel.dataSet.observe(viewLifecycleOwner, Observer {
            when (it.status){
                BaseModel.Companion.STATUS.LOADING -> {
                    controlVisibility(BaseModel.Companion.STATUS.LOADING)
                }
                BaseModel.Companion.STATUS.SUCCESS -> {
                    if (viewModel.getCardsList()==null){
                        viewModel.getCards(viewModel.getSetCodeAtPosition(38))
                    }
                    Log.i("aspk","SET CODE TO REQUEST CARDS: ${viewModel.getSetCodeAtPosition(38)}")
                }
                BaseModel.Companion.STATUS.ERROR -> {
                    navigateToErrorFragment()
                }

            }
        })
        viewModel.dataCard.observe(viewLifecycleOwner, Observer {
            when (it.status){
                BaseModel.Companion.STATUS.LOADING -> {


                }
                BaseModel.Companion.STATUS.SUCCESS -> {
                    it.data?.let { it1 -> mAdapter.addItems(it1) }
                    controlVisibility(BaseModel.Companion.STATUS.SUCCESS)

                }

                BaseModel.Companion.STATUS.ERROR -> {
                    navigateToErrorFragment()
                }

            }
        })
    }

    private fun controlVisibility(it:BaseModel.Companion.STATUS){
        when (it){
            BaseModel.Companion.STATUS.LOADING -> {
                home_loader_place_holder.visibility = View.VISIBLE
                recycleCards.visibility = View.GONE
            }
            BaseModel.Companion.STATUS.SUCCESS -> {
                home_loader_place_holder.visibility = View.GONE
                recycleCards.visibility = View.VISIBLE
            }


        }
    }

    private fun navigateToErrorFragment(){
        findNavController().navigate(HomeFragmentDirections.actionGoToError())
        callMainAnimationHideBottomTab()
    }

    private fun configureCardAdapter(cards: Cards) {

    }


    override fun GoToDetails(card: Cards, index: Int) {
        val action = HomeFragmentDirections.actionGoToDetail(
            viewModel.getCardsList() ?: Cards(
                arrayListOf()
            ), index
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



