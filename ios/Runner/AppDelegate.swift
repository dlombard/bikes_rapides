import UIKit
import Flutter
import Firebase
import GoogleMaps

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate {
    var CHANNEL = "net.dlombard/dlo_notes"
  override func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
  ) -> Bool {
    GeneratedPluginRegistrant.register(with: self)
    GMSServices.provideAPIKey("AIzaSyDSjCMVzej-kSO_vdkgMe4e7OWMMGhxbMY")
    if FirebaseApp.app() == nil { FirebaseApp.configure()}
    
    
    let controller : FlutterViewController = window?.rootViewController as! FlutterViewController
    let fChannel = FlutterMethodChannel(name: CHANNEL, binaryMessenger: controller.binaryMessenger)
    
    let rHandler = RealmHandler(chan: fChannel);
    
    fChannel.setMethodCallHandler({
      (call: FlutterMethodCall, result: @escaping FlutterResult) -> Void in
      // Note: this method is invoked on the UI thread.
      // Handle battery messages.
        switch call.method{
        case "LOGIN":
            guard let args = call.arguments else {
              return
            }
            if let myArgs = args as? [String: Any]{
                let idToken = myArgs["idToken"] as! String
                rHandler.logIn(idToken: idToken, result: result)
            }
            break
        case "GET_BIKE_STATIONS":
            result(rHandler.getStationsInJson())
            break;
            
        case "GET_BIKE_STATIONS_STATUS":
            result(rHandler.getStationsStatusInJson())
            break;
        default:
            break;
        }
    })

    
    return super.application(application, didFinishLaunchingWithOptions: launchOptions)
  }
}
