package dev.matt2393.carreterasnex.solucion.reporte

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import dev.matt2393.carreterasnex.databinding.FragmentReporteBinding
import dev.matt2393.carreterasnex.solucion.SolucionViewModel

class ReporteFragment: Fragment() {
    private val viewModel: SolucionViewModel by activityViewModels()
    private var binding: FragmentReporteBinding? = null
    private var adapter: ReporteAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentReporteBinding.inflate(inflater, container, false)
        adapter = ReporteAdapter()
        binding?.recyclerReporte?.layoutManager = LinearLayoutManager(requireContext())
        binding?.recyclerReporte?.adapter = adapter
        return binding?.root
    }

    override fun onStart() {
        super.onStart()
        initObservers()
    }

    override fun onStop() {
        super.onStop()
        removeObservers()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun initObservers() {
        viewModel.allSol.observe(this) {
            adapter?.reporte = it.reporte
            adapter?.notifyDataSetChanged()
        }
    }
    private fun removeObservers() {
        viewModel.allSol.removeObservers(this)
    }
}