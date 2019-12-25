package ua.turskyi.basicactivity

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.view.ViewCompat
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

/**
 * @Description
 * On tap on fab the custom snackbar is showing up.
 */
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private var mSnackbar: Snackbar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        super.onCreate(savedInstanceState)
        setSupportActionBar(toolbar)

        fab.setOnClickListener { view ->
            mSnackbar = Snackbar.make(view, "Пора кормить кота!", Snackbar.LENGTH_INDEFINITE)
                .setAction("Да", snackbarOnClickListener)
                .setActionTextColor(Color.WHITE) // цвет текста у кнопки действия

            val snackbarView = mSnackbar!!.view
            snackbarView.setBackgroundColor(Color.BLUE)
            val snackTextView =
                snackbarView.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
            snackTextView.setTextColor(Color.RED)
            mSnackbar!!.config(this)
            mSnackbar!!.show()
        }
    }

    private fun Snackbar.config(context: Context){
        val params = this.view.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(12, 12, 12, 12)
        this.view.layoutParams = params

        this.view.background = context.getDrawable(R.drawable.bg_snackbar)

        ViewCompat.setElevation(this.view, 6f)
    }

    var snackbarOnClickListener: View.OnClickListener = View.OnClickListener {
        Toast.makeText(
            applicationContext, "Молодец!",
            Toast.LENGTH_LONG
        ).show()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}
