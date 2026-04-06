package org.microg.vending.playapi.utils

import okhttp3.HttpUrl

object Util {

    fun parseResponse(response: ByteArray): Map<String, String> {
        val map = mutableMapOf<String, String>()
        val text = String(response)

        text.split("\n", "\r").forEach { line ->
            val parts = line.split("=", limit = 2)
            if (parts.size >= 2) {
                map[parts[0]] = parts[1]
            }
        }

        return map
    }

    fun buildUrlEx(
        url: String,
        params: Map<String, List<String>>?
    ): String {
        val base = url.toHttpUrlOrNull()
            ?: throw IllegalArgumentException("Invalid URL: $url")

        val builder = base.newBuilder()

        params?.forEach { (key, values) ->
            values.forEach { value ->
                builder.addQueryParameter(key, value)
            }
        }

        return builder.build().toString()
    }
}