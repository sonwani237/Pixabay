package com.exchange.model


data class SearchPics(val total: String, val totalHits: String, val hits: MutableList<Pics>) {
    data class Pics(val userImageURL: String, val webformatURL: String)
}