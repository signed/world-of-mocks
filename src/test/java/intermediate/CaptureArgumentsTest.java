package intermediate;

import static org.easymock.EasyMock.capture;
import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.newCapture;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.function.Function;

import org.easymock.Capture;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import annotations.UnderTest;
import business.Result;

@RunWith(Enclosed.class)
public class CaptureArgumentsTest {


    public static class EasyMock {

        @SuppressWarnings("unchecked")
        private final Function<Result, Void> callback = createNiceMock(Function.class);
        private final BusinessLogic logic = new BusinessLogic();

        @Test
        public void captureArgumentPassedToAMock() throws Exception {
            Capture<Result> resultCapture = newCapture();
            callback.apply(capture(resultCapture));
            org.easymock.EasyMock.expectLastCall().andStubReturn(null);
            replay(callback);

            logic.onSuccess(callback);
            logic.runLogic();

            org.easymock.EasyMock.verify(callback);

            assertThat(resultCapture.getValue().message, is("Everything was fine"));
        }
    }

    public static class Mockito {

        @SuppressWarnings("unchecked")
        private final Function<Result, Void> callback = mock(Function.class);
        private final BusinessLogic logic = new BusinessLogic();

        @Test
        public void captureArgumentPassedToAMock() throws Exception {
            logic.onSuccess(callback);
            logic.runLogic();

            ArgumentCaptor<Result> captor = ArgumentCaptor.forClass(Result.class);
            verify(callback).apply(captor.capture());

            assertThat(captor.getValue().message, is("Everything was fine"));
        }
    }

    @UnderTest
    public static class BusinessLogic {
        private Function<Result, Void> resultCallback;

        public void onSuccess(Function<Result, Void> resultCallback) {
            this.resultCallback = resultCallback;
        }

        public void runLogic() {
            resultCallback.apply(new Result("Everything was fine"));
        }
    }
}
