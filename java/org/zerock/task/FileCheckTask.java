package org.zerock.task;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.zerock.domain.BoardAttachVO;
import org.zerock.mapper.BoardAttachMapper;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@Log4j
@Component
public class FileCheckTask {

	@Setter(onMethod_ = @Autowired)
	private BoardAttachMapper attachMapper;
	
	private String getFolderYesterDay() {	//어제 날짜 폴더 문자열 가져오기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.DATE, -1);
		String str = sdf.format(cal.getTime());
		
		return str.replace("-", File.separator);
	}

//	@Scheduled(cron = "0 * * * * * ")	//매 분마다 실행
	@Scheduled(cron = "0 50 16 * * * ")	//매 새벽 2 시마다 실행
	public void checkFiles() throws Exception {
		Date now = new Date();
		log.warn("Running....... : " + now.toLocaleString());
		log.warn("==========================");
		
		List<BoardAttachVO> fileList = attachMapper.getOldFiles();
		
		List<Path> fileListPaths 
			= fileList.stream().map(vo -> Paths.get("c:\\upload", 
													vo.getUploadPath(),
													vo.getUuid(),
													vo.getFileName()))
					  .collect(Collectors.toList());
		
		fileList.stream()
				.filter(vo -> vo.isFileType() == true)
				.map(vo -> Paths.get("c:\\upload", 
													vo.getUploadPath(),
													vo.getUuid(),
													vo.getFileName()))
				.forEach(p -> fileListPaths.add(p));
		
		log.warn("======================================");
		
		fileListPaths.forEach(p -> log.warn(p));
		File targetDir = Paths.get("c:\\upload", getFolderYesterDay())
							  .toFile();
		
		File[] removeFiles = targetDir.listFiles(
			file -> fileListPaths.contains(file.toPath()) == false );
		
		log.warn("-------------------------");
		for(File file : removeFiles) {
			log.warn(file.getAbsolutePath());
			file.delete();
		}
		
	}
	
}









