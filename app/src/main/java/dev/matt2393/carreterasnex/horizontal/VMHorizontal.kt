package dev.matt2393.carreterasnex.horizontal

import androidx.lifecycle.ViewModel
import dev.matt2393.carreterasnex.*
import dev.matt2393.carreterasnex.model.DMSModel
import dev.matt2393.carreterasnex.model.horizontal.RowCoordenadas
import dev.matt2393.carreterasnex.model.horizontal.RowDeflexionModel
import dev.matt2393.carreterasnex.model.horizontal.TableCoordenadas
import dev.matt2393.carreterasnex.model.horizontal.TableDeflexionModel
import kotlin.math.*

class VMHorizontal : ViewModel() {
    private var progP1 = 0.0
    private var delta = DMSModel(0, 0, 0.0)
    private var r = 0.0
    private var cu = 0
    var az = DMSModel(0, 0, 0.0)
    var n = 0.0
    var e = 0.0

    private val rows = mutableListOf<RowDeflexionModel>()
    val table = TableDeflexionModel(rows)

    private val rows2 = mutableListOf<RowCoordenadas>()
    val table2 = TableCoordenadas(rows2)

    fun loadData(
        progP1: Double, delta: DMSModel, r: Double, cu: Int, az: DMSModel, n: Double, e: Double
    ) {
        this.progP1 = progP1
        this.delta = delta
        this.r = r
        this.cu = cu
        this.az = az
        this.n = n
        this.e = e
    }

    // Calculate T = R tan(delta/2)
    fun calcT(): Double = r * tan((delta.toDegree() / 2).degreeToRadian())

    // Calculate Gc = 2 arcsen(cu/2R)
    fun calcGc(): Double = 2 * asin(cu / (2 * r)).radianToDegree()

    // Calculate Lc = cu (delta/Gc)
    fun calcLc(): Double = cu * (delta.divide(calcGc().toDMS())).toDegree()

    // Calculate E = r ( ( 1/cos(delta/2) ) - 1 )
    fun calcExtern(): Double = r * ((1 / cos((delta.toDegree() / 2).degreeToRadian())) - 1)

    // Calculate M = r ( 1 - cos(delta/2) )
    fun calcM(): Double = r * (1 - cos((delta.toDegree() / 2).degreeToRadian()))

    // Calculate cl = 2 r sen(delta/2)
    fun calcCl(): Double = 2 * r * sin((delta.toDegree() / 2).degreeToRadian())

    // Calculate progPC = progP1 - T
    fun calcProgPc(): Double = progP1 - calcT()

    // Calculate progPT = progPC + Lc
    fun calcProgPt(): Double = calcProgPc() + calcLc()

