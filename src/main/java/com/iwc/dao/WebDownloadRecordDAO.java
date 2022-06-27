package com.iwc.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.iwc.bean.vo.WebDownloadRecordVO;

@Repository
public interface WebDownloadRecordDAO extends JpaRepository<WebDownloadRecordVO, Integer> {

	@Query(value = "SELECT * FROM web_download_record order by create_time desc LIMIT 10", nativeQuery = true)
	List<WebDownloadRecordVO> queryLatestTenWeb();

}