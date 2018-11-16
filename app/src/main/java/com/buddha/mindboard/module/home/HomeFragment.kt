package com.buddha.mindboard.module.home

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.buddha.mindboard.R
import com.buddha.mindboard.data.model.Datum
import com.buddha.mindboard.di.scope.PerActivity
import com.buddha.mindboard.module.base.DaggerBaseFragment
import com.buddha.mindboard.util.CustomViewModelFactory
import com.buddha.mindboard.util.ErrorMessageFactory
import com.buddha.mindboard.util.Utils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import retrofit2.Response
import javax.inject.Inject


@PerActivity
class HomeFragment : DaggerBaseFragment(), SwipeRefreshLayout.OnRefreshListener {

    private var isLoading = false
    private var isLastPage = false
    private var OFFSET = 0
    private var PAGE_SIZE = 10
    private var TOTAL_PRODUCT = 0

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory
    @Inject
    lateinit var errorMessageFactory: ErrorMessageFactory
    @Inject
    lateinit var utils: Utils
    private var homeViewModel: HomeViewModel? = null

    private var imageView: ImageView? = null
    private var recyclerView: RecyclerView? = null
    private var swipeRefreshLayout: SwipeRefreshLayout? = null

    private var mList: List<Datum>? = arrayListOf()

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    private var adapterPhoto: AdapterPhoto? = null

    private lateinit var layoutManager: GridLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        imageView = rootView?.findViewById(R.id.imageView)
        recyclerView = rootView?.findViewById(R.id.recyclerView)
        layoutManager = GridLayoutManager(activity, 2)
        val onSpanSizeLookup = object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (adapterPhoto?.getItemViewType(position) === adapterPhoto?.VIEW_ITEM) 1 else 2
            }
        }
        layoutManager.spanSizeLookup = onSpanSizeLookup
        recyclerView?.layoutManager = layoutManager
        swipeRefreshLayout = rootView?.findViewById(R.id.swipeRefreshLayout)
        swipeRefreshLayout?.setOnRefreshListener(this)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        adapterPhoto = AdapterPhoto(activity)
        recyclerView?.adapter = adapterPhoto

        recyclerView?.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
            }

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0) {
                    val visibleItemCount = layoutManager.getChildCount()
                    val totalItemCount = layoutManager.getItemCount()
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (!isLoading && !isLastPage && OFFSET <= TOTAL_PRODUCT) {
                        if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                            OFFSET += PAGE_SIZE
                            loadItems(true)
                        }
                    }
                }
            }
        })

        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        if (swipeRefreshLayout?.isRefreshing == false)
            onRefresh()
    }

    companion object {

        fun newInstance(): HomeFragment {
            return HomeFragment()
        }
    }

    override fun onRefresh() {
        loadItems(false)
    }

    private fun loadItems(isLoadMore: Boolean) {
        swipeRefreshLayout?.isRefreshing = true
        homeViewModel?.getData()
            ?.subscribe(object : Observer<Response<List<Datum>>> {
                override fun onComplete() {

                }

                override fun onSubscribe(d: Disposable) {
                    homeViewModel?.addSubscription(d)
                }

                override fun onNext(t: Response<List<Datum>>) {
                    if (t.isSuccessful) {
                        mList = t.body()
                        if (isLoadMore) {
                            adapterPhoto?.removeLoadingFooter();
                            adapterPhoto?.addMoreItems(mList)
                            isLastPage = adapterPhoto?.itemCount == TOTAL_PRODUCT
                            if (!isLastPage)
                                adapterPhoto?.addLoadingFooter()
                        } else {
                            TOTAL_PRODUCT = mList?.size ?: 0

                            adapterPhoto?.addItems(mList)
                            isLastPage = adapterPhoto?.itemCount == TOTAL_PRODUCT
                            if (!isLastPage)
                                adapterPhoto?.addLoadingFooter()
                        }
                        isLoading = false
                        swipeRefreshLayout?.isRefreshing = false
                    } else {
                        utils.showErrorDialog("Oops! Unable to retrieve images. Keep patience, we are looking into it")
                        swipeRefreshLayout?.isRefreshing = false
                    }
                }

                override fun onError(e: Throwable) {
                    errorMessageFactory.getError(e)
                    swipeRefreshLayout?.isRefreshing = false
                }

            })
    }
}
