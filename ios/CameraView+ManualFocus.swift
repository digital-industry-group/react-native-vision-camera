//
//  CameraView+ManualFocus.swift
//  VisionCamera
//
//  This is to support a manual focus feature where the user can set the lens position to be
//  a specific value and it stays there unless otherwise denoted from the JS layer.
//
//  Created by James Lund on 8/29/22.
//

import AVFoundation
import Foundation

extension CameraView {
    func manuallyFocus(lensPosition: NSNumber, promise: Promise) {
        withPromise(promise) {
            guard let device = self.videoDeviceInput?.device else {
                throw CameraError.session(SessionError.cameraNotReady)
            }
            if !device.isLockingFocusWithCustomLensPositionSupported {
                throw CameraError.device(DeviceError.manualFocusNotSupported)
            }

            var pos = lensPosition.floatValue

            // Only lens positions between 0 and 1.0 are supported on iOS devices.
            if pos > 1.0 {
                pos = 1.0
            } else if pos < 0 {
                pos = 0
            }

            do {
                try device.lockForConfiguration()

                device.setFocusModeLocked(lensPosition: pos)

                device.unlockForConfiguration()
                return nil
            } catch {
                throw CameraError.device(DeviceError.configureError)
            }
        }
    }

    func getCurrentLensPosition(promise: Promise) {
        withPromise(promise) {
            guard let device = self.videoDeviceInput?.device else {
                throw CameraError.session(SessionError.cameraNotReady)
            }

            return device.lensPosition
        }
    }
}
