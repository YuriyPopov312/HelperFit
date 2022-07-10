//package com.example.vkr;
//import androidx.test.espresso.action.ViewActions;
//import androidx.test.espresso.matcher.ViewMatchers;
//import androidx.test.ext.junit.runners.AndroidJUnit4;
//import androidx.test.rule.ActivityTestRule;
//import static androidx.test.espresso.Espresso.onView;
//import static androidx.test.espresso.action.ViewActions.click;
//import static androidx.test.espresso.action.ViewActions.typeText;
//import static androidx.test.espresso.matcher.ViewMatchers.withId;
//import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
//import static androidx.test.espresso.assertion.ViewAssertions.matches;
//
//import com.example.vkr.Activity.AuthenticationActivity;
//import com.example.vkr.R;
//
//import org.junit.Rule;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//
//@RunWith(AndroidJUnit4.class)
//
//public class RegressionTestEspresso {
//
//    @Rule
//    public ActivityTestRule<AuthenticationActivity> mActivityRule = new ActivityTestRule<AuthenticationActivity>(
//            AuthenticationActivity.class);
//
//
//    @Test
//    public void clickStartAvtorisation() throws Exception{
//
//        onView(withId(R.id.editTextLoginA)).perform(typeText("Admiral"));
//        onView(withId(R.id.editTextPasswordA)).perform(typeText("123456"));
//        onView(withId(R.id.btnSingIn)).perform(click());
//        onView(withId(R.id.allTrnings)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void clickStartAvtorisationAdmin() throws Exception{
//
//        onView(withId(R.id.editTextLoginA)).perform(typeText("admin"));
//        onView(withId(R.id.editTextPasswordA)).perform(typeText("admin"));
//        onView(withId(R.id.btnSingIn)).perform(click());
//        onView(withId(R.id.linerlayout_fragmentadmin_client)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void clickStartRegistr() throws Exception{
//
//        onView(withId(R.id.btnRegister)).perform(click());
//        onView(withId(R.id.editTextNameR)).perform(typeText("Aristarh"));
//        onView(withId(R.id.editTextLoginR)).perform(typeText("LoginCli"));
//        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.editTextWeight)).perform(typeText("95"));
//        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.editTextHeight)).perform(typeText("2"));
//        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.editTextPasswordR)).perform(typeText("Passw!$"));
//        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.editSex)).perform(click());
//        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.btnRegister)).perform(click());
//        onView(withId(R.id.btnRegister)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void clickLogTrening() throws Exception{
//
//        onView(withId(R.id.editTextLoginA)).perform(typeText("Admiral"));
//        onView(withId(R.id.editTextPasswordA)).perform(typeText("123456"));
//        onView(withId(R.id.btnSingIn)).perform(click());
//        onView(withId(R.id.logTrening)).perform(click());
//        onView(withId(R.id.btn_addlogtrening)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void clickAddLogTrening() throws Exception{
//
//        onView(withId(R.id.editTextLoginA)).perform(typeText("Admiral"));
//        onView(withId(R.id.editTextPasswordA)).perform(typeText("123456"));
//        onView(withId(R.id.btnSingIn)).perform(click());
//        onView(withId(R.id.logTrening)).perform(click());
//        onView(withId(R.id.btn_addlogtrening)).perform(click());
//        onView(withId(R.id.edit_titletrening)).perform(typeText("123456"));
//        onView(withId(R.id.btn_plan)).perform(click());
//        onView(withId(R.id.btn_addlogtrening)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void clickAdminAllTrening() throws Exception{
//
//        onView(withId(R.id.editTextLoginA)).perform(typeText("admin"));
//        onView(withId(R.id.editTextPasswordA)).perform(typeText("admin"));
//        onView(withId(R.id.btnSingIn)).perform(click());
//        onView(withId(R.id.btn_worktotrening)).perform(click());
//        onView(withId(R.id.btn_addtrening)).check(matches(isDisplayed()));
//    }
//
//    @Test
//    public void clickAdminAddTrening() throws Exception{
//
//        onView(withId(R.id.editTextLoginA)).perform(typeText("admin"));
//        onView(withId(R.id.editTextPasswordA)).perform(typeText("admin"));
//        onView(withId(R.id.btnSingIn)).perform(click());
//        onView(withId(R.id.btn_worktotrening)).perform(click());
//        onView(withId(R.id.btn_addtrening)).perform(click());
//        onView(withId(R.id.editTitle)).perform(typeText("Run to street"));
//        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.editTip)).perform(typeText("Running"));
//        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.editCountex)).perform(typeText("20"));
//        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.editLink)).perform(typeText("34df5g56g"));
//        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.editTime)).perform(typeText("20:15"));
//        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.editOpisanie)).perform(typeText("Run to street"));
//        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.editTrening)).perform(typeText("Test opisanie for regression testing"));
//        onView(ViewMatchers.isRoot()).perform(ViewActions.closeSoftKeyboard());
//        onView(withId(R.id.btnAdd)).perform(click());
//        onView(withId(R.id.btn_addtrening)).check(matches(isDisplayed()));
//    }
//}