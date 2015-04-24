package basic;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.replay;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.argThat;
import static org.mockito.Matchers.startsWith;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static support.ResultMatcher.message;

import org.junit.Test;
import org.junit.runner.RunWith;

import annotations.UnderTest;
import business.Result;
import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class ArgumentMatchersTest {

    private interface MethodWithTwoParameters {
        void callMethod(String first, String second);

        void callMethod(Result result);
    }

    public class WithEasyMock {
        private final MethodWithTwoParameters mock = createNiceMock(MethodWithTwoParameters.class);

        @Test
        public void matcherForPrimitiveTypes() throws Exception {
            mock.callMethod(org.easymock.EasyMock.anyString(), org.easymock.EasyMock.startsWith("Hello"));
            replay(mock);

            callMethodWithPrimitivesOn(mock);

            org.easymock.EasyMock.verify(mock);
        }
    }

    /**
     * Custom argument matchers use hamcrest matchers, so you can use your existing hamcrest matchers.
     */
    public class WithMockito {
        private final MethodWithTwoParameters mock = mock(MethodWithTwoParameters.class);

        @Test
        public void matcherForPrimitiveTypes() throws Exception {
            callMethodWithPrimitivesOn(mock);

            verify(this.mock).callMethod(anyString(), startsWith("Hello"));
        }

        @Test
        public void matcherForCustomTypes() throws Exception {
            mock.callMethod(new Result("Hello World"));

            verify(mock).callMethod(argThat(message(org.hamcrest.CoreMatchers.startsWith("Hello"))));
        }
    }

    @UnderTest
    private static void callMethodWithPrimitivesOn(MethodWithTwoParameters mock) {
        mock.callMethod("any string", "Hello World");
    }
}
