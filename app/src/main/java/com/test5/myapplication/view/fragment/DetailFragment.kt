package com.test5.myapplication.view.fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.test5.myapplication.data.model.Desc
import com.test5.myapplication.data.model.NetworkResult
import com.test5.myapplication.data.model.User
import com.test5.myapplication.databinding.UserdetailsBinding
import com.test5.myapplication.viewmodel.DetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment: Fragment() {
    private lateinit var binding: UserdetailsBinding
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{

        binding = UserdetailsBinding.inflate(inflater, container, false)

       return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.getInt("id")?.let {
        viewModel.getId(it)}
        setupObserverUser()
          setupObserverDetail()
    }

    private fun setupObserverUser() {

        viewModel.User.observe(viewLifecycleOwner, Observer {

            when (it.status) {
                NetworkResult.Status.Success -> {
                    bindUser(it.data!!)
                    binding.progressBar.visibility = View.GONE
                    binding.UserDe.visibility = View.VISIBLE
                }

                NetworkResult.Status.Error ->
                    Toast.makeText(activity, it.message, Toast.LENGTH_SHORT).show()

                NetworkResult.Status.Loading -> {
                    binding.progressBar.visibility = View.VISIBLE
                    binding.UserDe.visibility = View.GONE
                }
            }
        })
    }
    private fun setupObserverDetail() {

        viewModel.Desc.observe(viewLifecycleOwner, Observer {

            if (it.status ==  NetworkResult.Status.Success ) {
                    if(it.data != null)
                    bindDetail(it.data)
                }



        })
    }

    private fun bindUser(user: User) {
        binding.firstName.text = user.first_name
        binding.lastName.text = user.last_name
        binding.email.text = user.email
        Glide.with(binding.root)
            .load(user.avatar)
            .transform(CircleCrop())
            .into(binding.image)
    }
    private fun bindDetail(desc: Desc){
        binding.desc.text = desc.text
    }
}