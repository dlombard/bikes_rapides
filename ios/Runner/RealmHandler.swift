//
//  RealmHandler.swift
//  Runner
//
//  Created by Davenson Lombard on 2020-02-20.
//  Copyright © 2020 The Chromium Authors. All rights reserved.
//

import Foundation
import RealmSwift

class RealmHandler{
    let fChannel: FlutterMethodChannel
    var realm: Realm!
    var bikeStations: Results<BikeStations>!
    var bikeStationStatus: Results<BikeStationStatus>!
    
    init(chan: FlutterMethodChannel){
        self.fChannel = chan
    }
    
    func logIn(idToken: String, result: @escaping FlutterResult) {
        
        if(SyncUser.current == nil){
            let creds = SyncCredentials.init(customToken: idToken, provider: Provider.JWT)
            print(Constants.AUTH_URL)
            
            print(SyncUser.all.count)
            SyncUser.logIn(with: creds, server: Constants.AUTH_URL, onCompletion: { (user, err) -> () in
                
                if let error = err {
                    // Auth error: user already exists? Try logging in as that user.
                    print("Login failed: \(error)");
                    result(FlutterError(code: "UNAVAILABLE",
                    message: "\(error)",
                    details: nil))
                    return;
                }

                print("Login succeeded!");
                let config = SyncUser.current?.configuration(realmURL: Constants.BIKES_REALM, fullSynchronization: true )
                    
                Realm.asyncOpen(configuration: config!){ realm, error in
                      if let realm = realm {
                        self.realm = realm
                      } else if let error = error {
                        // Handle error that occurred while opening the Realm.
                        print(error)
                      }
                    }
                result(true)
                
            });
        }else {
             let config = SyncUser.current?.configuration(realmURL: Constants.BIKES_REALM, fullSynchronization: true )
            do{
                self.realm =  try Realm(configuration: config!)
            result(true)
            }catch{
                result(FlutterError(code: "UNAVAILABLE",
                message: "Failed to open realm",
                details: nil))
            }
            
            
        }
        print("Log in with token: \(idToken)");
       
    }
    
    func getStationsInJson() ->  [String]{
        var results: [String] = []
        if bikeStations == nil{
            bikeStations = realm.objects(BikeStations.self)
        }
        var properties = bikeStations.first?.objectSchema.properties.map() { $0.name }
        
        /*
             Removing rental_methods: Currently unnecessary and I don't feel like dealing with list<string> conversions
        */
        let index = properties?.lastIndex(of: "rental_methods")
        properties?.remove(at: index!)
        
        do{
            for bs in bikeStations {
                let dictionary = bs.dictionaryWithValues(forKeys: properties!)

                if let jsonData = try? JSONSerialization.data(withJSONObject: dictionary, options: .prettyPrinted){
                    let theJSONText = String(data: jsonData,
                                               encoding: .ascii)
                    results.append(theJSONText!)
                }

            }
            
            return results
        }catch{
            
        }

    }
    
    func getStationsStatusInJson()->  [String]{
    var results: [String] = []
    if bikeStationStatus == nil{
        bikeStationStatus = realm.objects(BikeStationStatus.self)
    }
    let properties = bikeStationStatus.first?.objectSchema.properties.map() { $0.name }

        do{
            for bs in bikeStationStatus {
                let dictionary = bs.dictionaryWithValues(forKeys: properties!)
                if let jsonData = try? JSONSerialization.data(withJSONObject: dictionary, options: .prettyPrinted){
                    let theJSONText =  String(data: jsonData,
                                               encoding: .ascii)
                    results.append(theJSONText!)
                }

            }
            print(results)
            return results
        }catch{
            
        }

    }
}
