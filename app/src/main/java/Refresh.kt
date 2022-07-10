import androidx.fragment.app.Fragment
import com.example.vkr.R

fun Fragment.replaceFragment(fragment: Fragment)
{
    fragmentManager?.beginTransaction()
        ?.addToBackStack(null)
        ?.replace(R.id.fl_wrapper,
            fragment
        )?.commit()

}