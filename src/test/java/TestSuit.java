import org.junit.experimental.categories.Categories;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import tags.SmockTest;

//@RunWith(Suite.class)
@RunWith(Categories.class)
//只执行两个测试集中带有SmockTest标签的用例
@Categories.IncludeCategory(SmockTest.class)
@Suite.SuiteClasses({
        TestPageObject.class,
        TestPageObjectDataDriver.class
})
public class TestSuit {

}
