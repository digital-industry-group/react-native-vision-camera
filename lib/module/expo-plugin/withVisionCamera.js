import { withPlugins, AndroidConfig, createRunOncePlugin } from '@expo/config-plugins';
import { withDisableFrameProcessorsAndroid } from './withDisableFrameProcessorsAndroid';
import { withDisableFrameProcessorsIOS } from './withDisableFrameProcessorsIOS'; // eslint-disable-next-line @typescript-eslint/no-var-requires, @typescript-eslint/no-unsafe-assignment

const pkg = require('../../../package.json');

const CAMERA_USAGE = 'Allow $(PRODUCT_NAME) to access your camera';
const MICROPHONE_USAGE = 'Allow $(PRODUCT_NAME) to access your microphone';

const withCamera = function (config) {
  let props = arguments.length > 1 && arguments[1] !== undefined ? arguments[1] : {};
  if (config.ios == null) config.ios = {};
  if (config.ios.infoPlist == null) config.ios.infoPlist = {};
  config.ios.infoPlist.NSCameraUsageDescription = props.cameraPermissionText ?? config.ios.infoPlist.NSCameraUsageDescription ?? CAMERA_USAGE;

  if (props.enableMicrophonePermission) {
    config.ios.infoPlist.NSMicrophoneUsageDescription = props.microphonePermissionText ?? config.ios.infoPlist.NSMicrophoneUsageDescription ?? MICROPHONE_USAGE;
  }

  const androidPermissions = ['android.permission.CAMERA'];
  if (props.enableMicrophonePermission) androidPermissions.push('android.permission.RECORD_AUDIO');

  if (props.disableFrameProcessors) {
    config = withDisableFrameProcessorsAndroid(config);
    config = withDisableFrameProcessorsIOS(config);
  }

  return withPlugins(config, [[AndroidConfig.Permissions.withPermissions, androidPermissions]]);
};

export default createRunOncePlugin(withCamera, pkg.name, pkg.version);
//# sourceMappingURL=withVisionCamera.js.map