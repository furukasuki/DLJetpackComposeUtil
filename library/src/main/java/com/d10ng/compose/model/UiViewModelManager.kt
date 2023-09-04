package com.d10ng.compose.model

import android.app.Activity
import android.app.Application
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import com.d10ng.compose.ui.base.ToastPosition

/**
 * UI ViewModel 管理器
 * @Author d10ng
 * @Date 2023/9/4 10:24
 */
object UiViewModelManager : IUiViewModel {

    private lateinit var application: Application

    // Activity对应的ViewModel
    private val modelMap = mutableMapOf<ComponentActivity, UiViewModel>()

    // 最顶部展示的Activity
    private var topActivity: ComponentActivity? = null

    internal fun init(app: Application) {
        application = app.apply {
            registerActivityLifecycleCallbacks(object : Application.ActivityLifecycleCallbacks {
                override fun onActivityCreated(p0: Activity, p1: Bundle?) {
                    if (p0 !is ComponentActivity) return
                    val vm by p0.viewModels<UiViewModel>()
                    modelMap[p0] = vm
                    topActivity = p0
                }

                override fun onActivityStarted(p0: Activity) {}

                override fun onActivityResumed(p0: Activity) {
                    if (p0 !is ComponentActivity) return
                    topActivity = p0
                }

                override fun onActivityPaused(p0: Activity) {}

                override fun onActivityStopped(p0: Activity) {}

                override fun onActivitySaveInstanceState(p0: Activity, p1: Bundle) {}

                override fun onActivityDestroyed(p0: Activity) {
                    if (p0 !is ComponentActivity) return
                    modelMap.remove(p0)
                    if (topActivity == p0) topActivity = null
                }
            })
        }
    }

    @Composable
    fun Init(act: ComponentActivity) {
        modelMap[act]!!.Init()
    }

    private fun getTopVM() = topActivity?.let { modelMap[it] }

    override fun showToast(msg: String, duration: Long, position: ToastPosition) {
        getTopVM()?.showToast(msg, duration, position)
    }

    override fun showSuccessToast(msg: String, duration: Long) {
        getTopVM()?.showSuccessToast(msg, duration)
    }

    override fun showFailToast(msg: String, duration: Long) {
        getTopVM()?.showFailToast(msg, duration)
    }

    override fun showLoading(text: String) {
        getTopVM()?.showLoading(text)
    }

    override fun hideLoading() {
        getTopVM()?.hideLoading()
    }
}