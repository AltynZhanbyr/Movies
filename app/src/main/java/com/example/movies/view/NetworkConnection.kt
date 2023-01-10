package com.example.movies.view

import android.annotation.TargetApi
import android.content.Context
import android.net.*
import android.os.Build
import androidx.core.view.isVisible
import androidx.lifecycle.LiveData


class NetworkConnection(private val context: Context):LiveData<Boolean>() {
    private var connectivityManager =context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

    private lateinit var networkCallback: ConnectivityManager.NetworkCallback

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private fun lollipopNetworkRequest(){
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()

        connectivityManager.registerNetworkCallback(networkRequest,connectivityManagerCallBack())
    }

    override fun onActive() {
        super.onActive()
        when{
            Build.VERSION.SDK_INT>=Build.VERSION_CODES.N->{
                connectivityManager.registerDefaultNetworkCallback(connectivityManagerCallBack())
            }
            Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP->{
                lollipopNetworkRequest()
            }
        }
    }

    private fun connectivityManagerCallBack():ConnectivityManager.NetworkCallback{
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.LOLLIPOP){
            networkCallback = object:ConnectivityManager.NetworkCallback(){
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    postValue(true)
                }

                // Network capabilities have changed for the network
                override fun onCapabilitiesChanged(
                    network: Network,
                    networkCapabilities: NetworkCapabilities
                ) {
                    super.onCapabilitiesChanged(network, networkCapabilities)
                    val unmetered = networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
                    postValue(true)
                }

                // lost network connection
                override fun onLost(network: Network) {
                    super.onLost(network)
                    postValue(false)
                }

                override fun onUnavailable() {
                    super.onUnavailable()
                    postValue(false)
                }
            }
            return  networkCallback
        }
        else
            throw IllegalAccessError("Error")
    }

}