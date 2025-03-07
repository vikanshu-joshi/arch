@file:OptIn(ExperimentalSharedTransitionApi::class)

package com.example.practice

/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionLayout
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.SharedTransitionScope.PlaceHolderSize.Companion.animatedSize
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.rounded.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay

@Composable
fun SharedTransitionScope.Listing(
    modifier: Modifier = Modifier
) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    val alpha: Float by animateFloatAsState(
        targetValue = if (isExpanded) 0.5f else 0f,
        animationSpec = tween(durationMillis = 600) // Customize duration here
    )

    Box(modifier = modifier) {
        LazyColumn(
            modifier = Modifier.padding(start = 16.dp, end = 16.dp, top = 140.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            items(20) {
                Image(painter = painterResource(R.drawable.ic_card), contentDescription = null)
            }
        }
        Column(
            modifier = Modifier.background(Color.White)
        ) {
            AnimatedVisibility(
                visible = !isExpanded,
                enter = EnterTransition.None,
                exit = ExitTransition.None
            ) {
                Box(modifier = Modifier
                    .sharedBounds(
                        rememberSharedContentState(key = "box"),
                        this@AnimatedVisibility,
                        enter = EnterTransition.None,
                    )
                    .background(Color.White)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .sharedBounds(
                                rememberSharedContentState(key = "container3"),
                                this@AnimatedVisibility,
                                enter = EnterTransition.None,
                            )
                            .height(60.dp)
                            .background(
                                Color.LightGray.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
                            .alpha(0f),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.DateRange, contentDescription = null)
                        Spacer(modifier = Modifier.width(16.dp))
                        Text(
                            "22 Feb, 2025",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Start
                        )
                        Text(
                            "5 Nights",
                            fontWeight = FontWeight.SemiBold,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.Center
                        )
                        Text(
                            "27 Feb, 2025",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.weight(1f),
                            textAlign = TextAlign.End
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                    }
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .sharedBounds(
                                rememberSharedContentState(key = "container2"),
                                this@AnimatedVisibility,
                                enter = EnterTransition.None,
                            )
                            .height(60.dp)
                            .background(
                                Color.LightGray.copy(alpha = 0.5f),
                                shape = RoundedCornerShape(8.dp)
                            )
                            .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Spacer(modifier = Modifier.width(8.dp))
                        Icon(Icons.Default.DateRange, contentDescription = null)
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("1 Room, 2 Adults & 0 Children", fontWeight = FontWeight.Bold)
                        Spacer(modifier = Modifier.width(8.dp))
                    }
                    Row(
                        modifier = Modifier
                            .sharedBounds(
                                rememberSharedContentState(key = "container"),
                                this@AnimatedVisibility,
                                enter = EnterTransition.None,
                            )
                            .background(
                                Color.White
                            )
                            .padding(12.dp)
                            .clickable {
                                isExpanded = true
                            },
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Row(
                            modifier = Modifier
                                .weight(1f)
                                .background(
                                    Color.LightGray.copy(alpha = 0.5f),
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Spacer(modifier = Modifier.width(8.dp))
                            Icon(Icons.AutoMirrored.Rounded.ArrowBack, contentDescription = null)
                            Column(
                                modifier = Modifier
                                    .weight(1f)
                                    .padding(horizontal = 8.dp, vertical = 4.dp)
                            ) {
                                Text("Goa", fontWeight = FontWeight.Bold)
                                Text(
                                    "22 Feb - 27 Feb, 1 room, 2 Adults",
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis
                                )
                            }
                            Icon(
                                Icons.Rounded.Edit,
                                contentDescription = null,
                                tint = Color.Blue.copy(0.7f)
                            )
                            Spacer(modifier = Modifier.width(8.dp))
                        }
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null,
                                tint = Color.Blue.copy(0.7f)
                            )
                            Text("Search", color = Color.Blue.copy(0.7f), fontWeight = FontWeight.Bold)
                        }
                    }
                }
            }
            LazyRow(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                item {
                    Box(Modifier)
                }
                repeat(10) {
                    item {
                        Row(
                            modifier = Modifier
                                .background(
                                    Color.White,
                                    shape = RoundedCornerShape(8.dp)
                                )
                                .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp))
                                .padding(vertical = 4.dp, horizontal = 8.dp),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(4.dp)
                        ) {
                            Text("Filter")
                            Icon(Icons.Default.KeyboardArrowDown, contentDescription = null)
                        }
                    }
                }
                item {
                    Box(Modifier)
                }
            }
        }
        Box(modifier = Modifier.fillMaxSize().background(Color.Black.copy(alpha = alpha)))
        AnimatedVisibility(
            visible = isExpanded,
            enter = EnterTransition.None,
            exit = ExitTransition.None
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .sharedBounds(
                        rememberSharedContentState(key = "box"),
                        this@AnimatedVisibility,
                        enter = EnterTransition.None,
                    )
                    .background(Color.White)
                    .padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        Icons.Default.Clear,
                        contentDescription = null,
                        modifier = Modifier.clickable {
                            isExpanded = false
                        })
                    Spacer(modifier = Modifier.width(12.dp))
