package sample._2;

import org.hamcrest.CoreMatchers;
import org.junit.Ignore;
import org.junit.Test;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.util.List;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;

public class DataRetentionTest {

    @Ignore
    private static class Sample {
        public List<String> sample() {
            return null;
        }
    }

    private final Class<?> type = mock(Sample.class).getClass();

    @Test
    public void retainTypeAnnotations() throws Exception {
        assertThat("should be present on mocked class", type.isAnnotationPresent(Ignore.class));
    }

    @Test
    public void retainMethodAnnotations() throws Exception {
        Method sample = type.getDeclaredMethod("sample");
        assertThat("should be present on mocked method", sample.isAnnotationPresent(Ignore.class));
    }

    @Test
    public void retainTypeInformation() throws Exception {
        assertThat(type.getDeclaredMethod("sample").getGenericReturnType(), instanceOf(ParameterizedType.class));
    }
}
