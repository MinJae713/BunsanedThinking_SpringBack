package com.example.bunsanedthinking_springback.model.service.authentication;

import com.example.bunsanedthinking_springback.dto.authentication.LoginResponse;
import com.example.bunsanedthinking_springback.entity.customer.Customer;
import com.example.bunsanedthinking_springback.entity.employee.Employee;
import com.example.bunsanedthinking_springback.entity.partnerCompany.PartnerCompany;
import com.example.bunsanedthinking_springback.global.exception.NotExistException;
import com.example.bunsanedthinking_springback.model.service.customer.CustomerService;
import com.example.bunsanedthinking_springback.model.service.employee.humanResource.HumanResourceService;
import com.example.bunsanedthinking_springback.model.service.partnerCompany.PartnerCompanyService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private HumanResourceService humanResourceService;
    @Autowired
    private PartnerCompanyService partnerCompanyService;
    @Getter
    public enum UserType {
        DUMY(-1),
        CUSTOMER(-1),
        LOAN_MANAGEMENT(9101),
        CONTRACT_MANAGEMENT(9102),
        COMPENSATION_PLANNING(9103),
        UNDERWRITING(9104),
        PRODUCT_MANAGEMENT(9105),
        SALES(9106),
        CUSTOMER_INFORMATION_MANAGEMENT(9107),
        HUMAN_RESOURCE(9108),
        MANAGEMENT_PLANNING(9109),
        CUSTOMER_SUPPORT(91010),
        COMPENSATION(91011),
        ADMINISTRATIVE(91012),
        FINANCIAL_ACCOUNTANT(91013),
        PARTNERCOMPANY(-1);
        private final int departmentId;
        private UserType(int departmentId) {
            this.departmentId = departmentId;
        }
    }

    public LoginResponse loginCustomer(int id) {
        Customer customer = customerService.getCustomerById(id);
        if (customer == null) return null;
        return new LoginResponse(customer.getName(), UserType.CUSTOMER.name());
    }

    public LoginResponse loginEmployee(int id) {
        try {
            Employee employee = humanResourceService.getEmployee(id);
            for (UserType userType : UserType.values())
                if (userType.getDepartmentId() == employee.getDepartmentID())
                    return new LoginResponse(employee.getName(), userType.name());
            return null;
        } catch (NotExistException e) {
            return null;
        }
    }

    public LoginResponse loginPartnerCompany(int id) {
        try {
            PartnerCompany partnerCompany = partnerCompanyService.getPartnerCompanyById(id);
            return new LoginResponse(partnerCompany.getName(), UserType.PARTNERCOMPANY.name());
        } catch (NotExistException e) {
            return null;
        }
    }
}
