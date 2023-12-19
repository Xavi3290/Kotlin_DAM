import com.google.gson.annotations.SerializedName

class Productes : ArrayList<ProductesItem>()

class ProductesItem(
    val codi: Int?,
    val descripcio: String,
    val descripciocompleta: String,
    val imatge: String,
    val preu: Double,
    val tipuscardview: Int,
    val tipusproducte: Int
)

data class ResponseModel(
    @SerializedName("missatge") var missatge : String? = null
)