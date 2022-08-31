package com.mrousavy.camera

import android.hardware.camera2.*
import androidx.camera.camera2.internal.compat.quirk.CameraQuirks.get
import androidx.camera.camera2.interop.Camera2Interop
import androidx.camera.core.Camera
import androidx.camera.core.CameraSelector
import androidx.camera.core.Preview
import java.lang.reflect.Array.get

suspend fun CameraView.manuallyFocus(lensDistance: Float, builder: Preview.Builder) {
  System.out.println("JAMES calling update focus distance" + lensDistance.toString());

  val extender: Camera2Interop.Extender<*> = Camera2Interop.Extender(builder)
  extender.setCaptureRequestOption(CaptureRequest.CONTROL_AE_MODE, CameraMetadata.CONTROL_AE_MODE_OFF)
  extender.setCaptureRequestOption(CaptureRequest.CONTROL_AF_MODE,CaptureRequest.CONTROL_AF_MODE_OFF);
  extender.setCaptureRequestOption(CaptureRequest.LENS_FOCUS_DISTANCE, lensDistance)

  builder.build()


//
//  try {
//    val characteristics = cameraManager.getCameraCharacteristics(1);
//  } catch (CameraAccessException e) {
//    e.printStackTrace();
//  }
//
//  float minimumLens = characteristics.get(CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE);
//
//  float num = (distance * minimumLens / 100);
//  captureRequestBuilder.set(CaptureRequest.CONTROL_AF_MODE, CaptureRequest.CONTROL_AF_MODE_OFF);
//  captureRequestBuilder.set(CaptureRequest.LENS_FOCUS_DISTANCE, num);

//  CaptureRequest.LENS_INFO_FOCUS_DISTANCE_CALIBRATION_APPROXIMATE

  System.out.println("DONE FOCUSING " + cameraId);
}


suspend fun CameraView.getCurrentLensPosition(): String {
  System.out.println("JAMES calling get lens position")
  System.out.println("JAMES CAPTURE LENS" + CaptureRequest.LENS_FOCUS_DISTANCE);
  System.out.println("JAMES CAPTURE LENS LEN" + CaptureRequest.LENS_FOCAL_LENGTH);
//  val camChars: CameraCharacteristics = CameraManager.get(CaptureRequest.LENS_FOCAL_LENGTH)


//  System.out.println("JAMES CAPTURE LENS thing" + CaptureRequest.get(CaptureRequest.LENS_FOCUS_DISTANCE));
  val camCharacteristics = CameraCharacteristics.LENS_INFO_AVAILABLE_FOCAL_LENGTHS.toString()
  val camCharacteristics2 = CameraCharacteristics.LENS_INFO_MINIMUM_FOCUS_DISTANCE.toString()
  val thing: CameraManager

  System.out.println("JAMES CAMERA CHARACTERISTICS" + camCharacteristics)
  System.out.println("JAMES CAMERA CHARACTERISTICS2" + camCharacteristics2)

  return camCharacteristics
//  return CaptureRequest.get(CaptureRequest.LENS_FOCUS_DISTANCE)
}
