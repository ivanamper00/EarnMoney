package van.per.hoccachkiemthemtien.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dakulangsakalam.customwebview.presentation.ui.jump.JumpActivity
import com.dakulangsakalam.customwebview.presentation.ui.jump.JumpType
import kotlinx.coroutines.ExperimentalCoroutinesApi
import van.per.hoccachkiemthemtien.R

@ExperimentalCoroutinesApi
class SplashActivity : JumpActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        splashAction(JumpType.JUMP_LINK, 1){ _, _ ->
            startActivity(MainActivity.getStartIntent(this))
            finish()
        }
    }
}