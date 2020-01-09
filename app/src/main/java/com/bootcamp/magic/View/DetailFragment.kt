package com.bootcamp.magic.View


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bootcamp.magic.Adapter.DetailAdapter
import com.bootcamp.magic.Models.Card
import com.bootcamp.magic.R
import com.yarolegovich.discretescrollview.DiscreteScrollView
import com.yarolegovich.discretescrollview.transform.Pivot
import com.yarolegovich.discretescrollview.transform.ScaleTransformer
import kotlinx.android.synthetic.main.fragment_detail.*


class DetailFragment : Fragment() {


    lateinit var scrollView: DiscreteScrollView
    lateinit var mAdapter: DetailAdapter
    lateinit var listCard:ArrayList<Card>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpAdapter()
        setUpScrollView()
        setUpListeners()
    }

    private fun setUpListeners(){
        detail_button_close.setOnClickListener {
            (requireActivity() as MainActivity).showComponentsBack()
            findNavController().popBackStack()
        }
    }

    private fun setUpAdapter() {
        listCard = arrayListOf(
            Card(
                1,
                "A",
                "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                "A",
                arrayListOf()
            ), Card(
                1,
                "A",
                "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                "A",
                arrayListOf()
            ), Card(
                1,
                "A",
                "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                "A",
                arrayListOf()
            ), Card(
                1,
                "A",
                "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                "A",
                arrayListOf()
            ), Card(
                1,
                "A",
                "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                "A",
                arrayListOf()
            ), Card(
                1,
                "A",
                "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                "A",
                arrayListOf()
            ), Card(
                1,
                "A",
                "https://gatherer.wizards.com/Handlers/Image.ashx?multiverseid=130483&type=card",
                "A",
                arrayListOf()
            )
        )
        mAdapter = DetailAdapter(requireContext(), listCard)
    }

    private fun setUpScrollView() {
        scrollView = detail_scroll_view
        scrollView.setOffscreenItems(10)
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
