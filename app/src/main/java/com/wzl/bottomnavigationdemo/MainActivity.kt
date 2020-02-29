package com.wzl.bottomnavigationdemo

import android.os.Bundle
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.wzl.bottomnavigationdemo.ui.dashboard.DashboardFragment
import com.wzl.bottomnavigationdemo.ui.home.HomeFragment
import com.wzl.bottomnavigationdemo.ui.notifications.NotificationsFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager2: ViewPager2 = findViewById(R.id.viewPager2)
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.nav_view)

        val toolbar: Toolbar = findViewById(R.id.toolbar)

        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int {
                return 3
            }

            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> HomeFragment()
                    1 -> DashboardFragment()
                    else -> NotificationsFragment()
                }
            }
        }

        // 当ViewPager切换页面时，改变底部导航栏的状态
        viewPager2.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                bottomNavigationView.menu.getItem(position).isChecked = true
                when (position) {
                    0 -> toolbar.title = "HomeFragment"
                    1 -> toolbar.title = "DashboardFragment"
                    2 -> toolbar.title = "NotificationFragment"
                }
            }
        })

        // 当ViewPager切换页面时，改变ViewPager的显示
        bottomNavigationView.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_home -> {
                    viewPager2.setCurrentItem(0, true)
                    toolbar.title = "HomeFragment"
                }
                R.id.navigation_dashboard -> {
                    viewPager2.setCurrentItem(1, true)
                    toolbar.title = "DashboardFragment"
                }
                R.id.navigation_notifications -> {
                    viewPager2.setCurrentItem(2, true)
                    toolbar.title = "NotificationFragment"
                }
            }
            true
        }

        setSupportActionBar(toolbar)
    }
}
