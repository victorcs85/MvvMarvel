package br.com.android.victorcs.mvvmarvel.data.extensions

private const val HTTPS = "https://"
private const val HTTP = "http://"

fun String.toHttps() = this.replace(HTTP, HTTPS)
