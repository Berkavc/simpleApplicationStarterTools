package com.example.marketRecognizerApp.ui.detector

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.marketRecognizerApp.Constants
import com.example.marketRecognizerApp.R
import com.example.marketRecognizerApp.databinding.FragmentDetectorBinding
import com.example.marketRecognizerApp.ui.BaseFragment
import com.example.marketRecognizerApp.ui.detectorcamera.DetectorCameraActivity
import com.example.marketRecognizerApp.utils.*
import com.example.marketRecognizerApp.utils.CameraPermissionHelper.settingsIntent
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetectorFragment : BaseFragment(R.layout.fragment_detector) {
    private val detectorFragmentViewModel: DetectorFragmentViewModel by viewModels()
    private val binding by viewBinding(FragmentDetectorBinding::bind)
    private lateinit var settingsIntent: Intent
    private lateinit var resultLauncher: ActivityResultLauncher<Intent>

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(detectorFragmentViewModel) {
            observe(isCameraClicked, ::controlCameraClicked)
            observe(isVoiceClicked, ::controlVoiceClicked)
        }
        binding.lifecycleOwner = this
        binding.viewModel = detectorFragmentViewModel

        activity?.let {
            settingsIntent = settingsIntent(it)
            resultLauncher =
                registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
//                    if (result.resultCode == Activity.RESULT_OK) {
//                        // There are no request codes
//                        val data: Intent? = result.data
//                        if (data?.extras?.getInt("requestCode") == Constants.CAMERA_DETECTOR_PERMISSION_REQUEST_CODE) {
//                            val intent = Intent(it, DetectorCameraActivity::class.java)
//                            navigateToWithoutPopNextActivity(intent)
//                        }
//
//                    }
//                    if(CameraPermissionHelper.hasCameraPermission(it)){
//                        val intent = Intent(it, DetectorCameraActivity::class.java)
//                        navigateToWithoutPopNextActivity(intent)
//                    }
                }
        }


        arrangeUI()

    }

    private fun arrangeUI() {
        activity?.let {
            if (!checkCameraHardware(it)) {
                binding.constraintLayoutDetectorCamera.background =
                    ContextCompat.getDrawable(it, R.drawable.bg_grey_label)
            }
        }
    }

    private fun controlCameraClicked(isClicked: Boolean?) {
        if (isClicked == true) {
            activity?.let {
                if (!checkCameraHardware(it)) {
                    initializeErrorDialogPopup(
                        message = resources.getString(R.string.error_camera_not_supported),
                        context = it
                    )
                } else {
                    if (CameraPermissionHelper.requestCameraPermissionFragment(
                            this,
                            settingsIntent
                        )
                    ) {
                        val intent = Intent(it, DetectorCameraActivity::class.java)
                        navigateToWithoutPopNextActivity(intent)
                    }
                }
            }
        }
    }

    private fun controlVoiceClicked(isClicked: Boolean?) {
        if (isClicked == true) {

        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        permissionResult()
    }

    private fun permissionResult() {
        activity?.let {
            if (!CameraPermissionHelper.hasCameraPermission(it)) {
                if (!CameraPermissionHelper.shouldShowRequestPermissionRationale(it)) {
                    // Permission denied with checking "Do not ask again".

//                    CameraPermissionHelper.launchPermissionSettings(it,settingsIntent)
                    if (this::resultLauncher.isInitialized) {
                        resultLauncher.launch(settingsIntent)
                    }
                }
            } else {
                val intent = Intent(it, DetectorCameraActivity::class.java)
                navigateToWithoutPopNextActivity(intent)
            }
        }
    }
}