package sample._2;

import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.VerificationCollector;

import java.util.List;

import static org.mockito.Mockito.verify;

public class VerificationCollectorTest {

    //@Rule
    //public VerificationCollector collector = MockitoJUnit.collector();

    @SuppressWarnings("unchecked")
    private List<String> mock = Mockito.mock(List.class);

    @Test
    public void report_on_all_failed_method_verifications() throws Exception {
        mock.size();

        verify(mock).stream();
        verify(mock).size();
        verify(mock).clear();
    }

}
