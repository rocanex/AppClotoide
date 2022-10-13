package dev.matt2393.carreterasnex.solucion.calculos

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import dev.matt2393.carreterasnex.*
import dev.matt2393.carreterasnex.databinding.ItemCalculosBinding
import dev.matt2393.carreterasnex.model.Solucion

class CalculosAdapter(var solucion: Solucion = Solucion()): RecyclerView.Adapter<CalculosAdapter.CalculosViewHolder>() {
    inner class CalculosViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        private val binding = ItemCalculosBinding.bind(itemView)
        fun changeData(tit: String, value: String) {
            binding.textTitItemCalculos.text = tit
            binding.textItemCalculos.text = value
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CalculosViewHolder =
        CalculosViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_calculos, parent, false)
        )

    override fun onBindViewHolder(holder: CalculosViewHolder, position: Int) {
        when(position) {
            0 -> holder.changeData("le:", solucion.lE.toRoundMillMetherString())
            1 -> holder.changeData("\u03B8e:", solucion.thetaE.toFormatSexadecimal())
            2 -> holder.changeData("Ac:", solucion.aC.toFormatSexadecimal())
            3 -> holder.changeData("Gc:", solucion.gC.toSexadecimal().toFormatSexadecimal())
            4 -> holder.changeData("Lcc:", solucion.lcc.toRoundMillMetherString())
            5 -> holder.changeData("Xc:", solucion.xC.toRoundMillMetherString())
            6 -> holder.changeData("Yc:", solucion.yC.toRoundMillMetherString())
            7 -> holder.changeData("k:", solucion.k.toRoundMillMetherString())
            8 -> holder.changeData("p:", solucion.p.toRoundMillMetherString())
            9 -> holder.changeData("Te:", solucion.tE.toRoundMillMetherString())
            10 -> holder.changeData("Tl:", solucion.tL.toRoundMillMetherString())
            11 -> holder.changeData("Tc:", solucion.tC.toRoundMillMetherString())
            12 -> holder.changeData("ee:", solucion.ee.toRoundMillMetherString())
            13 -> holder.changeData("Cl:", solucion.cL.toRoundMillMetherString())
            14 -> holder.changeData("\u03C6e:", solucion.phiE.toSexadecimal().toFormatSexadecimal())
            15 -> holder.changeData("ProgTE:", "${solucion.progTE}")
            16 -> holder.changeData("ProgEC:", "${solucion.progEC}")
            17 -> holder.changeData("ProgCE:", "${solucion.progCE}")
            18 -> holder.changeData("ProgET:", "${solucion.progET}")
        }
    }

    override fun getItemCount(): Int = solucion.size()
}