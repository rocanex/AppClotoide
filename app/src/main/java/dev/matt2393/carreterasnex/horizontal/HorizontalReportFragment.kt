package dev.matt2393.carreterasnex.horizontal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.alclabs.fasttablelayout.FastTableLayout
import dev.matt2393.carreterasnex.customFormat
import dev.matt2393.carreterasnex.databinding.FragmentHorizontalReportBinding
import dev.matt2393.carreterasnex.databinding.ItemCalculosBinding
import dev.matt2393.carreterasnex.model.DMSModel
import dev.matt2393.carreterasnex.splitWithPlus
import dev.matt2393.carreterasnex.toDMS

class HorizontalReportFragment : Fragment() {

    private lateinit var binding: FragmentHorizontalReportBinding
    private val viewModel: VMHorizontal by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHorizontalReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        for (item in viewModel.getListCalculates()) {
            val itemBinding = ItemCalculosBinding.inflate(LayoutInflater.from(requireContext()))
            itemBinding.textTitItemCalculos.text = item.text
            itemBinding.textItemCalculos.text = item.value
            binding.llResults.addView(itemBinding.root)
        }

        with(binding) {
            tvDataA1.text = "Prog IC = ${viewModel.calcProgPc().splitWithPlus()}"
            tvDataA2.text = "Prog FC = ${viewModel.calcProgPt().splitWithPlus()}"
            tvDataA3.text = "G = ${viewModel.calcGc().toDMS()}"
            tvDataA4.text = "Cu = ${viewModel.calcProgPt().splitWithPlus()}"
        }

        val headers = arrayOf(
            "PUNTO",
            "PROGRESIVAS",
            "LONGITUD ENTRE\nPUNTOS",
            "DEFLEXION\nUNITARIA",
            "DEFLEXION\nACUMULADA",
            "ANGULO DE\nREPLANTEO"
        )

        val data = mutableListOf<Array<String>>()

        data.add(
            arrayOf(
                "PC",
                "${viewModel.calcProgPc().customFormat()}",
                "",
                "",
                "${DMSModel(0, 0, 0.0)}",
                "${DMSModel(0, 0, 0.0)}",
            )
        )

        val rows = viewModel.table.rows
        for (i in rows.indices) {
            data.add(
                arrayOf(
                    if (i < rows.size - 1) "${i + 1}" else "PT",
                    "${rows[i].progresiva}",
                    "${rows[i].longPuntos}",
                    "${rows[i].deflexUnitaria.toDMS()}",
                    "${rows[i].deflexAcumulada.toDMS()}",
                    "${rows[i].angulo.toDMS()}",
                )
            )
        }

        val fastTable =
            FastTableLayout(requireContext(), binding.myTableLayout, headers, data.toTypedArray())
        fastTable.build()

        with(binding) {
            tvDataB1.text = "Prog IC = ${viewModel.calcProgPc().splitWithPlus()}"
            tvDataB2.text = "Prog FC = ${viewModel.calcProgPt().splitWithPlus()}"
            tvDataB3.text = "G = ${viewModel.calcGc().toDMS()}"
            tvDataB4.text = "Cu = ${viewModel.calcProgPt().splitWithPlus()}"
            tvDataB5.text = "Az = ${viewModel.az}"
            tvDataB6.text = "N = ${viewModel.n.customFormat()}"
            tvDataB7.text = "E = ${viewModel.e.customFormat()}"
            tvDataB7.text = "A = ${viewModel.e.customFormat()}"

        }

        val headers2 = arrayOf(
            "PROG",
            "CUEDA\nUNITARIA",
            "DELECCION\nUNITARIA",
            "DELECCION\nACUMULADA",
            "AZIMUT",
            "DH",
            "\u0394N",
            "\u0394E",
            "N",
            "E",
            "A",
        )

        val data2 = mutableListOf<Array<String>>()

        data2.add(
            arrayOf(
                "PC",
                "0",
                "${DMSModel(0, 0, 0.0)}",
                "${DMSModel(0, 0, 0.0)}",
                "${viewModel.az}",
                "0",
                "0",
                "0",
                "${viewModel.n.customFormat()}",
                "${viewModel.e.customFormat()}",
                "IC=${viewModel.calcProgPc().customFormat()}"


            )
        )

        val rows2 = viewModel.table2.rows
        for (i in rows2.indices) {
            data2.add(
                arrayOf(
                    "${rows2[i].progresiva}",
                    "${rows2[i].cuedaUnitaria.customFormat()}",
                    "${rows2[i].delecUnitaria.toDMS()}",
                    "${rows2[i].delecAcumulada.toDMS()}",
                    "${rows2[i].azimut.toDMS()}",
                    "${rows2[i].dh.customFormat()}",
                    "${rows2[i].deltaN.customFormat()}",
                    "${rows2[i].deltaE.customFormat()}",
                    "${rows2[i].n.customFormat()}",
                    "${rows2[i].e.customFormat()}",
                    "${rows2[i].a.customFormat()}",

                )
            )
        }

        val fastTable2 =
            FastTableLayout(
                requireContext(),
                binding.myTableLayout2,
                headers2,
                data2.toTypedArray()
            )
        fastTable2.build()
    }
}