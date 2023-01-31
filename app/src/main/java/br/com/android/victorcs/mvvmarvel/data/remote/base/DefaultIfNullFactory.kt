package br.com.android.victorcs.mvvmarvel.data.remote.base

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import java.lang.reflect.Type

class DefaultIfNullFactory: JsonAdapter.Factory {
    override fun create(
        type: Type,
        annotations: MutableSet<out Annotation>,
        moshi: Moshi
    ): JsonAdapter<*> {
        val delegate = moshi.nextAdapter<Any>(this, type, annotations)
        return object : JsonAdapter<Any>() {
            override fun fromJson(reader: JsonReader): Any? {
                val blob1 = reader.readJsonValue()
                return try {
                    val blob = blob1 as? Map<*, *>
                    blob?.let {
                        val noNulls = blob.filterValues { it != null }
                        delegate.fromJsonValue(noNulls)
                    } ?: delegate.fromJsonValue(blob1)
                } catch (e: Exception) {
                    delegate.fromJsonValue(blob1)
                }
            }
            override fun toJson(writer: JsonWriter, value: Any?) = delegate.toJson(writer, value)
        }
    }
}
