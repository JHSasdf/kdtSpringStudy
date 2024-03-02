package codingon.codingonspringboot.service;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ListTest {
    @Test
    void simpleTest() {
        List listMock = mock(List.class);
//        listMock.size() => 3;
        when(listMock.size()).thenReturn(3);

        assertEquals(3, listMock.size());
    }

    @Test
    void multipleReturns() {
        List listMock = mock(List.class);
//        listMock.size() => 3;
        when(listMock.size()).thenReturn(3).thenReturn(2);

        assertEquals(3, listMock.size());
        assertEquals(2, listMock.size());
        // 마지막 return 이후에는 마지막 return이 계속 나옴.
        assertEquals(2, listMock.size());
    }

    @Test
    void specificParameters() {
        List listMock = mock(List.class);
//        listMock.size() => 3;
        when(listMock.get(0)).thenReturn("SomeString");

        assertEquals("SomeString", listMock.get(0));
        assertEquals(null, listMock.get(1));

    }

    @Test
    void genericParameters() {
        List listMock = mock(List.class);
//        listMock.size() => 3;
        when(listMock.get(Mockito.anyInt())).thenReturn("SomeOtherString");

        assertEquals("SomeOtherString", listMock.get(0));
        assertEquals("SomeOtherString", listMock.get(1));
        assertEquals("SomeOtherString", listMock.get(2));

    }
}
