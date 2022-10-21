# read_open_api
검색엔진을 만들기 위한 수천만건의 데이터를 어떻게 하면 효율적으로 DB에 넣을수 있는지 그 방법을 
모색합니다.😀
<div align=center><h1>📚 STACKS</h1></div>

<div align=center> 
 <img src="https://img.shields.io/badge/java-007396?style=for-the-badge&logo=java&logoColor=white">
 <img src="https://img.shields.io/badge/html5-E34F26?style=for-the-badge&logo=html5&logoColor=white">
 <img src="https://img.shields.io/badge/mysql-4479A1?style=for-the-badge&logo=mysql&logoColor=white">
 <img src="https://img.shields.io/badge/springboot-6DB33F?style=for-the-badge&logo=springboot&logoColor=white">
</div>
<br>
<br>

# 💾 Data
```
도서관 정보나루는 전국의 도서관 데이터를(약 2억여건의 데이터) 오픈 API, csv file로 제공하고 있습니다.
저희들은 데이터를 수집할 지역을 서울로 한정하여 서울에 위치해있는 도서관 325개관의 책 25,399,865권의 데이터를 수집하였습니다.
```
[도서관 정보나루 홈페이지](https://www.data4library.kr/)
<br>

## 💿Data example
|번호|도서명|저자|출판사|발행년도|ISBN|권|주제분류번호|도서관이름|full-text|
|------|--------|-------|---------|-----|----|-----------|------|--------|------------------|
|1|선을 넘다|안철수|에스제이더블유인터내셔널|2022|9791161505299||340.4|2.28도서관|title, author(책들의 전체정보)|
|2|달콩이네 떡집|김리리|비룡소|2021|9788949162126||808.9|2.28도서관|title, author(책들의 전체정보)|


<br>
<br>


## 😁Conventions
CSV는 데이터의 필드들을 ,로 구분을 합니다. 하지만 도서관 정보나루에서 제공하는 csv파일을 ','로 parsing 할 경우에 특정 문자의 경우에는 문자열의
parsing이 제대로 이루어지지 않아, 팀원들은 local의 목록 구분 기호 설정을 기존의 ',' 에서 '|'로 변경하도록 하여서 정상적으로 도서관 정보나루에서 제공하는
파일들을 처리할 수 있도록 합시다.

<br>

## 😡TroubleShooting
<details>
    <summary>
        <b>오픈API를 사용하여 데이터를 가져오는데 시간이 많이 소요된다</b>
    </summary>
<br>
  &nbsp;&nbsp;&nbsp;&nbsp; <b>원인:</b> 오픈 API를 사용하여 데이터를 가져와 저장할려고 했지만 1000건의 데이터당 10초 60,000건의 데이터에는 1시간이 소유되었다. 
  <br>
  &nbsp;&nbsp;&nbsp;&nbsp; <b>해결방안:</b> 오픈 API 대신 도서관 325개관의 csv파일을 전부 다운받아 DB에 저장하였다.
</details>
<details>
    <summary>
        <b>csv파일을 받을때 데이터 형식(한글)이 깨진다.</b>
    </summary>
<br>
  &nbsp;&nbsp;&nbsp;&nbsp; <b>원인:</b> 엑셀에서 csv로 저장시에 기본 설정 인코딩이 유니코드로만 저장된다. 
  <br>
  &nbsp;&nbsp;&nbsp;&nbsp; <b>해결방안:</b> csv파일을 엑셀로 대신에 메모장으로 열어주고 다른 이름으로 저장을 이용하여 파일 인코딩 형식을 UTF-8로 변경하고 저장하였다
</details>
