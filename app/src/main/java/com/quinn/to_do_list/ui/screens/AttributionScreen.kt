package com.quinn.to_do_list.ui.screens


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.quinn.to_do_list.R
import com.quinn.to_do_list.ui.components.AttributionText
import com.quinn.to_do_list.ui.components.Card
import com.quinn.to_do_list.ui.components.GenericButton
import com.quinn.to_do_list.ui.components.NavBar


@Composable
fun AttributionScreen(
    navController: NavController
) {
    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .background(Color(0xFFFFFAFA))
            .fillMaxSize(),
        topBar = {
            NavBar(
                background = Color(0xFFFFFAFA),
                arrangement = Arrangement.Start,
                elements = listOf(
                    {
                        GenericButton(
                            content = {
                                Image(
                                    painter = painterResource(R.drawable.back),
                                    contentDescription = "back icon",
                                    modifier = Modifier.size(20.dp)
                                )
                            },
                            clickFunction = { navController.popBackStack() }
                        )
                    }
                )
            )
        }
    ) { innerPadding ->
        AttributionBody(Modifier.padding(innerPadding))
    }
}

@Composable
fun AttributionBody(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .background(Color(0xFFFFFAFA))
            .padding(20.dp)
            .fillMaxSize()
    ) {
        Header()
        Card(
            element = listOf(
                { Image(
                    painter = painterResource(R.drawable.checklist),
                    contentDescription = stringResource(R.string.checklist_icon_desc),
                    modifier = Modifier.size(30.dp)
                ) },
                { Image(
                    painter = painterResource(R.drawable.back),
                    contentDescription = stringResource(R.string.checklist_icon_desc),
                    modifier = Modifier.size(30.dp)
                ) },
                {
                    AttributionText(
                        author = stringResource(R.string.freepik_author),
                        authorLink = stringResource(R.string.freepik_author_link),
                        source = stringResource(R.string.flaticon_source),
                        sourceLink = stringResource(R.string.flaticon_source_link)
                    )
                }
            )
        )
        Card(
            element = listOf(
                { Image(
                    painter = painterResource(R.drawable.delete_icon),
                    contentDescription = stringResource(R.string.delete_icon_txt_desc),
                    modifier = Modifier.size(30.dp)
                ) },
                {
                    AttributionText(
                        author = stringResource(R.string.ilham_fitrotul_hayat),
                        authorLink = stringResource(R.string.ilham_fitrotul_hayat_author_link),
                        source = stringResource(R.string.flaticon_source),
                        sourceLink = stringResource(R.string.flaticon_source_link)
                    )
                }
            )
        )
        Card(
            element = listOf(
                { Image(
                    painter = painterResource(R.drawable.info_icon),
                    contentDescription = stringResource(R.string.info_icon_desc),
                    modifier = Modifier.size(30.dp)
                ) },
                {
                    AttributionText(
                        author = stringResource(R.string.graphics_plazza_author),
                        authorLink = stringResource(R.string.graphics_plazza_author_link),
                        source = stringResource(R.string.flaticon_source),
                        sourceLink = stringResource(R.string.flaticon_source_link)
                    )
                }
            )
        )
    }
}

@Composable
fun Header(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.padding(vertical = 20.dp)
    ) {
        Text(
            text = stringResource(R.string.attribution_header_txt),
            style = MaterialTheme.typography.titleLarge,
            textAlign = TextAlign.Start,
            color = Color.Black
        )
        Text(
            text = stringResource(R.string.this_app_uses_free_icons_header_txt2),
            style = MaterialTheme.typography.titleMedium,
            color = Color.Black,
            modifier = Modifier.padding(vertical = 20.dp)
        )
    }
}