    fun getListCalculates(): List<CalculateModel> = listOf(
        CalculateModel("T", "${calcT().customFormat()} m"),
        CalculateModel("G", "${calcGc().toDMS()}"),
        CalculateModel("LC", "${calcLc().customFormat()} m"),
        CalculateModel("E", "${calcExtern().customFormat()} m"),
        CalculateModel("F", "${calcM().customFormat()} m"),
        CalculateModel("CL", "${calcCl().customFormat()} m"),
        CalculateModel("IC", calcProgPc().splitWithPlus()),
        CalculateModel("FC", calcProgPt().splitWithPlus()),
    )
//// tabla de metodo de las deflexiones
    fun genDeflexionTable() {
    val newPc = calcProgPc().roundToInt().roundToNearestTen()
    val disPoints = calcProgPc().roundToInt().roundToNearestTen() - calcProgPc()
    //val newPc = (calcProgPc()+cu).roundToInt().roundToFarthestTen()
    //val disPoints = (calcProgPc()+cu).roundToInt().roundToFarthestTen() - calcProgPc()


    rows.clear()

        rows.add(
            RowDeflexionModel(
                newPc.toDouble(),
                disPoints.customFormat(),
                table.calcDeflexionUnitaria(disPoints, r),
                table.calcDeflexionUnitaria(disPoints, r),
                table.calcDeflexionUnitaria(disPoints, r),
            )
        )

        do {
            val lastRow = rows.last()
            rows.add(
                RowDeflexionModel(
                    lastRow.progresiva + cu,
                    cu.toDouble(),
                    table.calcDeflexionUnitaria(cu.toDouble(), r),
                    lastRow.deflexAcumulada + table.calcDeflexionUnitaria(cu.toDouble(), r),
                    lastRow.deflexAcumulada + table.calcDeflexionUnitaria(cu.toDouble(), r),
                )
            )
        } while (rows.last().progresiva <= calcProgPt())
        rows.removeLast()

        val lastRow = rows.last()
        rows.add(
            RowDeflexionModel(
                calcProgPt().customFormat(),
                (calcProgPt() - lastRow.progresiva).customFormat(),
                table.calcDeflexionUnitaria(calcProgPt() - lastRow.progresiva, r),
                lastRow.deflexAcumulada + table.calcDeflexionUnitaria(
                    calcProgPt() - lastRow.progresiva,
                    r
                ),
                lastRow.deflexAcumulada + table.calcDeflexionUnitaria(
                    calcProgPt() - lastRow.progresiva,
                    r
                ),
            )
        )
    }
///// metodo de las coordenadas
    fun genCoordenadasTable() {

    ///val newPc = (calcProgPc()+cu).roundToInt().roundToFarthestTen()
   ///val disPoints = (calcProgPc()+cu).roundToInt().roundToFarthestTen() - calcProgPc()
    val newPc = calcProgPc().roundToInt().roundToNearestTen()
    val disPoints = calcProgPc().roundToInt().roundToNearestTen() - calcProgPc()


        rows2.clear()
        rows2.add(
            RowCoordenadas(
                progresiva = newPc.toDouble(),
                cuedaUnitaria = disPoints.customFormat(),
                delecUnitaria = table2.calcDeflexionUnitaria(disPoints, r),
                delecAcumulada = table2.calcDeflexionUnitaria(disPoints, r),
                azimut = az.toDegree() + table2.calcDeflexionUnitaria(disPoints, r),
                dh = disPoints.customFormat(),
                deltaN = table2.calcDeltaN(disPoints.customFormat(), az.toDegree()),
                deltaE = table2.calcDeltaE(disPoints.customFormat(), az.toDegree()),
                n = table2.calcDeltaN(disPoints.customFormat(), az.toDegree()) + n,
                e = table2.calcDeltaE(disPoints.customFormat(), az.toDegree()) + e,
                a = newPc.toDouble(),
            )
        )

        do {
            val lastRow = rows2.last()

            val delecUnitaria = table2.calcDeflexionUnitaria(cu.toDouble(), r)
            val delecAcumulada =
                table2.calcDeflexionUnitaria(cu.toDouble(), r) + lastRow.delecUnitaria
            val azimut = lastRow.azimut + delecAcumulada
            val deltaN = table2.calcDeltaN(cu.toDouble(), azimut)
            val deltaE = table2.calcDeltaE(cu.toDouble(), azimut)


            rows2.add(
                RowCoordenadas(
                    progresiva = lastRow.progresiva + cu,
                    cuedaUnitaria = cu.toDouble(),
                    delecUnitaria = delecUnitaria,
                    delecAcumulada = delecAcumulada,
                    azimut = azimut,
                    dh = cu.toDouble(),
                    deltaN = deltaN,
                    deltaE = deltaE,
                    n = lastRow.n + deltaN,
                    e = lastRow.e + deltaE,
                    a = lastRow.progresiva + cu,
                )
            )
        } while (rows2.last().progresiva <= calcProgPt())

        rows2.removeLast()

        val lastRow = rows2.last()
        val cuedaUnitaria = calcProgPt() - lastRow.progresiva
        val delecUnitaria = table2.calcDeflexionUnitaria(cuedaUnitaria, r)
        val delecAcumulada = table2.calcDeflexionUnitaria(cuedaUnitaria, r) + lastRow.delecUnitaria
        val azimut = lastRow.azimut + delecAcumulada
        val deltaN = table2.calcDeltaN(cuedaUnitaria, azimut)
        val deltaE = table2.calcDeltaE(cuedaUnitaria, azimut)
        rows2.add(
            RowCoordenadas(
                progresiva = calcProgPt().customFormat(),
                cuedaUnitaria = cuedaUnitaria.customFormat(),
                delecUnitaria = delecUnitaria,
                delecAcumulada = delecAcumulada,
                azimut = azimut,
                dh = cuedaUnitaria.customFormat(),
                deltaN = deltaN,
                deltaE = deltaE,
                n = lastRow.n + deltaN,
                e = lastRow.e + deltaE,
                a = calcProgPt().customFormat(),
            )
        )
    }
}


data class CalculateModel(val text: String, val value: String)