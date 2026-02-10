package com.quinn.to_do_list.ui.screen.home

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults.buttonColors
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.quinn.to_do_list.R
import com.quinn.to_do_list.functions.addTaskFile
import com.quinn.to_do_list.data.model.TaskNode
import com.quinn.to_do_list.functions.removeTask


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = viewModel(),
) {
    val context = LocalContext.current
    homeViewModel.refresh(context)

    Scaffold(
        modifier = Modifier
            .statusBarsPadding()
            .background(Color(0xFFFFFAFA))
            .fillMaxSize(),
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color(0xFFFFFAFA))
                    .fillMaxWidth()
            ) {
                AppNameBar()
                AddTaskBar(
                    homeViewModel = homeViewModel,
                    context = context
                )
            }
        }
    ) { innerPadding ->
        HomeScreenBody(
            homeViewModel = homeViewModel,
            context = context,
            taskList = homeViewModel.taskList,
            Modifier.padding(innerPadding))
    }
}

@Composable
fun HomeScreenBody(
    homeViewModel: HomeViewModel,
    context: Context,
    taskList: List<TaskNode>,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .background(Color(0xFFFFFAFA))
            .padding(horizontal = 15.dp)
            .fillMaxSize()
    ) {
        TaskSection(
            homeViewModel = homeViewModel,
            context = context,
            taskList = taskList,
        )
    }
}

@Composable
fun AppNameBar(modifier: Modifier = Modifier) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(15.dp)
    ) {
        Text(
            text = stringResource(R.string.to_do_txt_title),
            style = MaterialTheme.typography.displaySmall,
            color = Color.Black
        )
        Text(
            text = stringResource(R.string.list_txt_title),
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
fun AddTaskBar(
    homeViewModel: HomeViewModel,
    context: Context,
    modifier: Modifier = Modifier
) {

    var taskName by remember { mutableStateOf("") }

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
                value = taskName,
                placeholder = {
                    Text(
                        text = stringResource(R.string.add_your_task_label_textField),
                        color = Color.Black,
                        style = MaterialTheme.typography.labelLarge
                    )
                },
                singleLine = true,
                onValueChange = { taskName = it },
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
                onClick = {
                    addTaskFile(
                        context = context,
                        taskName = taskName
                    )
                    homeViewModel.refresh(context)
                },
                colors = buttonColors(
                    contentColor = Color(0xFF845EC2),
                    containerColor = Color(0xFF845EC2),
                ),
                contentPadding = PaddingValues(25.dp, 20.dp)
            ) {
                Text(
                    text = stringResource(R.string.add_txt_btn),
                    color = Color(0xFFFFD3D3),
                    style = MaterialTheme.typography.labelLarge
                )
            }
        }
    }
}

@Composable
fun TaskSection(
    homeViewModel: HomeViewModel,
    context: Context,
    taskList: List<TaskNode>,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = Modifier
            .padding(vertical = 30.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (taskList.size > 1) {
            taskList.forEach { task ->
                TaskTab(
                    homeViewModel = homeViewModel,
                    task = task,
                    context = context
                    )
            }
        } else {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = stringResource(R.string.you_don_t_have_any_to_do_yet_homesc_txt),
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Black
                )
                Spacer(Modifier.width(15.dp))
                Image(
                    painter = painterResource(R.drawable.confetti_icon),
                    contentDescription = "confetti icon",
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun TaskTab(
    homeViewModel: HomeViewModel,
    context: Context,
    task: TaskNode,
    modifier: Modifier = Modifier
) {
    var selected by remember{ mutableStateOf(false) }

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
            onClick = { selected = !selected },
            colors = RadioButtonDefaults.colors(
                selectedColor = Color.Green,
                unselectedColor = Color.White
            )
        )
        Text(
            modifier = Modifier.weight(7f),
            text = task.taskName,
            style = MaterialTheme.typography.bodyMedium,
            color = Color.White
        )
        Button(
            modifier = Modifier.weight(1.5f),
            onClick = {
                removeTask(
                    context = context,
                    task.id
                )
                homeViewModel.refresh(context)
            },
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