package org.egov.pt.calculator.web.controller;

import java.util.Map;

import javax.validation.Valid;

import org.egov.pt.calculator.service.DemandService;
import org.egov.pt.calculator.service.EstimationService;
import org.egov.pt.calculator.service.PayService;
import org.egov.pt.calculator.web.models.Calculation;
import org.egov.pt.calculator.web.models.CalculationReq;
import org.egov.pt.calculator.web.models.CalculationRes;
import org.egov.pt.calculator.web.models.GetBillCriteria;
import org.egov.pt.calculator.web.models.demand.BillResponse;
import org.egov.pt.calculator.web.models.demand.DemandResponse;
import org.egov.pt.calculator.web.models.property.RequestInfoWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/propertytax")
public class CalculatorController {

	@Autowired
	private DemandService demandService;

	@Autowired
	private EstimationService calculatorService;
	
	@Autowired
	private PayService payService;

	@PostMapping("/_estimate")
	public ResponseEntity<CalculationRes> getTaxEstimation(@RequestBody @Valid CalculationReq calculationReq) {
		return new ResponseEntity<>(calculatorService.getTaxCalculation(calculationReq), HttpStatus.OK);
	}

	@PostMapping("/_calculate")
	public ResponseEntity<Map<String, Calculation>> generateDemands(@RequestBody @Valid CalculationReq calculationReq) {
		return new ResponseEntity<>(demandService.generateDemands(calculationReq), HttpStatus.OK);
	}
	
	@PostMapping("/_getbill")
	public ResponseEntity<BillResponse> getBill(@RequestBody @Valid RequestInfoWrapper requestInfoWrapper,
			@ModelAttribute @Valid GetBillCriteria getBillCriteria) {
		return new ResponseEntity<>(demandService.getBill(getBillCriteria, requestInfoWrapper), HttpStatus.OK);
	}

	@PostMapping("/_updatedemand")
	public ResponseEntity<DemandResponse> updateDemand(@RequestBody @Valid RequestInfoWrapper requestInfoWrapper,
			@ModelAttribute @Valid GetBillCriteria getBillCriteria) {
		return new ResponseEntity<>(demandService.updateDemands(getBillCriteria, requestInfoWrapper), HttpStatus.OK);
	}

}
