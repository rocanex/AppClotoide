package dev.matt2393.carreterasnex.solucion.reporte

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.matt2393.carreterasnex.R
import dev.matt2393.carreterasnex.databinding.ItemReporteBinding
import dev.matt2393.carreterasnex.model.Reporte
import katex.hourglass.`in`.mathlib.MathView

class ReporteAdapter(var reporte: Reporte = Reporte()): RecyclerView.Adapter<ReporteAdapter.ReporteViewHolder>() {
    inner class ReporteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemReporteBinding.bind(itemView)
        fun setMathText(tit:String, texts: ArrayList<String>) {
            if(tit.isEmpty()) {
                binding.textTitItemReporte.visibility = View.GONE
            } else {
                binding.textTitItemReporte.visibility = View.VISIBLE
            }
            binding.textTitItemReporte.text = tit
            binding.linearItemReporte.removeAllViews()
            texts.forEach {
                val mathView = MathView(binding.root.context)
                mathView.isClickable = true;
                mathView.setTextSize(14);
                mathView.setDisplayText(it)
                binding.linearItemReporte.addView(mathView)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReporteViewHolder =
        ReporteViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_reporte, parent, false)
        )

    override fun onBindViewHolder(holder: ReporteViewHolder, position: Int) {
        when(position) {
            0 -> holder.setMathText("1.- Calculo de la longitud espiral",reporte.lE.formatMath())
            1 -> holder.setMathText("2.- Calculo del ángulo θe",reporte.thetaE.formatMath())
            2 -> holder.setMathText("3.- Calculo de ∆c",reporte.ac.formatMath())
            3 -> holder.setMathText("4.- Calculo de la longitud de curva circular Lcc",reporte.gc.formatMath())
            4 -> holder.setMathText("",reporte.lcc.formatMath())
            5 -> holder.setMathText("5.- Calculo de coordenadas cartesianas",reporte.xc.formatMath())
            6 -> holder.setMathText("",reporte.yc.formatMath())
            7 -> holder.setMathText("6.- Calculo de K y P",reporte.k.formatMath())
            8 -> holder.setMathText("",reporte.p.formatMath())
            9 -> holder.setMathText("7.- Calculo de Te",reporte.te.formatMath())
            10 -> holder.setMathText("8.- Calculo de Tl y Tc",reporte.tl.formatMath())
            11 -> holder.setMathText("",reporte.tc.formatMath())
            12 -> holder.setMathText("9.- Calculo de la externa Ee",reporte.ee.formatMath())
            13 -> holder.setMathText("10.- Calculo cuerda larga Cl",reporte.cl.formatMath())
            14 -> holder.setMathText("11.- Calculo φe",reporte.phiE.formatMath())
            15 -> holder.setMathText("12.- Calculo de la progesiva",reporte.prTe.formatMath())
            16 -> holder.setMathText("",reporte.prEc.formatMath())
            17 -> holder.setMathText("",reporte.prCe.formatMath())
            18 -> holder.setMathText("",reporte.prEt.formatMath())
        }
    }

    override fun getItemCount(): Int = reporte.size()


}