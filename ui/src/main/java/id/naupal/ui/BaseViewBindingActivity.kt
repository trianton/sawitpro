package id.naupal.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding

/**
 * Created by Naupal T. on 06/05/22.
 */

abstract class BaseViewBindingActivity<T : ViewBinding> : AppCompatActivity() {

    private lateinit var _binding: T
    val binding get() = _binding

    override fun onCreate(savedInstanceState: Bundle?) {
        setupDependencyInjection()
        super.onCreate(savedInstanceState)
        _binding = getLayoutBinding()
        setContentView(binding.root)
    }

    abstract fun getLayoutBinding(): T

    abstract fun setupDependencyInjection()
}