package andrespin.onlinedictionary

import andrespin.domain.entity.Word
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle


//@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    lateinit var w: Word
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}