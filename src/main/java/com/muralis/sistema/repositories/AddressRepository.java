package com.muralis.sistema.repositories;

import com.muralis.sistema.models.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
    List<Address> findAll();

    @Query("""
        SELECT a FROM Address a WHERE 
            a.state = :state AND 
            a.zipCode = :zipCode AND 
            a.city = :city AND 
            a.district = :district AND 
            a.street = :street AND 
            a.number = :number AND 
            ((:complement IS NULL AND a.complement IS NULL) OR a.complement = :complement)
    """)
    Optional<Address> findExactMatch(
            @Param("state") String state,
            @Param("zipCode") String zipCode,
            @Param("city") String city,
            @Param("district") String district,
            @Param("street") String street,
            @Param("number") String number,
            @Param("complement") String complement
    );


}
