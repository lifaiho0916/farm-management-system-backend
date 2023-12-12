package com.farm.management.service;

import java.util.List;

import com.farm.management.model.Supplier;

public interface SupplierService {
	
	Supplier createSupplier(Supplier supplier);

	Supplier getSupplierById(Long supplierId);

    List<Supplier> findByfarmId(Long farmId);

    Supplier updateSupplier(Supplier supplier);

    void deleteSupplier(Long supplierId);
}
