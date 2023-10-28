package com.example.ucn_conectme

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(manager: FragmentManager) : FragmentPagerAdapter(manager) {

    private val fragmentList = ArrayList<Fragment>()
    private val titleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    fun addFragment(fragment: Fragment, title: String) {
        fragmentList.add(fragment)
        titleList.add(title)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> null  // Devuelve null solo para la primera pestaña
            else -> titleList[position] // Devuelve el título para las demás pestañas
        }
    }
    fun getIconResource(position: Int): Int {
        return when (position) {
            0 -> R.drawable.baseline_group_work_24
            else -> R.drawable.baseline_group_work_24
        }
    }
}
