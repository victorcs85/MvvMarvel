import br.com.android.victorcs.mvvmarvel.data.entity.Data
import com.google.gson.annotations.SerializedName

data class BaseApiResponse(
    val attributionHTML: String,
    val attributionText: String,
    val code: Int,
    val copyright: String,
    @SerializedName("data")
    val dataResponse: Data,
    val etag: String,
    val status: String
)