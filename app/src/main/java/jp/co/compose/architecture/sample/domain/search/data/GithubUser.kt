package jp.co.compose.architecture.sample.domain.search.data

import android.net.Uri
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class GithubUser(
    val id: Int,
    val login: String,
    val name: String,
    @SerializedName("avatar_url")
    var avatarUrl: String
) {
    fun toJson(): String {
        this.avatarUrl = Uri.encode(this.avatarUrl)
        return Gson().toJson(this)
    }

    companion object {
        fun String.fromJson(): GithubUser {
            return Gson().fromJson(this, GithubUser::class.java)
                .apply {
                    avatarUrl = Uri.decode(avatarUrl)
                }
        }
    }
}
