package tech.kuizz.api.data

import com.google.gson.annotations.SerializedName


data class MyApiData (

  @SerializedName("response_code" ) var responseCode : Int?               = null,
  @SerializedName("results"       ) var results      : ArrayList<Results> = arrayListOf()

)