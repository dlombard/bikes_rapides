import UIKit
import Flutter

@UIApplicationMain
@objc class AppDelegate: FlutterAppDelegate {
    var CHANNEL = "net.dlombard/bikes_rapides"
    var REALM_APP_ID = "dlo-wes-znvyv"
  override func application(
    _ application: UIApplication,
    didFinishLaunchingWithOptions launchOptions: [UIApplication.LaunchOptionsKey: Any]?
  ) -> Bool {
    GeneratedPluginRegistrant.register(with: self)
    initFlutter()
    return super.application(application, didFinishLaunchingWithOptions: launchOptions)
  }
    
    func initFlutter(){
        let controller : FlutterViewController = window?.rootViewController as! FlutterViewController
        let fChannel = FlutterMethodChannel(name: CHANNEL, binaryMessenger: controller.binaryMessenger)
        
        let rHandler = RealmHandler(chan: fChannel, realmAppId: REALM_APP_ID);
        
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
            case "LOGIN_EMAIL_PASSWORD":
                print("LOGIN_EMAIL_PASSWORD")
                guard let args = call.arguments else {
                  return
                }
                if let myArgs = args as? [String: Any]{
                    let email = myArgs["email"] as! String
                    let password = myArgs["password"] as! String
                    rHandler.emailLogIn(email: email, password: password, result: result)
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
    }
}
