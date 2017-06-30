package com.sap.ngp.sample.model;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.*;



public interface  TestScopeRepository extends CrudRepository<TestScope, Long>{
    Page<TestScope> findAll(Pageable pageable);      
}
