package com.example.petcareapp.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import androidx.compose.ui.platform.LocalContext

@Composable
fun PetAvatarImage(avatarUri: String, modifier: Modifier = Modifier) {
    val context = LocalContext.current

    Image(
        painter = rememberAsyncImagePainter(
            ImageRequest.Builder(context)
                .data(android.net.Uri.parse(avatarUri))
                .build()
        ),
        contentDescription = "Avatar",
        modifier = modifier
    )
}
