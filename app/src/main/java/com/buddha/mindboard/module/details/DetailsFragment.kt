package com.buddha.mindboard.module.details


import android.os.Bundle
import android.support.v4.widget.ContentLoadingProgressBar
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.buddha.mindboard.R
import com.buddha.mindboard.data.model.Datum
import com.buddha.mindboard.module.base.DaggerBaseFragment
import com.buddha.mindboard.util.Config
import com.buddha.mindboard.util.ErrorMessageFactory
import com.buddha.mindboard.util.Utils
import com.buddha.mindboardlib.ImageLoader
import com.buddha.mindboardlib.NetworkFileLoader.ProgressListener
import javax.inject.Inject

class DetailsFragment : DaggerBaseFragment() {

    @Inject
    lateinit var errorMessageFactory: ErrorMessageFactory

    @Inject
    lateinit var utils: Utils

    private var imageView: ImageView? = null
    private var imageLoadingProgressBar: ContentLoadingProgressBar? = null
    private var lblUserName: TextView? = null
    private var lblTotalLikes: TextView? = null
    private var likeButton:ImageView?=null

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
        lblTotalLikes=rootView?.findViewById(R.id.lblTotalLikes)
        lblUserName = rootView?.findViewById(R.id.lblUserName)
        likeButton=rootView?.findViewById(R.id.likeButton)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val datum: Datum? = arguments?.getParcelable(Config.Extras.DATUM)
        lblUserName?.text = datum?.user?.name
        lblTotalLikes?.text=datum?.likes?.toString()
        if(datum?.likedByUser==true){
            likeButton?.setImageResource(R.drawable.ic_like_filled_red_24dp)
        }else{
            likeButton?.setImageResource(R.drawable.ic_like_empty_red_24dp)
        }
        ImageLoader.Builder()
            .from(activity)
            .load(datum?.urls?.full)
            .errorImage(R.drawable.placeholder)
            .setProgressListener(object : ProgressListener {
                override fun onError(t: Throwable?) {
                    utils.showErrorDialog(errorMessageFactory.getError(t))
                    imageLoadingProgressBar?.hide()
                    imageLoadingProgressBar?.visibility = View.GONE
                }

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

        /**
         * Uncomment code to cancel request after 200ms
         */

        /*Handler().postDelayed({
            imageLoader.cancelRequest(imageLoader.request)
        },200)*/
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
