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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

    var selected by remember{ mutableStateOf(false) }

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .padding(15.dp),
        topBar = {
            Column(
                modifier = modifier.fillMaxWidth()
            ) {
                AppNameBar()
                AddTaskBar()
            }
        }
    ) { innerPadding ->
        HomeScreenBody(
            onSelect = { select ->
                selected = select
            },
            selected = selected,
            Modifier.padding(innerPadding))
    }
}

@Composable
fun HomeScreenBody(
    onSelect: (Boolean) -> Unit,
    selected: Boolean,
    modifier: Modifier = Modifier) {
    Column (
        modifier = modifier
            .background(Color(0xFFFFFAFA))
            .fillMaxSize()
    ) {
        TaskSection(
            onSelect = onSelect,
            selected = selected
        )
    }
}

@Composable
fun AppNameBar(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(vertical = 15.dp)
    ) {
        Text(
            text = "To - Do ",
            style = MaterialTheme.typography.displaySmall
        )
        Text(
            text = "List",
            style = MaterialTheme.typography.displaySmall,
            color = Color(0xFF845EC2)
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
        modifier = Modifier.padding(vertical = 15.dp)
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
                        color = Color.Black,
                        style = MaterialTheme.typography.labelLarge
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
fun TaskSection(
    onSelect: (Boolean) -> Unit,
    selected: Boolean,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TaskTab(
            onSelect = onSelect,
            selected = selected
        )
        TaskTab(
            onSelect = onSelect,
            selected = selected
        )

        TaskTab(
            onSelect = onSelect,
            selected = selected
        )

        TaskTab(
            onSelect = onSelect,
            selected = selected
        )

    }
}

@Composable
fun TaskTab(
    selected: Boolean,
    onSelect: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .padding(vertical = 10.dp)
            .background(Color(0xFF845EC2))
            .fillMaxWidth()
    ) {
        RadioButton(
            modifier = Modifier.weight(1.5f),
            selected = selected,
            onClick = { onSelect(!selected) },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Green,
                unselectedColor = Color.White
            )
        )
        Text(
            modifier = Modifier.weight(7f),
            text = "Sample Task Lorem ",
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
        Button(
            modifier = Modifier.weight(1.5f),
            onClick = {},
            colors = buttonColors(
                contentColor = Color.Transparent,
                containerColor = Color.Transparent
            ),
            contentPadding = PaddingValues(0.dp)
        ) {
            Icon(
                painter = painterResource(R.drawable.close_icon),
                contentDescription = stringResource(R.string.close_icon_desc),
                modifier = Modifier.size(15.dp),
                tint = Color.White
            )
        }
    }
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