package com.namma.shasane.ui.find

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.graphics.RectF
import android.graphics.Typeface
import android.util.AttributeSet
import android.view.View
import com.namma.shasane.model.InscriptionRepository
import com.namma.shasane.model.MarkerType

class HeritageMapView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    // Paints
    private val bgPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#2C1F10")
        style = Paint.Style.FILL
    }

    private val tilePaintLight = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#3D2E1F")
        style = Paint.Style.FILL
    }

    private val tilePaintDark = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#261A0A")
        style = Paint.Style.FILL
    }

    private val gridLinePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#4A3728")
        style = Paint.Style.STROKE
        strokeWidth = 0.8f
    }

    private val waterPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#1A2A3A")
        style = Paint.Style.FILL
    }

    private val borderPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#C8A86B")
        style = Paint.Style.STROKE
        strokeWidth = 2f
    }

    private val markerPaintFound = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#FF8C00")
        style = Paint.Style.FILL
    }
    private val markerPaintFoundStroke = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#FFAA44")
        style = Paint.Style.STROKE
        strokeWidth = 2f
    }

    private val markerPaintVerified = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#228B22")
        style = Paint.Style.FILL
    }
    private val markerPaintVerifiedStroke = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#44AA44")
        style = Paint.Style.STROKE
        strokeWidth = 2f
    }

    private val markerPaintAtRisk = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#CC2200")
        style = Paint.Style.FILL
    }
    private val markerPaintAtRiskStroke = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#EE4422")
        style = Paint.Style.STROKE
        strokeWidth = 2f
    }

    private val labelPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#F5DEB3")
        textSize = 22f
        typeface = Typeface.DEFAULT_BOLD
    }

    private val labelShadowPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#AA000000")
        textSize = 22f
        typeface = Typeface.DEFAULT_BOLD
    }

    private val titlePaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#C8A86B")
        textSize = 26f
        typeface = Typeface.DEFAULT_BOLD
    }

    private val roadPaint = Paint(Paint.ANTI_ALIAS_FLAG).apply {
        color = Color.parseColor("#5C4020")
        style = Paint.Style.STROKE
        strokeWidth = 2.5f
    }

    private val inscriptions = InscriptionRepository.getSampleInscriptions()

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        val w = width.toFloat()
        val h = height.toFloat()
        val cornerRadius = 20f

        // Draw clipped rounded rect background
        val bgRect = RectF(0f, 0f, w, h)
        val clipPath = Path().apply {
            addRoundRect(bgRect, cornerRadius, cornerRadius, Path.Direction.CW)
        }
        canvas.save()
        canvas.clipPath(clipPath)

        // Background fill
        canvas.drawRoundRect(bgRect, cornerRadius, cornerRadius, bgPaint)

        // Draw grid tiles (checkerboard map style)
        val tileSize = 40f
        val cols = (w / tileSize).toInt() + 2
        val rows = (h / tileSize).toInt() + 2
        for (row in 0..rows) {
            for (col in 0..cols) {
                val tileRect = RectF(
                    col * tileSize,
                    row * tileSize,
                    (col + 1) * tileSize,
                    (row + 1) * tileSize
                )
                val paint = if ((row + col) % 2 == 0) tilePaintLight else tilePaintDark
                canvas.drawRect(tileRect, paint)
            }
        }

        // Draw grid lines
        var x = 0f
        while (x <= w) {
            canvas.drawLine(x, 0f, x, h, gridLinePaint)
            x += tileSize
        }
        var y = 0f
        while (y <= h) {
            canvas.drawLine(0f, y, w, y, gridLinePaint)
            y += tileSize
        }

        // Draw simulated water body (river / lake patches)
        val waterRect1 = RectF(w * 0.60f, h * 0.10f, w * 0.80f, h * 0.35f)
        canvas.drawRoundRect(waterRect1, 8f, 8f, waterPaint)
        val waterRect2 = RectF(w * 0.05f, h * 0.40f, w * 0.22f, h * 0.58f)
        canvas.drawRoundRect(waterRect2, 6f, 6f, waterPaint)

        // Draw simulated road lines
        canvas.drawLine(w * 0.10f, h * 0.50f, w * 0.90f, h * 0.50f, roadPaint)
        canvas.drawLine(w * 0.50f, h * 0.05f, w * 0.50f, h * 0.95f, roadPaint)
        canvas.drawLine(w * 0.20f, h * 0.20f, w * 0.80f, h * 0.80f, roadPaint)

        // Draw title overlay
        canvas.drawText("Karnataka", w * 0.05f + 1f, h * 0.15f + 1f, labelShadowPaint)
        canvas.drawText("Karnataka", w * 0.05f, h * 0.15f, titlePaint)

        // Draw inscription markers
        val markerRadius = 14f
        for (inscription in inscriptions) {
            val mx = inscription.mapX * w
            val my = inscription.mapY * h

            // Select paint based on marker type
            val fillPaint = when (inscription.markerType) {
                MarkerType.FOUND -> markerPaintFound
                MarkerType.VERIFIED -> markerPaintVerified
                MarkerType.AT_RISK -> markerPaintAtRisk
            }
            val strokePaint = when (inscription.markerType) {
                MarkerType.FOUND -> markerPaintFoundStroke
                MarkerType.VERIFIED -> markerPaintVerifiedStroke
                MarkerType.AT_RISK -> markerPaintAtRiskStroke
            }

            // Draw marker circle
            canvas.drawCircle(mx, my, markerRadius, fillPaint)
            canvas.drawCircle(mx, my, markerRadius, strokePaint)

            // Draw pin stem
            val stemPaint = Paint(fillPaint).apply { style = Paint.Style.FILL }
            canvas.drawRect(mx - 2f, my + markerRadius - 2f, mx + 2f, my + markerRadius + 6f, stemPaint)

            // Draw label (short name)
            val label = inscription.location
            val labelX = mx - labelPaint.measureText(label) / 2f
            val labelY = my - markerRadius - 6f

            // Shadow
            canvas.drawText(label, labelX + 1f, labelY + 1f, labelShadowPaint)
            // Label
            canvas.drawText(label, labelX, labelY, labelPaint)
        }

        // Draw rounded border on top
        canvas.drawRoundRect(bgRect, cornerRadius, cornerRadius, borderPaint)

        canvas.restore()
    }
}
