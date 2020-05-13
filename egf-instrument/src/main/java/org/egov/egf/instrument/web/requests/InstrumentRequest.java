package org.egov.egf.instrument.web.requests;

import java.util.ArrayList;
import java.util.List;

import org.egov.common.contract.request.RequestInfo;
import org.egov.egf.instrument.web.contract.InstrumentContract;

import lombok.Data;

public @Data class InstrumentRequest {
    private RequestInfo requestInfo = new RequestInfo();
    private List<InstrumentContract> instruments = new ArrayList<InstrumentContract>();
}