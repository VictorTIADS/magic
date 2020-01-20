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
import com.bootcamp.magic.Animation.fadeIn
import com.bootcamp.magic.Animation.fadeOut
import com.bootcamp.magic.Interface.RecycleViewInterface
import com.bootcamp.magic.Listeners.ScrollListener
import com.bootcamp.magic.Models.BaseModel
import com.bootcamp.magic.Models.CardView
import com.bootcamp.magic.Models.Cards
import com.bootcamp.magic.Models.adapter.CardsAdapter
import com.bootcamp.magic.Models.adapter.CardsAdapter.Companion.CATEGORY_TYPE
import com.bootcamp.magic.Models.adapter.CardsAdapter.Companion.HEADER_SET
import com.bootcamp.magic.Models.adapter.CardsAdapter.Companion.ITEM
import com.bootcamp.magic.R
import com.bootcamp.magic.ViewModel.HomeFragmentViewModel
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.loader_bottom.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment(), RecycleViewInterface {

    private val viewModel: HomeFragmentViewModel by viewModel()
    lateinit var mAdapter: CardsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setObservable()
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
    }

    private fun setObservable() {
        viewModel.dataSet.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                BaseModel.Companion.STATUS.LOADING -> {
                    controlVisibility(BaseModel.Companion.STATUS.LOADING)
                }
                BaseModel.Companion.STATUS.SUCCESS -> {
                    if (viewModel.dataCard.value == null) {
                        viewModel.getCards(viewModel.getSetCodeAtPosition(viewModel.getSetPageIndex()))
                    }
                    Log.i(
                        "aspk",
                        "SET CODE TO REQUEST CARDS: ${viewModel.getSetCodeAtPosition(viewModel.getSetPageIndex())}"
                    )
                }
                BaseModel.Companion.STATUS.ERROR -> {
                    navigateToErrorFragment()
                }

            }
        })
        viewModel.dataCard.observe(this, Observer {
            when (it.status) {
                BaseModel.Companion.STATUS.LOADING -> {
                    if (home_loader_place_holder.visibility == View.GONE) {
                        progress_bar.fadeIn()
                        loader_status_page.text =
                            "Carregando ${viewModel.getSetNameAtPosition(viewModel.getSetPageIndex() + 1)}"
                    }

                }
                BaseModel.Companion.STATUS.SUCCESS -> {
                    progress_bar.fadeOut()
                }
            }

        })
        viewModel.objectList.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                BaseModel.Companion.STATUS.SUCCESS -> {
                    it.data?.let { it1 -> bindDataToAdapter(it1) }
                    controlVisibility(BaseModel.Companion.STATUS.SUCCESS)
                }
            }
        })

    }


    private fun bindDataToAdapter(list: ArrayList<CardView>) {
        recycleCards.fadeIn()
        mAdapter.addMoreCards(list)
    }


    private fun controlVisibility(it: BaseModel.Companion.STATUS) {
        when (it) {
            BaseModel.Companion.STATUS.LOADING -> {
                home_loader_place_holder.fadeIn()
                recycleCards.fadeOut()

            }
            BaseModel.Companion.STATUS.SUCCESS -> {
                home_loader_place_holder.fadeOut()
                recycleCards.fadeIn()
                progress_bar.visibility = View.GONE
            }


        }
    }

    fun setupRecyclerView() {
        mAdapter = CardsAdapter(arrayListOf(), this)
        recycleCards.adapter = mAdapter
        val layoutManager = GridLayoutManager(requireContext(), 3)
        layoutManager.spanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return when (mAdapter.getItemViewType(position)) {
                    HEADER_SET -> 3
                    ITEM -> 1
                    CATEGORY_TYPE -> 3
                    else -> -1
                }
            }
        }
        recycleCards.layoutManager = layoutManager
        recycleCards.addOnScrollListener(ScrollListener(layoutManager) { visibleItemCount, totalItemCount, firstVisibleItemPosition ->
            viewModel.loadMore(visibleItemCount, totalItemCount, firstVisibleItemPosition)
        })
    }

    private fun navigateToErrorFragment() {
        findNavController().navigate(HomeFragmentDirections.actionGoToError())
    }

    override fun GoToDetails(card: Cards, index: Int) {
        val action = HomeFragmentDirections.actionGoToDetail(card, index)
        findNavController().navigate(action)
    }


}



