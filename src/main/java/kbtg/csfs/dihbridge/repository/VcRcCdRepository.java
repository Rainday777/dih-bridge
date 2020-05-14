package kbtg.csfs.dihbridge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import kbtg.csfs.dihbridge.entity.VcRcCd;

public interface VcRcCdRepository extends JpaRepository<VcRcCd, Long> {

	List<VcRcCd> findByMaxLastUpdate();
	List<VcRcCd> findByCurrentdate();

}