package com.github.ahmetcanakgl.androidkotlincleantemplate.cleanKotlin.fragment

fun someFragmentViewModel(
    packageName: String,
    moduleName: String,
    usecaseClass: String,
    entityModelClass: String
): String {

    val usecaseClsProp = usecaseClass.substring(0, 1).toLowerCase() + usecaseClass.substring(1)
    val entityClsProp = entityModelClass.substring(0, 1).toLowerCase() + entityModelClass.substring(1)
    val moduleNameLower = moduleName.substring(0, 1).toLowerCase() + moduleName.substring(1)

    val code =
        """package $packageName.$moduleNameLower

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import carparts.online.feature.bases.BaseViewModel
import $packageName.interactor.${usecaseClass}
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ${moduleName}FRViewModel @Inject constructor(val $usecaseClsProp: $usecaseClass) : BaseViewModel() {

    val $entityClsProp: MutableLiveData<$entityModelClass> = MutableLiveData()

    fun f() =
        $usecaseClsProp($usecaseClass.Params(value = ""), viewModelScope) {
            it.fold(fnL = ::handleFailure,
                    fnR = ::handleMe)
        }

    private fun handleMe(response: $entityModelClass) {
        $entityClsProp.value = response
    }

}
"""
    return code
}

fun someUsecase(
    packageName: String,
    usecaseClass: String,
    repositoryClass: String,
    entityModelClass: String
): String {

    val repoClsProp = repositoryClass.substring(0, 1).toLowerCase() + repositoryClass.substring(1)

    val code =
        """package $packageName.interactor

import carparts.online.core.interactor.UseCase
import javax.inject.Inject

class $usecaseClass @Inject constructor(private val $repoClsProp: $repositoryClass) : UseCase<$entityModelClass, $usecaseClass.Params>() {

    override suspend fun run(params: Params) = $repoClsProp.x(Y(params.value))

    data class Params(val value: String)
}
"""
    return code
}

fun someRepository(
    packageName: String,
    repositoryClass: String,
    entityModelClass: String,
    serviceClass: String
): String {

    val serviceClsProp = serviceClass.substring(0, 1).toLowerCase() + serviceClass.substring(1)

    val code =
        """package $packageName.api

import carparts.online.core.exception.Failure
import carparts.online.core.functional.Either
import carparts.online.feature.bases.BaseNetwork
import javax.inject.Inject

interface $repositoryClass {

    fun x(y: Y): Either<Failure, $entityModelClass>

    class Network @Inject constructor(private val $serviceClsProp: $serviceClass) : BaseNetwork(), $repositoryClass {

        override fun x(y: Y): Either<Failure, $entityModelClass> {
            return request(call = $serviceClsProp.x(y),
                           transform = { it },
                           default = $entityModelClass())
        }
    }
}
"""
    return code
}

fun someService(
    packageName: String,
    apiClass: String,
    entityModelClass: String,
    serviceClass: String
): String {

    val apiClsProp = apiClass.substring(0, 1).toLowerCase() + apiClass.substring(1)

    val code =
        """package $packageName.api

import carparts.online.feature.models.bases.BaseResponse
import retrofit2.Call
import retrofit2.Retrofit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class $serviceClass
@Inject constructor(retrofit: Retrofit) : $apiClass {
    private val $apiClsProp by lazy { retrofit.create($apiClass::class.java) }

    override fun x(y: Y): Call<BaseResponse<$entityModelClass>> = $apiClsProp.x(y)

}
"""
    return code
}

fun someApi(
    packageName: String,
    apiClass: String,
    entityModelClass: String,
): String {

    val code =
        """package $packageName.api

import carparts.online.feature.models.bases.BaseResponse
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface $apiClass {
    
    @POST("/v1/....")
    fun x(@Body y: Y): Call<BaseResponse<$entityModelClass>>
}
"""
    return code
}

fun someEntity(
    packageName: String,
    entityModelClass: String,
): String {

    val code =
        """package $packageName.dao

data class $entityModelClass(
        var value: String? = null)
"""
    return code
}