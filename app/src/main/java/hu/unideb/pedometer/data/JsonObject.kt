package hu.unideb.pedometer.data

data class JsonObject(
    val userId: Int,
    val id: Long,
    val title: String
) {
    override fun toString() = "userID: $userId \nID: $id \ntitle: $title"
}