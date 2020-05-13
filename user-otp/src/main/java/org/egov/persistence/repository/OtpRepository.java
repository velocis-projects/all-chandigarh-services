package org.egov.persistence.repository;

import org.egov.domain.exception.OtpNumberNotPresentException;
import org.egov.domain.model.OtpRequest;
import org.egov.persistence.contract.OtpResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class OtpRepository {

	private final String otpCreateUrl;
	private RestTemplate restTemplate;

	public OtpRepository(RestTemplate restTemplate, @Value("${otp.host}") String otpHost,
			@Value("${otp.create.url}") String otpCreateUrl) {
		this.restTemplate = restTemplate;
		this.otpCreateUrl = otpHost + otpCreateUrl;
	}

	public String fetchOtp(OtpRequest otpRequest) {
		final org.egov.persistence.contract.OtpRequest request = new org.egov.persistence.contract.OtpRequest(
				otpRequest);

		OtpResponse otpResponse = null;
		try {
			otpResponse = restTemplate.postForObject(otpCreateUrl, request, OtpResponse.class);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (isOtpNumberAbsent(otpResponse)) {
			throw new OtpNumberNotPresentException();
		}

		return otpResponse.getOtpNumber();
	}

	private boolean isOtpNumberAbsent(OtpResponse otpResponse) {
		return otpResponse == null || otpResponse.isOtpNumberAbsent();
	}
}
