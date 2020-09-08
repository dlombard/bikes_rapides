//
//  BikeStations.swift
//  Runner
//
//  Created by Davenson Lombard on 2020-02-25.
//  Copyright © 2020 The Chromium Authors. All rights reserved.
//

import Foundation
import RealmSwift

class BikeStations: Object {
    @objc dynamic var station_id: String = ""
    @objc dynamic var external_id: String = ""
    @objc dynamic var name: String = ""
    @objc dynamic var short_name: String = ""
    @objc dynamic var lat: Double = 0
    @objc dynamic var lon: Double = 0
    let rental_methods = List<String?>()
    @objc dynamic var capacity: Int = 0

    override static func primaryKey() -> String? {
        return "station_id"
    }
}
