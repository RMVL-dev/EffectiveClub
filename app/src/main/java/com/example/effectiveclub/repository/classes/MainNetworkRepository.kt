package com.example.effectiveclub.repository.classes

import com.example.effectiveclub.data.main.Main
import com.example.effectiveclub.network.MainServiceApi
import com.example.effectiveclub.repository.interfaces.MainRepository

class MainNetworkRepository(
    private val retrofitMainService:MainServiceApi
):MainRepository {

    override suspend fun getMain(): Main = retrofitMainService.getMain("058729bd-1402-4578-88de-265481fd7d54")

}