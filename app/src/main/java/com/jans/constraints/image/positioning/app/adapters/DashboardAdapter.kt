package com.jans.constraints.image.positioning.app.adapters

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.LayerDrawable
import android.os.Looper
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.RelativeLayout
import androidx.cardview.widget.CardView
import android.view.View.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.transition.Transition
import com.jans.constraints.image.positioning.app.R
import com.jans.constraints.image.positioning.app.adapters.viewHolders.R1ViewHolder
import com.jans.constraints.image.positioning.app.adapters.viewHolders.R2ViewHolder
import com.jans.constraints.image.positioning.app.adapters.viewHolders.R3ViewHolder
import com.jans.constraints.image.positioning.app.adapters.viewHolders.RTFViewHolder
import com.jans.constraints.image.positioning.app.adapters.viewHolders.RTHViewHolder
import com.jans.constraints.image.positioning.app.adapters.viewHolders.RTViewHolder
import com.jans.constraints.image.positioning.app.adapters.viewHolders.S1ViewHolder
import com.jans.constraints.image.positioning.app.adapters.viewHolders.S2ViewHolder
import com.jans.constraints.image.positioning.app.enums.BlockDashboard
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.ITEM_R1
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.ITEM_R2
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.ITEM_R3
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.ITEM_RT
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.ITEM_RTF
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.ITEM_RTH
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.ITEM_S1
import com.jans.constraints.image.positioning.app.model.DashboardModel.Dashboard
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.ITEM_S2
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.bitmapDrawable1
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.getViewTypeLayout
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.setBGImage
import com.jans.constraints.image.positioning.app.utils.RVUtils.Companion.tvWidth
import com.jans.constraints.image.positioning.app.utils.SharedPrefsUtil

