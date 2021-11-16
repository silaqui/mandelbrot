import java.awt.image.BufferedImage
import java.io.File
import javax.imageio.ImageIO

class MandelbrotGenerator {

    fun generate(
        width : Int,
        height : Int,
        xRange : Double = 4.0,
        xCenter : Double = 0.0,
        yCenter : Double = 0.0,

    ): BufferedImage {

//    val xRange = 0.0010
        val yRange = xRange * height.toDouble() / width
//    val xCenter = 0.36
//    val yCenter = 0.51

        val image = BufferedImage(width, height, BufferedImage.TYPE_INT_RGB)

        for (col in 0 until width)
            for (row in 0 until height)
                image.setRGB(
                    col,
                    row,
                    getColor(
                        getComplexPlainCoords(col, width, xCenter, xRange),
                        getComplexPlainCoords(row, height, yCenter, yRange)
                    )
                )

        return image
//        ImageIO.write(image, "png", File("mandelbrot.png"))
    }

    private fun getComplexPlainCoords(position: Int, viewSize: Int, center: Double, range: Double): Double {
        val start = center - range / 2
        return start + position.toDouble() / viewSize * range
    }

    private fun getColor(c_re: Double, c_im: Double): Int {
//    println("$c_re + $c_im i")
        val black = 0x000000
        val white = 0xFFFFFF
        val max = 1000
        var iteration = 0

        var re = 0.0
        var im = 0.0

        while (iteration < max && re * re + im * im < 4) {
            val nextRe: Double = re * re - im * im + c_re
            im = 2 * re * im + c_im
            re = nextRe
            iteration++
        }
        return if (iteration == max) black else white * iteration / max
    }
}
