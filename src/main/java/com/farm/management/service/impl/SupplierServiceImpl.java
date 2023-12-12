package com.farm.management.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.farm.management.model.Supplier;
import com.farm.management.repository.SupplierRepository;
import com.farm.management.service.SupplierService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class SupplierServiceImpl implements SupplierService {

	private SupplierRepository supplierRepository;

    @Override
    public Supplier createSupplier(Supplier supplierId) {
        return supplierRepository.save(supplierId);
    }

    @Override
    public Supplier getSupplierById(Long supplierId) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(supplierId);
        return optionalSupplier.get();
    }

    @Override
    public Supplier updateSupplier(Supplier supplier) {
    	Supplier existingSupplier = supplierRepository.findById(supplier.getId()).get();
    	existingSupplier.setName(supplier.getName());
    	existingSupplier.setCnpj(supplier.getCnpj());
    	existingSupplier.setCity(supplier.getCity());
    	existingSupplier.setState(supplier.getState());
    	existingSupplier.setPhone(supplier.getPhone());
    	existingSupplier.setEmail(supplier.getEmail());
    	existingSupplier.setStreet(supplier.getStreet());
        Supplier updatedSupplier = supplierRepository.save(existingSupplier);
        return updatedSupplier;
    }

    @Override
    public void deleteSupplier(Long supplierId) {
    	supplierRepository.deleteById(supplierId);
    }

    @Override
    public List<Supplier> findByfarmId(Long farmId){
        return supplierRepository.getSupplierByFarmId(farmId);
    }

}
