    package com.example.foodordermanager.customer;

    import com.example.foodordermanager.customer.dto.CustomerDTO;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import java.util.List;

    @RestController
    @RequestMapping("/api/customer")
    public class CustomerController {

        @Autowired
        private CustomerService customerService;

        @GetMapping("/{id}")
        public ResponseEntity<CustomerDTO> getCustomerById(@PathVariable Long id) {
            CustomerDTO customerDTO = customerService.getCustomerById(id);
            return ResponseEntity.ok(customerDTO);
        }

        @PostMapping("/create")
        public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerDTO customerDTO) {
            CustomerDTO createdCustomer = customerService.createCustomer(customerDTO);
            return ResponseEntity.ok(createdCustomer);
        }

        @PutMapping("/update/{id}")
        public ResponseEntity<CustomerDTO> updateCustomer(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
            customerDTO.setId(id);
            CustomerDTO updatedCustomer = customerService.updateCustomer(customerDTO);
            return ResponseEntity.ok(updatedCustomer);
        }

        @GetMapping("name/{name}")
        public ResponseEntity<List<CustomerDTO>> getCustomersByName(@PathVariable String name) {
            List<CustomerDTO> customers = customerService.getCustomersByName(name);
            return ResponseEntity.ok(customers);
        }

    }
