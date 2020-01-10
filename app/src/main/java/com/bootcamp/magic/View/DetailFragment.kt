package com.bootcamp.magic.View


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.get
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bootcamp.magic.Adapter.DetailAdapter
import com.bootcamp.magic.Adapter.ViewlHolder
import com.bootcamp.magic.Models.Card
import com.bootcamp.magic.Models.Cards
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
    }

    private fun setUpCardsList() {
        arguments?.let {
            viewModel.initCardList(DetailFragmentArgs.fromBundle(it).cardList)
            viewModel.initCardListIndex(DetailFragmentArgs.fromBundle(it).listIndex)
        }

    }

    private fun setUpListeners() {
        detail_button_close.setOnClickListener {
            startAnim()
            findNavController().navigateUp()
        }


    }

    private fun startAnim() {
        if ((requireActivity() as MainActivity) != null) {
            (requireActivity() as MainActivity).showComponentsBack()
        }
    }

    private fun setUpScroll() {
        scrollView.scrollToPosition(viewModel.getIndexList())
    }

    private fun setUpAdapter() {
        mAdapter = DetailAdapter(requireContext(), viewModel.getCards())
    }

    private fun setUpScrollView() {
        scrollView = detail_scroll_view
        scrollView.setSlideOnFling(true)
        scrollView.scrollToPosition(viewModel.getIndexList())
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
