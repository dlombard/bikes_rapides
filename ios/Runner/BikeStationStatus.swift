//
//  Bikes.swift
//  Runner
//
//  Created by Davenson Lombard on 2020-02-25.
//  Copyright © 2020 The Chromium Authors. All rights reserved.
//

import Foundation
import RealmSwift

class BikeStationStatus: Object {
    @objc dynamic var station_id: String = ""
    @objc dynamic var num_bikes_available: Int = 0
    @objc dynamic var num_ebikes_available: Int = 0
    @objc dynamic var num_bikes_disabled: Int = 0
    @objc dynamic var num_docks_available: Int = 0
    @objc dynamic var num_docks_disabled: Int = 0
    @objc dynamic var is_installed: Int = 0
    @objc dynamic var is_renting: Int = 0
    @objc dynamic var is_returning: Int = 0
    @objc dynamic var last_reported: Int = 0

    override static func primaryKey() -> String? {
        return "station_id"
    }
}
