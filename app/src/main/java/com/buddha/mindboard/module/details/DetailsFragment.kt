package com.buddha.mindboard.module.details


import android.os.Bundle
import android.support.v4.widget.ContentLoadingProgressBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.buddha.mindboard.R
import com.buddha.mindboard.data.model.Datum
import com.buddha.mindboard.module.base.DaggerBaseFragment
import com.buddha.mindboard.util.Config
import com.buddha.mindboardlib.ImageLoader
import com.buddha.mindboardlib.NetworkFileLoader.ProgressListener

class DetailsFragment : DaggerBaseFragment() {

    private var imageView: ImageView? = null
    private var imageLoadingProgressBar: ContentLoadingProgressBar? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_details
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        imageView = rootView?.findViewById(R.id.imageView)
        imageLoadingProgressBar = rootView?.findViewById(R.id.progressBar)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val datum: Datum? = arguments?.getParcelable(Config.Extras.DATUM)
        ImageLoader.Builder()
            .from(activity)
            .load(datum?.urls?.full)
            .errorImage(R.drawable.placeholder)
            .setProgressListener(object : ProgressListener {
                override fun onRequestStarted() {
                    imageLoadingProgressBar?.show()
                    imageLoadingProgressBar?.visibility = View.VISIBLE
                }

                override fun onRequestComplete() {
                    imageLoadingProgressBar?.hide()
                    imageLoadingProgressBar?.visibility = View.GONE
                }
            })
            .into(imageView)
            .build()
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
