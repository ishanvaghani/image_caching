package com.example.imagecaching.data

import com.example.imagecaching.data.remote.dto.MediaDto
import com.example.imagecaching.domain.model.Media

fun MediaDto.toMedia(): Media {
    return Media(
        thumbnail.domain + "/" + thumbnail.basePath + "/0/" + thumbnail.key
    )
}