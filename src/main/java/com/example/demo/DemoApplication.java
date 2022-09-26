package com.example.demo;


import com.example.demo.domain.library;
import com.example.demo.repository.libraryRepository;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.io.FileInputStream;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@EnableJpaAuditing
@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);

    }

    @Bean
    public CommandLineRunner demo(libraryRepository libraryRepository) {

        return (args) -> {
            long beforeTime = System.currentTimeMillis(); //코드 실행 전에 시간 받아오기
         /*   int total=-1,size=500,page=1;
            Long resultInt=0l;
            do {
                String result = "";
                URL url = new URL("http://data4library.kr/api/itemSrch?authKey=d39c7f3d9d7c547994399f4d90e13c5529b752ba9f9345f4cf8a884c2e7e63fa&libCode=127058&type=ALL&pageNo="+page+"&pageSize="+size+"&format=json");
                BufferedReader bf;
                bf = new BufferedReader(new InputStreamReader(url.openStream(), StandardCharsets.UTF_8));
                result = bf.readLine();

                JSONParser jsonParser = new JSONParser();
                JSONObject jsonObject = (JSONObject) jsonParser.parse(result);


                JSONObject response = (JSONObject) jsonObject.get("response");
                resultInt=(Long) response.get("numFound");
                JSONArray infoArr = (JSONArray) response.get("docs");
                List<library> libraries = new ArrayList<>();
                for (int i = 0; i < infoArr.size(); i++) {
                    JSONObject tmp = (JSONObject) infoArr.get(i);
                    JSONObject tmp2 = (JSONObject) tmp.get("doc");
                    libraries.add(new library((String) tmp2.get("bookname"), (String) response.get("libNm")));
                }
                libraryRepository.saveAll(libraries);
                page++;
            }while(resultInt>total);


//csv 파일로
            try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("C:\\Users\\user\\Downloads\\도서관"))) {
                for (Path file : stream) {
                    List<List<String>> csvList = new ArrayList<>();
                    List<library> libraries = new ArrayList<>();

                    String[] tmp = file.getFileName().toString().split("\\.");
                    BufferedReader br = null;
                    String line = "";

                    FileInputStream input = new FileInputStream("C:\\Users\\user\\Downloads\\도서관\\" + file.getFileName());
                    InputStreamReader reader = new InputStreamReader(input, "UTF-8");
                    try {
                        br = new BufferedReader(reader);

                        while ((line = br.readLine()) != null) { // readLine()은 파일에서 개행된 한 줄의 데이터를 읽어온다.
                            List<String> aLine;
                            String[] lineArr = line.split("\\|"); // 파일의 한 줄을 |로 나누어 배열에 저장 후 리스트로 변환한다.
                            if (lineArr.length == 0) continue;

                            //한줄 밀리는 현상에 대해서 픽스하기위한 코드
                            if (lineArr.length != 13) {
                                StringBuilder sb = new StringBuilder();
                                sb.append(line);
                                line = br.readLine();
                                sb.append(line);
                                lineArr = sb.toString().split("\\|");
                            }

                            aLine = Arrays.asList(lineArr);

                            libraries.add(new library(aLine.get(1), tmp[0], aLine.get(3), aLine.get(4), aLine.get(2), aLine.get(9), aLine.get(5), aLine.get(8)));
                            csvList.add(aLine);

                        }
                        input.close();
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } finally {
                        try {
                            if (br != null) {
                                br.close(); // 사용 후 BufferedReader를 닫아준다.

                            }
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    libraryRepository.saveAll(libraries);

                }
            } catch (IOException | DirectoryIteratorException ex) {
                System.err.println(ex);
            }
*/

            try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get("C:\\Users\\user\\Downloads\\도서관 엑셀"))) {
                for (Path file : stream) {
                    List<List<String>> csvList = new ArrayList<>();
                    List<library> libraries = new ArrayList<>();
                    String libraryName = null;

                    String[] tmp2 = file.getFileName().toString().split(" ");

                    String line = "";

                    FileInputStream fis = new FileInputStream("C:\\Users\\user\\Downloads\\도서관 엑셀\\" +file.getFileName());

                    XSSFWorkbook workbook = new XSSFWorkbook(fis);
                    if (tmp2.length == 1) {
                        libraryName = tmp2[0].split("\\.")[0];
                    } else {
                        StringBuilder sb = new StringBuilder();

                        for (int i = 0; i < tmp2.length - 4; i++) {

                            sb.append(tmp2[i]);

                        }
                        libraryName = sb.toString();
                    }


// 시트의 수
                    int sheets = workbook.getNumberOfSheets();
                    for (int i = 0; i < sheets; i++) {
                        Sheet sheet = workbook.getSheetAt(i);


	/* 1. row 얻기 : getPhysicalNumberOfRows();
	int rows = sheet.getPhysicalNumberOfRows();
	for(int r = 0; r < rows; r++){
		..
	}
	*/

                        // 2. row 얻기 : iterator();
                        Iterator<Row> rowIterator = sheet.iterator();
                        rowIterator.next();
                        while (rowIterator.hasNext()) {
                            Row row = rowIterator.next();


                            List<String> aLine = new ArrayList<>();
                            // 2. cell 얻기 : cellIterator();
                            Iterator<Cell> cellIterator = row.cellIterator();
                            while (cellIterator.hasNext()) {
                                Cell cell = cellIterator.next();
                                switch (cell.getCellType()) {
                                    case BOOLEAN:

                                        aLine.add(String.valueOf(cell.getBooleanCellValue()));
                                        break;
                                    case NUMERIC:
                                        aLine.add(String.valueOf(cell.getNumericCellValue()));
                                        break;
                                    case STRING:
                                        aLine.add(cell.getStringCellValue());
                                        break;
                                    case FORMULA:

                                        break;
                                }// switch
                            }// while
                            if (aLine.size() == 0) continue;

                            libraries.add(new library(aLine.get(1), libraryName, aLine.get(3), aLine.get(4), aLine.get(2), aLine.get(9), aLine.get(5), aLine.get(8)));
                        }// while
                        libraryRepository.saveAll(libraries);
                    }// for
                }
            }
            long afterTime = System.currentTimeMillis(); // 코드 실행 후에 시간 받아오기
            long secDiffTime = (afterTime - beforeTime) / 1000; //두 시간에 차 계산
            System.out.println("시간차이(m) : " + secDiffTime);

        };

    }


}


