package basic;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.eq;
import static org.easymock.EasyMock.newCapture;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.easymock.Capture;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import annotations.UnderTest;
import business.Result;
import de.bechte.junit.runners.context.HierarchicalContextRunner;

/**
 * example derived from http://stackoverflow.com/a/8632734
 */
@RunWith(HierarchicalContextRunner.class)
public class BetterVerificationFailureReportingTest {

    public interface Interface {
        void method(String one, String two, Result result);
    }

    public class WithEasyMock {

        private final Interface mock = createNiceMock(Interface.class);

        @Test
        public void bunchOfArgumentMatchers() throws Exception {
            Capture<Result> result = newCapture();
            mock.method(eq("1"), eq("2"), capture(result));
            replay(mock);

            callBusinessLogicOn(mock);

            assertThat(result.getValue().message, is("the result"));
            verify(mock);
        }
    }

    public class WithMockito {
        private final Interface mock = Mockito.mock(Interface.class);

        @Test
        public void bunchOfArgumentMatchers() throws Exception {
            callBusinessLogicOn(mock);

            ArgumentCaptor<Result> result = ArgumentCaptor.forClass(Result.class);
            Mockito.verify(mock).method(Mockito.eq("1"), Mockito.eq("2"), result.capture());

            assertThat(result.getValue().message, is("the result"));
        }
    }

    @UnderTest
    private static void callBusinessLogicOn(Interface mock) {
        mock.method("1", "2", new Result("the result"));
    }
}
