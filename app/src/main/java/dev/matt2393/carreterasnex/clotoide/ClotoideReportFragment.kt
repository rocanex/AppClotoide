package dev.matt2393.carreterasnex.clotoide

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.alclabs.fasttablelayout.FastTableLayout
import dev.matt2393.carreterasnex.customFormat
import dev.matt2393.carreterasnex.databinding.FragmentClotoideReportBinding
import dev.matt2393.carreterasnex.databinding.ItemCalculosBinding
import dev.matt2393.carreterasnex.model.DMSModel
import dev.matt2393.carreterasnex.splitWithPlus
import dev.matt2393.carreterasnex.toDMS

class ClotoideReportFragment : Fragment() {
    private lateinit var binding: FragmentClotoideReportBinding
    private val viewModel: VMClotoide by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentClotoideReportBinding.inflate(inflater, container, false)
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

        val headers = arrayOf(
            "PROGRESIVAS",
            "CUERDA\nUNITARIA",
            "L",
            "L2",
            "K = 0e/le",
            "0 = K * L2",
            "P = (0/3) - Z"
        )

        val data = mutableListOf<Array<String>>()

        data.add(
            arrayOf(
                "${viewModel.calcProgTE().splitWithPlus()}",
                "",
                "0.0",
                "",
                "",
                "",
                "${DMSModel(0, 0, 0.0)}"
            )
        )

        val rows = viewModel.rows
        for (i in rows.indices) {
            data.add(
                arrayOf(
                    "${rows[i].progresiva.customFormat()}",
                    "${rows[i].cuerdaUnitaria.customFormat()}",
                    "${rows[i].L.customFormat()}",
                    "${rows[i].L2.customFormat(6)}",
                    "${rows[i].K.customFormat(6)}",
                    "${rows[i].theta.toDMS()}",
                    "${rows[i].P.toDMS()}",
                )
            )
        }

        val fastTable =
            FastTableLayout(requireContext(), binding.myTableLayout, headers, data.toTypedArray())
        fastTable.build()

        val headers2 = arrayOf(
            "PROGRESIVA",
            "CUERDA\nUNIT",
            "FLEXIONES\nUNITARIAS",
            "DEFLEXIONES\nACUMULADAS",
            "ANGULO DE REPLANTEO",
        )

        val data2 = mutableListOf<Array<String>>()

        data2.add(arrayOf("${viewModel.calcProgEC().customFormat()}", "", "", "", ""))

