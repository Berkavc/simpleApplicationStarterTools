package com.example.marketRecognizerApp.ui.detectorcamera

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.ImageFormat
import android.hardware.camera2.CameraCaptureSession
import android.hardware.camera2.CameraCharacteristics
import android.hardware.camera2.CameraDevice
import android.hardware.camera2.CameraManager
import android.os.Bundle
import android.os.Handler
import android.view.SurfaceHolder
import android.view.View
import androidx.activity.viewModels
import com.example.marketRecognizerApp.Constants.Companion.CAMERA_DETECTOR_EYE_TUTORIAL
import com.example.marketRecognizerApp.R
import com.example.marketRecognizerApp.databinding.ActivityDetectorCameraBinding
import com.example.marketRecognizerApp.ui.BaseActivity
import com.example.marketRecognizerApp.utils.CameraPermissionHelper
import com.example.marketRecognizerApp.utils.SharedPreference
import com.example.marketRecognizerApp.utils.observe
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*

@AndroidEntryPoint
class DetectorCameraActivity : BaseActivity() {
    private val detectorCameraActivityViewModel: DetectorCameraActivityViewModel by viewModels()
    private lateinit var binding: ActivityDetectorCameraBinding
    private var coroutineScopeEye: CoroutineScope = CoroutineScope(Dispatchers.IO)
    private var controlEyePlayed = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetectorCameraBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        with(detectorCameraActivityViewModel) {
            observe(isCloseClicked, ::controlCloseClicked)
            observe(isCaptureClicked, ::controlCaptureClicked)
        }

        binding.lifecycleOwner = this
        binding.viewModel = detectorCameraActivityViewModel

        controlEyePlayed =
            SharedPreference(context = this).getValueBoolean(CAMERA_DETECTOR_EYE_TUTORIAL, false)
        arrangeUI()

    }

    private fun arrangeUI() {
        arrangeCamera()
        if (!controlEyePlayed) {
            arrangeEyeChat()
        }else{
            binding.imageViewDetectorCameraCapture.visibility = View.VISIBLE
        }
    }

    private fun arrangeCamera() {
        val surfaceReadyCallback = object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                startCameraSession()
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {
            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {}
        }

        binding.surfaceView.holder.addCallback(surfaceReadyCallback)
    }

    private fun arrangeEyeChat() {
        binding.textViewDetectorCameraBubble.visibility = View.VISIBLE
        binding.imageViewDetectorCameraBubbleTriangle1.visibility = View.VISIBLE
        binding.imageViewDetectorCameraBubbleTriangle2.visibility = View.VISIBLE
        binding.lottieDetectorEye.visibility = View.VISIBLE
        coroutineScopeEye = CoroutineScope(Dispatchers.IO)
        coroutineScopeEye.launch {
            delay(3500)
            withContext(Dispatchers.Main) {
                binding.textViewDetectorCameraBubble.text =
                    resources.getString(R.string.detector_camera_tutorial_description_bye)
                delay(2000)
                SharedPreference(this@DetectorCameraActivity).saveBoolean(
                    CAMERA_DETECTOR_EYE_TUTORIAL,
                    true
                )
                binding.textViewDetectorCameraBubble.visibility = View.GONE
                binding.imageViewDetectorCameraBubbleTriangle1.visibility = View.GONE
                binding.imageViewDetectorCameraBubbleTriangle2.visibility = View.GONE
                binding.lottieDetectorEye.visibility = View.GONE
                binding.imageViewDetectorCameraCapture.visibility = View.VISIBLE
            }
        }
    }

    private fun controlCloseClicked(isClicked: Boolean?) {
        if (isClicked == true) {
            finish()
        }
    }

    private fun controlCaptureClicked(isClicked: Boolean?) {
        if (isClicked == true) {

        }
    }


    @SuppressLint("MissingPermission")
    private fun startCameraSession() {
        val cameraManager = getSystemService(Context.CAMERA_SERVICE) as CameraManager
        if (cameraManager.cameraIdList.isEmpty()) {
            // no cameras
            return
        }
        val firstCamera = cameraManager.cameraIdList[0]
        cameraManager.openCamera(firstCamera, object : CameraDevice.StateCallback() {
            override fun onDisconnected(p0: CameraDevice) {}
            override fun onError(p0: CameraDevice, p1: Int) {}

            override fun onOpened(cameraDevice: CameraDevice) {
                // use the camera
                val cameraCharacteristics =
                    cameraManager.getCameraCharacteristics(cameraDevice.id)

                cameraCharacteristics[CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP]?.let { streamConfigurationMap ->
                    streamConfigurationMap.getOutputSizes(ImageFormat.YUV_420_888)
                        ?.let { yuvSizes ->
                            val previewSize = yuvSizes.last()


                            val displayRotation: Int?
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.R) {
                                displayRotation = display?.rotation
                            } else {
                                @Suppress("DEPRECATION")
                                displayRotation = windowManager.defaultDisplay.rotation

                            }
                            displayRotation?.let {
                                val swappedDimensions =
                                    CameraPermissionHelper.areDimensionsSwapped(
                                        displayRotation,
                                        cameraCharacteristics
                                    )
                                val rotatedPreviewWidth =
                                    if (swappedDimensions) previewSize.height else previewSize.width
                                val rotatedPreviewHeight =
                                    if (swappedDimensions) previewSize.width else previewSize.height

                                binding.surfaceView.holder.setFixedSize(
                                    rotatedPreviewWidth,
                                    rotatedPreviewHeight
                                )

                                val previewSurface = binding.surfaceView.holder.surface

                                val captureCallback =
                                    object : CameraCaptureSession.StateCallback() {
                                        override fun onConfigureFailed(session: CameraCaptureSession) {}

                                        override fun onConfigured(session: CameraCaptureSession) {
                                            // session configured
                                            val previewRequestBuilder =
                                                cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW)
                                                    .apply {
                                                        addTarget(previewSurface)
                                                    }
                                            session.setRepeatingRequest(
                                                previewRequestBuilder.build(),
                                                object :
                                                    CameraCaptureSession.CaptureCallback() {},
                                                Handler { true }
                                            )
                                        }
                                    }

                                cameraDevice.createCaptureSession(
                                    mutableListOf(previewSurface),
                                    captureCallback,
                                    Handler { true })
                            }


                        }

                }
            }
        }, Handler { true })
    }

    private fun clearEye() {
        if (coroutineScopeEye.isActive) {
            coroutineScopeEye.cancel()
        }
    }

    override fun onBackPressed() {
        clearEye()
        super.onBackPressed()
    }


}