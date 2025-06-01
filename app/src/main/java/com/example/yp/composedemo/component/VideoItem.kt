package com.example.yp.composedemo.component
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.compose.Dimension
import androidx.constraintlayout.compose.layoutId
import coil.compose.AsyncImage
import com.example.yp.composedemo.model.VideoEntity


@Composable
fun VideoItem(videoEntity: VideoEntity) {

    val constraintSet = ConstraintSet {
        val title = createRefFor("title")
        val cover = createRefFor("cover")
        val type = createRefFor("type")
        val duration = createRefFor("duration")
        val divider = createRefFor("divider")
        val icon = createRefFor("icon")

        constrain(cover) {
            start.linkTo(parent.start)
            centerVerticallyTo(parent)
            width = Dimension.value(115.5.dp)
        }

        constrain(title) {
            start.linkTo(cover.end, margin = 8.dp)
            end.linkTo(parent.end)
            width = Dimension.fillToConstraints
        }

        constrain(type) {
            start.linkTo(icon.end, margin = 4.dp,)
            bottom.linkTo(parent.bottom)
        }

        constrain(duration) {
            end.linkTo(parent.end)
            bottom.linkTo(parent.bottom)
        }

        constrain(divider) {
            top.linkTo(cover.bottom, margin = 4.dp)
        }

        constrain(icon) {
            start.linkTo(title.start)
            bottom.linkTo(parent.bottom)
        }

    }

    ConstraintLayout(
        constraintSet, modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        AsyncImage(
            model = videoEntity.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .layoutId("cover")
                .aspectRatio(16 / 9f)
                .clip(RoundedCornerShape(8.dp))
        )
        Text(
            text = videoEntity.title,
            fontSize = 16.sp,
            color = Color(0xFF666666),
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .layoutId("title")
        )
        Icon(
            imageVector = Icons.Default.Movie,
            contentDescription = null,
            tint = Color(0xFF999999),
            modifier = Modifier
                .size(16.dp)
                .layoutId("icon")
        )
        Text(
            text = videoEntity.type,
            fontSize = 14.sp,
            color = Color(0xFF999999),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .layoutId("type")
        )
        Text(
            text = "时长:${videoEntity.duration}",
            fontSize = 14.sp,
            color = Color(0xFF999999),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .layoutId("duration")
        )
        Divider(
            modifier = Modifier
                .layoutId("divider")
                .padding(top = 4.dp)

        )
    }
}