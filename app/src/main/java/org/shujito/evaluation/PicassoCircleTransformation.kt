package org.shujito.evaluation

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PorterDuff
import android.graphics.PorterDuffXfermode
import android.graphics.Rect
import com.squareup.picasso.Transformation

class PicassoCircleTransformation : Transformation {
	override fun transform(source: Bitmap): Bitmap {
		val result = Bitmap.createBitmap(source.width, source.height, Bitmap.Config.ARGB_8888)
		val canvas = Canvas(result)
		val paint = Paint(Paint.ANTI_ALIAS_FLAG)
		val rect = Rect(0, 0, source.width, source.height)
		paint.isAntiAlias = true
		paint.color = Color.BLUE
		canvas.drawARGB(0, 0, 0, 0)
		canvas.drawCircle(source.width / 2f, source.height / 2f, source.width / 2f, paint)
		paint.xfermode = PorterDuffXfermode(PorterDuff.Mode.SRC_IN)
		canvas.drawBitmap(source, rect, rect, paint)
		source.recycle()
		return result
	}

	override fun key(): String {
		return "."
	}
}
