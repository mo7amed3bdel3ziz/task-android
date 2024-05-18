package com.example.androidtask.data.remote.models

data class InformationModel(
    var id: Int,
    var identityNumber: String,
    var tax_record: String,
    var activity: String,
    var nationality: String,
    var vehicle_image: String,
    var vehicle_tablet_image: String,
    var vehicle_registration: String,
    var driving_image: String
)