//                    Text("Hotels & Homestays", fontWeight = FontWeight.Bold)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .sharedBounds(
                            rememberSharedContentState(key = "container"),
                            this@AnimatedVisibility,
                            enter = EnterTransition.None,
                        )
                        .height(60.dp)
                        .background(
                            Color.LightGray.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.Search, contentDescription = null)
                    Column(
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 8.dp, vertical = 4.dp)
                    ) {
                        Text("Goa", fontWeight = FontWeight.Bold)
                        Text(
                            "India",
                            maxLines = 1,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                    Spacer(modifier = Modifier.width(8.dp))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .sharedBounds(
                            rememberSharedContentState(key = "container3"),
                            this@AnimatedVisibility,
                            enter = EnterTransition.None,
                        )
                        .height(60.dp)
                        .background(
                            Color.LightGray.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.DateRange, contentDescription = null)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        "22 Feb, 2025",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Start
                    )
                    Text(
                        "5 Nights",
                        fontWeight = FontWeight.SemiBold,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.Center
                    )
                    Text(
                        "27 Feb, 2025",
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f),
                        textAlign = TextAlign.End
                    )
                    Spacer(modifier = Modifier.width(12.dp))
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .sharedBounds(
                            rememberSharedContentState(key = "container2"),
                            this@AnimatedVisibility,
                            enter = EnterTransition.None,
                        )
                        .height(60.dp)
                        .background(
                            Color.LightGray.copy(alpha = 0.5f),
                            shape = RoundedCornerShape(8.dp)
                        )
                        .border(1.dp, Color.LightGray, shape = RoundedCornerShape(8.dp)),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(Icons.Default.DateRange, contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text("1 Room, 2 Adults & 0 Children", fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.width(8.dp))
                }
            }
        }
    }
}

@Preview
@Composable
fun ContainerTransformDemo(model: MyModel = remember { MyModel().apply { selected = items[1] } }) {
    SharedTransitionLayout {
        LaunchedEffect(key1 = Unit) {
            while (true) {
                delay(2500)
                if (model.selected == null) {
                    model.selected = model.items[1]
                } else {
                    model.selected = null
                }
            }
        }
        AnimatedContent(
            model.selected,
            transitionSpec = {
                fadeIn(tween(600)) togetherWith
                        fadeOut(tween(600)) using SizeTransform { _, _ ->
                    spring()
                }
            },
            label = ""
        ) {
            if (it != null) {
                DetailView(model = model, selected = it, model.items[6])
            } else {
                GridView(model = model)
            }
        }
    }
}

