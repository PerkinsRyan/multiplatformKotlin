package com.rperkins.nationalparks

import com.google.firebase.firestore.FirebaseFirestore

class ParksCloud: IParksCloud {
    private val db = FirebaseFirestore.getInstance()

    override fun getParks(onSuccess: (List<Park>) -> Unit) {
        db.collection("parks").addSnapshotListener { querySnapshot, _ ->
            querySnapshot?.let {
                val parks = it.toObjects(Park::class.java)
                parks.sortBy { it.name }
                onSuccess(parks)
            }
        }
    }
}