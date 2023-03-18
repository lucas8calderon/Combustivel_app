package com.lucascalderon1.combustivel.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.lucascalderon1.combustivel.R

class IntroSliderAdapter(private val introSlides: List<IntroSlide>)
    : RecyclerView.Adapter<IntroSliderAdapter.IntroSlideViewHolder> (){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IntroSlideViewHolder {
        return IntroSlideViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.slider_item_container,
                parent,
                false
            )
        )

    }

    override fun getItemCount(): Int {
        return  introSlides.size

    }


    override fun onBindViewHolder(holder: IntroSlideViewHolder, position: Int) {
        holder.bind(introSlides[position])

    }


    inner class  IntroSlideViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private  val textTitle = view.findViewById<TextView>(R.id.textTittle)
        private  val textDescription = view.findViewById<TextView>(R.id.textDescription)
        private  val imageIcon = view.findViewById<ImageView>(R.id.imageSliderIcon)

        fun bind (introSlide: IntroSlide) {
            textTitle.text = introSlide.title
            textDescription.text = introSlide.description
            imageIcon.setImageResource(introSlide.icon)
        }

    }

}

