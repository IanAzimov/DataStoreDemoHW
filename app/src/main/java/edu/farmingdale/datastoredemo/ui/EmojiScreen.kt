package edu.farmingdale.datastoredemo.ui

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Checkbox
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import edu.farmingdale.datastoredemo.R

import edu.farmingdale.datastoredemo.data.local.LocalEmojiData


/*
 * Screen level composable
 */
@Composable
fun EmojiReleaseApp(
    emojiViewModel: EmojiScreenViewModel = viewModel(
        factory = EmojiScreenViewModel.Factory
    )
) {
    EmojiScreen(
        uiState = emojiViewModel.uiState.collectAsState().value,
        selectLayout = emojiViewModel::selectLayout,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun EmojiScreen(
    uiState: EmojiReleaseUiState,
    selectLayout: (Boolean) -> Unit
) {
    val isLinearLayout = uiState.isLinearLayout
    //val isDarkTheme = uiState.
    var chkd by remember { mutableStateOf(true) }


    Scaffold(
    topBar = {
            TopAppBar(
                title = { Text(stringResource(R.string.top_bar_name)) },
                actions = {
                    Checkbox(checked = chkd, onCheckedChange = { chkd=it }, modifier = Modifier.padding(10.dp))
                    IconButton(
                        onClick = {
                            selectLayout(!isLinearLayout)
                        }
                    ) {
                        Icon(
                            painter = painterResource(uiState.toggleIcon),
                            contentDescription = stringResource(uiState.toggleContentDescription),
                            tint = MaterialTheme.colorScheme.onBackground
                        )
                    }

                },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.inversePrimary
                )
            )
        }
    ) { innerPadding ->
        val modifier = Modifier
            .padding(
                top = dimensionResource(R.dimen.padding_medium),
                start = dimensionResource(R.dimen.padding_medium),
                end = dimensionResource(R.dimen.padding_medium),
            )
        if (isLinearLayout) {
            EmojiReleaseLinearLayout(
                modifier = modifier.fillMaxWidth(),
                contentPadding = innerPadding
            )
        } else {
            EmojiReleaseGridLayout(
                modifier = modifier,
                contentPadding = innerPadding,
            )
        }
    }
}

@Composable
fun EmojiReleaseLinearLayout(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val cntxt = LocalContext.current
    LazyColumn(
        modifier = modifier,
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_small)),
    ) {
        items(
            items = LocalEmojiData.EmojiList,
            key = { e -> e }
        ) { e ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),

                shape = MaterialTheme.shapes.medium,
                modifier = Modifier.height(110.dp).clickable{
                    val emojiDescription = when(e) {
                        "😀" -> "Grinning face"
                        "😃" -> "Big smile"
                        "😄" -> "Beaming happiness"
                        "😁" -> "Proud smile"
                        "😆" -> "Laughing joyfully"
                        "😅" -> "Relieved sweat"
                        "😂" -> "Laughing with tears"
                        "🤣" -> "Rolling on floor laughing"
                        "😊" -> "Warm smile"
                        "😇" -> "Innocent smile"
                        "🙂" -> "Slight smile"
                        "🙃" -> "Upside-down smile"
                        "😉" -> "Winking flirtation"
                        "😌" -> "Calm relief"
                        "😍" -> "Heart-eyed love"
                        "🥰" -> "Heart-filled affection"
                        "😘" -> "Blowing kiss"
                        "😗" -> "Kissing face"
                        "😙" -> "Smiling kiss"
                        "😚" -> "Closed-eye kiss"
                        "😋" -> "Savoring food"
                        "😛" -> "Playful tongue out"
                        "😝" -> "Mischievous tongue out"
                        "😜" -> "Winking tongue out"
                        "🤪" -> "Silly face"
                        "🤨" -> "Scrutinizing look"
                        "🧐" -> "Skeptical expression"
                        "🤓" -> "Nerdy smile"
                        "😎" -> "Cool sunglasses"
                        "🤩" -> "Star-struck amazement"
                        "🥳" -> "Party celebration"
                        "😏" -> "Sly smirk"
                        "😒" -> "Unamused expression"
                        "😞" -> "Disappointed look"
                        "😔" -> "Pensive thought"
                        "😟" -> "Worried face"
                        "😕" -> "Confused expression"
                        "🙁" -> "Slight frown"
                        "☹️" -> "Sad face"
                        "😣" -> "Strained frustration"
                        "😖" -> "Strong frustration"
                        "😫" -> "Exhausted face"
                        "😩" -> "Overwhelmed emotion"
                        "🥺" -> "Pleading eyes"
                        "😢" -> "Crying face"
                        "😭" -> "Loudly crying"
                        "😤" -> "Frustrated steam"
                        "😠" -> "Angry face"
                        "😡" -> "Fuming rage"
                        "🤬" -> "Cursing face"
                        "😈" -> "Devilish grin"
                        "👿" -> "Evil smirk"
                        "💀" -> "Skull symbol"
                        "☠️" -> "Dangerous symbol"
                        "💩" -> "Smiling poop"
                        "🤡" -> "Clown face"
                        "👹" -> "Ogre face"
                        "👺" -> "Goblin face"
                        "👻" -> "Ghost"
                        "👽" -> "Alien"
                        "👾" -> "Alien monster"
                        "🤖" -> "Robot face"
                        "😺" -> "Grinning cat"
                        "😸" -> "Happy cat"
                        "😹" -> "Laughing cat"
                        "😻" -> "Heart-eyed cat"
                        "😼" -> "Sly cat"
                        "😽" -> "Kissing cat"
                        "🙀" -> "Shocked cat"
                        "😿" -> "Crying cat"
                        "😾" -> "Annoyed cat"
                        "🙈" -> "See-no-evil monkey"
                        "🙉" -> "Hear-no-evil monkey"
                        "🙊" -> "Speak-no-evil monkey"
                        "💋" -> "Kiss mark"
                        "💌" -> "Love letter"
                        "💘" -> "Heart with arrow"
                        "💝" -> "Gifted heart"
                        "💖" -> "Sparkling heart"
                        "💗" -> "Growing heart"
                        "💓" -> "Beating heart"
                        "💞" -> "Revolving hearts"
                        "💕" -> "Two hearts"
                        "💟" -> "Heart with spiral"
                        "❣️" -> "Heart exclamation"
                        "👍" -> "Thumbs up"
                        "👎" -> "Thumbs down"
                        "✊" -> "Raised fist"
                        "👊" -> "Fist bump"
                        "✌️" -> "Peace Symbol"
                        else -> {"None"}
                    }
                    Toast.makeText(cntxt, emojiDescription, Toast.LENGTH_SHORT).show();
                }
            ) {
                    Text(
                        text = e, fontSize = 50.sp,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(dimensionResource(R.dimen.padding_medium)),
                        textAlign = TextAlign.Center
                    )


            }
        }
    }
}

