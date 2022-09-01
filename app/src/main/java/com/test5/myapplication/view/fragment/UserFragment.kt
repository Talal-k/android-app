package com.test5.myapplication.view.fragment

import com.test5.myapplication.view.adapter.UserAdapter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.test5.myapplication.R
import com.test5.myapplication.data.model.NetworkResult
import com.test5.myapplication.databinding.UsersBinding
import com.test5.myapplication.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment:Fragment(), UserAdapter.ItemListener {
    private lateinit var binding: UsersBinding
    private val viewModel: MainViewModel by viewModels()
    private lateinit var adapter: UserAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = UsersBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        setupObservers()
    }

    private fun setupRecyclerView() {
        adapter = UserAdapter(this)
        binding.userRv.layoutManager = LinearLayoutManager(requireContext())
        binding.userRv.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.users.observe(viewLifecycleOwner, Observer {
            when (it.status) {
                NetworkResult.Status.Success -> {
                    binding.progressBar.visibility = View.GONE
                    if (!it.data.isNullOrEmpty()) adapter.setUsers(ArrayList(it.data))
                }
                NetworkResult.Status.Error ->{}
                NetworkResult.Status.Loading ->
                    binding.progressBar.visibility = View.VISIBLE
            }
        })
    }

    override fun OnClicked(id: Int) {
        findNavController().navigate(
            R.id.action_UserFragment_to_DetailFragment,
            bundleOf("id" to id))
    }
}