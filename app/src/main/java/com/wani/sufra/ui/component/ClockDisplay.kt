import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wani.sufra.ui.theme.Red100
import kotlinx.coroutines.delay
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ClockDisplay() {
    // Initial timestamp (can be any Unix time in milliseconds)
    val unixTime = remember { System.currentTimeMillis() }

    // Mutable state for current time, updated every second
    var currentTime by remember { mutableStateOf(Date(unixTime)) }

    LaunchedEffect(Unit) {
        while (true) {
            currentTime = Date(System.currentTimeMillis())
            delay(1000L) // update every second
        }
    }

    val hourMinuteTimeFormat = SimpleDateFormat("hh:mm:", Locale.getDefault())
    val secondTimeFormat = SimpleDateFormat("ss", Locale.getDefault())
    val amPmFormat = SimpleDateFormat(" a", Locale.getDefault())
    val dateFormat = SimpleDateFormat("d MMM, yyyy", Locale.getDefault())

    Column(horizontalAlignment = Alignment.Start) {
        Row {
            Text(
                text = hourMinuteTimeFormat.format(currentTime),
//            fontSize = 48.sp, // big time,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodyLarge
            )
            Text(
                text = secondTimeFormat.format(currentTime),
//            fontSize = 48.sp, // big time,
                color = Red100,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodyLarge)
            Text(
                text = amPmFormat.format(currentTime).lowercase(),
//            fontSize = 48.sp, // big time,
                textAlign = TextAlign.Left,
                style = MaterialTheme.typography.bodyLarge)
        }

        Text(
            text = dateFormat.format(currentTime),
//            fontSize = 20.sp, // smaller date
            textAlign = TextAlign.Left,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}


@Composable
fun DateHeader() {
    Text("09:43:33 am")
    Text("4 Sept, 2025")
}
