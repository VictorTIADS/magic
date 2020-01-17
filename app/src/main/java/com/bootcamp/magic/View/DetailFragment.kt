package com.bootcamp.magic.View

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bootcamp.magic.Adapter.DetailAdapter
import com.bootcamp.magic.Models.Card
import com.bootcamp.magic.R
import com.bootcamp.magic.ViewModel.DetailFragmentViewModel
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.fragment_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailFragment : Fragment() {

    private val viewModel: DetailFragmentViewModel by viewModel()
    lateinit var scrollView: DiscreteScrollView
    private lateinit var mAdapter: DetailAdapter
    private var listCards = ArrayList<Card>()
    private var indexList = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpCardsList()
        setUpAdapter()
        setUpScrollView()
        setUpListeners()
        setUpScroll()
        viewModel.getCardRoom()

    }
    fun setUpFavoriteButton(){

        if(listCards[scrollView.currentItem].favorite){
            detail_button_favorite.text = "Unfavorite"
        }
        else{
            detail_button_favorite.text = "Favorite"
        }
    }

    private fun setUpCardsList() {
        arguments?.let {

            listCards = DetailFragmentArgs.fromBundle(it).cardList.cards
            indexList = DetailFragmentArgs.fromBundle(it).listIndex
        }
    }

    private fun setUpListeners() {
        detail_button_close.setOnClickListener {
            findNavController().navigate(DetailFragmentDirections.actionGoToHomeFromDetail())
        }
        detail_button_favorite.setOnClickListener {

            if (listCards[scrollView.currentItem].favorite) {
                detail_button_favorite.text = "Favorite"
                listCards[scrollView.currentItem].favorite = false
                val item = viewModel.converterCardOFItem(listCards[scrollView.currentItem])
                viewModel.deleteCard(item)
            } else {
                detail_button_favorite.text = "Unfavorite"
                listCards[scrollView.currentItem].favorite = true
                val item = viewModel.converterCardOFItem(listCards[scrollView.currentItem])
                viewModel.insertCard(item)
            }

            viewModel.dataCard.observe(this, Observer {

                listCards = viewModel.checkFavoritesCards(listCards)
                setUpFavoriteButton()
            })

            scrollView.addOnItemChangedListener { viewHolder, i ->
                setUpFavoriteButton()
            }
        }
    }



    private fun setUpScroll() {

        scrollView.scrollToPosition(indexList)
        Log.i("aspk",indexList.toString())
    }

    private fun setUpAdapter() {
        mAdapter = DetailAdapter(requireContext(), listCards)
    }

    private fun setUpScrollView() {
        scrollView = detail_scroll_view
        scrollView.setSlideOnFling(true)
        scrollView.setItemTransformer(
            ScaleTransformer.Builder()
                .setMaxScale(1.05f)
                .setMinScale(0.8f)
                .setPivotX(Pivot.X.CENTER) // CENTER is a default one
                .setPivotY(Pivot.Y.CENTER) // CENTER is a default one
                .build()
        )
        scrollView.adapter = mAdapter
    }
}