        val rows2 = viewModel.table.rows
        for (i in rows2.indices) {
            data2.add(
                arrayOf(
                    "${rows2[i].progresiva}",
                    "${rows2[i].longPuntos.customFormat()}",
                    "${rows2[i].deflexUnitaria.customFormat()}",
                    "${rows2[i].deflexAcumulada.toDMS()}",
                    "${rows2[i].angulo.toDMS()}",
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


        val data3 = mutableListOf<Array<String>>()

        data3.add(
            arrayOf(
                "${viewModel.calcProgET().splitWithPlus()}",
                "",
                "0.0",
                "",
                "",
                "",
                "${DMSModel(0, 0, 0.0)}"
            )
        )

        val rows3 = viewModel.rows3
        for (i in rows3.indices) {
            data3.add(
                arrayOf(
                    "${rows3[i].progresiva.customFormat()}",
                    "${rows3[i].cuerdaUnitaria.customFormat()}",
                    "${rows3[i].L.customFormat()}",
                    "${rows3[i].L2.customFormat(6)}",
                    "${rows3[i].K.customFormat(6)}",
                    "${rows3[i].theta.toDMS()}",
                    "${rows3[i].P.toDMS()}",
                )
            )
        }

        val fastTable3 =
            FastTableLayout(requireContext(), binding.myTableLayout3, headers, data3.toTypedArray())
        fastTable3.build()

        val headers5 = arrayOf(
            "DE PROG",
            "L",
            "0",
            "0rad",
            "X",
            "Y",
            "C.L.",
            "Phi",
            "AZIMUT",
            "N",
            "E",
            "Norte",
            "Este",
            "A PROG",
        )

        val data5 = mutableListOf<Array<String>>()

        data5.add(
            arrayOf(
                "0",
                "0",
                "${DMSModel(0, 0, 0.0)}",
                "0",
                "0",
                "0",
                "0",
                "${DMSModel(0, 0, 0.0)}",
                "${viewModel.az1}",
                "0",
                "0",
                "${viewModel.n}",
                "${viewModel.e}",
                "${viewModel.calcProgTE().customFormat()}",
            )
        )

        val rows5 = viewModel.rows5
        for (i in rows5.indices) {
            data5.add(
                arrayOf(
                    "${rows5[i].deProg.customFormat()}",
                    "${rows5[i].L.customFormat()}",
                    "${rows5[i].theta.toDMS()}",
                    "${rows5[i].thetaRad.customFormat()}",
                    "${rows5[i].X.customFormat()}",
                    "${rows5[i].Y.customFormat()}",
                    "${rows5[i].CL.customFormat()}",
                    "${rows5[i].Phi.toDMS()}",
                    "${rows5[i].az.toDMS()}",
                    "${rows5[i].deltaN.customFormat()}",
                    "${rows5[i].deltaE.customFormat()}",
                    "${rows5[i].Norte.customFormat()}",
                    "${rows5[i].Estte.customFormat()}",
                    "${rows5[i].aProg.customFormat()}",
                )
            )
        }

        val fastTable5 =
            FastTableLayout(requireContext(), binding.myTableLayout5, headers5, data5.toTypedArray())
        fastTable5.build()

        val headers6 = arrayOf(
            "PROG",
            "CUEDA\nUNITARIA",
            "DELECCION\nUNITARIA",
            "DELECCION\nACUMULADA",
            "AZIMUT",
            "DH",
            "\u0394N",
            "\u0394E",
            "NORTE",
            "ESTE",
        )

        val data6 = mutableListOf<Array<String>>()

        val rows6 = viewModel.rows6
        for (i in rows6.indices) {
            data6.add(
                arrayOf(
                    "${rows6[i].progresiva.customFormat()}",
                    "${rows6[i].cuedaUnitaria.customFormat()}",
                    "${rows6[i].delecUnitaria.toDMS()}",
                    "${rows6[i].delecAcumulada.toDMS()}",
                    "${rows6[i].azimut.toDMS()}",
                    "${rows6[i].dh.customFormat()}",
                    "${rows6[i].deltaN.customFormat()}",
                    "${rows6[i].deltaE.customFormat()}",
                    "${rows6[i].n.customFormat()}",
                    "${rows6[i].e.customFormat()}",
                )
            )
        }

        val fastTable6 =
            FastTableLayout(requireContext(), binding.myTableLayout6, headers6, data6.toTypedArray())
        fastTable6.build()

        val data7 = mutableListOf<Array<String>>()

        data7.add(
            arrayOf(
                "0",
                "0",
                "${DMSModel(0, 0, 0.0)}",
                "0",
                "0",
                "0",
                "0",
                "${DMSModel(0, 0, 0.0)}",
                "${viewModel.az1}",
                "0",
                "0",
                "${viewModel.n}",
                "${viewModel.e}",
                "${viewModel.calcProgTE().customFormat()}",
            )
        )

        val rows7 = viewModel.rows7
        for (i in rows7.indices) {
            data7.add(
                arrayOf(
                    "${rows7[i].deProg.customFormat()}",
                    "${rows7[i].L.customFormat()}",
                    "${rows7[i].theta.toDMS()}",
                    "${rows7[i].thetaRad.customFormat()}",
                    "${rows7[i].X.customFormat()}",
                    "${rows7[i].Y.customFormat()}",
                    "${rows7[i].CL.customFormat()}",
                    "${rows7[i].Phi.toDMS()}",
                    "${rows7[i].az.toDMS()}",
                    "${rows7[i].deltaN.customFormat()}",
                    "${rows7[i].deltaE.customFormat()}",
                    "${rows7[i].Norte.customFormat()}",
                    "${rows7[i].Estte.customFormat()}",
                    "${rows7[i].aProg.customFormat()}",
                )
            )
        }

        val fastTable7 =
            FastTableLayout(requireContext(), binding.myTableLayout7, headers5, data7.toTypedArray())
        fastTable7.build()
    }
}