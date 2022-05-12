package api.dto

import com.google.gson.annotations.SerializedName
import java.util.*

data class Resource(
    var id: Int?,
    var name: String?,
    var year: Date?,
    var color: String?,

    @SerializedName("pantone_value")
    var pantoneValues: String?
)