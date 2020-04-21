package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_profile.*

class ProfileFragment : Fragment() {

    private lateinit var tabsText: List<String>
    private val adapter by lazy {
        ViewPagerAdapter(childFragmentManager, lifecycle)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        tabsText = listOf<String>(getString(R.string.tab1),
            getString(R.string.tab2), getString(R.string.tab3))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pager.adapter = adapter

        TabLayoutMediator(tabs, pager) { tab, position ->
            tab.text = tabsText.get(position)
        }.attach()
    }
}

class ViewPagerAdapter(manager: FragmentManager, lifecycle: Lifecycle): FragmentStateAdapter(manager, lifecycle) {
    override fun getItemCount(): Int = 3

    override fun createFragment(position: Int): Fragment = when(position) {
        0 -> UserInfoFragment.newInstance()
        1 -> SavedRecipesFragment.newInstance()
        else -> ShoppingListFragment.newInstance()
    }
}

class UserInfoFragment : Fragment(R.layout.fragment_layout) {

    companion object {
        fun newInstance() = UserInfoFragment()
    }

}

class SavedRecipesFragment : Fragment(R.layout.fragment_layout) {
    companion object {
        fun newInstance() = SavedRecipesFragment()
    }
}

class ShoppingListFragment : Fragment(R.layout.fragment_layout) {
    companion object {
        fun newInstance() = ShoppingListFragment()
    }
}