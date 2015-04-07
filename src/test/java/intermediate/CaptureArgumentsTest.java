package intermediate;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import java.util.function.Function;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;

import business.BusinessLogic;
import business.Result;

@RunWith(Enclosed.class)
public class CaptureArgumentsTest {

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

}
