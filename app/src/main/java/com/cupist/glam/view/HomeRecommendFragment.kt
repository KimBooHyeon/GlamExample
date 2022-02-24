package com.cupist.glam.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SimpleItemAnimator
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.cupist.glam.Global
import com.cupist.glam.R
import com.cupist.glam.databinding.FragmentHomeRecommendBinding
import com.cupist.glam.network.model.User
import com.cupist.glam.utils.VerticalSpaceDecoration
import com.cupist.glam.view.adapter.UserCardAdapter
import com.cupist.glam.view.vm.UserVM
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.*

@AndroidEntryPoint
class HomeRecommendFragment: Fragment() {

    val mGlobal by lazy { Global.INSTANCE }
    lateinit var binding: FragmentHomeRecommendBinding
    private val vm: UserVM by viewModels()
    private val adapter by lazy { UserCardAdapter(vm) }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeRecommendBinding.inflate(inflater, container, false)
        binding.vm = vm
        binding.lifecycleOwner = this

        init()
        setObserve()
        onRefresh()

        return binding.root
    }

    private fun init() {
        binding.list.let {
            it.adapter = adapter
            val animator: RecyclerView.ItemAnimator? = it.itemAnimator
            if (animator is SimpleItemAnimator) {
                animator.supportsChangeAnimations = false
            }
            it.addItemDecoration(VerticalSpaceDecoration(mGlobal.convertDpToPixel(context!!, 20)))
            it.addOnScrollListener(object: RecyclerView.OnScrollListener() {
                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                    super.onScrolled(recyclerView, dx, dy)

                    val visibleItemCount = it.layoutManager!!.childCount
                    val totalItemCount = it.layoutManager!!.itemCount
                    val pastVisibleItems = (it.layoutManager!! as LinearLayoutManager).findFirstVisibleItemPosition()

                    if (totalItemCount > 0 && visibleItemCount + pastVisibleItems >= totalItemCount - 5) {
                        if (binding.vm!!.isLoading.value == false && binding.vm!!.nextUrl != null) {
                            vm.getDynamicUrlUserList()
                        }
                    }
                }
            })
        }
        binding.swipeRefresh.setOnRefreshListener {
            onRefresh()
            binding.swipeRefresh.isRefreshing = false
        }
    }

    private fun setObserve() {
        vm.userData.observe(this, {
            it?.let { res ->
                res.forEach { item ->
                    adapter.addItem(item)
                }
            }
        })
        vm.personalizedUserData.observe(this, {
            it.reversed().let { res ->
                res.forEach { item ->
                    adapter.addItemToFirstPosition(item)
                }
            }
            binding.list.smoothScrollToPosition(0)
        })
    }

    fun onRefresh() {
        adapter.clearItems()
        vm.reset()
    }
}