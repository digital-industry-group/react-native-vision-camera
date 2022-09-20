package com.mrousavy.camera

import android.annotation.SuppressLint
import android.hardware.camera2.*
import androidx.camera.camera2.Camera2Config
import androidx.camera.camera2.interop.Camera2CameraControl
import androidx.camera.camera2.interop.Camera2Interop
import androidx.camera.camera2.interop.Camera2CameraInfo
import androidx.camera.core.ImageCapture
import androidx.camera.core.Preview
import androidx.camera.core.impl.PreviewConfig
import androidx.camera.lifecycle.ProcessCameraProvider

@SuppressLint("RestrictedApi")
suspend fun CameraView.manuallyFocus(lensDistance: Float, builder: Preview.Builder) {
//  System.out.println("JAMES calling update focus distance" + lensDistance.toString());

  val extender: Camera2Interop.Extender<*> = Camera2Interop.Extender<Preview>(builder)
  extender.setCaptureRequestOption(CaptureRequest.CONTROL_AE_MODE, CameraMetadata.CONTROL_AE_MODE_OFF)
  extender.setCaptureRequestOption(CaptureRequest.CONTROL_AF_MODE,CaptureRequest.CONTROL_AF_MODE_OFF);

  val camChars: CameraCharacteristics? = camera?.let { Camera2CameraInfo.extractCameraCharacteristics(it.cameraInfo) }
  val minimumLens = camChars?.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE)

  if (minimumLens == null) {
    return
  }

  val newDistance = lensDistance * minimumLens

  extender.setCaptureRequestOption(CaptureRequest.LENS_FOCUS_DISTANCE, newDistance)
}


suspend fun CameraView.getCurrentLensPosition(): String {
  val camCharacteristics = CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS.toString()
  val camCharacteristics2 = CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE.toString()
  val thing: CameraManager

  return camCharacteristics
}
