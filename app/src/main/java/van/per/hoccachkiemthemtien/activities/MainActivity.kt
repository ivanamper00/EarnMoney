package van.per.hoccachkiemthemtien.activities

import amp.er.kiemtientainha.data.Data
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.viewpager2.widget.ViewPager2
import van.per.hoccachkiemthemtien.R
import van.per.hoccachkiemthemtien.binding.viewBinding
import van.per.hoccachkiemthemtien.databinding.ActivityMainBinding
import van.per.hoccachkiemthemtien.fragment.BannerAdapter
import van.per.hoccachkiemthemtien.fragment.DetailsFragment
import van.per.hoccachkiemthemtien.utils.SliderTransformer

class MainActivity : AppCompatActivity(),
    BannerAdapter.Listener {

    private val binding by viewBinding(ActivityMainBinding::inflate)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        with(binding){
            bannerPager.apply {
                adapter = BannerAdapter(this@MainActivity)
                offscreenPageLimit = 3
                setPageTransformer(SliderTransformer(3))

                registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback(){
                    override fun onPageSelected(position: Int) {
                        description.text = Data.contents[position].content
                    }
                })
            }

            cardViewAbout.setOnClickListener {
                DetailsFragment(Data.contents[1], supportFragmentManager).show()
            }
            cardViewStrategy.setOnClickListener {
                DetailsFragment(Data.contents[2], supportFragmentManager).show()
            }
            cardViewTips.setOnClickListener {
                DetailsFragment(Data.contents[3], supportFragmentManager).show()
            }

            exitButton.setOnClickListener {
                onBackPressed()
            }
        }
    }

    companion object {
        fun getStartIntent(context: Context) = Intent(context, MainActivity::class.java)
    }

    override fun onItemClick(position: Int) {
        DetailsFragment(Data.contents[position], supportFragmentManager).show()
    }

    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Exit Application?")
            .setMessage("Do you want to exit?")
            .setPositiveButton("Ok"){ _,_ -> super.onBackPressed() }
            .setNegativeButton("Cancel"){ d, _ -> d.dismiss()}
            .show()
    }
}