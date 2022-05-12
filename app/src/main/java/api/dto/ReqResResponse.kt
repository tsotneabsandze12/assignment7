package api.dto

import com.google.gson.annotations.SerializedName

data class ReqResResponse<T>(

    val page: Int?,

    @SerializedName("per_page")
    val perPage: Int?,

    val total: Int?,

    @SerializedName("total_pages")
    val totalPages: Int?,

    val data: T?

)

