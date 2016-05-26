package org.aksw.limes.core.measures.mapper.string;

import static org.junit.Assert.assertTrue;

import java.util.Map;
import java.util.Set;

import org.aksw.commons.util.StopWatch;
import org.aksw.limes.core.io.mapping.Mapping;
import org.aksw.limes.core.measures.mapper.MapperTest;
import org.aksw.limes.core.measures.mapper.MappingOperations;
import org.aksw.limes.core.measures.measure.string.JaroWinkler;
import org.junit.Test;

/**
 * @author Kevin Dreßler
 * @since 1.0
 */
public class JaroWinklerMapperTest extends MapperTest {

    @Test
    public void testGetMapping() {
        double theta = 0.92d;
        int sourceSize = 1000;
        int targetSize = 1000;
        JaroWinklerMapper jwm = new JaroWinklerMapper();
        Map<String, Set<String>> s = generateRandomMap(sourceSize);
        Map<String, Set<String>> t = generateRandomMap(targetSize);
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Mapping m1 = jwm.getMapping(s, t, theta);
        stopWatch.getElapsedTime();
        stopWatch.start();
        Mapping m2 = bruteForce(s, t, theta, new JaroWinkler());
        stopWatch.getElapsedTime();
        stopWatch.stop();
        assertTrue(MappingOperations.difference(m1, m2).size() == 0);
    }
}