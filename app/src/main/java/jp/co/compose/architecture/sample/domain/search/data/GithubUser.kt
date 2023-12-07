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
    fun toJson(): String =
        Gson().toJson(this.encodeAvatarUrl())

    private fun encodeAvatarUrl(): GithubUser =
        copy().apply {
            avatarUrl = Uri.encode(avatarUrl)
        }

    companion object {
        fun String.fromJson(): GithubUser =
            Gson().fromJson(this, GithubUser::class.java)
                .apply {
                    avatarUrl = Uri.decode(avatarUrl)
                }
    }
}
