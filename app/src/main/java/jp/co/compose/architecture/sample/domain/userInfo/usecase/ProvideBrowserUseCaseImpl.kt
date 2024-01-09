package jp.co.compose.architecture.sample.domain.userInfo.usecase

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent
import androidx.browser.customtabs.CustomTabsService.ACTION_CUSTOM_TABS_CONNECTION
import java.lang.IllegalStateException
import javax.inject.Inject

interface ProvideBrowserUseCase {

    fun launch(activity: Activity, url: String)
}

class ProvideBrowserUseCaseImpl @Inject constructor() : ProvideBrowserUseCase {

    private val customTabsIntent: CustomTabsIntent = CustomTabsIntent.Builder().build()

    override fun launch(activity: Activity, url: String) {
        val customTabsPackages = getCustomTabsPackages(activity)
        if (!containsChromePackage(customTabsPackages)) {
            throw IllegalStateException("Not Installed Google Chrome.")
        }
        customTabsIntent.intent.apply {
            setPackage(CHROME_PACKAGE_NAME)
        }
        customTabsIntent.launchUrl(activity, Uri.parse(url))
    }

    private fun getCustomTabsPackages(context: Context): List<ResolveInfo> {
        val packageManager = context.packageManager

        val activityIntent = Intent()
            .setAction(Intent.ACTION_VIEW)
            .addCategory(Intent.CATEGORY_BROWSABLE)
            .setData(Uri.fromParts(SCHEME, "", null))

        val resolvedActivityList = packageManager.queryIntentActivities(
            activityIntent,
            PackageManager.MATCH_ALL
        )

        return resolvedActivityList.mapNotNull { info ->
            val serviceIntent = Intent()
            serviceIntent.action = ACTION_CUSTOM_TABS_CONNECTION
            serviceIntent.setPackage(info.activityInfo.packageName)
            if (packageManager.resolveService(serviceIntent, 0) != null) {
                return@mapNotNull info
            }
            return@mapNotNull null
        }.toList()
    }

    private fun containsChromePackage(customTabsPackages: List<ResolveInfo>): Boolean {
        val chromePackage = customTabsPackages.find {
            it.activityInfo.packageName == CHROME_PACKAGE_NAME
        }
        return chromePackage != null
    }

    companion object {
        private const val SCHEME = "https"
        private const val CHROME_PACKAGE_NAME = "com.android.chrome"
    }
}
