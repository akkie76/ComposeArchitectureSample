package jp.co.compose.architecture.sample.domain.search.data

import android.net.Uri
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

data class GithubUser(
    val id: Int,
    val login: String,
    val name: String = "undefined",
    @SerializedName("avatar_url")
    var avatarUrl: String,
    @SerializedName("followers_url")
    var followersUrl: String,
    @SerializedName("following_url")
    var followingUrl: String
) {
    fun toJson(): String {
        this.avatarUrl = Uri.encode(this.avatarUrl)
        this.followersUrl = Uri.encode(this.followersUrl)
        this.followingUrl = Uri.encode(this.followingUrl)
        return Gson().toJson(this)
    }

    companion object {
        fun String.fromJson(): GithubUser {
            return Gson().fromJson(this, GithubUser::class.java)
                .apply {
                    avatarUrl = Uri.decode(avatarUrl)
                    followersUrl = Uri.decode(followersUrl)
                    followingUrl = Uri.decode(followingUrl)
                }
        }
    }
}
