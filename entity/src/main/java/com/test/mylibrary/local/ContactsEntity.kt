package com.test.mylibrary.local

import android.net.Uri
import androidx.compose.runtime.Immutable
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

@Immutable
class ContactsEntity(
  var name: String = "",
  var surname: String? = null,
  var phone: String = "",
  var pictureUri: String? = null
) : RealmObject {
  @PrimaryKey
  var _id: ObjectId = ObjectId()

  constructor() : this(name = "", surname = null, phone = "", pictureUri = null)
}