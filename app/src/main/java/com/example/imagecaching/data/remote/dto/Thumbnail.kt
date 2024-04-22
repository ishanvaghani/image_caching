package com.example.imagecaching.data.remote.dto

data class Thumbnail(
    val aspectRatio: Int,
    val basePath: String,
    val domain: String,
    val id: String,
    val key: String,
    val qualities: List<Int>,
    val version: Int
)