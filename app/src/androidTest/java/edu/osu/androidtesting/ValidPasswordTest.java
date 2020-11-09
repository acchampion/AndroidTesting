package edu.osu.androidtesting;


import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import androidx.test.espresso.ViewInteraction;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ValidPasswordTest {

	@Rule
	public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

	@Test
	public void validPasswordTest() {
		ViewInteraction appCompatEditText = onView(
				allOf(withId(R.id.password_text),
						childAtPosition(
								childAtPosition(
										withId(R.id.fragment_container),
										0),
								6),
						isDisplayed()));
		appCompatEditText.perform(replaceText("ABC123abc"), closeSoftKeyboard());

		ViewInteraction appCompatButton = onView(
				allOf(withId(R.id.verify_password_button), withText("Verify Password"),
						childAtPosition(
								childAtPosition(
										withId(R.id.fragment_container),
										0),
								7),
						isDisplayed()));
		appCompatButton.perform(click());

		ViewInteraction textView = onView(
				allOf(withId(R.id.password_valid), withText("Valid"),
						withParent(withParent(withId(R.id.fragment_container))),
						isDisplayed()));
		textView.check(matches(withText("Valid")));
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
