package com.sangam.sangamportfolio.Firebase

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore

object GetDocuments {

    interface FirestoreCallback {
        fun onCallback(data: MutableMap<String, Any>?)
    }

    fun getDocument(firestore: FirebaseFirestore, documentId: String, callback: FirestoreCallback) {
        val docRef = firestore.collection("PortfolioDocuments").document(documentId)
        docRef.get().addOnSuccessListener { document ->
            if (document != null) {
                callback.onCallback(document.data)
            } else {
                Log.d("FirestoreData", "No such document")
                callback.onCallback(null)
            }
        }.addOnFailureListener { exception ->
            Log.d("FirestoreData", "get failed with ", exception)
            callback.onCallback(null)
        }
    }
}
