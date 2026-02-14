package com.quinn.to_do_list.ui.screens

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
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
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.quinn.to_do_list.MyApp
import com.quinn.to_do_list.R
import com.quinn.to_do_list.data.local.entity.Tasks
import com.quinn.to_do_list.ui.components.GenericButton
import com.quinn.to_do_list.ui.components.NavBar
import com.quinn.to_do_list.viewmodel.HomeViewModel
import com.quinn.to_do_list.viewmodel.HomeViewModelFactory
import kotlinx.coroutines.delay

@Composable
fun HomeScreen(
    navController: NavController
) {
    val application = LocalContext.current.applicationContext as MyApp

    val homeViewModel: HomeViewModel = viewModel(
        factory = HomeViewModelFactory(application.repository)
    )
    val tasks by homeViewModel.tasks.collectAsState(initial = emptyList())

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
                NavBar(
                    arrangement = Arrangement.SpaceBetween,
                    elements = listOf(
                        {AppNameBar()},
                        {
                            GenericButton(
                                clickFunction = { navController.navigate("attribution") },
                                content = {
                                    Image(
                                        painter = painterResource(R.drawable.info_icon),
                                        contentDescription = stringResource(R.string.info_icon_desc),
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            )
                        }
                    )
                )
                AddTaskBar(
                    homeViewModel = homeViewModel,
                )
            }
        }
    ) { innerPadding ->
        HomeScreenBody(
            homeViewModel = homeViewModel,
            taskList = tasks,
            Modifier.padding(innerPadding))
    }
}

@Composable
fun HomeScreenBody(
    homeViewModel: HomeViewModel,
    taskList: List<Tasks>,
    modifier: Modifier = Modifier
) {
    Column (
        modifier = modifier
            .background(Color(0xFFFFFAFA))
            .padding(horizontal = 15.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ) {
        TaskSection(
            homeViewModel = homeViewModel,
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
                    homeViewModel.addTask(taskName)
                    taskName = ""
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
    taskList: List<Tasks>,
) {
    Column(
        modifier = Modifier
            .padding(vertical = 30.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        if (taskList.isNotEmpty()) {
            Row (
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End,
                modifier = Modifier.fillMaxWidth()
            ) {
                Button (
                    onClick = {
                        homeViewModel.removeAllTask()
                    },
                    colors = buttonColors(
                        containerColor = Color(0xFFED4845),
                        contentColor = Color(0xFFED4845),
                    )
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center

                    ) {
                        Text(
                            text = stringResource(R.string.clear_text_btn_label),
                            color = Color.White,
                            style = MaterialTheme.typography.labelMedium
                        )
                        Spacer(Modifier.width(5.dp))
                        Icon (
                            painter = painterResource(R.drawable.delete_icon),
                            tint = Color.White,
                            contentDescription = stringResource(R.string.delete_icon_txt_desc),
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
            taskList.forEachIndexed { index, task ->
                TaskTab(
                    delay = (index * 50L),
                    homeViewModel = homeViewModel,
                    task = task,
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
                    contentDescription = stringResource(R.string.confetti_icon_desc),
                    modifier = Modifier.size(20.dp)
                )
            }
        }
    }
}

@Composable
fun TaskTab(
    delay: Long,
    homeViewModel: HomeViewModel,
    task: Tasks,
) {
    var isDone by remember { mutableStateOf(task.done)}
    var fadeIn by remember {mutableStateOf(false)}
    val offsetX by animateDpAsState(
        targetValue = if (fadeIn) 0.dp else -(500.dp),
        animationSpec = tween(durationMillis = 400),
        label = "fade in"
    )

    LaunchedEffect(Unit) {
        delay(delay)
        fadeIn = true
    }

    Row (
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceAround,
        modifier = Modifier
            .offset {
                IntOffset(offsetX.roundToPx(), 0)
            }
            .padding(vertical = 10.dp)
            .background(Color(0xFF845EC2))
            .fillMaxWidth()
    ) {
        RadioButton(
            modifier = Modifier.weight(1.5f),
            selected = isDone,
            onClick = {
                homeViewModel.updateTask(task)

            },
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
                homeViewModel.removeTask(task)
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