context(SharedTransitionScope)
@Composable
fun Details(kitty: Kitty) {
    Column(
        Modifier
            .padding(start = 10.dp, end = 10.dp, top = 10.dp)
            .fillMaxHeight()
            .wrapContentHeight(Alignment.Top)
            .fillMaxWidth()
            .background(Color.White)
            .padding(start = 10.dp, end = 10.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Column {
                Spacer(Modifier.size(20.dp))
                Text(
                    kitty.name,
                    fontSize = 25.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
                Text(
                    kitty.breed,
                    fontSize = 22.sp,
                    color = Color.Gray,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
                Spacer(Modifier.size(10.dp))
            }
            Spacer(Modifier.weight(1f))
            Icon(
                Icons.Outlined.Favorite,
                contentDescription = null,
                Modifier
                    .background(Color(0xffffddee), CircleShape)
                    .padding(10.dp)
            )
            Spacer(Modifier.size(10.dp))
        }
        Box(
            modifier = Modifier
                .padding(bottom = 10.dp)
                .height(2.dp)
                .fillMaxWidth()
                .background(Color(0xffeeeeee))
        )
        Text(
            text =
            "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Praesent fringilla" +
                    " mollis efficitur. Maecenas sit amet urna eu urna blandit suscipit efficitur" +
                    " eget mauris. Nullam eget aliquet ligula. Nunc id euismod elit. Morbi aliquam" +
                    " enim eros, eget consequat dolor consequat id. Quisque elementum faucibus" +
                    " congue. Curabitur mollis aliquet turpis, ut pellentesque justo eleifend nec.\n" +
                    "\n" +
                    "Suspendisse ac consequat turpis, euismod lacinia quam. Nulla lacinia tellus" +
                    " eu felis tristique ultricies. Vivamus et ultricies dolor. Orci varius" +
                    " natoque penatibus et magnis dis parturient montes, nascetur ridiculus mus." +
                    " Ut gravida porttitor arcu elementum elementum. Phasellus ultrices vel turpis" +
                    " volutpat mollis. Vivamus leo diam, placerat quis leo efficitur, ultrices" +
                    " placerat ex. Nullam mollis et metus ac ultricies. Ut ligula metus, congue" +
                    " gravida metus in, vestibulum posuere velit. Sed et ex nisl. Fusce tempor" +
                    " odio eget sapien pellentesque, sed cursus velit fringilla. Nullam odio" +
                    " ipsum, eleifend non consectetur vitae, congue id libero. Etiam tincidunt" +
                    " mauris at urna dictum ornare.\n" +
                    "\n" +
                    "Etiam at facilisis ex. Sed quis arcu diam. Quisque semper pharetra leo eget" +
                    " fermentum. Nulla dapibus eget mi id porta. Nunc quis sodales nulla, eget" +
                    " commodo sem. Donec lacus enim, pharetra non risus nec, eleifend ultrices" +
                    " augue. Donec sit amet orci porttitor, auctor mauris et, facilisis dolor." +
                    " Nullam mattis luctus orci at pulvinar.\n" +
                    "\n" +
                    "Sed accumsan est massa, ut aliquam nulla dignissim id. Suspendisse in urna" +
                    " condimentum, convallis purus at, molestie nisi. In hac habitasse platea" +
                    " dictumst. Pellentesque id justo quam. Cras iaculis tellus libero, eu" +
                    " feugiat ex pharetra eget. Nunc ultrices, magna ut gravida egestas, mauris" +
                    " justo blandit sapien, eget congue nisi felis congue diam. Mauris at felis" +
                    " vitae erat porta auctor. Pellentesque iaculis sem metus. Phasellus quam" +
                    " neque, congue at est eget, sodales interdum justo. Aenean a pharetra dui." +
                    " Morbi odio nibh, hendrerit vulputate odio eget, sollicitudin egestas ex." +
                    " Fusce nisl ex, fermentum a ultrices id, rhoncus vitae urna. Aliquam quis" +
                    " lobortis turpis.\n" +
                    "\n",
            modifier = Modifier.skipToLookaheadSize(),
            color = Color.Gray,
            fontSize = 15.sp,
        )
    }
}

context(AnimatedVisibilityScope, SharedTransitionScope)
@Suppress("UNUSED_PARAMETER")
@Composable
fun DetailView(
    model: MyModel,
    selected: Kitty,
    next: Kitty?
) {
    Column(
        Modifier
            .sharedBounds(
                rememberSharedContentState(key = "container + ${selected.id}"),
                this@AnimatedVisibilityScope,
                clipInOverlayDuringTransition = OverlayClip(RoundedCornerShape(20.dp))
            )
    ) {
        Row(Modifier.fillMaxHeight(0.5f)) {
            Image(
                painter = painterResource(selected.photoResId),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .padding(10.dp)
                    .sharedElement(
                        rememberSharedContentState(key = selected.id),
                        this@AnimatedVisibilityScope,
                        placeHolderSize = animatedSize
                    )
                    .fillMaxHeight()
                    .aspectRatio(1f)
                    .clip(RoundedCornerShape(20.dp))
            )
            if (next != null) {
                Image(
                    painter = painterResource(next.photoResId),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .padding(top = 10.dp, bottom = 10.dp, end = 10.dp)
                        .fillMaxWidth()
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(20.dp))
                        .blur(10.dp)
                )
            }
        }
        Details(kitty = selected)
    }
}

context(AnimatedVisibilityScope, SharedTransitionScope)
@OptIn(ExperimentalAnimationApi::class, ExperimentalMaterial3Api::class)
@Composable
fun GridView(model: MyModel) {
    Column(Modifier.background(lessVibrantPurple)) {
        Box(
            Modifier
                .padding(20.dp)
                .renderInSharedTransitionScopeOverlay(zIndexInOverlay = 2f)
                .animateEnterExit(fadeIn(), fadeOut())
        ) {
            SearchBar(
                modifier = Modifier.height(60.dp),
                query = "Search",
                onSearch = {

                },
                onQueryChange = {

                },
                active = true,
                onActiveChange = {

                },
                content = {

                }
            )
        }
        LazyVerticalGrid(columns = GridCells.Fixed(2)) {
            items(6) {
                KittyItem(model.items[it])
            }
        }
    }
}

class MyModel {
    val items = mutableListOf(
        Kitty("Waffle", R.drawable.ic_launcher_background, "American Short Hair", 0),
        Kitty("油条", R.drawable.ic_launcher_background, "Tabby", 1),
        Kitty("Cowboy", R.drawable.ic_launcher_background, "American Short Hair", 2),
        Kitty("Pepper", R.drawable.ic_launcher_background, "Tabby", 3),
        Kitty("Unknown", R.drawable.ic_launcher_background, "Unknown", 4),
        Kitty("Unknown", R.drawable.ic_launcher_background, "Unknown", 5),
        Kitty("YT", R.drawable.ic_launcher_background, "Tabby", 6),
    )
    var selected: Kitty? by mutableStateOf(null)
}

context(AnimatedVisibilityScope, SharedTransitionScope)
@Composable
fun KittyItem(kitty: Kitty) {
    Column(
        Modifier
            .padding(start = 10.dp, end = 10.dp, bottom = 10.dp)
            .sharedBounds(
                rememberSharedContentState(key = "container + ${kitty.id}"),
                this@AnimatedVisibilityScope
            )
            .background(Color.White, RoundedCornerShape(20.dp))
    ) {
        Image(
            painter = painterResource(kitty.photoResId),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .sharedElement(
                    rememberSharedContentState(key = kitty.id),
                    this@AnimatedVisibilityScope,
                    placeHolderSize = animatedSize
                )
                .aspectRatio(1f)
                .clip(RoundedCornerShape(20.dp))
                .background(Color(0xffaaaaaa))
        )
        Spacer(Modifier.size(10.dp))
        Text(
            kitty.name,
            fontSize = 18.sp,
            modifier = Modifier.padding(start = 10.dp)
        )
        Spacer(Modifier.size(5.dp))
        Text(
            kitty.breed,
            fontSize = 15.sp,
            color = Color.Gray,
            modifier = Modifier
                .padding(start = 10.dp)
        )
        Spacer(Modifier.size(10.dp))
    }
}

data class Kitty(val name: String, val photoResId: Int, val breed: String, val id: Int) {
    override fun equals(other: Any?): Boolean {
        return other is Kitty && other.id == id
    }
}

private val lessVibrantPurple = Color(0xfff3edf7)