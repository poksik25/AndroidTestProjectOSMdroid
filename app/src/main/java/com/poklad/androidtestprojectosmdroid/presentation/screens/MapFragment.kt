package com.poklad.androidtestprojectosmdroid.presentation.screens

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.snackbar.Snackbar
import com.poklad.androidtestprojectosmdroid.R
import com.poklad.androidtestprojectosmdroid.data.PointModel
import com.poklad.androidtestprojectosmdroid.databinding.FragmentMapBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.osmdroid.bonuspack.routing.OSRMRoadManager
import org.osmdroid.bonuspack.routing.RoadManager
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.CustomZoomButtonsController
import org.osmdroid.views.MapController
import org.osmdroid.views.overlay.Marker
import org.osmdroid.views.overlay.Polyline
import org.osmdroid.views.overlay.gestures.RotationGestureOverlay
import org.osmdroid.views.overlay.mylocation.GpsMyLocationProvider
import org.osmdroid.views.overlay.mylocation.MyLocationNewOverlay

class MapFragment : Fragment(R.layout.fragment_map) {

    private lateinit var binding: FragmentMapBinding
    private lateinit var mapController: MapController
    private lateinit var marker: Marker
    private lateinit var pointList: List<PointModel>
    private lateinit var mLocationOverlay: MyLocationNewOverlay

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpMapView()
        startPoint()
        initPointList()
        pointList.forEach { point ->
            setMarkerOnMap(point.geoPoint, point.name)
        }
        mLocationOverlay = MyLocationNewOverlay(GpsMyLocationProvider(context), binding.mapView)
        mLocationOverlay.enableMyLocation()
        binding.mapView.overlays.add(mLocationOverlay)
    }

    private fun initPointList() {
        pointList = listOf(

        )
    }

    private fun setUpMapView() {
        binding.mapView.apply {
            val gestureOverlay = RotationGestureOverlay(this)
            gestureOverlay.isEnabled = true
            setMultiTouchControls(true)
            overlays.add(gestureOverlay)
            setTileSource(TileSourceFactory.MAPNIK)
            zoomController.setVisibility(CustomZoomButtonsController.Visibility.SHOW_AND_FADEOUT)
        }
    }

    private fun setMarkerOnMap(geoPoint: GeoPoint, title: String) {
        marker = Marker(binding.mapView)
        marker.position = geoPoint
        marker.icon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_location_pin)
        marker.title = title
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        marker.setOnMarkerClickListener { marker, _ ->
            Snackbar.make(requireView(), marker.title, Snackbar.LENGTH_SHORT).show()
            buildRoad(marker.position)
            true
        }
        binding.mapView.overlays.add(marker)
        binding.mapView.invalidate()
    }

    private fun startPoint() {
        mapController = binding.mapView.controller as MapController
        mapController.setZoom(15)
        mapController.setCenter(START_POINT)
    }

    private fun loadConfigurationMap() {
        Configuration.getInstance().load(
            requireContext(),
            PreferenceManager.getDefaultSharedPreferences(requireContext())
        )
    }

    private fun buildRoad(endPoint: GeoPoint) {
        binding.mapView.overlays.removeAll { it is Polyline }
        CoroutineScope(Dispatchers.IO).launch {
            val roadManager = OSRMRoadManager(requireContext(), System.getProperty(AGENT))
            roadManager.setMean(OSRMRoadManager.MEAN_BY_FOOT)
            val wayPoints = arrayListOf(mLocationOverlay?.myLocation ?: START_POINT, endPoint)
            val road = roadManager.getRoad(wayPoints)
            val roadOverlay = RoadManager.buildRoadOverlay(road)
            withContext(Dispatchers.Main) {
                binding.mapView.apply {
                    overlays.add(0, roadOverlay)
                    invalidate()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        loadConfigurationMap()
        binding.mapView.onResume()
    }

    override fun onPause() {
        super.onPause()
        binding.mapView.onPause()
    }

    companion object {
        const val AGENT = "http.agent"
        val START_POINT = GeoPoint(49.9975076, 36.218518)
    }
}
