package csku.atm.controller.service;


import csku.atm.model.BankAccount;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Arrays;
import java.util.List;

@Service
public class BankAccountService {

    private RestTemplate restTemplate;


//    public List<BankAccount> getBankAccount (){
//        return new ArrayList<>(this.bankAccountsList);
//    }


    public BankAccountService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<BankAccount> getCustomerBankAccount(int customerId){
        //connect to BankAccount API service
        String url = "http://localhost:8091/api/bankaccount/customer/"+ customerId;

        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url,BankAccount[].class);


        BankAccount[] accounts = response.getBody();
        return Arrays.asList(accounts);

    }

    public List<BankAccount> getBankAccounts(){
        String url = "http://localhost:8091/api/bankaccount/";
        ResponseEntity<BankAccount[]> response =
                restTemplate.getForEntity(url,BankAccount[].class);

        BankAccount[] accounts = response.getBody();

        return Arrays.asList(accounts);

    }

    public void openBankAccount(BankAccount bankAccount){
        String url = "http://localhost:8091/api/bankaccount";
        restTemplate.postForEntity(url,bankAccount, BankAccount.class);

    }

    public void editBankAccount(BankAccount bankAccount){
        String url = "http://localhost:8091/api/bankaccount/"+
                bankAccount.getId();
        restTemplate.put(url,bankAccount);
    }

    public void deleteBankAccount(BankAccount bankAccount){
        String url = "http://localhost:8091/api/bankaccount/"+
                bankAccount.getId();
        restTemplate.delete(url,bankAccount);
    }

    public BankAccount getBankAccount(int id){
        String url = "http://localhost:8091/api/bankaccount/"+id;
        ResponseEntity<BankAccount> response =
                restTemplate.getForEntity(url,BankAccount.class);
        return response.getBody();
    }






}
