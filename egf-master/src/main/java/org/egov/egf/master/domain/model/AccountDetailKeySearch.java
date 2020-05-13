package org.egov.egf.master.domain.model ;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class AccountDetailKeySearch extends AccountDetailKey{ private String ids; 
private String  sortBy; 
private Integer pageSize; 
private Integer offset; 
} 