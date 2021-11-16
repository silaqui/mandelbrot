import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.toComposeBitmap
import androidx.compose.ui.graphics.withSave
import androidx.compose.ui.unit.dp
import org.jetbrains.skia.paragraph.TextBox

@Composable
@Preview
fun app() {
    val image = MandelbrotGenerator().generate(480, 270)


    var text = ""

    MaterialTheme {
        scrl()
        return@MaterialTheme
        val scrollState = rememberScrollState(0)
//        Row {
            Box(modifier = Modifier.width(200.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {

                    Button({}) {
                        Text("Mandelbrot")
                    }
                    BasicTextField(
                        text,
                        { text = it },
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }
            Box(Modifier
                .size(500.dp, 500.dp)
                .padding(end = 12.dp, bottom = 12.dp)
                .verticalScroll(scrollState)
                .horizontalScroll(scrollState)
            ) {
                Canvas(
                    modifier = Modifier.fillMaxSize()
                ) {
                    drawIntoCanvas { canvas ->
                        canvas.withSave {
                            canvas.drawImage(image.toComposeBitmap(), Offset(0f, 0f), Paint())
                        }
                    }
                }

                VerticalScrollbar(
                    modifier = Modifier.align(Alignment.CenterEnd)
                        .fillMaxHeight(),
                    adapter = rememberScrollbarAdapter(scrollState)
                )
                HorizontalScrollbar(
                    modifier = Modifier.align(Alignment.BottomStart)
                        .fillMaxWidth()
                        .padding(end = 12.dp),
                    adapter = rememberScrollbarAdapter(scrollState)
                )
            }
        }
//    }
}
