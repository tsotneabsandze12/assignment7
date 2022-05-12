package api.dto
import java.util.*

data class User(
    var id: Int?,

    var name: String?,

    var job: String?,

    var createdAt: Date?
)