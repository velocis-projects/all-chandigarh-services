package org.egov.web.contract;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.egov.common.contract.response.ResponseInfo;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CacheBustResponse {
	private ResponseInfo responseInfo;
	@JsonProperty("isSuccessful")
	private boolean isSuccessful;
}
