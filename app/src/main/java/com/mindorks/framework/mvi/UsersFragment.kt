package com.mindorks.framework.mvi

import android.os.Bundle
import android.os.Parcelable
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.google.gson.Gson
import com.mindorks.framework.mvi.data.model.Api_Response
import com.mindorks.framework.mvi.data.model.Data
import com.mindorks.framework.mvi.ui.main.Communicator
import com.mindorks.framework.mvi.ui.main.view.MainActivity

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [UsersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class UsersFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val args = arguments
        val response : Api_Response? = args?.getParcelable("users")
        if (response != null) {
            Toast.makeText(activity, response.data[0].name, Toast.LENGTH_LONG).show()
        }
        return inflater.inflate(R.layout.fragment_users, container, false)

    }

    companion object {

        @JvmStatic
        fun newInstance(users: Api_Response) : UsersFragment {
            val args = Bundle()
            args.putParcelable("users",users)
            val fragment = UsersFragment()
            fragment.arguments = args
            return fragment
    }

}}