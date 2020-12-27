package com.challenge.pulsus

import android.content.Context
import android.content.Intent

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import com.challenge.pulsus.adapters.ListAdapter
import org.hamcrest.MatcherAssert
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @get:Rule
    val rule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    private val _PACKAGE = "com.challenge.pulsus"
    private val LAUNCH_TIMEOUT = 5000
    private val STRING_TO_BE_TYPED = "UiAutomator"
    private var device: UiDevice? = null

    @Before
    fun startMainActivityFromHomeScreen() {
        device =
            UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())
        device?.pressHome()
        val launcherPackage = device?.getLauncherPackageName()
        MatcherAssert.assertThat(launcherPackage, Matchers.notNullValue())
        device?.wait(
            Until.hasObject(By.pkg(launcherPackage).depth(0)),
            LAUNCH_TIMEOUT.toLong()
        )
        val context =
            ApplicationProvider.getApplicationContext<Context>()
        val intent = context.packageManager
            .getLaunchIntentForPackage(_PACKAGE)
        intent!!.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)
        device?.wait(
            Until.hasObject(
                By.pkg(_PACKAGE).depth(0)
            ), LAUNCH_TIMEOUT.toLong()
        )
    }

    @Test
    fun checkLoadListWithSucess(){
        var isAvailable =
            Until.findObject(By.text("animal"))

        device?.wait(isAvailable, 2000)

        Espresso.onView(ViewMatchers.withId(R.id.categoryList)).perform(
            RecyclerViewActions.scrollTo<RecyclerView.ViewHolder>(
                ViewMatchers.hasDescendant(ViewMatchers.withText("sport"))))
            .perform(ViewActions.click())

        isAvailable =
            Until.findObject(By.text("URL"))
        device?.wait(isAvailable, 2000)
        device?.pressBack()
    }


}