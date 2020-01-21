package com.bootcamp.magic.View


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.bootcamp.magic.Animation.fadeIn
import com.bootcamp.magic.Animation.fadeOut
import com.bootcamp.magic.Interface.RecycleViewInterface
import com.bootcamp.magic.Models.BaseModel
import com.bootcamp.magic.Models.CardView
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.adapter.CardsAdapter
import com.bootcamp.magic.R
import com.bootcamp.magic.ViewModel.FavoriteFragmentViewModel
import kotlinx.android.synthetic.main.fragment_favorite.*
import kotlinx.android.synthetic.main.fragment_home.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class FavoriteFragment : Fragment(), RecycleViewInterface {

    private lateinit var mAdapter: CardsAdapter
    private val viewModel: FavoriteFragmentViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recycleCardsFavorite.fadeOut()
        viewModel.getFavorities()
        setupRecyclerView()
        setObserver()
    }

    fun setupRecyclerView(){
        mAdapter = CardsAdapter(arrayListOf(), this)
        recycleCardsFavorite.adapter = mAdapter
        val layoutManager = GridLayoutManager(requireContext(), 3)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup()
        {
            override fun getSpanSize(position: Int): Int {
                return when (mAdapter.getItemViewType(position)) {
                    CardsAdapter.HEADER_SET -> 3
                    CardsAdapter.ITEM -> 1
                    CardsAdapter.CATEGORY_TYPE -> 3
                    else -> -1
                }
            }
        }
        recycleCardsFavorite.layoutManager = layoutManager
    }


    fun setObserver() {
        viewModel.objectList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                BaseModel.Companion.STATUS.SUCCESS -> {
                    it.data?.let { it1 -> bindDataToAdapter(it1) }
                }
                BaseModel.Companion.STATUS.ERROR -> {
                    navigateToEmptyFragment()
                }
                BaseModel.Companion.STATUS.LOADING -> {

                }
            }
        })
    }

    private fun navigateToEmptyFragment() {
        findNavController().navigate(FavoriteFragmentDirections.actionGotToEmptyFromFavorite())
    }

    private fun bindDataToAdapter(list: ArrayList<CardView>) {
        recycleCardsFavorite.fadeIn()
        mAdapter.updateAdapter(list)
    }


    override fun GoToDetails(card: Cards, index: Int) {
        val action = FavoriteFragmentDirections.actionGoToDetail(card,index)
        findNavController().navigate(action)
    }

}
