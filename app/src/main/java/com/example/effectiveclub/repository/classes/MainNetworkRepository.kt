package com.example.effectiveclub.repository.classes

import com.example.effectiveclub.data.main.Main
import com.example.effectiveclub.network.MainServiceApi
import com.example.effectiveclub.repository.interfaces.AppRepository

class MainNetworkRepository(
    private val retrofit:MainServiceApi
):AppRepository {

    override suspend fun getMain(): Main = retrofit.getMain("058729bd-1402-4578-88de-265481fd7d54")

}