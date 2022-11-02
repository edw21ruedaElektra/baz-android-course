package com.example.wizeline.utils

import com.example.wizeline.data.datasource.models.BookInfoEntity

fun mockListBooks():List<BookInfoEntity>{
    return listOf(
        BookInfoEntity("dll_mxn","100","5000",
            "20","4000","20","2000"),
        BookInfoEntity("eur_mxn","100","5000",
            "20","4000","20","2000"),
        BookInfoEntity("hnd_mxn","100","5000",
            "20","4000","20","2000")
    )
}