package com.eazybytes.accounts.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.eazybytes.accounts.dto.AccountsDto;
import com.eazybytes.accounts.dto.CardsDto;
import com.eazybytes.accounts.dto.CustomerDetailsDto;
import com.eazybytes.accounts.dto.LoansDto;
import com.eazybytes.accounts.entity.Accounts;
import com.eazybytes.accounts.entity.Customer;
import com.eazybytes.accounts.exception.ResourceNotFoundException;
import com.eazybytes.accounts.mapper.AccountsMapper;
import com.eazybytes.accounts.mapper.CustomerMapper;
import com.eazybytes.accounts.repository.AccountsRepository;
import com.eazybytes.accounts.repository.CustomerRepository;
import com.eazybytes.accounts.service.ICustomerService;
import com.eazybytes.accounts.service.client.CardsFeignClient;
import com.eazybytes.accounts.service.client.LoansFeignClient;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements ICustomerService {
	
	private AccountsRepository accountsRepository;
	private CustomerRepository customerRepository;
	private CardsFeignClient cardsFeignClient;
	private LoansFeignClient loansFeignClient;
	

	@Override
	public CustomerDetailsDto fetchCustomerDetails(String mobileNumber,String traceId) {
		
		Customer customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new ResourceNotFoundException("Customer", "mobileNumber", mobileNumber)
        );
        Accounts accounts = accountsRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new ResourceNotFoundException("Account", "customerId", customer.getCustomerId().toString())
        );
        
        CustomerDetailsDto mapToCustomerDetailsDto = CustomerMapper.mapToCustomerDetailsDto(customer, new CustomerDetailsDto());
        mapToCustomerDetailsDto.setAccountsDto(AccountsMapper.mapToAccountsDto(accounts, new AccountsDto()));
        
        ResponseEntity<LoansDto> fetchLoanDetails = loansFeignClient.fetchLoanDetails(traceId,mobileNumber);
        
        if(null!=fetchLoanDetails) {
        	mapToCustomerDetailsDto.setLoansDto(fetchLoanDetails.getBody());
        }
        
        ResponseEntity<CardsDto> fetchCardsDetails = cardsFeignClient.fetchCardDetails(traceId,mobileNumber);
        if(fetchCardsDetails!=null) {
        	mapToCustomerDetailsDto.setCardsDto(fetchCardsDetails.getBody());
        }
        
        return mapToCustomerDetailsDto;
		
	}

}
