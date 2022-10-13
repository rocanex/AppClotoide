package dev.matt2393.carreterasnex.solucion.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.aghajari.graphview.AXGraphOptions
import dev.matt2393.carreterasnex.databinding.FragmentHomeBinding
import dev.matt2393.carreterasnex.model.Prog
import dev.matt2393.carreterasnex.solucion.SolucionViewModel

class HomeFragment : Fragment() {

    private val viewModel: SolucionViewModel by activityViewModels()
    private var binding: FragmentHomeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        binding?.layoutDatos?.buttonSolucion?.setOnClickListener {

            val pr1 = binding?.layoutDatos?.editDato11?.text.toString()
            val pr2 = binding?.layoutDatos?.editDato12?.text.toString()
            val progPI = Prog(pr1.toInt(), pr2.toFloat())

            val seg = binding?.layoutDatos?.editDato2seg?.text.toString()
            val min = binding?.layoutDatos?.editDato2min?.text.toString()
            val gr = binding?.layoutDatos?.editDato2?.text.toString()
            val angle = gr.toFloat() + (min.toFloat()/60f) + (seg.toFloat()/3600f)

            val ve = binding?.layoutDatos?.editDato3?.text.toString()
            val cu = binding?.layoutDatos?.editDato4?.text.toString()
            val n = binding?.layoutDatos?.editDato5?.text.toString()
            val a = binding?.layoutDatos?.editDato6?.text.toString()
            val r = binding?.layoutDatos?.editDato7?.text.toString()
            val aCal = binding?.layoutDatos?.editDato8?.text.toString()


            viewModel.solucionar(r.toFloat(), angle, cu.toFloat(), aCal.toFloat(),n.toInt(), a.toFloat(), ve.toFloat(), progPI)
        }
        return binding?.root
    }

    private fun graph() {
        val options = AXGraphOptions(requireContext())

        options.scrollEnabled = true
        options.xDividerIntervalInPx = 100f
        options.xDividerInterval = 1f
        options.yDividerIntervalInPx = 100f
        options.yDividerInterval = 1f
        options.maxZoom = 6f
        options.minZoom = 0.1f

        // options.drawAxisX = false
        // options.drawAxisY = false
        options.drawAxisXDivider = false
        options.drawAxisYDivider = false
        options.drawXText = false
        options.drawYText = false
        //binding?.graphView?.graphOptions = options

        /* binding?.graphView?.addFormula(
             Clotoide(5f, (42f* PI/180f).toFloat(),(16.5f* PI/180f).toFloat(),0f,0f,0f,0f,0f,1f)
         )*/
        options.drawAxisXDivider = false
        options.drawAxisYDivider = false
        options.drawXText = false
        options.drawYText = false
    }

}