package basics;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

@RunWith(Enclosed.class)
public class StubbingMethodsTest {

    public static class EasyMock{

        @SuppressWarnings("unchecked")
        private final List<String> list = createNiceMock(List.class);

        @Test
        public void configureAMockToReturnAValue() throws Exception {
            expect(list.get(0)).andReturn("the prepared value");
            replay(list);

            assertThat(list.get(0), is("the prepared value"));
            verify(list);
        }

    }

    /**
     * Verification of stubbed methods is optional because usually it's
     * more important to test if the stubbed value is used correctly
     * rather than where's it come from.
     */
    public static class Mockito {

        @SuppressWarnings("unchecked")
        private final List<String> list = mock(List.class);

        @Test
        public void configureAMockToReturnAValue() throws Exception {
            when(list.get(0)).thenReturn("the prepared value");

            assertThat(list.get(0), is("the prepared value"));
        }
    }

}
