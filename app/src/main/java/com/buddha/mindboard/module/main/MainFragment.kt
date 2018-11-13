package com.buddha.mindboard.module.main

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.buddha.mindboard.R
import com.buddha.mindboard.di.scope.PerActivity
import com.buddha.mindboard.module.base.DaggerBaseFragment
import com.buddha.mindboard.util.CustomViewModelFactory
import com.buddha.mindboard.util.ErrorMessageFactory
import com.buddha.mindboard.util.Utils
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import javax.inject.Inject

@PerActivity
class MainFragment : DaggerBaseFragment() {

    @Inject
    lateinit var viewModelFactory: CustomViewModelFactory
    @Inject
    lateinit var errorMessageFactory: ErrorMessageFactory
    @Inject
    lateinit var utils: Utils

    private var mainViewModel: MainViewModel? = null

    override fun getLayoutId(): Int {
        return R.layout.fragment_main
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        val lblGreetings = rootView?.findViewById<TextView>(R.id.lbl_greetings)
        mainViewModel?.greetings()
                ?.subscribe(object : Observer<String> {
                    override fun onSubscribe(d: Disposable) {
                        mainViewModel?.addSubscription(d)
                    }

                    override fun onNext(s: String) {
                        lblGreetings?.text = s
                    }

                    override fun onError(e: Throwable) {
                        utils.showToast(errorMessageFactory.getError(e))
                    }

                    override fun onComplete() {}
                })
    }

    companion object {

        fun newInstance(): MainFragment {
            return MainFragment()
        }
    }
}
