package com.example.sensorapp.data.export

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class History: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var title: String = ""
    var accelerometerPath: String = ""
    var stepCounterPath: String = ""
    var lightPath: String = ""
    var timestamp: Long = 0
}