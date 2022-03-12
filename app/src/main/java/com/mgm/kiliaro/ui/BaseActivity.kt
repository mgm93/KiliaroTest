package com.mgm.kiliaro.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.mgm.kiliaro.generals.extensions.viewBinding
import com.mgm.kiliaro.generals.utils.PermissionRequest

abstract class BaseActivity<T: ViewBinding>(bindingInflater: (LayoutInflater) -> T): AppCompatActivity() {

    protected val binding by viewBinding(bindingInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        PermissionRequest.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onStart() {
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
    }
}