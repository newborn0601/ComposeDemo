package com.example.yp.composedemo.model

import androidx.compose.ui.graphics.vector.ImageVector

/***
 * data class
 * @property title String
 * @property icon ImageVector
 * @constructor
 */
data class NavigationItem(
    val title:String,
    val icon: ImageVector

)