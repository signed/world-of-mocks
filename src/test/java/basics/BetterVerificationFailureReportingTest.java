package basics;

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
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import annotations.UnderTest;
import business.Result;

/**
 * example derived from http://stackoverflow.com/a/8632734
 */
@RunWith(Enclosed.class)
public class BetterVerificationFailureReportingTest {

    public interface Interface {
        void method(String one, String two, Result result);
    }

    public static class EasyMock {

        @Test
        public void bunchOfArgumentMatchers() throws Exception {
            Interface mock = createNiceMock(Interface.class);
            Capture<Result> result = newCapture();
            mock.method(eq("1"), eq("2"), capture(result));
            replay(mock);

            callBusinessLogicOn(mock);

            assertThat(result.getValue().message, is("the result"));
            verify(mock);
        }
    }

    public static class Mockito {

        @Test
        public void bunchOfArgumentMatchers() throws Exception {
            Interface mock = org.mockito.Mockito.mock(Interface.class);

            callBusinessLogicOn(mock);

            ArgumentCaptor<Result> result = ArgumentCaptor.forClass(Result.class);
            org.mockito.Mockito.verify(mock).method(org.mockito.Mockito.eq("1"), org.mockito.Mockito.eq("2"), result.capture());

            assertThat(result.getValue().message, is("the result"));
        }

    }

    @UnderTest
    private static void callBusinessLogicOn(Interface mock) {
        mock.method("1", "2", new Result("the result"));
    }
}
