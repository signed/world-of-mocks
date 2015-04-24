package basic;

import static org.easymock.EasyMock.createNiceMock;
import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;

import annotations.UnderTest;
import de.bechte.junit.runners.context.HierarchicalContextRunner;

@RunWith(HierarchicalContextRunner.class)
public class StubbingMethodsTest {

    public class WithEasyMock {

        @SuppressWarnings("unchecked")
        private final List<String> list = createNiceMock(List.class);

        @Test
        public void configureAMockToReturnAValue() throws Exception {
            expect(list.get(0)).andReturn("the prepared value");
            replay(list);

            assertThat(firstElementIn(list), is("the prepared value"));
        }

    }

    /**
     * Verification of stubbed methods is optional because usually it's
     * more important to test if the stubbed value is used correctly
     * rather than where it comes from.
     */
    public class WithMockito {

        @SuppressWarnings("unchecked")
        private final List<String> list = mock(List.class);

        @Test
        public void configureAMockToReturnAValue() throws Exception {
            when(list.get(0)).thenReturn("the prepared value");

            assertThat(firstElementIn(list), is("the prepared value"));
        }
    }

    @UnderTest
    private static String firstElementIn(List<String> list) {
        return list.get(0);
    }

}
