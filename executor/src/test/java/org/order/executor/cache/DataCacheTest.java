package org.order.executor.cache;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.jayway.jsonpath.JsonPath;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.order.common.enums.ResultTypeEnum;
import org.order.executor.cache.data.DataCache;

import java.util.concurrent.locks.ReentrantLock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DataCacheTest {


    /**
     * DataCacheTest.testJsonPathParse
     * 1 times,  0 ms
     * 10 times,  1 ms
     * 100 times,  7 ms
     * 1000 times,  19 ms
     * 10000 times,  58 ms
     * 100000 times,  256 ms
     * 1000000 times,  1343 ms
     *
     * @throws JsonProcessingException
     */
    @Test
    public void testJsonPathParse100000() throws JsonProcessingException {

        String json = "{ \"a\": \"v1\", \"b\": 1, \"aobj\": [ { \"k1\": \"v1\", \"k2\": 2, \"k3Obj\": { \"i\": 123, \"j\": \"xxxx\" } } ] }";
        JSONObject jsonObject = JSON.parseObject(json);


        int[] times = new int[]{1, 10, 100, 1000, 10000, 100000, 1000000, 10000000};
        System.out.println("DataCacheTest.testJsonPathParse");
        JsonPath.read(JSON.toJSONString(jsonObject), "$.aobj[0].k3Obj.i");
        for (int i : times) {
            jsonObject.put("b", i);
            long start = System.currentTimeMillis();
            for (int j = 0; j < i; j++) {
                Integer value = JsonPath.read(JSON.toJSONString(jsonObject), "$.aobj[0].k3Obj.i");
            }
            System.out.println(String.format("%d times,  %d ms", i, (System.currentTimeMillis() - start)));;
        }
    }


    private static final String TASK_ID = "taskId";
    private static final String DATA = "{ \"a\": \"v1\", \"b\": 1, \"aobj\": [ { \"k1\": \"v1\", \"k2\": 2, \"k3Obj\": { \"i\": 123, \"j\": \"xxxx\" } } ] }";

    @InjectMocks
    private DataCache dataCache;

    @Mock
    private ReentrantLock lock;

    @BeforeEach
    void setUp() {
        dataCache = new DataCache(TASK_ID, DATA);
    }

    @Test
    public void read_ValidPath_IntegerType_ReturnsInteger() {
        Integer result = dataCache.read("$.aobj[0].k2", ResultTypeEnum.INTEGER);
        assertNotNull(result);
        assertEquals(2, result.intValue());
    }

    @Test
    public void read_ValidPath_StringType_ReturnsString() {
        String result = dataCache.read("$.aobj[0].k1", ResultTypeEnum.STRING);
        assertNotNull(result);
        assertEquals("v1", result);
    }

    @Test
    public void read_ValidPath_NonMatchingType_ReturnsNull() {
        Integer result = dataCache.read("$.aobj[0].k1", ResultTypeEnum.INTEGER);
        assertNull(result);
    }

    @Test
    public void read_InvalidPath_ReturnsNull() {
        Integer result = dataCache.read("$.aobj[0].nonExistent", ResultTypeEnum.INTEGER);
        assertNull(result);
    }

    @Test
    public void read_NullValuePath_ReturnsNull() {
        Integer result = dataCache.read("$.nonExistent", ResultTypeEnum.INTEGER);
        assertNull(result);
    }

    @Test
    public void read_LockUnlocksProperly_WhenPathExists() {
        assertDoesNotThrow(() -> dataCache.read("$.aobj[0].k2", ResultTypeEnum.INTEGER));
        verify(lock, times(1)).lock();
        verify(lock, times(1)).unlock();
    }

    @Test
    public void read_LockUnlocksProperly_WhenPathNotFound() {
        assertDoesNotThrow(() -> dataCache.read("$.aobj[0].nonExistent", ResultTypeEnum.INTEGER));
        verify(lock, times(1)).lock();
        verify(lock, times(1)).unlock();
    }

}