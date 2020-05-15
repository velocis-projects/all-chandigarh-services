package org.egov.filters.post;

import com.netflix.util.Pair;
import com.netflix.zuul.context.RequestContext;
import org.junit.Before;
import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;

import java.util.List;

import static org.junit.Assert.*;

public class ResponseEnhancementFilterTest {

    private ResponseEnhancementFilter filter;

    @Before
    public void before() {
        filter = new ResponseEnhancementFilter();
        RequestContext.getCurrentContext().clear();
    }

    @Test
    public void test_should_set_correlation_id_response_header() {
        RequestContext.getCurrentContext().set("CORRELATION_ID", "someCorrelationId");
        final MockHttpServletResponse response = new MockHttpServletResponse();
        response.setStatus(400);
        RequestContext.getCurrentContext().setResponse(response);
        final MockHttpServletRequest request = new MockHttpServletRequest();
        request.setRequestURI("http://host/api/v1");
        RequestContext.getCurrentContext().setRequest(request);

        filter.run();

        final List<Pair<String, String>> zuulResponseHeaders =
            RequestContext.getCurrentContext().getZuulResponseHeaders();

        assertEquals(1, zuulResponseHeaders.size());
        final Pair<String, String> stringPair = zuulResponseHeaders.get(0);
        assertEquals("x-correlation-id", stringPair.first());
        assertEquals("someCorrelationId", stringPair.second());
    }

    @Test
    public void test_should_always_execute_filter() {
        assertTrue( filter.shouldFilter());
    }

    @Test
    public void test_should_execute_as_last_post_filter() {
        assertEquals(0,  filter.filterOrder());
    }

}