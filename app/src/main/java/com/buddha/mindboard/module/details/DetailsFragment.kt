package com.buddha.mindboard.module.details


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.buddha.mindboard.R
import com.buddha.mindboard.data.model.Datum
import com.buddha.mindboard.module.base.DaggerBaseFragment
import com.buddha.mindboard.util.Config
import com.buddha.mindboardlib.ImageLoader

class DetailsFragment : DaggerBaseFragment() {

    private var imageView: ImageView? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_details
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        imageView = rootView?.findViewById(R.id.imageView)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val datum:Datum?=arguments?.getParcelable(Config.Extras.DATUM)
        ImageLoader.Builder()
            .from(activity)
            .load(datum?.urls?.full)
            .errorImage(R.drawable.placeholder)
            .into(imageView)
    }

    companion object {
        fun newInstance(datum: Datum?): DetailsFragment? {
            val fragment = DetailsFragment()
            val bundle = Bundle()
            bundle.putParcelable(Config.Extras.DATUM, datum)
            fragment.arguments = bundle
            return fragment
        }
    }
}
