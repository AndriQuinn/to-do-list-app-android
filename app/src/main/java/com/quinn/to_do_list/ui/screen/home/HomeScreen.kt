package com.quinn.to_do_list.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.quinn.to_do_list.R

@Composable
fun HomeScreen(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                AppNameBar()
                AddTaskBar()
            }
        }
    ) { innerPadding ->
        HomeScreenBody(Modifier.padding(innerPadding))
    }
}

@Composable
fun HomeScreenBody(modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .background(Color(0xFFFFFAFA))
            .fillMaxSize()
    ) {
        TaskSection()
    }
}

@Composable
fun AppNameBar(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(15.dp)
    ) {
        Text(
            text = "To - Do List",
            style = MaterialTheme.typography.displaySmall
        )
        Spacer(Modifier.width(20.dp))
        Image(
            painter = painterResource(R.drawable.checklist),
            contentDescription = stringResource(R.string.checklist_icon_desc),
            modifier = Modifier.size(40.dp)
        )
    }
}

@Composable
fun AddTaskBar(modifier: Modifier = Modifier) {
    Surface(
        shape = RoundedCornerShape(100.dp),
        modifier = Modifier.padding(15.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center,
            modifier = Modifier
                .background(Color(0xFFD3D3D3))
                .fillMaxWidth()
        ) {
            TextField(
                modifier = modifier.weight(7f),
                value = "",
                label = {
                    Text(
                        text = stringResource(R.string.add_your_task_label_textField),
                        color = Color.Black
                    )
                },
                singleLine = true,
                onValueChange = {},
                colors = TextFieldDefaults.colors(
                    focusedTextColor = Color.Black,
                    unfocusedTextColor = Color.Black,
                    focusedContainerColor = Color(0xFFD3D3D3),
                    unfocusedContainerColor = Color(0xFFD3D3D3),
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,

                ),
                shape = RoundedCornerShape(40.dp)

            )
            Button(
                modifier = modifier.weight(3f),
                onClick = {},
                colors = buttonColors(
                    contentColor = Color(0xFF845EC2),
                    containerColor = Color(0xFF845EC2),
                ),
                contentPadding = PaddingValues(25.dp, 20.dp)
            ) {
                Text(
                    text = "Add",
                    color = Color(0xFFFFD3D3),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
fun TaskSection(modifier: Modifier = Modifier) {
    Column(

    ) { }
}

@Preview (
    name = "Home Screen",
    showBackground = true,
    showSystemUi = true
)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}