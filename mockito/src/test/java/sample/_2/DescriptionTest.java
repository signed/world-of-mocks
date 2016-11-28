package sample._2;

import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DescriptionTest {

    @Test
    public void describe_what_just_went_wrong() throws Exception {
        Runnable runnable = mock(Runnable.class);

        verify(runnable /*, Mockito.description("kernel panic - this just ended the world")*/).run();
    }

}
