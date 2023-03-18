package com.lucascalderon1.combustivel.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.button.MaterialButton
import com.lucascalderon1.combustivel.R
import com.lucascalderon1.combustivel.home.HomeActivity


class IntroActivity : AppCompatActivity() {

    private lateinit var introSliderViewPager: ViewPager2
    private lateinit var indicatorContainer: LinearLayout
    private lateinit var btn_proximo: MaterialButton
    private lateinit var txt_pular: TextView

    private val introSliderAdapter = IntroSliderAdapter(
        listOf(
            IntroSlide(
                "Controle de despesas",
                "Com o aplicativo você controla as despesas de seus veículos de maneira rápida e prática.",
                R.drawable.veiculos
            ),
            IntroSlide(
                "Consumo",
                "Saiba o consumo do seu carro, gastos e sugetões para economizar em todas viagens.",
                R.drawable.manutencao
            ),
            IntroSlide(
                "Relatórios",
                "Possivel gerar relatórios e gráficos completos de abastecimentos, serviços e despesas.",
                R.drawable.relatorio
            )
        )
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_intro)
        introSliderViewPager = findViewById(R.id.introSliderViewPager)
        indicatorContainer = findViewById(R.id.indicatorContainer)
        txt_pular = findViewById(R.id.txt_pular)
        btn_proximo = findViewById(R.id.btn_proximo)
        introSliderViewPager.adapter = introSliderAdapter

        setupIndicators()
        setCurrentIndicator(0)
        introSliderViewPager.registerOnPageChangeCallback(object :
            ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }

        })
        btn_proximo.setOnClickListener {
            if (introSliderViewPager.currentItem + 1 < introSliderAdapter.itemCount) {
                introSliderViewPager.currentItem += 1
            } else {
                Intent(applicationContext, HomeActivity::class.java).also {
                    startActivity(it)
                    finish()
                }

            }
        }

        txt_pular.setOnClickListener {
            Intent(applicationContext, HomeActivity::class.java).also {
                startActivity(it)
                finish()

            }
        }
    }


    private fun setupIndicators() {
        val indicators = arrayOfNulls<ImageView>(introSliderAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)
        layoutParams.setMargins(8, 0, 0, 0)
        for (i in indicators.indices) {
            indicators[i] = ImageView(applicationContext)
            indicators[i].apply {
                this?.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive

                    )
                )
                this?.layoutParams = layoutParams
            }
            indicatorContainer.addView(indicators[i])

        }

    }

    private fun setCurrentIndicator(index: Int) {
        val childCount = indicatorContainer.childCount
        for (i in 0 until childCount) {
            val imageView = indicatorContainer.getChildAt(i) as ImageView // <--- Correção aqui
            if (i == index) {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }
    }
}




