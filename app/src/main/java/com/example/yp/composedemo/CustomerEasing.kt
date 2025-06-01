package com.example.yp.composedemo

import androidx.compose.animation.core.Easing

class CustomerEasing:Easing {
    override fun transform(fraction: Float): Float {
            if (fraction < 0.3 ||fraction > 0.7 ){
                return  fraction
            }else{
                return  0.5f
            }
    }
}