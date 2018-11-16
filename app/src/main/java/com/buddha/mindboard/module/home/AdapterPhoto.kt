package com.buddha.mindboard.module.home

import android.app.Activity
import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.buddha.mindboard.R
import com.buddha.mindboard.data.model.Datum
import com.buddha.mindboard.module.details.DetailsActivity
import com.buddha.mindboard.util.RecyclerViewPagingAdapter
import com.buddha.mindboardlib.ImageLoader

class AdapterPhoto(private var mContext: Context?) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), RecyclerViewPagingAdapter<Datum> {

    val VIEW_ITEM = 1
    val VIEW_PROGRESS = 0
    private var isLoadingAdded = false
    private var mList: ArrayList<Datum> = arrayListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            VIEW_ITEM -> {
                val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_photo, viewGroup, false)
                PhotoViewHolder(view)
            }
            VIEW_PROGRESS -> {
                val view =
                    LayoutInflater.from(viewGroup.context).inflate(R.layout.list_item_progressbar, viewGroup, false)
                ProgressViewHolder(view)
            }
            else -> throw ClassCastException("Unsupported view type")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = mList[position]
        if (holder is PhotoViewHolder) {
            holder.lblUserName.text=item.user?.name
            ImageLoader.Builder().from(mContext as? Activity)
                .load(item.user?.profileImage?.large)
                .errorImage(R.drawable.placeholder)
                .into(holder.imageView)
                .build()

        } else if (holder is ProgressViewHolder) {
            holder.progressBar.isIndeterminate = true
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (position == mList.size - 1 && isLoadingAdded) VIEW_PROGRESS else VIEW_ITEM
    }

    override fun getItemCount(): Int {
        return mList.size
    }


    override fun addLoadingFooter() {
        isLoadingAdded = true
        addItem(Datum())
    }

    override fun removeLoadingFooter() {
        isLoadingAdded = false

        val position = mList.size - 1
        val result = getItem(position)

        if (result != null) {
            mList.removeAt(position)
            notifyItemRemoved(position)
        }
    }

    override fun addItem(item: Datum?) {
        mList.add(item ?: return)
        notifyItemInserted(mList.size - 1);

    }

    override fun getItem(position: Int?): Datum? {
        return mList[position ?: return null]
    }

    override fun addItems(list: List<Datum>?) {
        mList.clear()
        mList.addAll(list ?: return)
        notifyDataSetChanged()
    }

    override fun addMoreItems(list: List<Datum>?) {
        mList.addAll(list ?: return)
        notifyItemRangeInserted(mList.size - 1, list.size);
    }

    inner class ProgressViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal var progressBar: ProgressBar = itemView.findViewById(R.id.progressBar)
    }

    private inner class PhotoViewHolder internal constructor(itemView: View) : RecyclerView.ViewHolder(itemView) {
        internal val imageView: ImageView = itemView.findViewById(R.id.imageView)
        internal val lblUserName:TextView=itemView.findViewById(R.id.lblUserName)
        init {
            itemView.setOnClickListener {
                DetailsActivity.start(mContext, mList[adapterPosition])
            }
        }
    }
}
