package basics;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.replay;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static support.ResultMatcher.message;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import business.Result;

@RunWith(Enclosed.class)
public class ArgumentMatchersTest {

    private interface MethodWithTwoParameters {
        void callMethod(String first, String second);

        void callMethod(Result result);
    }

    public static class EasyMock {

        @Test
        public void matcherForPrimitiveTypes() throws Exception {
            MethodWithTwoParameters mock = createNiceMock(MethodWithTwoParameters.class);
            mock.callMethod(org.easymock.EasyMock.anyString(), org.easymock.EasyMock.startsWith("Hello"));
            replay(mock);

            mock.callMethod("any string", "Hello world");

            org.easymock.EasyMock.verify(mock);
        }
    }

    /**
     * Custom argument matchers use hamcrest matchers, so you can use your existing hamcrest matchers.
     */
    public static class Mockito {
        private final MethodWithTwoParameters mock = mock(MethodWithTwoParameters.class);

        @Test
        public void matcherForPrimitiveTypes() throws Exception {
            mock.callMethod("any string", "Hello World");

            verify(mock).callMethod(anyString(), startsWith("Hello"));
        }

        @Test
        public void matcherForCustomTypes() throws Exception {
            mock.callMethod(new Result("Hello World"));

            verify(mock).callMethod(argThat(message(org.hamcrest.CoreMatchers.startsWith("Hello"))));
        }
    }
}
