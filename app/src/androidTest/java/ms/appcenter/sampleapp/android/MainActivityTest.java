package ms.appcenter.sampleapp.android;


import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import static androidx.test.InstrumentationRegistry.getInstrumentation;
import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.Espresso.openActionBarOverflowOrOptionsMenu;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;

import ms.appcenter.sampleapp.android.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.hamcrest.core.IsInstanceOf;
import org.junit.Rule;
import org.junit.After;
import com.microsoft.appcenter.espresso.Factory;
import com.microsoft.appcenter.espresso.ReportHelper;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anything;
import static org.hamcrest.Matchers.is;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Rule
	public ReportHelper reportHelper = Factory.getReportHelper();

	@After
	public void TearDown(){
	    reportHelper.label("Stopping App");
	}

    @Test
    public void mainActivityTest() {
        ViewInteraction textView = onView(
                allOf(withText("Build"),
                        childAtPosition(
                                allOf(withId(R.id.pager_title_strip),
                                        withParent(withId(R.id.container))),
                                2),
                        isDisplayed()));
        textView.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withText("Test"),
                        childAtPosition(
                                allOf(withId(R.id.pager_title_strip),
                                        withParent(withId(R.id.container))),
                                2),
                        isDisplayed()));
        textView2.perform(click());

        ViewInteraction textView3 = onView(
                allOf(withText("Distribute"),
                        childAtPosition(
                                allOf(withId(R.id.pager_title_strip),
                                        withParent(withId(R.id.container))),
                                2),
                        isDisplayed()));
        textView3.perform(click());

        ViewInteraction textView4 = onView(
                allOf(withText("Crashes"),
                        childAtPosition(
                                allOf(withId(R.id.pager_title_strip),
                                        withParent(withId(R.id.container))),
                                2),
                        isDisplayed()));
        textView4.perform(click());

        ViewInteraction textView5 = onView(
                allOf(withText("Analytics"),
                        childAtPosition(
                                allOf(withId(R.id.pager_title_strip),
                                        withParent(withId(R.id.container))),
                                2),
                        isDisplayed()));
        textView5.perform(click());

        ViewInteraction textView6 = onView(
                allOf(withText("Push"),
                        childAtPosition(
                                allOf(withId(R.id.pager_title_strip),
                                        withParent(withId(R.id.container))),
                                2),
                        isDisplayed()));
        textView6.perform(click());
    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}