@Composable
fun EmojiReleaseGridLayout(
    modifier: Modifier = Modifier,
    contentPadding: PaddingValues = PaddingValues(0.dp)
) {
    val cntxt = LocalContext.current
    LazyVerticalGrid(
        modifier = modifier,
        columns = GridCells.Fixed(3),
        contentPadding = contentPadding,
        verticalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium)),
        horizontalArrangement = Arrangement.spacedBy(dimensionResource(R.dimen.padding_medium))
    ) {
        items(
            items = LocalEmojiData.EmojiList,
            key = { e -> e }
        ) { e ->
            Card(
                colors = CardDefaults.cardColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                //code below makes clickable
                modifier = Modifier.height(110.dp).clickable {
                    //below are the descriptions for each emoji
                    val emojiDescription = when(e) {
                        "😀" -> "Grinning face"
                        "😃" -> "Big smile"
                        "😄" -> "Beaming happiness"
                        "😁" -> "Proud smile"
                        "😆" -> "Laughing joyfully"
                        "😅" -> "Relieved sweat"
                        "😂" -> "Laughing with tears"
                        "🤣" -> "Rolling on floor laughing"
                        "😊" -> "Warm smile"
                        "😇" -> "Innocent smile"
                        "🙂" -> "Slight smile"
                        "🙃" -> "Upside-down smile"
                        "😉" -> "Winking flirtation"
                        "😌" -> "Calm relief"
                        "😍" -> "Heart-eyed love"
                        "🥰" -> "Heart-filled affection"
                        "😘" -> "Blowing kiss"
                        "😗" -> "Kissing face"
                        "😙" -> "Smiling kiss"
                        "😚" -> "Closed-eye kiss"
                        "😋" -> "Savoring food"
                        "😛" -> "Playful tongue out"
                        "😝" -> "Winking tongue out"
                        "😜" -> "Mischievous tongue out"
                        "🤪" -> "Silly face"
                        "🤨" -> "Skeptical expression"
                        "🧐" -> "Scrutinizing look"
                        "🤓" -> "Nerdy smile"
                        "😎" -> "Cool sunglasses"
                        "🤩" -> "Star-struck amazement"
                        "🥳" -> "Party celebration"
                        "😏" -> "Sly smirk"
                        "😒" -> "Unamused expression"
                        "😞" -> "Disappointed look"
                        "😔" -> "Pensive thought"
                        "😟" -> "Worried face"
                        "😕" -> "Confused expression"
                        "🙁" -> "Slight frown"
                        "☹️" -> "Sad face"
                        "😣" -> "Strained frustration"
                        "😖" -> "Strong frustration"
                        "😫" -> "Exhausted face"
                        "😩" -> "Overwhelmed emotion"
                        "🥺" -> "Pleading eyes"
                        "😢" -> "Crying face"
                        "😭" -> "Loudly crying"
                        "😤" -> "Frustrated steam"
                        "😠" -> "Angry face"
                        "😡" -> "Fuming rage"
                        "🤬" -> "Cursing face"
                        "😈" -> "Devilish grin"
                        "👿" -> "Evil smirk"
                        "💀" -> "Skull symbol"
                        "☠️" -> "Dangerous symbol"
                        "💩" -> "Smiling poop"
                        "🤡" -> "Clown face"
                        "👹" -> "Ogre face"
                        "👺" -> "Goblin face"
                        "👻" -> "Friendly ghost"
                        "👽" -> "Kiss mark"
                        "👾" -> "Alien monster"
                        "🤖" -> "Robot face"
                        "😺" -> "Grinning cat"
                        "😸" -> "Happy cat"
                        "😹" -> "Laughing cat"
                        "😻" -> "Heart-eyed cat"
                        "😼" -> "Sly cat"
                        "😽" -> "Kissing cat"
                        "🙀" -> "Shocked cat"
                        "😿" -> "Crying cat"
                        "😾" -> "Annoyed cat"
                        "🙈" -> "See-no-evil monkey"
                        "🙉" -> "Hear-no-evil monkey"
                        "🙊" -> "Speak-no-evil monkey"
                        "💋" -> "Kiss mark"
                        "💌" -> "Love letter"
                        "💘" -> "Heart with arrow"
                        "💝" -> "Gifted heart"
                        "💖" -> "Sparkling heart"
                        "💗" -> "Growing heart"
                        "💓" -> "Beating heart"
                        "💞" -> "Revolving hearts"
                        "💕" -> "Two hearts"
                        "💟" -> "Heart with spiral"
                        "❣️" -> "Heart exclamation"
                        "👍" -> "Thumbs up"
                        "👎" -> "Thumbs down"
                        "✊" -> "Raised fist"
                        "👊" -> "Fist bump"
                        "✌️" -> "Victory hand"
                        else -> {"None"}
                    }
                    Toast.makeText(cntxt, emojiDescription, Toast.LENGTH_SHORT).show();
                },
                shape = MaterialTheme.shapes.medium
            ) {
                Text(
                    text = e, fontSize = 50.sp,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis,
                    modifier = Modifier
                        .fillMaxHeight()
                        .wrapContentHeight(Alignment.CenterVertically)
                        .padding(dimensionResource(R.dimen.padding_small))
                        .align(Alignment.CenterHorizontally),
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}