class DashboardAdapter(private var dashboardItem: List<Dashboard>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var isExpanded = false
    private lateinit var context: Context
    private var myPrefs: SharedPrefsUtil? = null


    override fun getItemCount(): Int {
        return dashboardItem.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            ITEM_R1 -> R1ViewHolder(getViewTypeLayout(R.layout.item_r1, parent))
            ITEM_R2 -> R2ViewHolder(getViewTypeLayout(R.layout.item_r2, parent))
            ITEM_R3 -> R3ViewHolder(getViewTypeLayout(R.layout.item_r3, parent))
            ITEM_S1 -> S1ViewHolder(getViewTypeLayout(R.layout.item_s1, parent))
            ITEM_S2 -> S2ViewHolder(getViewTypeLayout(R.layout.item_s2, parent))
            ITEM_RTF -> RTFViewHolder(getViewTypeLayout(R.layout.item_rtf, parent))
            ITEM_RTH -> RTHViewHolder(getViewTypeLayout(R.layout.item_rth, parent))
            ITEM_RT -> RTViewHolder(getViewTypeLayout(R.layout.item_rt, parent))
            else -> throw IllegalArgumentException("Invalid view type")
        }

    }


    override fun getItemViewType(position: Int): Int {
        return when (dashboardItem[position].block) {
            BlockDashboard.R1.toString() -> ITEM_R1
            BlockDashboard.R2.toString() -> ITEM_R2
            BlockDashboard.R3.toString() -> ITEM_R3
            BlockDashboard.S1.toString() -> ITEM_S1
            BlockDashboard.S2.toString() -> ITEM_S2
            BlockDashboard.RTF.toString() -> ITEM_RTF
            BlockDashboard.RTH.toString() -> ITEM_RTH
            BlockDashboard.RT.toString() -> ITEM_RT
            else -> ITEM_S1
        }
    }


    private fun setupSublistRV(h: R3ViewHolder) {
        val listSubList = listOf(
            "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
            "Contrary to popular belief, Lorem Ipsum is not simply random text. It has roots in a piece of classical Latin literature from 45 BC, making it over 2000 years old. Richard McClintock, a Latin professor at Hampden-Sydney College in Virginia, looked up one of the more obscure Latin words, consectetur, from a Lorem Ipsum passage, and going through the cites of the word in classical literature, discovered the undoubtable source. Lorem Ipsum comes from sections 1.10.32 and 1.10.33 of \"de Finibus Bonorum et Malorum\" (The Extremes of Good and Evil) by Cicero, written in 45 BC.",
            "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like).",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc.",
            "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable. If you are going to use a passage of Lorem Ipsum, you need to be sure there isn't anything embarrassing hidden in the middle of text. All the Lorem Ipsum generators on the Internet tend to repeat predefined chunks as necessary, making this the first true generator on the Internet. It uses a dictionary of over 200 Latin words, combined with a handful of model sentence structures, to generate Lorem Ipsum which looks reasonable. The generated Lorem Ipsum is therefore always free from repetition, injected humour, or non-characteristic words etc."
        )
        h.rvSubList.layoutManager = LinearLayoutManager(h.itemView.context)
        val adapter = SubListAdapter(listSubList)
        h.rvSubList.adapter = adapter
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, pos: Int) {
        context = holder.itemView.context
        myPrefs = SharedPrefsUtil(context)
        val dashboardItems = dashboardItem[pos]

        when (getItemViewType(pos)) {
            ITEM_R1 -> itemR1Code(holder as R1ViewHolder, dashboardItems)
            ITEM_R2 -> itemR2Code(holder as R2ViewHolder, dashboardItems)
            ITEM_R3 -> itemR3Code(holder as R3ViewHolder, dashboardItems, pos)
            ITEM_S1 -> itemS1Code(holder as S1ViewHolder, dashboardItems)
            ITEM_S2 -> itemS2Code(holder as S2ViewHolder, dashboardItems)
            ITEM_RT -> itemRTCode(holder as RTViewHolder, dashboardItems)
            ITEM_RTF -> itemRTFCode(holder as RTFViewHolder, dashboardItems)
            ITEM_RTH -> itemRTHCode(holder as RTHViewHolder, dashboardItems)
            else -> {
            }
        }

    }

    private fun itemR1Code(holder: R1ViewHolder, dashboardItems: Dashboard) {
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val maxHeight = (tvWidth) + 0

            setBGImage(dashboardItems.imagename, holder.imgBG, maxHeight)
            val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
            holder.itemView.layoutParams.height = maxHeight
//            mainL.setBackgroundColor(context.resources.getColor(R.color.green))
        }, 50)
    }

    private fun itemR2Code(holder: R2ViewHolder, dashboardItems: Dashboard) {
        val paramStaggered =
            (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams)
        paramStaggered.isFullSpan = true
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title





        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val maxHeight = (tvWidth) / 2
            setBGImage(dashboardItems.imagename, holder.imgBG, maxHeight)

            val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
//            mainL.setBackgroundColor(context.resources.getColor(R.color.green))
        }, 50)
    }

    private fun itemR3Code(holder: R3ViewHolder, dashboardItems: Dashboard, pos: Int) {
        val tv1 = holder.tv1
        val paramStaggered =
            (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams)
        paramStaggered.isFullSpan = true
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        tv1.text = title
        tv1.viewTreeObserver.addOnPreDrawListener(object :
            ViewTreeObserver.OnPreDrawListener {
            override fun onPreDraw(): Boolean {
                tv1.viewTreeObserver.removeOnPreDrawListener(this)
                tvWidth = tv1.width
                return true
            }
        })
        val layMain = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)

        android.os.Handler(Looper.getMainLooper()).postDelayed({

            holder.dropDownbtn.visibility = View.VISIBLE
            paramStaggered.isFullSpan = true
            layMain.layoutParams.height = 504
            tv1.setPadding(0, 80, 0, 80)
            tv1.setTextColor(Color.WHITE)
            setupSublistRV(holder)

            Glide.with(holder.itemView.context)
                .asBitmap()
                .load(R.drawable.img).placeholder(R.drawable.loading_spinner)
                .into(object : SimpleTarget<Bitmap>() {
                    override fun onResourceReady(
                        resource: Bitmap,
                        transition: Transition<in Bitmap>?
                    ) {

                        val maxHeight = 504
                        val layerDrawable =
                            LayerDrawable(arrayOf(bitmapDrawable1(context,resource, maxHeight)))
                        holder.imgBG.background = layerDrawable
                        val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
//                        mainL.setBackgroundColor(context.resources.getColor(R.color.green))
                    }
                })


            holder.itemView.setOnClickListener {
                isExpanded = !isExpanded
                if (isExpanded) {
                    holder.rvSubList.visibility = VISIBLE
                    layMain.setBackgroundColor(Color.TRANSPARENT)
                    holder.dropDownbtn.setImageResource(R.drawable.top_arrow_svgrepo_com)
                } else {
                    holder.rvSubList.visibility = GONE
                    layMain.setBackgroundColor(Color.TRANSPARENT)
                    holder.dropDownbtn.setImageResource(R.drawable.bottom_arrow_svgrepo_com)
                    notifyItemChanged(pos)
                }
            }
        }, 50)
    }

    private fun itemRTCode(holder: RTViewHolder, dashboardItems: Dashboard) {
        val paramStaggered =
            (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams)
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title
        paramStaggered.isFullSpan = true


        val imgBG = holder.imgBG
        val cardBG = holder.itemView.findViewById<CardView>(R.id.cardV)
        cardBG.setCardBackgroundColor(Color.BLACK)
        val imgBGChild = holder.itemView.findViewById<ImageView>(R.id.imgLogo)
        val linParent = holder.itemView.findViewById<LinearLayout>(R.id.linParent)
        linParent.layoutParams.height = 504

        val layoutParamsParent = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        val layoutParamsChild = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        // Set margins (left, top, right, bottom) in pixels
        layoutParamsParent.setMargins(60, 100, 60, 0)
        layoutParamsChild.setMargins(160, 200, 160, 0)

        // Apply the layout parameters to the TextView
        imgBG.layoutParams = layoutParamsParent

        imgBGChild.layoutParams = layoutParamsChild



        android.os.Handler(Looper.getMainLooper()).postDelayed({
//            holder.tv1.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
//            holder.tv1.setPadding(0, 80, 0, 80)
//            val maxHeight = tvWidth / 2 + 0
//            val backgroundImage: Bitmap = BitmapFactory.decodeResource(
//                context.resources,
//                getDrawableResourceId(context, dashboardItems.imageName)
//            )
//            val layerDrawable =
//                LayerDrawable(arrayOf(bitmapDrawable1(backgroundImage, maxHeight)))
//            holder.imgBG.background = layerDrawable


//            holder.tv1.setTextColor(Color.BLACK)
//            val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
//            mainL.setBackgroundColor(context.resources.getColor(R.color.green))
        }, 50)
    }

    private fun itemRTFCode(holder: RTFViewHolder, dashboardItems: Dashboard) {
        Log.d("type", ITEM_RTF.toString())
        val paramStaggered =
            (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams)
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title
        paramStaggered.isFullSpan = true
        holder.tv1.text = title
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            paramStaggered.isFullSpan = true
            holder.tv1.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            holder.tv1.setPadding(0, 80, 0, 80)
            val maxHeight = tvWidth / 2 + 0
            setBGImage(dashboardItems.imagename, holder.imgBG, maxHeight)

            holder.tv1.setTextColor(Color.BLACK)
            val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
//            mainL.setBackgroundColor(context.resources.getColor(R.color.green))
        }, 50)
    }

    private fun itemRTHCode(holder: RTHViewHolder, dashboardItems: Dashboard) {
        val paramStaggered =
            (holder.itemView.layoutParams as StaggeredGridLayoutManager.LayoutParams)
        paramStaggered.isFullSpan = true
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title
        paramStaggered.isFullSpan = true
        holder.tv1.text = title
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            paramStaggered.isFullSpan = true
            holder.tv1.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            holder.tv1.setPadding(0, 80, 0, 80)
            val maxHeight = tvWidth + tvWidth + 126
            setBGImage(dashboardItems.imagename, holder.imgBG, maxHeight)

            holder.tv1.setTextColor(Color.BLACK)
            val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
//            mainL.setBackgroundColor(context.resources.getColor(R.color.green))


        }, 50)
    }

    private fun itemS1Code(holder: S1ViewHolder, dashboardItems: Dashboard) {
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val maxHeight = (tvWidth) / 2
            holder.itemView.layoutParams.height = maxHeight


            setBGImage(dashboardItems.imagename, holder.imgBG, maxHeight)



            val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
//            mainL.setBackgroundColor(context.resources.getColor(R.color.green))
        }, 50)
    }

    private fun itemS2Code(holder: S2ViewHolder, dashboardItems: Dashboard) {
        val title = "${dashboardItems.title}\n${dashboardItems.block}"
        holder.tv1.text = title
        android.os.Handler(Looper.getMainLooper()).postDelayed({
            val maxHeight = (tvWidth) / 2
            holder.itemView.layoutParams.height = maxHeight


            setBGImage(dashboardItems.imagename, holder.imgBG, maxHeight)

            val mainL = holder.itemView.findViewById<RelativeLayout>(R.id.layMain)
//            mainL.setBackgroundColor(context.resources.getColor(R.color.green))
        }, 50)
    }

}