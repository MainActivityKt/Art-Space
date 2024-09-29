package com.practice.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.practice.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ArtSpaceTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    ArtSpaceApp(modifier = Modifier)
                }
            }
        }
    }
}

@Composable
fun ArtSpaceApp(modifier: Modifier = Modifier) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
    )
    {
        val listOfArtworks = arrayOf(
            ArtWork(
                R.drawable.stars_in_the_patagonian_lake,
                R.string.stars_description,
                R.string.stars_credit
            ),
            ArtWork(
                R.drawable.versailles__france,
                R.string.versailles_france_description,
                R.string.versailles_france_credit
            ),
            ArtWork(R.drawable.nature, R.string.nature_description, R.string.nature_credit),
            ArtWork(
                R.drawable.flower_image,
                R.string.flowe_image_description,
                R.string.flowe_image_credit
            ),
            ArtWork(
                R.drawable.textures_and_patterns,
                R.string.texture_and_patterns_description,
                R.string.texture_and_patterns_credit
            ),
        )

        var currentIndex by rememberSaveable { mutableIntStateOf(0) }
        val currentArtWork = listOfArtworks[currentIndex]

        Spacer(Modifier.size(15.dp))
        ArtworkWall(drawableId = currentArtWork.drawableId, currentArtWork.descriptionId)
        Column(
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            ArtworkDescription(currentArtWork.descriptionId, currentArtWork.creditId)
            Spacer(Modifier.size(24.dp))
            ArtworkNavigation(
                onPreviousClick = {
                    currentIndex =
                        if (currentIndex > 0) currentIndex - 1 else listOfArtworks.lastIndex
                },
                onNextClick = {
                    currentIndex =
                        if (currentIndex < listOfArtworks.lastIndex) currentIndex + 1 else 0
                }
            )
        }
    }
}

@Composable
fun ArtworkWall(
    @DrawableRes drawableId: Int,
    @StringRes contentDescriptionId: Int,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .shadow(elevation = 16.dp, shape = RectangleShape)
    ) {
        Image(
            painter = painterResource(drawableId),
            contentDescription = stringResource(contentDescriptionId),
            contentScale = ContentScale.FillWidth,
            modifier = modifier
                .padding(24.dp)
        )
    }
}

@Composable
fun ArtworkDescription(
    @StringRes descriptionId: Int,
    @StringRes creditId: Int,
    modifier: Modifier = Modifier
) {
    Column(
        verticalArrangement = Arrangement.SpaceBetween,
        modifier = modifier
            .background(Color.LightGray)
            .clip(RectangleShape)
            .fillMaxWidth()
            .fillMaxHeight(0.4f)
            .padding(24.dp)

    ) {
        Text(
            text = stringResource(descriptionId),
            fontWeight = FontWeight.W300,
            fontSize = 24.sp,
        )
        Text(
            text = stringResource(creditId),
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,

        )
    }
}

@Composable
fun ArtworkNavigation(
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.fillMaxWidth()) {
        Button(
            onPreviousClick,
            modifier = Modifier.weight(0.4f)
        ) {
            Text("Previous")
        }

        Spacer(modifier = Modifier.weight(0.1f))

        Button(
            onNextClick,
            modifier = Modifier.weight(0.4F)
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}