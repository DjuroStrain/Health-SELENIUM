package hr.vuv.health.testcases;

import hr.vuv.health.testcases.prijava.PrijavaTests;
import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


@RunWith(Categories.class)
@Categories.IncludeCategory(SmokeTest.class)
@Suite.SuiteClasses({PrijavaTests.class})
public class StartSmokeTestsSuite {
}
