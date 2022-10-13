package dev.matt2393.carreterasnex.model.horizontal

import android.util.Log
import dev.matt2393.carreterasnex.radianToDegree
import dev.matt2393.carreterasnex.toDMS
import kotlin.math.asin

data class TableDeflexionModel(val rows: List<RowDeflexionModel>) {
    fun calcDeflexionUnitaria(cu: Double, r: Double): Double {
        val gc = 2 * asin(cu / (2 * r)).radianToDegree()
        return gc / 2
    }

    fun printTable() {
        Log.e("TAG", "==== TABLE ====")
        for (r in rows.indices) {
            Log.e("TAG", "${r + 1} ${rows[r]}")
        }
    }
}

data class RowDeflexionModel(
    val progresiva: Double,
    val longPuntos: Double,
    val deflexUnitaria: Double,
    val deflexAcumulada: Double,
    val angulo: Double,
) {
    override fun toString(): String {
        return "$progresiva-$longPuntos-${deflexUnitaria.toDMS()}-${deflexAcumulada.toDMS()}-${angulo.toDMS()}"
    }
}