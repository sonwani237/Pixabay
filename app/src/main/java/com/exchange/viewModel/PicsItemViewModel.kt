package com.exchange.viewModel

import androidx.databinding.BaseObservable
import com.exchange.model.SearchPics

class PicsItemViewModel(model: SearchPics.Pics) : BaseObservable(){

    private var mModel: SearchPics.Pics? =null

    init {
        this.mModel = model
    }

    fun setData(exchangeRete: SearchPics.Pics) {
        mModel = exchangeRete
        notifyChange()
    }

    fun getProductImg() : String{
        return mModel?.webformatURL ?:"N/A"
    }


}