package codingon.codingonspringboot.service;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class SomeBusinessImplStubTest {


    @Test
    void findTheGreatestAllData_basicScenario() {
        DataServiceStub dataServiceStub = new DataServiceStub();
        SomeBusinessImpl business = new SomeBusinessImpl(dataServiceStub);
        int result = business.findTheGreatestFromAllData();
        assertEquals(25, result);
    }
}

// 이렇게 Stub을 이용할 경우 경우의 수마다 계속 stub을 만들어 줘야 한다.
class DataServiceStub implements DataService {

    @Override
    public int[] retrieveAllData() {
        return new int[]{25, 15, 5};
    }
}